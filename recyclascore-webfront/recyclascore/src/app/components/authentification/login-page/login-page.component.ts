import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TokenService } from '../../../services/token/token.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
})
export class LoginPageComponent implements OnInit {
  loginForm!: FormGroup;
  submitted: boolean = false;
  hideFormLogin?: boolean;

  constructor(
    private route: Router,
    private formBuilder: FormBuilder,
    public tokenService: TokenService
  ) {
    this.hideFormLogin = true;
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    // stop here if form is invalid
    if (!this.loginForm || this.loginForm.invalid) {
      return;
    }
    // get email and password
    const email = this.loginForm.get('email')?.value;
    const password = this.loginForm.get('password')?.value;

    // Call the authentification service
    this.proceedLogin(email, password);
  }

  /**
   * Connexion with email and password
   */
  private proceedLogin(email: string, password: string) {
    this.submitted = true;
    // call tokenService for connexion
    this.tokenService.login(email, password).subscribe({
      next: (accessToken: any) => {
        console.log('token recovered : %s', accessToken);
        this.route.navigateByUrl('/material');
        console.info('logIn succeed');
      },
      error: (e: Error) => console.error(e),
      complete: () => {
        this.submitted = false;
      },
    });
  }

  public navigateToSignUpPage() {
    this.route.navigateByUrl('/signup');
  }
}
