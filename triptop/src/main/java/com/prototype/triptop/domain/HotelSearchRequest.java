package com.prototype.triptop.domain;

public class HotelSearchRequest {
        private String destinationId;
        private int adults;
        private int children;
        private int rooms;

        public HotelSearchRequest(String destinationId, int adults, int children, int rooms) {
            this.destinationId = destinationId;
            this.adults = adults;
            this.children = children;
            this.rooms = rooms;
        }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }
}
