create table PlannedWorkouts(
  PWID varchar(36),
  PlanID varchar(36),
  WorkoutID varchar(36),
  WorkoutDay tinyint unsigned,
  WorkoutDOW enum('Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun')
)
