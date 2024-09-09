package za.ac.cput.factory;

import za.ac.cput.entity.Room;
import za.ac.cput.entity.enums.RoomType;

public class RoomFactory {

    // Factory method to create a Room with all details
    public static Room buildRoom(long roomId, long roomNumber, double pricePerNight, int roomCount,
                                 RoomType roomType) {
        if (roomId < 0 || roomNumber < 0 || pricePerNight <= 0 || roomCount <= 0 || roomType == null ) {
            return null;
        }
        return new Room.Builder()
                .setRoomId(roomId)
                .setRoomNumber(roomNumber)
                .setPricePerNight(pricePerNight)
                .setRoomCount(roomCount)
                .setRoomType(roomType)
                .build();
    }

    // Factory method to create a Room without an ID
    public static Room buildRoomWithoutId(long roomNumber, double pricePerNight, int roomCount,
                                          RoomType roomType) {
        if (roomNumber < 0 || pricePerNight <= 0 || roomCount <= 0 || roomType == null ) {
            return null;
        }
        return new Room.Builder()
                .setRoomNumber(roomNumber)
                .setPricePerNight(pricePerNight)
                .setRoomCount(roomCount)
                .setRoomType(roomType)
                .build();
    }
}
