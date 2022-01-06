package model;

import java.io.Serializable;

public class Room implements Serializable {
    private int roomID;
    private int countOfBed;
    private int countOfRestroom;
    private String type;
    private int price;
    private String status;

    public Room(int roomID, int countOfBed, int countOfRestroom, String type, int price, String status) {
        this.roomID = roomID;
        this.countOfBed = countOfBed;
        this.countOfRestroom = countOfRestroom;
        this.type = type;
        this.price = price;
        this.status = status;
    }

    public Room() {
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getCountOfBed() {
        return countOfBed;
    }

    public void setCountOfBed(int countOfBed) {
        this.countOfBed = countOfBed;
    }

    public int getCountOfRestroom() {
        return countOfRestroom;
    }

    public void setCountOfRestroom(int countOfRestroom) {
        this.countOfRestroom = countOfRestroom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Tên Phòng : "+getRoomID()+" ---*--- "+"Giá : "+getPrice()+" ---*--- "+"Trạng Thái : "+getStatus();
    }
}
