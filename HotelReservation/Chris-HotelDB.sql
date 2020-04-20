DROP DATABASE if exists HotelReservation;

CREATE DATABASE HotelReservation;

USE HotelReservation;

Create table Guests (
	GuestsID INT primary key auto_increment,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Address VARCHAR(50) NOT NULL,
    City VARCHAR(30) NOT NULL,
    State CHAR(2) NOT NULL,
    Zip INT NOT NULL,
    Phone VARCHAR(15) NOT NULL
);

Create table Amenities (
	AmenitiesID INT primary key auto_increment,
    AmenTypes VARCHAR(30) NOT NULL,
    Price decimal(4,2) NULL
);

Create table Roomtype (
	RoomTypeID INT primary key auto_increment,
    TypeName VARCHAR(10) NOT NULL,
    StandardOcc INT NOT NULL,
    MaxOcc INT NOT NULL,
    ExtraPerson decimal(2,0),
    BasePrice decimal(5,2) NOT NULL
);

Create table Reservations (
	ReservationsID INT primary key auto_increment,
    GuestsID INT NOT NULL,
    StartDate date NOT NULL,
    EndDate date NOT NULL,
    
    foreign key (GuestsID) references Guests(GuestsID)
);

Create table Rooms (
	RoomsID INT primary key auto_increment,
    RoomNumber INT NOT NULL,
    ADA boolean NOT NULL,
    RoomTypeID INT NOT NULL,
    
    foreign key (RoomTypeID) references RoomType(RoomTypeID)
);

Create table ReservationRooms (
	ReservationRoomsID INT primary key auto_increment,
    ReservationsID INT NOT NULL,
    RoomsID INT NOT NULL,
    Adults INT NOT NULL,
    Children INT NOT NULL,
    
    foreign key (ReservationsID) references Reservations(ReservationsID),
    foreign key (RoomsID) references Rooms(RoomsID)
);

Create table RoomAmenities (
    RoomsID INT NOT NULL,
    AmenitiesID INT NOT NULL,
    
    foreign key (RoomsID) references Rooms(RoomsID),
    foreign key (AmenitiesID) references Amenities(AmenitiesID)
);



						  
		

						  	
						  
                             
							 
                  
                        
                    
                    
                   




