import { TestBed } from '@angular/core/testing';

import { EtapeService } from './etape.service';

describe('EtapeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EtapeService = TestBed.get(EtapeService);
    expect(service).toBeTruthy();
  });
});
