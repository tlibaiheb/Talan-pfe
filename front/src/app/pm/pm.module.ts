import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgSelectModule } from '@ng-select/ng-select';
import { PmRoutingModule } from './pm-routing.module';
import { PmComponent } from './addpm/pm.component';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { CalendarModule } from 'primeng/calendar';
import { TriStateCheckboxModule } from 'primeng/tristatecheckbox';
import { InputGroupModule } from 'primeng/inputgroup';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { AdresseModule } from '../shared/components/addresse/adresse.module';
import { PmSidebarModule } from '../shared/components/pm-sidebar/pm-sidebar.module';
import { ReactiveFormsModule } from '@angular/forms';
import { PmtableComponent } from './pmtable/pmtable.component';
import { TabMenuModule } from 'primeng/tabmenu';
import { Tablemodule } from '../shared/components/table/table.module';
import { ModifassiteModuleTsModule } from '../shared/components/modifassite/modifassite.module';

@NgModule({
  declarations: [
    PmComponent,
    PmtableComponent,
    
    ],
  imports: [
    ModifassiteModuleTsModule,
    Tablemodule,
    TabMenuModule,
    ReactiveFormsModule,
    PmSidebarModule,
    AdresseModule,
    NgSelectModule,
    CommonModule,
    PmRoutingModule,
    ButtonModule,
    CardModule,
    FormsModule,
    InputTextModule,
    DropdownModule,
    CalendarModule,
    TriStateCheckboxModule,
    InputGroupModule,

    ],
    exports: []

})
export class PmModule { }
