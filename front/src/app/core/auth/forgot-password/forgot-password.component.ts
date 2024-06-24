import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrl: './forgot-password.component.css'
})
export class ForgotPasswordComponent {

  email: string = '';

  constructor(private http: HttpClient) { }

  sendResetPasswordEmail() {
    if (!this.email) {
      Swal.fire('Erreur', 'Veuillez entrer votre adresse e-mail.', 'error');
      return;
    }

    this.http.post('http://localhost:8080/api/v1/forgot-password', { email: this.email }, { responseType: 'text' }).subscribe(
      response => {
        // Gérer le texte brut de la réponse
        if (response === 'Un email contenant votre adresse e-mail a été envoyé.') {
          Swal.fire('Succès', 'L\'e-mail de réinitialisation de mot de passe a été envoyé.', 'success');
        } else {
          Swal.fire('Erreur', 'Une erreur est survenue lors de l\'envoi de l\'e-mail de réinitialisation de mot de passe.', 'error');
        }
      },
      error => {
        console.error('Error:', error);
        Swal.fire('Erreur', 'Une erreur est survenue lors de l\'envoi de l\'e-mail de réinitialisation de mot de passe.', 'error');
      }
    );
  }
}



