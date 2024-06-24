import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VerifyEmailComponent } from './shared/components/verify-email/verify-email.component';
import { LoginComponent } from './core/auth/login/login.component';
import { RegisterComponent } from './core/auth/register/register.component';
import { AuthGuard } from './shared/services/auth.guard';
import { ForgotPasswordComponent } from './core/auth/forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './core/auth/reset-password/reset-password.component';
import { TableComponent } from './shared/components/table/table.component';
//import { RegistrationComponent } from './core/auth1/registration/registration.component';


const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  {path:'home', loadChildren: () => import('./layout1/layout1.module').then((m) => m.Layout1Module) , canActivate: [AuthGuard]},
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  {path:'verify', component:VerifyEmailComponent},
  {path:'forgotPassword', component:ForgotPasswordComponent},
  { path: 'reset-password/:token', component: ResetPasswordComponent },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
