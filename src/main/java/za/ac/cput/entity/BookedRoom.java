package za.ac.cput.entity;

import jakarta.persistence.*;
import za.ac.cput.entity.enums.RoomType;

import java.util.Objects;

@Entity
public class BookedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookedRoomId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Booking booking;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomType roomType;

    @Column(nullable = false)
    private int count;

    public Long getBookedRoomId() {
        return bookedRoomId;
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

    protected BookedRoom() {}

    private BookedRoom(Builder builder) {
        this.bookedRoomId = builder.bookedRoomId;
        this.booking = builder.booking;
        this.roomType = builder.roomType;
        this.count = builder.count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookedRoom that = (BookedRoom) o;
        return count == that.count && Objects.equals(bookedRoomId, that.bookedRoomId) && Objects.equals(booking, that.booking) && roomType == that.roomType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookedRoomId, booking, roomType, count);
    }

    @Override
    public String toString() {
        return "BookedRoom{" +
                "bookedRoomId=" + bookedRoomId +
                ", booking=" + booking +
                ", roomType=" + roomType +
                ", count=" + count +
                '}';
    }

    public static class Builder {
        private long bookedRoomId;
        private Booking booking;
        private RoomType roomType;
        private int count;

        public Builder setBookedRoomId(long bookedRoomId) {
            this.bookedRoomId = bookedRoomId;
            return this;
        }

        public Builder setBooking(Booking booking) {
            this.booking = booking;
            return this;
        }

        public Builder setRoomType(RoomType roomType) {
            this.roomType = roomType;
            return this;
        }

        public Builder setCount(int count) {
            this.count = count;
            return this;
        }

        public Builder copy(BookedRoom bookedRoom) {
            this.bookedRoomId = bookedRoom.getBookedRoomId();
            this.booking = bookedRoom.getBooking();
            this.roomType = bookedRoom.getRoomType();
            this.count = bookedRoom.getCount();
            return this;
        }

        public BookedRoom build() {
            return new BookedRoom(this);
        }
    }
}
