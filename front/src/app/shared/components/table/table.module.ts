import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableModule } from 'primeng/table';
import { ToolbarModule } from 'primeng/toolbar';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { DialogModule } from 'primeng/dialog'; 
import { ButtonModule } from 'primeng/button';
import { FormsModule } from '@angular/forms';
import { TabMenuModule } from 'primeng/tabmenu';
import { RadioButtonModule } from 'primeng/radiobutton';
import { TableComponent } from './table.component';
import { PaginatorModule } from 'primeng/paginator';



@NgModule({
  declarations: [TableComponent],
  imports: [
    RadioButtonModule,
    TabMenuModule,
    FormsModule,
    ButtonModule,
    DialogModule,
    ToolbarModule,
    ToastModule,
    TableModule,
    PaginatorModule,
    CommonModule
  ],
  providers: [
    MessageService 
  ]
,
  exports:[TableComponent]

})
export class Tablemodule { }
