import { Component } from '@angular/core';

@Component({
  selector: 'app-pmtable',
  templateUrl: './pmtable.component.html',
  styleUrl: './pmtable.component.css'
})
export class PmtableComponent {

  type='PM'

  cols = [
   {finally:'id',header:'ID Tiers'},
   { field: 'tradingName', header: 'Raison social / Nom' },
   { field: 'siren', header: 'Siren' },
   { field: 'location', header: 'Localisation' },
   { field: 'shareCapital', header: 'shareCapital' },

   
 ];

}
