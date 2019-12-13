import { TestBed } from '@angular/core/testing';

import { ServerInfoService } from './server-info.service';

describe('TimeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ServerInfoService = TestBed.get(ServerInfoService);
    expect(service).toBeTruthy();
  });
});
