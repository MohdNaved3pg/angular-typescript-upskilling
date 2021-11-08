import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../service/user-service';
import { first } from 'rxjs/operators';
import { Router } from '@angular/router';
import { ConditionalExpr } from '@angular/compiler';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    loginForm : FormGroup = this.formBuilder.group([]);
    loading : boolean = false;
    submitted: boolean = false;
    isUsernameValid: boolean = true;
    isPasswordValid: boolean = true;
    areCredsValid: boolean = true;
    constructor(
      private router: Router,
      private formBuilder : FormBuilder,
      private userService : UserService
    ) {
      if(!this.userService.currentUser.isEmpty) {
        this.router.navigate(['/']);
      }
     }

    ngOnInit(): void {
      this.loginForm = this.formBuilder.group({
        username: ['', Validators.required],
        password: ['', Validators.required]
    });
    }

    onSubmit(): void {
      console.log(this.loginForm.valid);
      this.submitted = true;
      if(this.loginForm.invalid) {
        this.validateForm();
        return;
      }
      let email : String = this.loginForm.controls.username.value;
      let password : String = this.loginForm.controls.password.value;
      this.loading = true;
      this.userService.authenticate(email, password)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate(['/']);
        },
        error => {
          console.error("===================="+error);
          if(error instanceof HttpErrorResponse) {
            if(error.status == 400) {
              this.areCredsValid = false;
            }
          }
          this.loading = false;
        },
        () => this.loading = false
      )
      ;
    }

    validateForm() {
        this.isUsernameValid = !this.loginForm.controls.username.errors;
        this.isPasswordValid = !this.loginForm.controls.password.errors;
    }

}