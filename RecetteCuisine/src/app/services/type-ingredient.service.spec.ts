import {TestBed} from '@angular/core/testing';

import {TypeIngredientService} from './type-ingredient.service';

describe('TypeIngredientService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TypeIngredientService = TestBed.get(TypeIngredientService);
    expect(service).toBeTruthy();
  });
});
