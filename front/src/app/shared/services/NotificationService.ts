import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private http: HttpClient, private snackBar: MatSnackBar) { }

  getNotifications() {
    return this.http.get<any[]>('http://localhost:8086/api/v1/getnotifications');
  }

  showNotification(message: string) {
    this.snackBar.open(message, 'Close', {
      duration: 3000 // Durée d'affichage de la notification en millisecondes
    });
  }
}
