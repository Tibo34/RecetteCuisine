import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {FormTypeIngredientComponent} from './form-type-ingredient.component';

describe('FormTypeIngredientComponent', () => {
  let component: FormTypeIngredientComponent;
  let fixture: ComponentFixture<FormTypeIngredientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [FormTypeIngredientComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormTypeIngredientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
