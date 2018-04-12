import {Component, Inject, OnInit} from '@angular/core';
import {ExerciseService} from '../exercise/exercise.service';
import {Exercise} from '../exercise/exercise';
import {MAT_DIALOG_DATA, MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-create-exercise',
  templateUrl: './create-exercise.component.html',
  styleUrls: ['./create-exercise.component.css']
})
export class CreateExerciseComponent implements OnInit {

  modalData: any;
  newExercise: Exercise = new Exercise();

  // match categories upto the enums used in the back end
  exerciseCategories = [
    {name: 'Aerobic', value: 'AEROBIC'},
    {name: 'Anaerobic', value: 'ANAEROBIC'},
    {name: 'Strength Training', value: 'STRENGTH_TRAINING'},
    {name: 'Balancing', value: 'BALANCING'},
    {name: 'Default', value: 'DEFAULT'},
  ];

  // match bodyparts upto the enums used in the back end
  bodyParts = [
    {name: 'Arms', value: 'ARMS'},
    {name: 'Legs', value: 'LEGS'},
    {name: 'Back', value: 'BACK'},
    {name: 'Chest', value: 'CHEST'},
    {name: 'Shoulders', value: 'SHOULDERS'},
  ];

  bodypart;
  category;

  constructor(private exerciseService: ExerciseService,
              private snackbar: MatSnackBar,
              @Inject(MAT_DIALOG_DATA)public data: any) {
    this.modalData = data;
  }

  ngOnInit() {
  }

  createExercise(exercise: Exercise): void{

    if (this.bodypart != null && this.category != null && exercise.exerciseName !== ''){

      exercise.bodypart = this.bodypart;
      exercise.exerciseCategory = this.category;

      this.exerciseService.create(exercise).subscribe(
        resp => {
          if (resp !== '0'){
            this.data.parent.getExercises();
            this.data.parent.currentInput = exercise.exerciseName;
            this.data.parent.dialogRef.close();
          }else{
            this.snackbar.open('Something went wrong creating your exercise');
          }
        }
      );

    }else{
      this.snackbar.open('Please enter all required details', 'Dismiss', {duration: 10000});
    }
  }

  cancel(): void{
    this.modalData.parent.dialogRef.close();
  }

}
