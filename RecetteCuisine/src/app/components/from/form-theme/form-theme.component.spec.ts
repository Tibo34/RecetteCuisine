import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormThemeComponent } from './form-theme.component';

describe('FormThemeComponent', () => {
  let component: FormThemeComponent;
  let fixture: ComponentFixture<FormThemeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormThemeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormThemeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
