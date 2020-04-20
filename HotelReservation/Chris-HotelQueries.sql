USE hotelreservation;
/*
1. Write a query that returns a list of reservations 
that end in July 2023, including the name of the 
guest, the room number(s), and the reservation dates.
*/
SELECT g.FirstName, rm.RoomNumber, r.StartDate, r.EndDate
FROM Reservations r
INNER JOIN ReservationRooms rr ON r.ReservationsId = rr.ReservationsId
INNER JOIN Rooms rm ON rr.RoomsID = rm.RoomsId
INNER JOIN Guests g ON r.GuestsID = g.GuestsId
WHERE r.EndDate >= '2023/07/01' AND r.StartDate < '2023/07/31';
/*
2. Write a query that returns a list of all reservations 
for rooms with a jacuzzi, displaying the guest's name, 
the room number, and the dates of the reservation.
*/
SELECT g.FirstName, rm.RoomNumber, r.StartDate, r.EndDate
FROM Reservations r
INNER JOIN ReservationRooms rr ON r.ReservationsId = rr.ReservationsId
INNER JOIN Rooms rm ON rr.RoomsID = rm.RoomsId
INNER JOIN roomamenities ra ON rm.RoomsId = ra.RoomsId
INNER JOIN amenities a ON ra.AmenitiesID = a.AmenitiesID
INNER JOIN guests g on r.GuestsID = g.GuestsID
WHERE a.AmenTypes = 'Jacuzzi';
/*
3. Write a query that returns all the rooms reserved for a specific guest, 
including the guest's name, the room(s) reserved, 
the starting date of the reservation, and how many people were included in the reservation. 
(Choose a guest's name from the existing data.)
*/
SELECT g.FirstName, rm.RoomNumber, r.StartDate, rr.Adults, rr.Children
FROM Reservations r
INNER JOIN ReservationRooms rr ON r.ReservationsId = rr.ReservationsId
INNER JOIN Rooms rm ON rr.RoomsID = rm.RoomsId
INNER JOIN guests g on r.GuestsID = g.GuestsID
WHERE g.FirstName = 'Mark';
/*
4. Write a query that returns a list of rooms, reservation ID, and per-room cost for each reservation.
The results should include all rooms, whether or not there is a reservation associated with the room.
*/

SELECT rm.ReservationsId, r.RoomsID, r.RoomNumber, (SUM(Price)+BasePrice) as Total 
FROM Rooms r
JOIN RoomType rt ON r.RoomTypeID = rt.RoomTypeID
JOIN RoomAmenities ra ON r.RoomsId = ra.RoomsId
JOIN Amenities a ON ra.AmenitiesId = a.AmenitiesId
LEFT JOIN ReservationRooms rm ON r.RoomsId = rm.RoomsId
Group By r.RoomNumber, rm.ReservationsId;

/*
5. Write a query that returns all the rooms accommodating at 
least three guests and that are reserved on any date in April 2023.
*/

SELECT r.RoomNumber, rt.MaxOcc, (StartDate <= '2023/04/30') StartDate, (EndDate > '2023/04/01') EndDate, (Adults + Children) RoomOcc
FROM Rooms r
LEFT JOIN ReservationRooms rr ON rr.RoomsId = r.RoomsId
LEFT JOIN Reservations rv ON rv.ReservationsId = rr.ReservationsId
LEFT JOIN RoomType rt ON rt.RoomTypeId = r.RoomTypeId
WHERE MaxOcc >= 3 AND StartDate <= '2023/04/30' AND EndDate > '2023/04/01';

/*
6. Write a query that returns a list of all guest names and the number of reservations per guest, 
sorted starting with the guest with the most reservations and then by the guest's last name.
*/
SELECT gu.FirstName, gu.LastName, rc.Reservations
FROM
(SELECT g.GuestsId, COUNT(*) Reservations
FROM Reservations r
INNER JOIN Guests g ON r.GuestsID = g.GuestsID
Group By GuestsID) as rc
INNER JOIN Guests gu ON rc.GuestsID = gu.GuestsID
Order By rc.Reservations DESC;

/*
7. Write a query that displays the name, address, and 
phone number of a guest based on their phone number. 
(Choose a phone number from the existing data.)
*/
SELECT g.FirstName, g.Address, g.Phone
FROM Guests g
WHERE g.Phone = '(814) 485-2615';