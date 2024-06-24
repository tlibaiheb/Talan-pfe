import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PmComponent } from './addpm/pm.component';
import { PmtableComponent } from './pmtable/pmtable.component';

const routes: Routes = [
  {
    path:'',
    component:PmComponent,
  },

  
  {
    
    path: 'table',
    component: PmtableComponent,
  
},
{
  path:':id',
  component:PmComponent,
},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PmRoutingModule { }
