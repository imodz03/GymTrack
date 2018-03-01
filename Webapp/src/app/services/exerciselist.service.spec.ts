import { TestBed, inject } from '@angular/core/testing';

import { ExerciselistService } from './exerciselist.service';

describe('ExerciselistService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ExerciselistService]
    });
  });

  it('should be created', inject([ExerciselistService], (service: ExerciselistService) => {
    expect(service).toBeTruthy();
  }));
});
