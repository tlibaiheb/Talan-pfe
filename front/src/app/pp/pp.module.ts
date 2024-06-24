import {  CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PpRoutingModule } from './pp-routing.module';
import { PpComponent } from './addpp/pp.component';
import { CardModule } from 'primeng/card';
import { SidebarModule } from 'primeng/sidebar';
import { ButtonModule } from 'primeng/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { CalendarModule } from 'primeng/calendar';
import { TriStateCheckboxModule } from 'primeng/tristatecheckbox';
import { InputGroupModule } from 'primeng/inputgroup';
import { PpSidebarComponent } from '../shared/components/pp-sidebar/pp-sidebar.component';

import { NgSelectModule } from '@ng-select/ng-select';
import { AdresseModule } from '../shared/components/addresse/adresse.module';
import { PptableComponent } from './pptable/pptable.component';
import { Tablemodule } from '../shared/components/table/table.module';









@NgModule({
    declarations: [
        PpComponent,
        PpSidebarComponent,
        PptableComponent,

    ],
    imports: [
        Tablemodule,
        AdresseModule,
        NgSelectModule,
        CommonModule,
        PpRoutingModule,
        SidebarModule,
        ButtonModule,
        CardModule,
        FormsModule,
        InputTextModule,
        DropdownModule,
        CalendarModule,
        TriStateCheckboxModule,
        InputGroupModule,
        DropdownModule,
        NgSelectModule,
        ReactiveFormsModule,
        FormsModule,





    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PpModule { }
