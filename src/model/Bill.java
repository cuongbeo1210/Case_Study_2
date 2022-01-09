package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Bill implements Serializable {
    private String guestName;
    private LocalDate timeStart;
    private LocalDate timeEnd;
    private Long price;

    public Bill(String guestName, LocalDate timeStart, LocalDate timeEnd, Long price) {
        this.guestName = guestName;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.price = price;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Bill nè ^^";
    }
}
