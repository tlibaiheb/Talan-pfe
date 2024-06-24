import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../shared/services/auth.service';
import { Router } from '@angular/router';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit {
  errorMessage: string = '';
  registerForm!: FormGroup;

  passwordVisible: boolean = false;

  togglePasswordVisibility(): void {
    this.passwordVisible = !this.passwordVisible;
  }



  constructor(private formBuilder: FormBuilder, private AuthService: AuthService , private router:Router,private toastr: ToastrService
  ) { }

  ngOnInit(): void {
    this.initForm();
  }

  initForm(): void {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      confirm: ['', Validators.required]
    }, {
      validator: this.passwordMatchValidator
    });
  }

  passwordMatchValidator(control: AbstractControl): { [key: string]: boolean } | null {
    const password = control.get('password');
    const confirm = control.get('confirm');

    if (!password || !confirm) {
      return null;
    }

    return password.value === confirm.value ? null : { 'passwordMismatch': true };
  }


  onSubmit(): void {
    if (this.registerForm.valid) {
      this.AuthService.registerUser(this.registerForm.value)
        .subscribe(
          response => {
            console.log('Registration successful:', response);
            this.router.navigateByUrl('/verify');

          },

          error => {
            console.error('Registration failed:', error);
            if (error.error === "Email already exists" || error.error === "Email not valid") {
              Swal.fire({
                  title: 'Erreur',
                  text: "L'utilisateur existe déjà ou demande invalide.",
                  icon: 'error'
              });
          } else if (error.error === "Password does not meet the criteria") {
              Swal.fire({
                  title: 'Erreur',
                  text: "Le mot de passe doit comporter au moins 8 caractères (au moins un chiffre et au moins un caractère spécial).",
                  icon: 'error'
              });
          } else {
              Swal.fire({
                  title: 'Erreur',
                  text: 'Une erreur inattendue est apparue. Veuillez réessayer plus tard.',
                  icon: 'error'
              });
          }
          
          }
        );
    }
  }
}
