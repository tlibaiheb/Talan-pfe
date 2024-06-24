import { Component, Input, OnInit } from '@angular/core';
import { OrganisationPartyService } from '../../services/pm.service';
import { Router } from '@angular/router';
import { CacheDataService } from '../../services/CacheDataService';
import { Observable } from 'rxjs';
import { Location } from '../../models/location.model';
import { Store, select } from '@ngrx/store';
import { loadLocations } from '../../state/actions/location.actions';
import { selectLocations } from '../../state/reducers/location.reducer';
import Swal from 'sweetalert2';
import { Civilite } from '../../models/civilite.model';
import { Country } from '../../models/country.model';
import { selectCountries } from '../../state/reducers/country.reducer';
import { selectCivilites } from '../../state/reducers/civilite.reducer';
import { loadCivilites } from '../../state/actions/civilitie.action';
import { CiviliteService, CountryService } from '../../services/nomenclature.service';
import { loadCountries } from '../../state/actions/country.action';
import { PersonPartyService } from '../../services/pp.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrl: './table.component.css'
})
export class TableComponent implements OnInit  {
  showSearchPopup = false; // Peut-être initialisé à true ou false selon vos besoins
  showPopup = false;
  entrepriseName: string = '';
  searchPlaceholder: string = "Saisir le nom de l'entreprise";
  showCreateButton: boolean = false;


  civilites$: Observable<Civilite[]> ;
  countries$:Observable<Country[]>
  persons: any[] = [];
  birthName:string=""

  locationName: string = '';

  organisationParties: any[] = [];
  searchValue: string = '';
  //showPopup: boolean = false;
  siren:  string = '';
  sirenExists!: boolean ;
  locations$:Observable<Location[]>;
  disabled: boolean =true;


  @Input() cols:any;
  @Input() type:any;

  routeItems = [
    { label: 'Personne morale', routerLink: '/home/pm/table', icon: 'pi pi-building' },
    { label: 'Personne physique', routerLink: '/home/pp/table' , icon: 'pi pi-user' },

];

    civilitesMap: { [key: string]: string } = {};
    countriesMap: { [key: string]: string } = {};
    searchQueryId:string='';
    searchQuery: string = '';
    searchQueryCp:string=''
    searchQueryBirthName: string = '';
    searchQueryMainFirstName: string = '';
    selectedOption: string = "NomPrénom";
    showSearch: boolean = false;
    cpNumber: string = '';

  constructor(private messageService:MessageService  ,private store: Store,private organisationPartyService: OrganisationPartyService, private router: Router, private cacheDataService: CacheDataService,private PersonPartyService: PersonPartyService ,private civiliteService: CiviliteService,private countryService:CountryService) {
    this.locations$=this.store.pipe(select(selectLocations));
    this.countries$=this.store.pipe(select(selectCountries));
    this.civilites$ = this.store.pipe(select(selectCivilites));
    this.sirenExists = false;        

  }

  ngOnInit(): void {

      this.cacheDataService.checkAndLoadData( this.locations$, loadLocations);
      this.cacheDataService.checkAndLoadData(this.civilites$, loadCivilites);
      this.loadCivilites();
      this.cacheDataService.checkAndLoadData( this.countries$, loadCountries);
      this.loadCountries();

      this.getAllOrganisationParties();
      this.getAllPP();
      this.showSearchPopup=false;

  }

  searchSiren() {
    this.organisationPartyService.searchByNomComplet(this.entrepriseName).subscribe(
      (siren: string) => {
        // Mettez à jour le placeholder et l'input avec le numéro de SIREN retourné
        this.searchPlaceholder = "Voici le numéro de SIREN: " ;
        this.entrepriseName = siren; // Si vous voulez afficher le SIREN dans l'input
        this.showCreateButton = true; // Afficher le bouton Créer après avoir trouvé le SIREN
      },
      (error: any) => {
        console.error('Une erreur s\'est produite lors de la recherche du numéro de SIREN : ', error);
        // Gérer les erreurs ici
      }
    );
  }
  //PP functions

  loadCivilites() {
    this.civiliteService.getCivilites().subscribe(
      (civilites: Civilite[]) => {
        civilites.forEach(civilite => {
          this.civilitesMap[civilite.gId] = civilite.label; // Mapper les identifiants avec les libellés
        });
      },
      (error) => {
        console.error('Erreur lors du chargement des civilités:', error);
      }
    );
  }
  loadCountries() {
    this.countryService.getCountries().subscribe(
      (countries: Country[]) => {
        countries.forEach(country => {
          this.countriesMap[country.countryGId] = country.countryLabel; // Mapper les identifiants avec les libellés
        });
      },
      (error: any) => {
        console.error('Erreur lors du chargement des pays:', error);
      }
    );
  }
  getAllPP() {
    this.PersonPartyService.getAllPersonParties().subscribe(
      data => {
        this.persons = data;
        console.log(this.persons)
      },
      error => {
        console.log('Une erreur est survenue lors de la récupération des parties d\'organisation : ', error);
      }
    );
  }

  searchByCP() {
    this.PersonPartyService.getPersonPartiesByCpNumber(this.searchQuery).subscribe(
     (data) => {
       this.persons = data;

     },
     (error) => {
       console.error('Erreur lors de la récupération des données par numéro de code postal:', error);
     }
   );
 }


 searchByIdTiers() {
   this.PersonPartyService.getPersonPartiesByIdTiers(this.searchQueryId).subscribe(
     (data)=>{
       this.persons=data;
      },
     (error) => {
       console.error('Erreur lors de la récupération des données par numéro de code postal:', error);
     }
   )
   }


   searchByName() {
    this.PersonPartyService.getPersonPartiesByBirthName(this.searchQueryBirthName).subscribe(
        (data) => {
            this.persons = data;
        },
        (error) => {
            console.error('Erreur lors de la récupération des données par Nom:', error);
        }
    );
}

searchByMainFirstName() {
    this.PersonPartyService.getPersonPartiesByMainFirstName(this.searchQueryMainFirstName).subscribe(
        (data) => {
            this.persons = data;
        },
        (error) => {
            console.error('Erreur lors de la récupération des données par Prénom:', error);
        }
    );
}
search() {
   if (this.searchQueryBirthName) {
      this.searchByName();
  } else if (this.searchQueryMainFirstName) {
      this.searchByMainFirstName();
  } else{
    console.log("error !")
  }
}

  goToPpDetails(id: number): void {
    this.router.navigate(['/home/pp/',id]);
  }




goTocreatePP(): void {
this.router.navigate(['/home/pp']);
}


  //PM functions
 // showSearchPopup: any;

  getAllOrganisationParties() {
    this.organisationPartyService.getAllOrganisationParties().subscribe(
      data => {
        this.organisationParties = data;
        console.log(this.organisationParties)
      },
      error => {
        console.log('Une erreur est survenue lors de la récupération des parties d\'organisation : ', error);
      }
    );
  }



  filtrerListe() {
    if(this.selectedOption==='NomPrénom'){
      Swal.fire({
        text: "Veuillez sélectionner une option de recherche",
        icon: "error"
      });
    }else{
      this.organisationPartyService.filtrerListe(this.selectedOption, this.searchValue)
      .subscribe(
        (result) => {
          this.organisationParties = result;
        },
        (error) => {
          console.error('Erreur lors de la récupération des données filtrées :', error);
        }
      );
    }

  }





  goTocreate(): void {
    Swal.fire({
      title: "<strong><p style='font-size:18px; color:black'>Voulez-vous créer une personne morale ?</p></strong>",
      html: `
      <div>
      <div>
        <input type="radio" id="france" name="location" value="france">
        <label style='font-size:15px; color:black'for="france">Une personne morale française</label>
      </div>
      <div>
        <input type="radio" id="foreign" name="location" value="etrangère">
        <label style='font-size:15px; color:black' for="foreign">Une personne morale étrangère</label>
      </div>
    </div>
      `,
      showCloseButton: true,
      showCancelButton: true,
      focusConfirm: false,
      confirmButtonColor:' #FFCD00',
      confirmButtonText: `
        Créer
      `,
      confirmButtonAriaLabel: "Créer",
      cancelButtonText: `
        Annuler
      `,
      cancelButtonAriaLabel: "Annuler",




    }).then((result) => {
      if (result.isConfirmed) {
        const selectedLocationInput = document.querySelector('input[name="location"]:checked') as HTMLInputElement;
        if (selectedLocationInput !== null) {
          const selectedLocation = selectedLocationInput.value;
          if (selectedLocation === 'france') {
            // window.location.href = 'http://localhost:4200/home/pm';
            this.showPopup=true
          }
        } else {
          Swal.fire("Veuillez sélectionner une option de localisation.");
        }
      }
    });



    document.addEventListener('click', (event) => {
      const cancelBtn = document.getElementById('cancelBtn');

      if (event.target === cancelBtn) {
        this.showPopup=false
        this.sirenExists = false;
        this.siren=''
      }

    });

  }
  createfn(){
    const sirenInput = document.getElementById('sirenInput') as HTMLInputElement;
      const sirenValue = sirenInput.value;
      if(sirenValue.length!=9){
        Swal.fire({
          title: "Erreur !",
          text: "Siren doit contenir 9 chiffre",
          icon: "error"
        });

      }else{
        this.organisationPartyService.checkSirenExistence(sirenValue).subscribe(


          (result) => {
            console.log("result",result)
            if (!result) {
              this.sirenExists = false;
              window.location.href = `http://98.66.200.87/home/pm?siren=${sirenValue}`;
            } else {
              console.log("Siren exists:", result);
              this.sirenExists = true;
            }
          },
          (error) => {
            console.error("Error checking SIREN existence:", error);
            Swal.fire("Erreur", "Une erreur s'est produite lors de la vérification du SIREN.", "error");
          }
        );
      }


  }
  createfn1() {
    this.organisationPartyService.checkSirenExistence(this.entrepriseName).subscribe(

      (result) => {
        console.log("result",result)
        if (!result) {
          this.sirenExists = false;
          window.location.href = `http://98.66.200.87/home/pm?siren=${this.entrepriseName}`;
        } else {
          console.log("Siren exists:", result);
          this.sirenExists = true;
          this.showToast('error', 'Erreur', 'Vous avez déjà utilisé ce numéro de SIREN.');
        }
      },
      (error) => {
        console.error("Error checking SIREN existence:", error);
        Swal.fire("Erreur", "Une erreur s'est produite lors de la vérification du SIREN.", "error");
      }
    );

  }
  private showToast(severity: string, summary: string, detail: string) {
    this.messageService.add({ severity: severity, summary: summary, detail: detail });
  }
  goToPmDetails(id: number): void {
    this.router.navigate(['/home/pm/',id]);
  }


  getfrville(id: string): string {
    this.locations$.subscribe((locations: Location[]) => {
      const location =  locations.find(c => c.locationGId === id);
      if (location) {
        this.locationName = location.postCode + "-" + location.locationLabel;
      }
    });
    return this.locationName;
}


getOrganisationByIdAndNavigate(siren: string): void {
  
  this.organisationPartyService.getOrganisationPartyBySiren(siren).subscribe(
    (data) => {
      this.goToPmDetails(data.id)
    },
    (error) => {
      console.error('Erreur lors de la récupération de l\'organisation:', error);
    }
  );
}


  resetSearch() {
    this.siren=''
    this.searchPlaceholder = "Saisir le nom de l'entreprise"; // Réinitialiser le placeholder
    this.entrepriseName = ''; // Réinitialiser le champ de saisie
    this.showCreateButton = false; // Masquer le bouton Créer
    this.sirenExists = false;

  }
  protected readonly onreset = onreset;


}
