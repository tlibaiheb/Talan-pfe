import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../shared/services/auth.service';
import { Router } from '@angular/router';
import { NotificationService } from '../../shared/services/NotificationService';
import { MessageService } from 'primeng/api';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {

  searchText: string = '';
  notifications!: any[];
  userName: string = '';
  x: string = '';
ln: any;
  constructor(private messageService: MessageService,private authService: AuthService , private router: Router,private notificationService: NotificationService) { }

  ngOnInit() {
    const token = localStorage.getItem('token');
    if (token) {
      this.authService.getUserFromToken(token).subscribe(
        (user: any) => {
          this.userName = user.firstName + ' '+ user.lastName; 
        },
        error => {
          console.error('Erreur lors de la récupération de l\'utilisateur à partir du token :', error);
        }
      );
    }

    this.notificationService.getNotifications().subscribe(
      (data) => {
        this.ln=data.length
      }
    );
  }

  onSearchInput(event: Event) {
    this.searchText = (event.target as HTMLInputElement).value;
  }

  isSearchTextEmpty() {
    return this.searchText.length === 0;

}

isDropdownOpen: boolean = false;
isDropdownOpen1: boolean = false;
toggleDropdown(): void {
  this.isDropdownOpen = !this.isDropdownOpen;
}

toggleDropdown1(): void {
  this.isDropdownOpen1 = !this.isDropdownOpen1;
  if (this.isDropdownOpen1) {
    this.loadNotifications();
    
  }

  
}
closeDropdown() {
  this.isDropdownOpen1 = false;
  }


loadNotifications() {
  this.notificationService.getNotifications().subscribe(
    (data) => {
      this.ln=data.length
      if (data.length === 0) {
        this.messageService.add({ severity: 'Contrast', summary: 'Information', detail: "Il n'y pas de modification dans market data " , life: 50000 });
      } else {
        data.forEach(notification => {
          const detail = `<a [routerLink]="'/home/pm/' + ${notification.idPm}">Il y a une modification dans market data au niveau du siren ${notification.siren}</a>`;
          this.messageService.add({ severity: 'Contrast', summary: notification.idPm , detail: ` ${notification.siren}`, life: 50000 });
        });
      }
    },
    (error) => {
      console.error('Failed to load notifications: ', error);
      this.messageService.add({ severity: 'error', summary: 'Erreur', detail: 'Erreur lors du chargement des notifications' , life: 50000 });
    }
  );
}




logout() {
  this.authService.logout().subscribe(
    response => {
      console.log('Déconnexion réussie');
      this.router.navigateByUrl('/login');
    },
    error => {
      console.error('Erreur lors de la déconnexion :', error);
    }
  );
}
}
