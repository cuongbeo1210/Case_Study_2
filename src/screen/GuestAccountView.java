package screen;

import IOFile.WriteReadFile;
import account.GuestAccount;
import regex.RegexEmail;
import regex.RegexPhoneNumber;
import regex.RegexUsernamePassword;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class GuestAccountView extends JFrame {
    private JTextField textName;
    private JTextField textPhoneNumber;
    private JTextField textEmail;
    private JTextField textDateOfBirth;
    private JTextField textAddress;
    private JTextField textUserName;
    private JTextField textPassword;
    private JButton buttonSignUp;
    private JButton cancelButton;
    private JPanel panelMain;
    private JLabel textAlert;
    private static final WriteReadFile<GuestAccount> writeReadFile = new WriteReadFile<>();
    private static final String PATH_GUEST_ACCOUNT = "src/data_base/guestAccount";
    private ArrayList<GuestAccount> guestAccounts;

    GuestAccountView() {
        super("Register");
        this.setContentPane(panelMain);
        this.setLocationRelativeTo(null);
        this.setPreferredSize(new Dimension(300, 500));
        this.pack();

        buttonSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkFileGuestAccount();
                int age;
                String name = textName.getText();
                String phoneNumber = textPhoneNumber.getText();
                String dateOfBirth = textDateOfBirth.getText();
                String address = textAddress.getText();
                String email = textEmail.getText();
                String userName = textUserName.getText();
                String password = textPassword.getText();
                GuestAccount guestAccount = new GuestAccount(name, phoneNumber, dateOfBirth, address, email, userName, password);
                boolean checkEmailRegex = RegexEmail.validate(email);
                boolean checkUsernameRegex = RegexUsernamePassword.validateUserName(userName);
                boolean checkPasswordRegex = RegexUsernamePassword.validatePassword(password);
                boolean checkPhoneNumberRegex = RegexPhoneNumber.validate(phoneNumber);
                if (!checkUsernameRegex || !checkPasswordRegex) {
                    textAlert.setText("Ch??? g???m c??c s??? ho???c ch??? t??? 8 - 16 k?? t???");
                } else {
                    if (!checkSameUserName(userName)){
                        textAlert.setText("Tr??ng t??n ????ng nh???p !!!");
                    } else if (!checkEmailRegex) {
                        textAlert.setText("Sai ?????nh D???ng Email !!!");
                    } else if (!checkPhoneNumberRegex) {
                        textAlert.setText("Sai ?????nh d???ng s??? ??i???n tho???i !!!");
                    }else {
                        boolean check = guestAccounts.add(guestAccount);
                        if (check) {
                            writeReadFile.writerFile(guestAccounts, PATH_GUEST_ACCOUNT);
                            textAlert.setText("T???o t??i kho???n Th??nh C??ng !!!");
                        } else {
                            textAlert.setText("T???o t??i kho???n th???t b???i !!!");
                        }
                    }
                }
            }
        });

    }

    public void checkFileGuestAccount() {
        if (writeReadFile.readFile(PATH_GUEST_ACCOUNT) == null) {
            guestAccounts = new ArrayList<>();
        } else {
            guestAccounts = writeReadFile.readFile(PATH_GUEST_ACCOUNT);
        }
    }

    public boolean checkSameUserName(String userName) {
        for (GuestAccount guestAccount : guestAccounts) {
            if (guestAccount.getGuestUserName().equals(userName)) {
                return false;
            }
        }
        return true;
    }

}
