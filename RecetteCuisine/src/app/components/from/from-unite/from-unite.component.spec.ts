import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {FromUniteComponent} from './from-unite.component';

describe('FromUniteComponent', () => {
  let component: FromUniteComponent;
  let fixture: ComponentFixture<FromUniteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [FromUniteComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FromUniteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
