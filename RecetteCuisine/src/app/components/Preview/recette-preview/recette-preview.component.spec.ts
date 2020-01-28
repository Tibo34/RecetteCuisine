import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecettePreviewComponent } from './recette-preview.component';

describe('RecettePreviewComponent', () => {
  let component: RecettePreviewComponent;
  let fixture: ComponentFixture<RecettePreviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecettePreviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecettePreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
