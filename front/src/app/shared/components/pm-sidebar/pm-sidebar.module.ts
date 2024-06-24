import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PmSidebarComponent } from './pm-sidebar.component';
import { PmSidebarRoutingModule } from './pm-sidebar-routing.module';
import { CardModule } from 'primeng/card';


@NgModule({
  declarations: [PmSidebarComponent],
  imports: [
    CardModule,
    CommonModule,
    PmSidebarRoutingModule
  ],
  exports:[PmSidebarComponent]

})
export class PmSidebarModule { }
