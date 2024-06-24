import { Component, Input } from '@angular/core';
import { modeEnum } from '../../enums/enums';

@Component({
  selector: 'app-pp-sidebar',
  templateUrl: './pp-sidebar.component.html',
  styleUrl: './pp-sidebar.component.css'
})
export class PpSidebarComponent {
  @Input() name: string = '--- ---'; // Modifier le type selon vos besoins
  @Input() idTiers: any;
  @Input() address: string = '...';
  @Input() isClient: boolean = false;
}
