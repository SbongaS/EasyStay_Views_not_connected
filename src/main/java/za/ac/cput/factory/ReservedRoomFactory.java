package za.ac.cput.factory;

import za.ac.cput.entity.Booking;
import za.ac.cput.entity.ReservedRoom;
import za.ac.cput.entity.enums.RoomType;

public class ReservedRoomFactory {

    // Factory method to create a ReservedRoom with all details
    public static ReservedRoom buildReservedRoom(long reservedRoomId, Booking booking, RoomType roomType, int count) {
        if (reservedRoomId < 0 || booking == null || roomType == null || count <= 0) {
            return null;
        }
        return new ReservedRoom.Builder()
                .reservedRoomId(reservedRoomId)
                .booking(booking)
                .roomType(roomType)
                .count(count)
                .build();
    }

    // Factory method to create a ReservedRoom without an ID
    public static ReservedRoom buildReservedRoomWithoutId(Booking booking, RoomType roomType, int count) {
        if (booking == null || roomType == null || count <= 0) {
            return null;
        }
        return new ReservedRoom.Builder()
                .booking(booking)
                .roomType(roomType)
                .count(count)
                .build();
    }
}
