DROP DATABASE IF EXISTS GuessTheNumber;

CREATE DATABASE GuessTheNumber;

USE GuessTheNumber;

CREATE TABLE Games (
	GamesId INT PRIMARY KEY auto_increment,
	targetNumber INT NOT NULL,
    isComplete boolean NOT NULL
);

CREATE TABLE Rounds (
	RoundsId INT PRIMARY KEY auto_increment,
    TimeOfGuess datetime,
    Guess INT NOT NULL,
    ExactGuess INT NOT NULL,
    PartialGuess INT NOT NULL,
    GamesId INT NOT NULL,
    
    foreign key (GamesId) references Games(GamesId)
);



			