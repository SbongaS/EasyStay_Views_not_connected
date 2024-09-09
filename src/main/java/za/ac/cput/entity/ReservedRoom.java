package za.ac.cput.entity;

import jakarta.persistence.*;
import za.ac.cput.entity.enums.RoomType;

import java.util.Objects;

public class ReservedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservedRoomId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Booking booking;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomType roomType;

    @Column(nullable = false)
    private int count;

    protected ReservedRoom() {}

    private ReservedRoom(Builder builder) {
        this.reservedRoomId = builder.reservedRoomId;
        this.booking = builder.booking;
        this.roomType = builder.roomType;
        this.count = builder.count;
    }

    public long getReservedRoomId() {
        return reservedRoomId;
    }

    public Booking getBooking() {
        return booking;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservedRoom that = (ReservedRoom) o;
        return count == that.count && Objects.equals(reservedRoomId, that.reservedRoomId) && Objects.equals(booking, that.booking) && roomType == that.roomType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservedRoomId, booking, roomType, count);
    }

    @Override
    public String toString() {
        return "ReservedRoom{" +
                "id=" + reservedRoomId +
                ", booking=" + booking +
                ", roomType=" + roomType +
                ", count=" + count +
                '}';
    }

    public static class Builder {
        private long reservedRoomId;
        private Booking booking;
        private RoomType roomType;
        private int count;

        public Builder reservedRoomId(long reservedRoomId) {
            this.reservedRoomId = reservedRoomId;
            return this;
        }

        public Builder booking(Booking booking) {

        this.booking = booking;
        return this;
        }

        public Builder roomType(RoomType roomType) {
            this.roomType = roomType;
            return this;
        }
        public Builder count(int count) {
            this.count = count;
            return this;
        }

        public Builder copy(ReservedRoom reservedRoom) {
            this.reservedRoomId = reservedRoom.getReservedRoomId();
            this.booking = reservedRoom.getBooking();
            this.roomType = reservedRoom.getRoomType();
            this.count = reservedRoom.getCount();
            return this;
        }
        public ReservedRoom build() {
            return new ReservedRoom(this);
        }
    }
}
