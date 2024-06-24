import { Component, Input, OnInit, ViewChild, output } from '@angular/core';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Naf } from '../../shared/models/naf.model';
import { loadNafs } from '../../shared/state/actions/naf.actions';
import { selectNafs, selectNafLoading, selectNafError } from '../../shared/state/reducers/naf.reducer';
import { Category } from '../../shared/models/category.model';
import { loadCategories } from '../../shared/state/actions/category.action';
import { selectCategories, selectCategoryLoading, selectCategoryError } from '../../shared/state/reducers/category.reducer';
import { Gicscode } from '../../shared/models/gicscode.model';
import { loadGicscodes } from '../../shared/state/actions/gicscode.actions';
import { selectGicscodes, selectGicscodeLoading, selectGicscodeError } from '../../shared/state/reducers/gicscode.reducer';
import { Missingsiren } from '../../shared/models/missingsiren.model';
import { selectMissingsirenError, selectMissingsirenLoading, selectMissingsirens } from '../../shared/state/reducers/missingsiren.reducer';
import { loadMissingsirens } from '../../shared/state/actions/missingsiren.actions';
import { TailleEntreprise } from '../../shared/models/tailleEntreprise.model';
import {selectTailleEntrepriseError,selectTailleEntrepriseLoading,selectTailleEntreprises } from '../../shared/state/reducers/taille-entreprise.reducer';
import { loadTailleEntreprises } from '../../shared/state/actions/tailleEntreprise.action';
import { CacheDataService } from '../../shared/services/CacheDataService';
import { AuthService } from '../../shared/services/user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { OrganisationPartyService } from '../../shared/services/pm.service';
import { FormControl } from '@angular/forms';
import { AddresseComponent } from '../../shared/components/addresse/addresse.component';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { modeEnum } from '../../shared/enums/enums';
import { selectLocations } from '../../shared/state/reducers/location.reducer';
import { Location } from '../../shared/models/location.model';
import { loadLocations } from '../../shared/state/actions/location.actions';

@Component({
  selector: 'app-pm',
  templateUrl: './pm.component.html',
  styleUrl: './pm.component.css'
})
export class PmComponent implements OnInit {
  formData: any;

  modificationAssiste:boolean=false;
  type = 'PM';
  inFrance= true;
  formulaire: FormGroup = new FormGroup({});


  readonlyMode:any;
  modeEnum = modeEnum;


  //categorie
  categories$: Observable<Category[]>;

  //nafs
  nafs$: Observable<Naf[]>;

  //gics
  gicscodes$: Observable<Gicscode[]>;

  //missing siren
  missingsirens$:Observable<Missingsiren[]>;

  //taille entreprise
  tailleEntreprises$:Observable<TailleEntreprise[]>;
  //france location
  locations$:Observable<Location[]>;
  locationName: any;
  SirenExist:boolean=false;
  modeAssite:boolean=false;
 //id
  id!:number;
  siren: any;

  idT!: any;
  legalName: any;
  legalCategoryGId: any;
  europeanOrganisationPartySizeGId: any;
  creationDate: any;
  registrationDate:any;
  naf:any;
raisonSociale1: any;
nafCodeGId1: any;
legalCategoryGId1: any;
europeanOrganisationPartySizeGId1: any;
creationDate1: any;
registrationDate1: any;

  constructor(private router: Router,private route: ActivatedRoute,private organisationPartyService: OrganisationPartyService,private store: Store , private cacheDataService: CacheDataService,private formBuilder: FormBuilder) {

    //categories
    this.categories$ = this.store.pipe(select(selectCategories));

    //nafs
    this.nafs$ = this.store.pipe(select(selectNafs));

    //gics
    this.gicscodes$ = this.store.pipe(select(selectGicscodes));

    //siren
    this.missingsirens$ = this.store.pipe(select(selectMissingsirens));

      //taille entreprise
    this.tailleEntreprises$=this.store.pipe(select(selectTailleEntreprises))

    this.locations$=this.store.pipe(select(selectLocations));




  }



  ngOnInit() {


    this.formulaire = this.formBuilder.group({
     
      legalName: ['', Validators.compose([Validators.required, Validators.maxLength(150)])], // Raison Sociale
      tradingName: [''], // Libellé commercial
      siren: [''],
      nafCodeGId: ['', Validators.required], // Secteur d'activité NAF
      legalCategoryGId: ['', Validators.required], // Catégorie juridique
      gicsCodeGId: [''], // Identifiant domaine activité GICS
      missingSIRENSIRETJustificationGId: [''], // Motif d'absence de SIREN
      shareCapital: ['', [Validators.required, Validators.pattern(/^-?\d+\.?\d*$/)]], // Montant capital social (en£)
      europeanOrganisationPartySizeGId: ['', Validators.required], // Taille d'entreprise
      creationDate: [''], // Date de création
      registrationDate: [''], // Date d'enregistrement,
      date_dernier_traitement:[''],
      cp:[''],//code postal
      organisationPartyIdentifications: this.formBuilder.array([
      this.formBuilder.group({
        organisationPartyIdentificationTypeGId:[1],
        organisationPartyIdentificationValue: [''] // LEI
      })
    ]) ,
    legalName1: [''], 
    legalCategoryGId1: [''], 
    europeanOrganisationPartySizeGId1: [''], 
    creationDate1: [''],  
    nafCodeGId1:[''],
    registrationDate1:['']
   
    });

    console.log(this.formulaire)


 



    this.cacheDataService.checkAndLoadData(this.categories$, loadCategories);
    this.cacheDataService.checkAndLoadData(this.nafs$, loadNafs);
    this.cacheDataService.checkAndLoadData(this.gicscodes$,loadGicscodes);
    this.cacheDataService.checkAndLoadData(this.missingsirens$,loadMissingsirens);
    this.cacheDataService.checkAndLoadData(this.tailleEntreprises$,loadTailleEntreprises);
    this.cacheDataService.checkAndLoadData( this.locations$, loadLocations);


    this.route.params.subscribe(params => {
      if (params['id']) {
        this.id = +params['id'];
        this.loadOrganisationPartyById(this.id);
        this.readonlyMode='consult';


      } else {
        this.readonlyMode="create";
        console.log("L'ID n'est pas présent dans les paramètres de la route.");
      }
    });


    // this.idT = this.id;

    this.siren = this.route.snapshot.queryParamMap.get('siren');
    console.log('SIREN récupéré depuis l\'URL:', this.siren);
    if(this.siren){
      this.formulaire.patchValue({siren:this.siren})
      this.SirenExist=true;
      this.modeAssite=true;
      this.fetchEtablissementData(this.siren);
      console.log("SirenExist",this.SirenExist)
    }

    }

    get organisationPartyIdentificationValueControl(): FormControl {
      const control = this.formulaire.get('organisationPartyIdentifications.0.organisationPartyIdentificationValue') as FormControl;
      return control ? control : new FormControl('');
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
       // this.formulaire.patchValue({ date_dernier_traitement: new Date() });
        // Récupérer le formulaire adress
        const addressForm = this.getAddressForm();
        // this.formulaire.patchValue({cp: this.getfrville(addressForm?.get('frLocationGId')?.value)});

        const formData = this.formulaire.value;
        // Appelez la fonction createOrganisationParty du service OrganisationPartyService avec les données du formulaire
        this.organisationPartyService.createOrganisationParty(formData).subscribe(
          response => {
            // Gérez la réponse de l'API en cas de succès
            console.log('Organisation Party created successfully:', response);
            this.id=response.id;
            console.log(this.id)

            this.idT = response.idTiers;



                if (addressForm) {
                    console.log(addressForm.value);
                    // Vérifier si le formulaire adress est valide
                    if (addressForm.valid) {
                        // Le formulaire adress est valide, vous pouvez l'utiliser ici
                        console.log('Address form is valid:', addressForm.value );
                        this.organisationPartyService.createAddress(this.id, addressForm.value).subscribe(
                            response => {
                                // Gérez la réponse de l'API en cas de succès
                                console.log('address created successfully:', addressForm.value);
                                Swal.fire({
                                  title: "Parfait !",
                                  text: "PM enregistre avec succès",
                                  icon: "success"
                                });
                                this.readonlyMode = "consult";
                                console.log(this.readonlyMode)


                            },
                            error => {
                                // Gérez les erreurs en cas d'échec de la création de l'organisation party
                                console.error('Error creating Organisation Party:', error);
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
            // Gérez les erreurs en cas d'échec de la création de l'organisation party
            console.error('Error creating Organisation Party:', error);
          }
        );
      } else {
        Swal.fire({
          title: "Erreur !",
          text: "formulaire invalid",
          icon: "error"
        });
        this.formulaire.markAllAsTouched();
      }








    }


    loadOrganisationPartyById(id: number): void {
      this.organisationPartyService.getOrganisationPartyById(id).subscribe(
        organisationParty => {
          // Mettre à jour les données dans le formulaire
          this.formulaire.patchValue({
            siren:organisationParty.siren,
            cp:organisationParty.cp,
            legalName: organisationParty.legalName,
            tradingName: organisationParty.tradingName,
            nafCodeGId: organisationParty.nafCodeGId,
            legalCategoryGId: organisationParty.legalCategoryGId,
            gicsCodeGId: organisationParty.gicsCodeGId,
            missingSIRENSIRETJustificationGId: organisationParty.missingSIRENSIRETJustificationGId,
            shareCapital: organisationParty.shareCapital,
            europeanOrganisationPartySizeGId: organisationParty.europeanOrganisationPartySizeGId,
            creationDate: organisationParty.creationDate,
            registrationDate: organisationParty.registrationDate,
            organisationPartyIdentifications:organisationParty.organisationPartyIdentifications,

          });
          this.addressComponent.updateAddressForm(organisationParty); 
          this.idT=organisationParty.idTiers;
          if(organisationParty.siren){
            this.SirenExist=true;
            console.log("SirenExist",this.SirenExist)
          }

        },
        error => {
          console.error('Error loading Organisation Party:', error);
        }
      );

    // from api externe
  this.organisationPartyService.getEtablissementData(this.formulaire.get('siren')!.value).subscribe(
    organisationParty => {
      console.log(organisationParty);
      this.formulaire.patchValue({
        legalName1: organisationParty.legalName,
        legalCategoryGId1: organisationParty.legalCategoryGId,
        europeanOrganisationPartySizeGId1: organisationParty.europeanOrganisationPartySizeGId,
        creationDate1: organisationParty.creationDate,
        nafCodeGId1:organisationParty.nafCodeGId,
        registrationDate1:organisationParty.registrationDate,

        
     
        
      });
      console.log("organisationPartyModifasiis  ", organisationParty)
  
    },
    error => {
      console.error(error);
    }
  );

      
      


    }




    toggleEditMode() {
         
      if (this.readonlyMode === modeEnum.consult) {
        this.readonlyMode = modeEnum.update; 
      } 
    if(this.siren){
      this.modeAssite=false
    }

      // from api externe
  this.organisationPartyService.getEtablissementData(this.formulaire.get('siren')!.value).subscribe(
    organisationParty => {
      console.log(organisationParty);
      this.formulaire.patchValue({
        legalName1: organisationParty.legalName,
        legalCategoryGId1: organisationParty.legalCategoryGId,
        europeanOrganisationPartySizeGId1: organisationParty.europeanOrganisationPartySizeGId,
        creationDate1: organisationParty.creationDate,
        nafCodeGId1:organisationParty.nafCodeGId,
        registrationDate1:organisationParty.registrationDate,

        
      });
      console.log("organisationPartyModifasiis  ", organisationParty)
  
    },
    error => {
      console.error(error);
    }
  );

    }




    updateOrganisation() {

      if(this.legalName!= null){
        this.formulaire.patchValue({
          legalName: this.legalName,
        });

      }
      if(this.legalCategoryGId!= null){
        this.formulaire.patchValue({
          legalCategoryGId: this.legalCategoryGId,
        });

      }
      if(this.europeanOrganisationPartySizeGId!= null){
        this.formulaire.patchValue({
          europeanOrganisationPartySizeGId: this.europeanOrganisationPartySizeGId,
        });

      }
      if(this.creationDate!= null){
        this.formulaire.patchValue({
          creationDate: this.creationDate,
        });

      }

      if(this.registrationDate!=null){
        this.formulaire.patchValue({
          registrationDate: this.registrationDate,
        });
      }

      if(this.naf!= null){
        this.formulaire.patchValue({
          nafCodeGId: this.naf,
        });

      }
  
      
      this.modeAssite=false;
      this.modificationAssiste=false;

      if (this.formulaire.invalid) {
        Swal.fire({
          title: "Erreur !",
          text: "formulaire invalid",
          icon: "error"
        });
        this.formulaire.markAllAsTouched();
      }else {
      if (this.id) {
        const addressForm = this.getAddressForm();
        // this.formulaire.patchValue({cp: this.getfrville(addressForm?.get('frLocationGId')?.value)});
        const formData = {
          ...this.formulaire.value,
          addressForm: addressForm!.value
        };


        console.log('FormData:', formData);

          this.organisationPartyService.updateOrganisationParty(this.id, formData).subscribe(
          response => {
            console.log('Organisation Party updated successfully:', response);
            Swal.fire({
              title: "Mise à jour réussie !",
              text: "Les informations de l'organisation ont été mises à jour avec succès.",
              icon: "success"
            });

            this.readonlyMode = "consult";
          },
          error => {
            console.error('Error updating Organisation Party:', error);
            Swal.fire({
              title: "Erreur !",
              text: "Une erreur est survenue lors de la mise à jour des informations de l'organisation. Veuillez réessayer.",
              icon: "error"
            });
          }
        );
      } else {
        console.error("Cannot update organisation: ID is not available.");
      }}
    
      this.modificationAssiste=false;
    }



      resetForm() {
        this.formulaire.reset();
        const addressForm = this.getAddressForm();
        if (addressForm) {
          addressForm.reset();
        }
      }

      annulermodif(){
        this.modeAssite=false;
        this.modificationAssiste=false;

        this.readonlyMode = modeEnum.consult; 
         
         this.loadOrganisationPartyById(this.id)
      }

      getfrville(id: string): string {
        this.locations$.subscribe((locations: Location[]) => {
          const location =  locations.find(c => c.locationGId === id);
          if (location) {
            this.locationName = location.postCode;
          }
        });
        return this.locationName;
    }


    fetchEtablissementData(siren: string) {
      this.organisationPartyService.getEtablissementData(siren).subscribe(
        organisationParty => {
          this.raisonSociale1=organisationParty.raisonSociale;
          this.nafCodeGId1=organisationParty.nafCodeGId;
          this.legalCategoryGId1=organisationParty.legalCategoryGId
          this.europeanOrganisationPartySizeGId1=organisationParty.europeanOrganisationPartySizeGId
          this.creationDate1=organisationParty.creationDate
          this.registrationDate1=organisationParty.registrationDate1
          this.modeAssite=true;
          console.log(organisationParty);
          this.formulaire.patchValue({
            siren:siren,
            legalName: organisationParty.legalName,
            legalCategoryGId: organisationParty.legalCategoryGId,
            europeanOrganisationPartySizeGId: organisationParty.europeanOrganisationPartySizeGId,
            creationDate: organisationParty.creationDate,
            registrationDate:organisationParty.registrationDate,
            nafCodeGId:organisationParty.nafCodeGId,
            date_dernier_traitement:organisationParty.date_dernier_traitement,



          });
          console.log("organisationParty  ", organisationParty)
          this.addressComponent.updateAddressForm(organisationParty); 

        },
        error => {
          this.router.navigate(['/home/pm/table']);
          Swal.fire({
            text: "Siren introuvable dans market data ",
            icon: "error"
          });
          console.error(error);
        }
      );

          // from api externe
  this.organisationPartyService.getEtablissementData(siren).subscribe(
    organisationParty => {
      console.log(organisationParty);
      this.formulaire.patchValue({
        siren:siren,
        legalName1: organisationParty.legalName,
        legalCategoryGId1: organisationParty.legalCategoryGId,
        europeanOrganisationPartySizeGId1: organisationParty.europeanOrganisationPartySizeGId,
        creationDate1: organisationParty.creationDate,
        nafCodeGId1:organisationParty.nafCodeGId,
        registrationDate1:organisationParty.registrationDate,

        
      });
      console.log("organisationPartyModifasiis  ", organisationParty)
      this.addressComponent.updateAddressForm(organisationParty); 

    },
    error => {
      console.error(error);
    }
  );
    }



    //Modification assiste :

    getEtablissementData() {
      this.modificationAssiste=true;
      this.modeAssite=true;
      
    }


    updateLegalName(value: string) {
      this.legalName=value;
      
    }
    
    updateCategorie(value: string) {
      this.legalCategoryGId=value;
   
    
    }
    
    
    updateSize(value: string){
      console.log("value",value)
      this.europeanOrganisationPartySizeGId=value
     
    }
    
    updateDate(value: string){
      this.creationDate=value

    }

    updatenaf(value: string){
     this.naf=value;
    }

    updateDateregister(value:any){
      this.registrationDate=value;
    }





}
