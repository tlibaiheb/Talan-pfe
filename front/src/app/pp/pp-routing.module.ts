import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PpComponent } from './addpp/pp.component';
import { PmtableComponent } from '../pm/pmtable/pmtable.component';
import { PptableComponent } from './pptable/pptable.component';

const routes: Routes = [
  {
    path:'',
    component:PpComponent,


  },


  {

    path: 'table',
    component: PptableComponent,

},
  {
    path:':id',
    component:PpComponent,
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PpRoutingModule { }
