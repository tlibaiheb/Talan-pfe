import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifassiteComponent } from './modifassite.component';

describe('ModifassiteComponent', () => {
  let component: ModifassiteComponent;
  let fixture: ComponentFixture<ModifassiteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ModifassiteComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ModifassiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
