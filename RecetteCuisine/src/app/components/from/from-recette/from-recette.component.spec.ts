import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {FromRecetteComponent} from './from-recette.component';

describe('FromRecetteComponent', () => {
  let component: FromRecetteComponent;
  let fixture: ComponentFixture<FromRecetteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [FromRecetteComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FromRecetteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
