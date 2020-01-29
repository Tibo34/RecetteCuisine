import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TypeingredientComponent } from './typeingredient.component';

describe('TypeingredientComponent', () => {
  let component: TypeingredientComponent;
  let fixture: ComponentFixture<TypeingredientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TypeingredientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TypeingredientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
