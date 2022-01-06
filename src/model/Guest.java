package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Guest implements Serializable {
    private int idGuest;
    private String guest_Name;
    private String guest_PhoneNumber;
    private LocalDate dateOfBirth;
    private String address;
    private String guest_Email;
    private static int idNumber = 1;

    public Guest(String guest_Name, String guest_PhoneNumber, String dateOfBirth, String address, String guest_Email) {
        this.idGuest = idNumber++;
        this.guest_Name = guest_Name;
        this.guest_PhoneNumber = guest_PhoneNumber;
        this.setDateOfBirth(dateOfBirth);
        this.address = address;
        this.guest_Email = guest_Email;
    }
    public Guest(String guest_Name, String guest_PhoneNumber, LocalDate dateOfBirth, String address, String guest_Email) {
        this.idGuest = idNumber++;
        this.guest_Name = guest_Name;
        this.guest_PhoneNumber = guest_PhoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.guest_Email = guest_Email;
    }

    public int getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(int idGuest) {
        this.idGuest = idGuest;
    }

    public String getGuest_Name() {
        return guest_Name;
    }

    public void setGuest_Name(String guest_Name) {
        this.guest_Name = guest_Name;
    }

    public String getGuest_PhoneNumber() {
        return guest_PhoneNumber;
    }

    public void setGuest_PhoneNumber(String guest_PhoneNumber) {
        this.guest_PhoneNumber = guest_PhoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGuest_Email() {
        return guest_Email;
    }

    public void setGuest_Email(String guest_Email) {
        this.guest_Email = guest_Email;
    }

    public static int getIdNumber() {
        return idNumber;
    }

    public static void setIdNumber(int idNumber) {
        Guest.idNumber = idNumber;
    }

    public int getAge() {
        LocalDate today = LocalDate.now();
        Period period = Period.between(this.dateOfBirth, today);
        return period.getYears();
    }


    @Override
    public String toString() {
        return "Guest{" +
                "idGuest=" + idGuest +
                ", guest_Name='" + guest_Name + '\'' +
                ", guest_PhoneNumber=" + guest_PhoneNumber +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", guest_Email='" + guest_Email + '\'' +
                '}';
    }
}
