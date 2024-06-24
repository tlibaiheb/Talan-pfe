import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.css'
})
export class ResetPasswordComponent {
  token: string = '';
  password: string = '';
  confirmPassword: string = '';
  resetSuccess: boolean = false;
  errorMessage: string = '';

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {
    this.token = this.route.snapshot.params['token']; // Capturer le token de réinitialisation de mot de passe à partir de l'URL
  }

  resetPassword() {
    // Vérifier si les mots de passe correspondent
    if (this.password !== this.confirmPassword) {
      // this.errorMessage = 'Les mots de passe ne correspondent pas.';
      Swal.fire({
        title: 'Erreur',
        text: 'Les mots de passe ne correspondent pas.',
        icon: 'error'
      });
      return;
    }

    // Envoyer une demande HTTP avec le nouveau mot de passe au backend
    this.http.post<any>('http://localhost:8080/api/v1/reset-password', { token: this.token, newPassword: this.password }).subscribe(
      response => {
        this.resetSuccess = true;
        Swal.fire({
          title: "Good job!",
          text: "Votre mot de passe a été réinitialisé avec succès.",
          icon: "success"
        });
      },
      error => {
        console.error('Error:', error);
        Swal.fire({
          title: 'Erreur',
          text: 'Une erreur est survenue lors de la réinitialisation du mot de passe.',
          icon: 'error'
        });
        // this.errorMessage = 'Une erreur est survenue lors de la réinitialisation du mot de passe.';
      }
    );
  }

  goToLoginPage() {
    this.router.navigate(['/login']); // Rediriger vers la page de connexion
  }
}
