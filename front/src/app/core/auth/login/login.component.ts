import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../shared/services/auth.service';
import { Router } from '@angular/router';
import { HeaderService } from '../../../shared/services/header.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

passwordValue: string="";
searchText: string = '';
email: string = '';
  password: string = '';
  errorMessage: string = '';

constructor(private authService: AuthService, private router: Router) { }

ngOnInit(): void {
}

  onSearchInput(event: Event) {
    this.searchText = (event.target as HTMLInputElement).value;
  }

  isSearchTextEmpty() {
    return this.searchText.length === 0;

}
/*connect(): void {
  this.authService.login(this.email, this.password).subscribe(
    (response) => {
      // Connexion réussie
      localStorage.setItem('token', response.token); // Stockez le token dans le localStorage
      this.router.navigate(['/home']); // Redirigez l'utilisateur vers la page '/home'
    },
    (error) => {
      // Erreur lors de la connexion
      this.errorMessage = error.message;
    }
  );
}*/
connect(): void {
  const credentials = { email: this.email, password: this.password };
  this.authService.loginUser(credentials).subscribe(
    (response) => {
      localStorage.setItem('token', response.token);
      this.router.navigate(['/home/pm/table']);
    },
    (error) => {
 
      if (error.status === 429) {
        Swal.fire({
          title: 'Erreur',
          text: "Trop de tentatives de connexion. Veuillez réessayer plus tard.",
          icon: 'error'
        });
      } else if (error.status === 401 && error.error && error.error === "User is not verified") {
        Swal.fire({
          title: 'Erreur',
          text: "Votre compte n'est pas encore vérifié. Veuillez vérifier votre boîte de réception pour le lien de vérification.",
          icon: 'error'
        });
      } else if (error.status === 401 || error.status === 404) {
        Swal.fire({
          title: 'Erreur',
          text: "Adresse e-mail ou mot de passe incorrect.",
          icon: 'error'
        });
      } else {
        Swal.fire({
          title: 'Erreur',
          text: "Une erreur s'est produite lors de la connexion. Veuillez réessayer plus tard.",
          icon: 'error'
        });
      }
    }
  );
}
}
