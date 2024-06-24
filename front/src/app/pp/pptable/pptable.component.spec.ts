import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PptableComponent } from './pptable.component';

describe('PptableComponent', () => {
  let component: PptableComponent;
  let fixture: ComponentFixture<PptableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PptableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PptableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
