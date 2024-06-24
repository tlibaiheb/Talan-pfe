import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddresseComponent } from './addresse.component';
import { NgSelectModule } from '@ng-select/ng-select';
import { ReactiveFormsModule } from '@angular/forms';
import { DropdownModule } from 'primeng/dropdown';



@NgModule({
  declarations: [AddresseComponent],
  imports: [
    DropdownModule,
    ReactiveFormsModule,
    CommonModule,
    NgSelectModule,

  ],
  exports:[AddresseComponent]
})
export class AdresseModule { }
