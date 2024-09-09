package za.ac.cput.entity;

import jakarta.persistence.*;
import za.ac.cput.entity.enums.RoomType;

import java.util.Objects;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roomId;
    private long roomNumber;
    private double pricePerNight;
    private int roomCount;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;


    protected Room() {}

    private Room(Builder builder) {
        this.roomId = builder.roomId;
        this.roomNumber = builder.roomNumber;
        this.pricePerNight = builder.pricePerNight;
        this.roomType = builder.roomType;
        this.roomCount = builder.roomCount;
    }

    public long getRoomId() {
        return roomId;
    }

    public long getRoomNumber() {
        return roomNumber;
    }
    public double getPricePerNight() {
        return pricePerNight;
    }
    public int getRoomCount() {
        return roomCount;
    }
    public RoomType getRoomType() {
        return roomType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomId == room.roomId && roomNumber == room.roomNumber && Double.compare(pricePerNight, room.pricePerNight) == 0 && roomCount == room.roomCount && roomType == room.roomType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, roomNumber, pricePerNight, roomCount, roomType);
    }

    @Override
    public String toString() {
        return "Room{" +
                "RoomId=" + roomId +
                ", roomNumber=" + roomNumber +
                ", pricePerNight=" + pricePerNight +
                ", roomCount=" + roomCount +
                ", roomType=" + roomType +
                '}';
    }

    public static class Builder {
        private long roomId;
        private long roomNumber;
        private double pricePerNight;
        private int roomCount;
        private RoomType roomType;

        public Builder setRoomId(long roomId) {
            this.roomId = roomId;
            return this;
        }
        public Builder setRoomNumber(long roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public Builder setPricePerNight(double pricePerNight) {
            this.pricePerNight = pricePerNight;
            return this;
        }

        public Builder setRoomCount(int roomCount) {
            this.roomCount = roomCount;
            return this;
        }

        public Builder setRoomType(RoomType roomType) {
            this.roomType = roomType;
            return this;
        }

        public Builder copy(Room room) {
            this.roomId = room.roomId;
            this.roomNumber = room.roomNumber;
            this.pricePerNight = room.pricePerNight;
            this.roomCount = room.roomCount;
            this.roomType = room.roomType;
            return this;
        }

        public Room build() {
            return new Room(this);
        }
    }
}
