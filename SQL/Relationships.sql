/*Exercise list foreign keys*/
Alter table ExerciseList add foreign key (ExerciseID) references ExerciseLibrary(ExerciseLibraryID);

/*Sets foreign keys*/
Alter table Sets add foreign key(ExerciseID) references ExerciseLibrary(ExerciseLibraryID);

/*Logs foreign keys*/
Alter table Logs add foreign key(SetID) references Sets(SetsID);
Alter table Logs add foreign key(WorkoutID) references Workout(WorkoutID);
Alter table Logs add foreign key(UserID) references User(UserID);

/*Workout foreign keys*/
Alter table Workout add foreign key(SetsID) references Sets(SetsID);
Alter table Workout add foreign key(UserID) references User(UserID);

/*Goal foreign keys*/
Alter table Goal add foreign key(SetID) references Sets(SetsID);
Alter table Goal add foreign key(UserID) references User(UserID);

/*Planned workouts foreign keys*/
Alter table PlannedWorkouts add foreign key(PlanID) references Plan(PlanID);
Alter table PlannedWorkouts add foreign key(WorkoutID) references Workout(WorkoutID);

/*Plan foreign keys*/
Alter table Plan add foreign key(UserID) references User(UserID);
