import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OrganisationPartyService } from '../../services/pm.service';
import { Store, select } from '@ngrx/store';
import { CacheDataService } from '../../services/CacheDataService';
import { modeEnum } from '../../enums/enums';
import { selectCategories } from '../../state/reducers/category.reducer';
import { selectNafs } from '../../state/reducers/naf.reducer';
import { selectGicscodes } from '../../state/reducers/gicscode.reducer';
import { selectMissingsirens } from '../../state/reducers/missingsiren.reducer';
import { selectTailleEntreprises } from '../../state/reducers/taille-entreprise.reducer';
import { selectLocations } from '../../state/reducers/location.reducer';
import { Observable } from 'rxjs';
import { TailleEntreprise } from '../../models/tailleEntreprise.model';
import { Missingsiren } from '../../models/missingsiren.model';
import { Gicscode } from '../../models/gicscode.model';
import { Naf } from '../../models/naf.model';
import { Category } from '../../models/category.model';
import { Location } from '../../models/location.model';
import { loadCategories } from '../../state/actions/category.action';
import { loadNafs } from '../../state/actions/naf.actions';
import { loadGicscodes } from '../../state/actions/gicscode.actions';
import { loadMissingsirens } from '../../state/actions/missingsiren.actions';
import { loadTailleEntreprises } from '../../state/actions/tailleEntreprise.action';
import { loadLocations } from '../../state/actions/location.actions';

@Component({
  selector: 'app-modifassite',
  templateUrl: './modifassite.component.html',
  styleUrl: './modifassite.component.css'
})
export class ModifassiteComponent implements OnInit  {

  @Input() formulaire:any;
  @Input() siren:any;
  @Input() readonlyMode:any;
  @Input() modeAssite:any;
  @Input() SirenExist:any;
  modeEnum = modeEnum;

  @Output() formulaireModifie: EventEmitter<any> = new EventEmitter<any>();


  formulaire1: FormGroup = new FormGroup({});


  

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

    this.cacheDataService.checkAndLoadData(this.categories$, loadCategories);
    this.cacheDataService.checkAndLoadData(this.nafs$, loadNafs);
    this.cacheDataService.checkAndLoadData(this.gicscodes$,loadGicscodes);
    this.cacheDataService.checkAndLoadData(this.missingsirens$,loadMissingsirens);
    this.cacheDataService.checkAndLoadData(this.tailleEntreprises$,loadTailleEntreprises);
    this.cacheDataService.checkAndLoadData( this.locations$, loadLocations);


  this.formulaire1 = this.formBuilder.group({
    legalName1: ['', Validators.compose([Validators.required, Validators.maxLength(150)])], 
    siren1: [''],
    legalCategoryGId1: ['', Validators.required], 
    europeanOrganisationPartySizeGId1: ['', Validators.required], 
    creationDate1: [''], 
    frLocationGId1: ['', Validators.required],
    num1: [''],
    typeDeVoie1: [''],
    complementAdresse1: [''],
    libelleDeLaVoie1: [''],

 
     
  });

 

// from api externe
  this.organisationPartyService.getEtablissementData(this.formulaire.get('siren').value).subscribe(
    organisationParty => {
      console.log(organisationParty);
      this.formulaire1.patchValue({
        siren1:this.formulaire.get('siren').value,
        legalName1: organisationParty.legalName,
        legalCategoryGId1: organisationParty.legalCategoryGId,
        europeanOrganisationPartySizeGId1: organisationParty.europeanOrganisationPartySizeGId,
        creationDate1: organisationParty.creationDate,
        frLocationGId1: organisationParty.addressForm.frLocationGId,
        num1: organisationParty.addressForm.num,
        typeDeVoie1: organisationParty.addressForm.typeDeVoie,
        complementAdresse1:organisationParty.addressForm.complementAdresse,
        libelleDeLaVoie1: organisationParty.addressForm.libelleDeLaVoie,
        
      });
      console.log("organisationPartyModifasiis  ", organisationParty)
  
    },
    error => {
      console.error(error);
    }
  );



  // from base de donnees 
  this.organisationPartyService.getOrganisationPartyBySiren(this.formulaire.get('siren').value).subscribe(
    organisationParty => {
      console.log(organisationParty);
      this.formulaire1.patchValue({
        siren:this.formulaire.get('siren').value,
        cp:organisationParty.cp,
        legalName: organisationParty.legalName,
        tradingName: organisationParty.tradingName,
        nafCodeGId: organisationParty.nafCodeGId,
        legalCategoryGId: organisationParty.legalCategoryGId,
        gicsCodeGId: organisationParty.gicsCodeGId,
        europeanOrganisationPartySizeGId: organisationParty.europeanOrganisationPartySizeGId,
        shareCapital: organisationParty.shareCapital,
        creationDate: organisationParty.creationDate,
        registrationDate: organisationParty.registrationDate,
        organisationPartyIdentifications:organisationParty.organisationPartyIdentifications,
        frLocationGId: organisationParty.addressForm.frLocationGId,
        num: organisationParty.addressForm.num,
        typeDeVoie: organisationParty.addressForm.typeDeVoie,
        complementAdresse:organisationParty.addressForm.complementAdresse,
        libelleDeLaVoie: organisationParty.addressForm.libelleDeLaVoie,
        
      });
      console.log("organisationPartyModifasiis  ", organisationParty)
  
    },
    error => {
      console.error(error);
    }
  );
  

  
   


}

get organisationPartyIdentificationValueControl(): FormControl {
  const control = this.formulaire.get('organisationPartyIdentifications.0.organisationPartyIdentificationValue') as FormControl;
  return control ? control : new FormControl('');
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


updateLegalName(value: string) {
  this.formulaire.get('legalName').setValue(value );
  console.log("formulaire",this.formulaire.value)
}

updateCategorie(value: string) {
  this.formulaire.get('legalCategoryGId').setValue(value );
  console.log("formulaire",this.formulaire.value)
}


updateSize(value: string){
  this.formulaire.get('europeanOrganisationPartySizeGId').setValue(value );
  console.log("formulaire",this.formulaire.value)
}

updateDate(value: string){
  this.formulaire.get('creationDate').setValue(value );
  console.log("formulaire",this.formulaire.value)
}

updateformulaire(){
  this.formulaire.patchValue({
    tradingName: this.formulaire1.get('tradingName')!.value,
    nafCodeGId: this.formulaire1.get('nafCodeGId')!.value,
    gicsCodeGId: this.formulaire1.get('gicsCodeGId')!.value,
    cp: this.formulaire1.get('cp')!.value,
    organisationPartyIdentifications: this.formulaire1.get('organisationPartyIdentifications')!.value,
    registrationDate: this.formulaire1.get('registrationDate')!.value,
    shareCapital: this.formulaire1.get('shareCapital')!.value,




  })

  this.formulaireModifie.emit(this.formulaire.value);


}
}
