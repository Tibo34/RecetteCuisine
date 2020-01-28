import {TestBed} from '@angular/core/testing';

import {UniteService} from './unite.service';

describe('UniteService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UniteService = TestBed.get(UniteService);
    expect(service).toBeTruthy();
  });
});
