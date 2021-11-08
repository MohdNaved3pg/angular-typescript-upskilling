import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../service/user-service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

    registrationForm : FormGroup = this.formBuilder.group([]);
    loading : boolean = false;

    isNameValid : boolean = true;
    isUsernameValid : boolean = true;
    isEmailValid : boolean = true;
    isPasswordValid : boolean = true;

    constructor(
      private formBuilder : FormBuilder,
      private userService : UserService
    ) { }

    ngOnInit(): void {
      this.registrationForm = this.formBuilder.group({
        full_name: ['', Validators.required],
        username: ['', Validators.required],
        email: ['', Validators.required],
        password: ['', Validators.required]
    });
    }

    onSubmit(): void {
      if(!this.validateForm()) {
        return;
      }
      
      this.loading = true;
      this.userService.register(this.registrationForm.value)
      .pipe(first())
      .subscribe(
        data => {
          window.alert("Registration Successfull")
        },
        error => {
          console.error(error);
          this.loading = false;
        },
        () => this.loading = false
      )
      ;
    }

    validateForm() {
      this.isNameValid = !this.registrationForm.controls.full_name.errors;
      this.isUsernameValid = !this.registrationForm.controls.username.errors;
      this.isEmailValid = !this.registrationForm.controls.email.errors;
      this.isPasswordValid = !this.registrationForm.controls.password.errors;
      return !this.registrationForm.invalid;
  }

}