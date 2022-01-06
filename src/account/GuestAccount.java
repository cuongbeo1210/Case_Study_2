package account;

import model.Guest;

import java.io.Serializable;
import java.time.LocalDate;

public class GuestAccount extends Guest implements Serializable {
    private String guestUserName;
    private String guestPassword;

    public GuestAccount(String guest_Name, String guest_PhoneNumber, String dateOfBirth, String address, String guest_Email, String guestUserName, String guestPassword) {
        super(guest_Name, guest_PhoneNumber, dateOfBirth, address, guest_Email);
        this.guestUserName = guestUserName;
        this.guestPassword = guestPassword;
    }

    public GuestAccount(String guest_Name, String guest_PhoneNumber, LocalDate dateOfBirth, String address, String guest_Email, String guestUserName, String guestPassword) {
        super(guest_Name, guest_PhoneNumber, dateOfBirth, address, guest_Email);
        this.guestUserName = guestUserName;
        this.guestPassword = guestPassword;
    }


    public String getGuestUserName() {
        return guestUserName;
    }

    public void setGuestUserName(String guestUserName) {
        this.guestUserName = guestUserName;
    }

    public String getGuestPassword() {
        return guestPassword;
    }

    public void setGuestPassword(String guestPassword) {
        this.guestPassword = guestPassword;
    }


    @Override
    public String toString() {
        return "GuestAccount{ "+super.toString() +
                " guestUserName='" + guestUserName + '\'' +
                ", guestPassword='" + guestPassword + '\'' +
                '}';
    }
}
