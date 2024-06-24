import {CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MessageService } from 'primeng/api';

import { HeaderRoutingModule } from './header-routing.module';
import { HeaderComponent } from './header.component';
import { DropdownModule } from 'primeng/dropdown';
import { ButtonModule } from 'primeng/button';

import { ToastModule } from 'primeng/toast';

@NgModule({
  declarations: [
    HeaderComponent
  ],
  imports: [
    ToastModule,
    CommonModule,
    HeaderRoutingModule,
    DropdownModule,
    ButtonModule
  ],
  exports:[
    HeaderComponent
  ],
  providers: [MessageService],

  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HeaderModule { }
