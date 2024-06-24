import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { NgSelectModule } from '@ng-select/ng-select';
import { VerifyEmailComponent } from './components/verify-email/verify-email.component';
import { RouterModule } from '@angular/router';
import { TabMenuModule } from 'primeng/tabmenu';

import { MatSnackBarModule } from '@angular/material/snack-bar';





@NgModule({
  declarations: [



    VerifyEmailComponent,

  ],
  imports: [
    MatSnackBarModule,
    TabMenuModule,
    RouterModule,
    NgSelectModule,
    CommonModule,
    CardModule,
    ButtonModule,

  ],


})
export class SharedModule { }
