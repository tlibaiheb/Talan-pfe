import { Component, Input, OnInit } from '@angular/core';
import { Observable, map, take } from 'rxjs';
import { Store, select } from '@ngrx/store';
import { selectLocationError, selectLocationLoading, selectLocations } from '../../state/reducers/location.reducer';
import { loadLocations } from '../../state/actions/location.actions';
import { Location } from '../../models/location.model';
import { CacheDataService } from '../../services/CacheDataService';
import { selectTypeadresses } from '../../state/reducers/typeadress.reducer';
import { Typeadress } from '../../models/typeadress.model';
import { loadTypeadresses } from '../../state/actions/typeadress.actions';
import { Country } from '../../models/country.model';
import { selectCountries } from '../../state/reducers/country.reducer';
import { loadCountries } from '../../state/actions/country.action';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { modeEnum } from '../../enums/enums';
import Swal from 'sweetalert2';
import { SelectItem } from 'primeng/api';
import { OrganisationPartyService } from '../../services/pm.service';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-addresse',
  templateUrl: './addresse.component.html',
  styleUrl: './addresse.component.css'
})
export class AddresseComponent implements OnInit {
  
  num:any
  typeDeVoie:any
  typeDeVoie1:any
  complementAdresse:any
  libelleDeLaVoie:any 
  frLocationGId:any
  @Input() SirenExist:any;
  @Input() type: any;
  @Input() inFrance:any;
  @Input() readonlyMode:any
  @Input() modeAssite:any
  @Input() modificationAssiste:any ;
  adress: FormGroup = new FormGroup({});
  isSaved: boolean = false;

     listess: { label: string }[] = [];

     bufferSize = 20;
     loading = false;

  //ville france locations
  locations$:Observable<Location[]>;
  //typeadresse
  typeadresses$: Observable<Typeadress[]>;
  //countries
  countries$:Observable<Country[]>
  siren: any;
  frLocationGId1: any;
  num1: any;
  complementAdresse1: any;
  libelleDeLaVoie1: any;


  constructor(private router: Router,private route: ActivatedRoute,private organisationPartyService: OrganisationPartyService,private store: Store, private cacheDataService: CacheDataService,private formBuilder: FormBuilder) {
    //ville:france location
    this.locations$=this.store.pipe(select(selectLocations));
    //typeadress
    this.typeadresses$=this.store.pipe(select(selectTypeadresses));
    //countries
    this.countries$=this.store.pipe(select(selectCountries))
  }


  modeEnum = modeEnum;

  ngOnInit() {

    if (this.type === 'PM') {
      this.adress = this.formBuilder.group({
        frLocationGId: ['', Validators.required],
        num: [''],
        typeDeVoie: [''],
        complementAdresse: [''],
        libelleDeLaVoie: [''],
        frLocationGId1: [''],
        num1: [''],
        typeDeVoie1: [''],
        complementAdresse1: [''],
        libelleDeLaVoie1: [''],
      });
    } else {
      this.adress = this.formBuilder.group({
        address1: [null],
        address2: [null],
        address3: [null, Validators.required],
        address4: [null],
        frLocationGId: ['', Validators.required],
        countryGId: ['', Validators.required], // this.franceLocationGid = 81
        outOfFrTown: [null],
        outOfFrPostCode: [null],
        physicalContactPointTypeGId: [''], // Aucune valeur par défaut ni validateur
        num: [''],
        typeDeVoie: [''],
        complementAdresse: [''],
        libelleDeLaVoie: [''],
        regionOutFr:[''],
        inFrance:['']
      });
    }



      this.cacheDataService.checkAndLoadData( this.locations$, loadLocations);
      this.locations$.subscribe((data: Location[])=> {
        console.log(data); 
        this.listess = data.slice(0, data.length).map(location => ({
          label: `${location.postCode}-${location.locationLabel}`,
          value: location.locationGId,
          postCode: location.postCode 

          
        }))

      });
      this.cacheDataService.checkAndLoadData(this.typeadresses$, loadTypeadresses);
      this.cacheDataService.checkAndLoadData( this.countries$, loadCountries);

      this.siren = this.route.snapshot.queryParamMap.get('siren');
      console.log('SIREN récupéré depuis l\'URL:', this.siren);
      if(this.siren){
        this.SirenExist=true;
        this.modeAssite=true;
        
      }
    }

    getAdressForm(): any {
      if (this.type === 'PM') {
        if (this.adress.invalid) {
          Swal.fire({
            title: "Erreur !",
            text: "Adresse invalide",
            icon: "error"
          });
          this.adress.markAllAsTouched();
        } else {

          if(this.num!= null){
            this.adress.patchValue({
              num: this.num,
            });
    
          }
          if(this.typeDeVoie!= null){
            this.adress.patchValue({
              typeDeVoie: this.typeDeVoie,
            });
    
          }
          if(this.complementAdresse!= null){
            this.adress.patchValue({
              complementAdresse: this.complementAdresse,
            });
    
          }
          if(this.libelleDeLaVoie!= null){
            this.adress.patchValue({
              libelleDeLaVoie: this.libelleDeLaVoie,
            });
    
          }
          if(this.frLocationGId!= null){
            this.adress.patchValue({
              frLocationGId: this.frLocationGId,
            });
    
          }




          return this.adress;
        }
      } else {
        console.log(this.inFrance);
    
        if (this.inFrance) {
          this.adress.get('inFrance')?.setValue(this.inFrance);
          this.adress.get('address3')?.clearValidators();
          this.adress.get('address3')?.updateValueAndValidity();
          this.adress.get('countryGId')?.clearValidators();
          this.adress.get('countryGId')?.updateValueAndValidity();
          this.adress.get('frLocationGId')?.setValidators(Validators.required);
          this.adress.get('frLocationGId')?.updateValueAndValidity();
        } else {
          this.adress.get('address3')?.setValidators(Validators.required);
          this.adress.get('address3')?.updateValueAndValidity();
          this.adress.get('countryGId')?.setValidators(Validators.required);
          this.adress.get('countryGId')?.updateValueAndValidity();
          this.adress.get('frLocationGId')?.clearValidators();
          this.adress.get('frLocationGId')?.updateValueAndValidity();
        }
    
        return this.adress;
      }
    }
    
    fetchMore() {
      const len = this.listess.length;
 
      this.locations$.subscribe((data: Location[]) => {
        const more = data.filter((x: Location) => x.locationLabel).slice(len, this.bufferSize + len);
        this.loading = true;
        setTimeout(() => {
          this.loading = false;
          this.listess = this.listess.concat(more.map(location => ({
            label: `${location.postCode}-${location.locationLabel}`, // Concaténer code postal et ville
            value: location.locationGId
  
          })));
        }, 200);
      });



    }









  updateAddressForm(addressData: any): void {
    console.log("addressData",addressData.addressForm)
    if(this.type=="PM"){
      this.typeDeVoie1=addressData.addressForm.typeDeVoie
      this.frLocationGId1=addressData.addressForm.frLocationGId;
      this.num1= addressData.addressForm.num;
      this.complementAdresse1=addressData.addressForm.complementAdresse;
      this.libelleDeLaVoie1= addressData.addressForm.libelleDeLaVoie;
      this.adress.patchValue({

        frLocationGId: addressData.addressForm.frLocationGId,
        num: addressData.addressForm.num,
        typeDeVoie: addressData.addressForm.typeDeVoie,
        complementAdresse:addressData.addressForm.complementAdresse,
        libelleDeLaVoie: addressData.addressForm.libelleDeLaVoie,
      });
          // from api externe
          if(addressData.siren){
            this.siren=addressData.siren
          }
          this.organisationPartyService.getEtablissementData(this.siren).subscribe(
            organisationParty => {
              console.log("fromapi",organisationParty);
              this.adress.patchValue({
                frLocationGId1: organisationParty.addressForm.frLocationGId,
                num1: organisationParty.addressForm.num,
                complementAdresse1: organisationParty.addressForm.complementAdresse,
                libelleDeLaVoie1: organisationParty.addressForm.libelleDeLaVoie,
                typeDeVoie1:organisationParty.addressForm.typeDeVoie
             
                
              });
              console.log("adress",this.adress)
              console.log("organisationPartyModifasiis  ", organisationParty)
          
            },
            error => {
              console.error(error);
            }
          );
      


    }
    else{
      this.adress.patchValue({

        frLocationGId: addressData.frLocationGId,
        num: addressData.num,
        typeDeVoie: addressData.typeDeVoie,
        complementAdresse:addressData.complementAdresse,
        libelleDeLaVoie: addressData.complementAdresse,
        address1:addressData.address1,
        address2:addressData.address2,
        address3:addressData.address3,
        address4:addressData.address4,
        countryGId:addressData.countryGId,
        outOfFrTown:addressData.outOfFrTown,
        outOfFrPostCode:addressData.outOfFrPostCode,
        physicalContactPointTypeGId:addressData.physicalContactPointTypeGId,
        regionOutFr:addressData.regionOutFr,
        inFrance:addressData.inFrance,



      });


      this.isSaved = true;


    }

  }


  selectedLocation: any;

  // Méthode pour gérer le changement de sélection
  onLocationChange(selectedValue: any) {
    this.selectedLocation = selectedValue;
    // Vous pouvez faire des actions supplémentaires en fonction de la sélection
  }


//modif assiste

updateNum(value: string) {
  this.num=value;
  
}

updatetypeDeVoie(value: string) {
  console.log("value",value)
  this.typeDeVoie=value;
  
}

updatecomplementAdresse(value: string) {
  this.complementAdresse=value;
  
}

updatelibelleDeLaVoie(value: string) {
  this.libelleDeLaVoie=value;
  
}

updatefrLocationGId(value: string) {
  this.frLocationGId=value;
  
}




}
