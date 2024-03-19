import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './components/authentification/login-page/login-page.component';
import { SigninPageComponent } from './components/authentification/signin-page/signin-page.component';
import { MaterialObjectComponent } from './components/material-object/material-object.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginPageComponent },
  { path: 'signup', component: SigninPageComponent },
  { path: 'material', component: MaterialObjectComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
