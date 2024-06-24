import { Component } from '@angular/core';

@Component({
  selector: 'app-pptable',
  templateUrl: './pptable.component.html',
  styleUrl: './pptable.component.css'
})
export class PptableComponent {
  type='PP'

  cols = [
   {field:'id',header:'ID Tiers'},
   { field: 'namePrefixGId', header: 'Civilité' },
   { field: 'birthName', header: 'Nom' },
   { field: 'mainFirstName', header: 'Prénom' },
   { field: 'Datedenaissance', header: 'Date de naissance' },
   { field: 'location', header: 'Localisation' },

   
 ];

}
