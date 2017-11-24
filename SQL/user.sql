create table User(
  UserID varchar(36) not null,
  firstname varchar(50),
  surname varchar(50),
  email varchar(200),
  username varchar(50),
  password char(64),
  bio text,
  photo varchar(200),
  preferences JSON,
  weight Decimal(3,2),
  height Decimal(3,2),
  BMI smallint,
  bodyFatPercentage smallint
);
