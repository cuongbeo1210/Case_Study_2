package model;

import java.time.LocalDate;

public class Bill {
    private String guestName;
    private LocalDate timeStart;
    private LocalDate timeEnd;

    public Bill(String guestName, LocalDate timeStart, LocalDate timeEnd) {
        this.guestName = guestName;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public Bill() {
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public LocalDate getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDate timeStart) {
        this.timeStart = timeStart;
    }

    public LocalDate getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDate timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "guestName='" + guestName + '\'' +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                '}';
    }
}
