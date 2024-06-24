import { Component, OnInit, ViewChild } from '@angular/core';
import { Observable, } from 'rxjs';
import { Store, select } from '@ngrx/store';
import { Location } from '../../shared/models/location.model';
import { Country } from '../../shared/models/country.model';
import { selectLocations } from '../../shared/state/reducers/location.reducer';
import { loadCountries } from '../../shared/state/actions/country.action';
import { selectCountries} from '../../shared/state/reducers/country.reducer';
import { loadLocations } from '../../shared/state/actions/location.actions';
import { loadCivilites } from '../../shared/state/actions/civilitie.action';
import { selectCivilites} from '../../shared/state/reducers/civilite.reducer';
import { Civilite } from '../../shared/models/civilite.model';
import { CacheDataService } from '../../shared/services/CacheDataService';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PersonPartyService } from '../../shared/services/pp.service';
import { AddresseComponent } from '../../shared/components/addresse/addresse.component';
import Swal from 'sweetalert2';
import { ActivatedRoute, Router } from '@angular/router';
import { differenceInYears } from 'date-fns';
@Component({
  selector: 'app-pp',
  templateUrl: './pp.component.html',
  styleUrl: './pp.component.css'
})
export class PpComponent implements OnInit {

  type = 'PP';
  date: Date;
  inFrance = false;
  isButtonEnabled: boolean = true;
  formulaire: FormGroup = new FormGroup({});
  readonlyMode: boolean = false;
  isSaved: boolean = false;
  name: string = '--- ---';
  address: string = '...';
  editingConcatenatedName: string = ''; // Variable pour stocker le nom concaténé en cours d'édition

  originalFormData: any; // Ajout d'une variable pour stocker les données originales avant la modification
  originalAddressFormData: any; // Propriété pour stocker les données originales du formulaire d'adresse


  /*listes: Location[] = [];
   bufferSize = 20;
   loading = false;*/
  listes: { label: string }[] = [];
  bufferSize = 20;
  loading = false;
  //modeEnum = modeEnum;

//ville france locations
  locations$:Observable<Location[]>;
  //civilite
  civilites$: Observable<Civilite[]> ;
  //countries
  countries$:Observable<Country[]>
  //id
  id!:number;

  idTiers!: string;
  isEditMode: boolean = false;

  isClient: boolean = false;
  countries: Country[] = [];

  locations:Location[] =[];

  constructor(private router: Router ,private route: ActivatedRoute,private store: Store , private cacheDataService: CacheDataService, private personPartyService:PersonPartyService,private formBuilder: FormBuilder) {
    this.date = new Date();
    //ville:france location
    this.locations$=this.store.pipe(select(selectLocations));
    //civilite
    this.civilites$ = this.store.pipe(select(selectCivilites));
    //countries
    this.countries$=this.store.pipe(select(selectCountries));
  }
  ngOnInit() {
    this.formulaire = this.formBuilder.group({
      namePrefixGId: ['', Validators.required],
      birthName: ['', [Validators.required, Validators.maxLength(150)]],
      mainFirstName: ['', [Validators.required, Validators.maxLength(150)]],
      firstNames: [null, [Validators.maxLength(150)]],
      birthDate: ['', [Validators.required,this.dateOfBirthRangeValidator]],
      countryOfBirthGId: ['', Validators.required],
      useName:[''],
      cpNumber:[''],
      frPlaceOfBirthGId: ['',Validators.required],
      outOfFrBirthPostCode: [null, [Validators.pattern('^[0-9]{3,}$'),Validators.required]],
      outOfFrPlaceOfBirth: ['',Validators.required]

    });








    this.cacheDataService.checkAndLoadData(this.locations$, loadLocations);
    this.locations$.subscribe((data: Location[]) => {
      this.listes = data.slice(0, this.bufferSize).map(location => ({
        label: `${location.postCode}-${location.locationLabel}`,
        value: location.locationGId
      }));
    });

    this.cacheDataService.checkAndLoadData(this.civilites$, loadCivilites);
    this.cacheDataService.checkAndLoadData(this.countries$, loadCountries);

    this.route.params.subscribe(params => {
      if (params['id']) {
        this.id = +params['id'];
        this.readonlyMode=true;
        this.loadPersonPartyById(this.id);
        this.isSaved = true;
        this.addressComponent.isSaved = true;



      } else {
        console.log("L'ID n'est pas présent dans les paramètres de la route.");
      }
    });





    this.countries$.subscribe((data: Country[]) => {
      this.countries = data;
    });

    this.locations$.subscribe((data: Location[]) => {
      this.locations = data;
    });


  }
  fetchMore() {
    const len = this.listes.length;
    this.locations$.subscribe((data: Location[]) => {
      const more = data.filter((x: Location) => x.locationLabel).slice(len, this.bufferSize + len);
      this.loading = true;
      setTimeout(() => {
        this.loading = false;
        this.listes = this.listes.concat(more.map(location => ({
          label: `${location.postCode}-${location.locationLabel}`, // Concaténer code postal et ville
          value: location.locationGId
        })));
      }, 200);
    });
  }
  onCheckboxChange(isChecked: boolean) {
    this.inFrance = isChecked;
  }
// Déclaration d'une référence au composant AddresseComponent
  @ViewChild(AddresseComponent)
  private addressComponent!: AddresseComponent;
// Méthode pour récupérer le formulaire adress de AddresseComponent
  getAddressForm(): FormGroup | undefined {
    return this.addressComponent ? this.addressComponent.getAdressForm() : undefined;
  }

  onSubmit() {
    if (this.formulaire.valid) {
      //const formData = this.formulaire.value;
      const addressForm = this.getAddressForm();

      const formData = {
        ...this.formulaire.value,
        physicalContactPoint: addressForm!.value
      };
      console.log(formData + "dfsfdsdfsdfsdf")
      if (this.isEditMode) {
        this.editingConcatenatedName = this.concatenateName();

        // Effectuer les actions nécessaires pour soumettre les modifications
        this.personPartyService.updatePersonParty(this.id, formData).subscribe(
          response => {
            Swal.fire({
              title: "Parfait !",
              text: "PP a a été modifié avec succès",
              icon: "success"
            });
            this.name = response.birthName + ' ' + response.mainFirstName;
            this.idTiers = response.idTiers;


// Déterminer l'adresse en fonction du pays sélectionné
            let address;
            if (formData.physicalContactPoint.inFrance) {
              // Si c'est en France, utiliser la méthode maplocationLabel avec frLocationGId
              address = this.maplocationLabel(formData.physicalContactPoint.frLocationGId);
            } else {
              // Sinon, utiliser la méthode mapCountryLabel avec countryGId
              const countryLabel = this.mapCountryLabel(formData.physicalContactPoint.countryGId);
              address = formData.physicalContactPoint.outOfFrTown + ' - ' + countryLabel;
            }
            this.address = address;



            //const countryLabel = this.mapCountryLabel(addressForm.value.countryGId);
            //this.address = response.outOfFrTown + ' - ' + countryLabel;
            // Gérez la réponse de l'API en cas de succès
            console.log('Person Party updated successfully:', response);
            // Réinitialisez le formulaire après la soumission réussie
            this.formulaire.reset();
            this.loadPersonPartyById(this.id);
            this.readonlyMode = true; // Désactiver le mode édition après la soumission réussie
          },
          error => {
            // Gérez les erreurs en cas d'échec de la mise à jour de la partie de personne
            console.error('Error updating Person Party:', error);
          }
        );

      } else {
        // Effectuer les actions nécessaires pour créer une nouvelle partie de personne
        this.personPartyService.createPersonParties(formData).subscribe(
          response => {
            this.name = response.birthName + ' ' + response.mainFirstName;
            this.idTiers = response.idTiers;
            this.isClient = true; // Activer l'affichage du statut client

            // Gérez la réponse de l'API en cas de succès
            console.log('Person Party created successfully:', response.id);
            // Réinitialisez le formulaire après la soumission réussie
            this.formulaire.reset();
            this.id = response.id;
            console.log(this.id);
            //this.isSaved = true;
            // Récupérer le formulaire adress
            const addressForm = this.getAddressForm();
            if (addressForm) {
              console.log(addressForm.value);
              // Vérifier si le formulaire adress est valide
              if (addressForm.valid) {
                // Le formulaire adress est valide, vous pouvez l'utiliser ici
                console.log('Address form is valid:', addressForm.value);
                this.personPartyService.createPhysicalContactPoint(this.id, addressForm.value).subscribe(
                  response => {



                    if (addressForm.value.inFrance) {

                      this.address = this.maplocationLabel(addressForm.value.frLocationGId);
                    } else {
                      const countryLabel = this.mapCountryLabel(addressForm.value.countryGId);
                      this.address = response.outOfFrTown + ' - ' + countryLabel;
                    }


                    /*const countryLabel = this.mapCountryLabel(addressForm.value.countryGId);
                    this.address = response.outOfFrTown + ' - ' + countryLabel;*/





                    // Gérez la réponse de l'API en cas de succès
                    console.log('Address created successfully:', addressForm.value);

                    Swal.fire({
                      title: "Parfait !",
                      text: "PP a a été enregistré avec succès",
                      icon: "success"
                    });



                    // Réinitialisez le formulaire après la soumission réussie
                    addressForm.reset();
                    this.loadPersonPartyById(this.id);
                    this.readonlyMode = true;
                    console.log(this.readonlyMode);
                  },
                  error => {
                    // Gérez les erreurs en cas d'échec de la création de l'organisation party
                    console.error('Error creating person Party:', error);
                  }
                );
              } else {
                // Le formulaire adress est invalide
                console.log('Address form is invalid.');
              }
            } else {
              // Impossible de récupérer le formulaire adress
              console.log('Address form is not available.');
            }
          },
          error => {
            // Gérez les erreurs en cas d'échec de la création de la partie de personne
            console.error('Error creating Person Party:', error);
          }
        );
      }
    } else {
      // Si le formulaire est invalide, marquez tous les champs comme touchés pour afficher les messages d'erreur
      this.formulaire.markAllAsTouched();
    }

    this.isSaved = true;
    this.addressComponent.isSaved = true;
    // Mise à jour du statut de sauvegarde dans le composant Adresse
  }


  loadPersonPartyById(id: number): void {
    this.personPartyService.getPersonPartyById(id).subscribe(
      personParty => {
        // Mettre à jour les données dans le formulaire
        this.formulaire.patchValue({
          namePrefixGId: personParty.namePrefixGId,
          birthName: personParty.birthName,
          mainFirstName: personParty.mainFirstName,
          firstNames: personParty.firstNames,
          birthDate: personParty.birthDate,
          countryOfBirthGId: personParty.countryOfBirthGId,
          useName: personParty.useName,
          cpNumber: personParty.cpNumber,
          frPlaceOfBirthGId:personParty.frPlaceOfBirthGId,
          outOfFrBirthPostCode:personParty.outOfFrBirthPostCode,
          outOfFrPlaceOfBirth:personParty.outOfFrPlaceOfBirth
        });
        this.name = personParty.birthName + ' ' + personParty.mainFirstName;
        this.idTiers = personParty.idTiers;
        this.isClient = true; // Activer l'affichage du statut client
        this.onCheckboxChange (personParty.physicalContactPoint.inFrance);
        this.addressComponent.updateAddressForm(personParty.physicalContactPoint);

        let address;
        if (personParty.physicalContactPoint.inFrance) {
          // Si c'est en France, utiliser la méthode maplocationLabel avec frLocationGId
          address = this.maplocationLabel(personParty.physicalContactPoint.frLocationGId);
        } else {
          // Sinon, utiliser la méthode mapCountryLabel avec countryGId
          const countryLabel = this.mapCountryLabel(personParty.physicalContactPoint.countryGId);
          address = personParty.physicalContactPoint.outOfFrTown + ' - ' + countryLabel;
        }
        this.address = address;




      },
      error => {
        console.error('Error loading person Party:', error);
      }
    );
  }
// Dans votre composant TypeScript
  isFranceSelected(): boolean {
    const countryGId = this.formulaire.get('countryOfBirthGId')?.value;
    return countryGId === '81'; // '81' correspond à la France dans votre cas
  }
  concatenateName(): string {


    const birthName = this.formulaire.get('birthName')?.value;
    const mainFirstName = this.formulaire.get('mainFirstName')?.value;
    return `${birthName} ${mainFirstName}`;
  }


  generateRandomFiveDigitId(): string {
    const min = 10000;
    const max = 99999;
    return String(Math.floor(Math.random() * (max - min + 1)) + min);
  }



  mapCountryLabel(countryGId: any): string {
    const country = this.countries.find(c => c.countryGId === countryGId);
    return country ? country.countryLabel : '';
  }



  maplocationLabel(locationGId: any): string {
    const location = this.locations.find(c => c.locationGId === locationGId);
    return location ? `${location.postCode}-${location.locationLabel}` : '';
  }


// Logique pour activer/désactiver les champs en fonction de l'état de modification
  onModify() {
    this.editingConcatenatedName = this.concatenateName();

    this.originalFormData = { ...this.formulaire.value };


    this.originalFormData = { ...this.formulaire.value };
    const addressForm = this.getAddressForm();
    if (addressForm) {
      this.originalAddressFormData = { ...addressForm.value };
    }

    this.isSaved = false;
    this.readonlyMode = false; // Activer le mode édition
    this.isEditMode = true; // Activer le mode modification
  }



  onCancel() {
    if (this.isEditMode) {
      this.formulaire.patchValue(this.originalFormData);
      const addressForm = this.getAddressForm();
      if (addressForm) {
        addressForm.patchValue(this.originalAddressFormData);
      }
      this.isEditMode = false; // Désactiver le mode édition
      this.isSaved = true; // Marquer comme sauvegardé pour afficher uniquement le bouton "Modifier"

    } else {
      this.formulaire.reset();
      const addressForm = this.getAddressForm();
      addressForm?.reset();
      this.isSaved = false;
      this.isEditMode = false; // Désactiver le mode édition

    }
    // this.readonlyMode = true; // Activer le mode lecture seule

  }




  dateOfBirthRangeValidator(control: AbstractControl): { [key: string]: string } | null {
    if (!control.value) {
      return { 'required': 'La date de naissance est obligatoire' };
    }

    const selectedDate = new Date(control.value);
    const age = differenceInYears(new Date(), selectedDate);

    if (age < 18 || age > 120) {
      return { 'dateOfBirthRange': 'La date de naissance doit être comprise entre 18 et 120 ans' };
    }

    return null;
  }




  updateBirthPlaceValidators() {
    const countryOfBirthGId = this.formulaire.get('countryOfBirthGId')?.value;
    const frPlaceOfBirthGIdControl = this.formulaire.get('frPlaceOfBirthGId');
    const outOfFrPlaceOfBirthControl = this.formulaire.get('outOfFrPlaceOfBirth');
    const outOfFrBirthPostCodeControl = this.formulaire.get('outOfFrBirthPostCode');

    if (countryOfBirthGId === '81') { // Si le pays de naissance est la France
      frPlaceOfBirthGIdControl?.setValidators([Validators.required]);
      outOfFrPlaceOfBirthControl?.clearValidators();
      outOfFrBirthPostCodeControl?.clearValidators();
    } else {
      frPlaceOfBirthGIdControl?.clearValidators();
      outOfFrPlaceOfBirthControl?.setValidators([Validators.required]);
      outOfFrBirthPostCodeControl?.setValidators([Validators.required, Validators.pattern('^[0-9]{3,}$')]);
    }

    // Mettre à jour les validateurs pour chaque contrôle
    frPlaceOfBirthGIdControl?.updateValueAndValidity();
    outOfFrPlaceOfBirthControl?.updateValueAndValidity();
    outOfFrBirthPostCodeControl?.updateValueAndValidity();
  }

  onCountryOfBirthChange() {
    this.updateBirthPlaceValidators();
  }

}
