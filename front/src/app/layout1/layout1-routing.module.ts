import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Layout1Component } from './layout1/layout1.component';
import { AuthGuard } from '../shared/services/auth.guard';

const routes: Routes = [
  {
    path: '',
    component: Layout1Component,
    children: [
     
      {
        path: 'pm',
        loadChildren: () => import('../pm/pm.module').then((m) => m.PmModule), canActivate: [AuthGuard]
      },
      {
        path: 'pp',
        loadChildren: () => import('../pp/pp.module').then((m) => m.PpModule), canActivate: [AuthGuard]
      },
      
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class Layout1RoutingModule {}
