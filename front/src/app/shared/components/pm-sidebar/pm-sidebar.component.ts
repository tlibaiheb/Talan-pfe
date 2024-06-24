import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { modeEnum } from '../../enums/enums';

@Component({
  selector: 'app-pm-sidebar',
  templateUrl: './pm-sidebar.component.html',
  styleUrl: './pm-sidebar.component.css'
})
export class PmSidebarComponent {

  @Input() formulaire!: FormGroup;

  @Input() readonlyMode!:any;

  @Input() idT!:any;

  modeEnum = modeEnum;


  generateRandomFiveDigitId(): string {
    const min = 10000; 
    const max = 99999; 
    return String(Math.floor(Math.random() * (max - min + 1)) + min);
  }


}
