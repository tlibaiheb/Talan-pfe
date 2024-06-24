import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PpSidebarComponent } from './pp-sidebar.component';

describe('PpSidebarComponent', () => {
  let component: PpSidebarComponent;
  let fixture: ComponentFixture<PpSidebarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PpSidebarComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PpSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
