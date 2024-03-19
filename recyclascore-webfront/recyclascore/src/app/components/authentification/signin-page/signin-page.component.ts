import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TokenService } from 'src/app/services/token/token.service';

@Component({
  selector: 'app-signin-page',
  templateUrl: './signin-page.component.html',
  styleUrls: ['./signin-page.component.scss'],
})
export class SigninPageComponent implements OnInit {
  signUpForm!: FormGroup;
  submitted: boolean = false;
  hideSignInForm?: boolean;

  constructor(
    private route: Router,
    private formBuilder: FormBuilder,
    public tokenService: TokenService
  ) {}

  ngOnInit() {
    this.signUpForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    // stop here if form is invalid
    if (!this.signUpForm || this.signUpForm.invalid) {
      return;
    }
    // get values to the new user
    const username = this.signUpForm.get('username')?.value;
    const email = this.signUpForm.get('email')?.value;
    const password = this.signUpForm.get('password')?.value;

    //call the authentification service
    this.proceedSignUp(username, email, password);
  }

  /**
   * Create user count with name, username, email and password
   */
  private proceedSignUp(username: string, email: string, password: string) {
    this.submitted = true;
    // call the service for connexion
    this.tokenService.signUp(username, email, password).subscribe({
      next: (accessToken: any) => {
        console.log('token recovered : %s', accessToken);
        this.route.navigateByUrl('/material-object');
        console.info('signUp succeed');
      },
      error: (err: Error) => console.error(err),
      complete: () => {
        this.submitted = false;
      },
    });
  }
}
