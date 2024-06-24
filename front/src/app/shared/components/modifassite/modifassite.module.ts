import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModifassiteComponent } from './modifassite.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgSelectModule } from '@ng-select/ng-select';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';



@NgModule({
  declarations: [ModifassiteComponent],
  imports: [
    ReactiveFormsModule,
    FormsModule,
    CommonModule,
    NgSelectModule,
    ButtonModule,
    InputTextModule,



  ],
  exports:[ModifassiteComponent]
})
export class ModifassiteModuleTsModule { }
