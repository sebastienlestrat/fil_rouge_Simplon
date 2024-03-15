import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './authentification/login-page/login-page.component';
import { SigninPageComponent } from './authentification/signin-page/signin-page.component';
import { MaterialObjectComponent } from './components/material-object/material-object.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginPageComponent },
  { path: 'signup', component: SigninPageComponent },
  { path: 'material-object', component: MaterialObjectComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
