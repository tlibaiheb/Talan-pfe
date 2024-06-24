import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PmtableComponent } from './pmtable.component';

describe('PmtableComponent', () => {
  let component: PmtableComponent;
  let fixture: ComponentFixture<PmtableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PmtableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PmtableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
