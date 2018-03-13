create table Plan(
  PlanID varchar(36),
  PlanName varchar(100),
  Description text,
  UserID varchar(36),
  Public boolean,
  Repeats tinyint unsigned,
  startDate date
)
