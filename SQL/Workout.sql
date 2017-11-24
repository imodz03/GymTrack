create table Workout(
  WorkoutID varchar(36),
  Exercises varchar(36),
  workoutName varchar(100),
  description TEXT,
  category ENUM('Weight Training', 'Cardio'),
  SetsID varchar(36),
  UserID varchar(36),
  public BOOLEAN,
  dateOfWorkout date
)
