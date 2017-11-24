create table ExerciseLibrary(
  ExerciseLibraryID varchar(36),
  exerciseName varchar(50),
  description TEXT,
  category ENUM('Aerobic', 'Anaerobic', 'Strength Training', 'Stretching', 'Balancing'),
  bodypart ENUM('Arms', 'Legs', 'Back', 'Chest', 'Shoulders')
);
/*needs expanding, potentially not enum?*/
