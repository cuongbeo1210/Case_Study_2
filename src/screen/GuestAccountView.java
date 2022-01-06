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
                    textAlert.setText("Chỉ gồm các số hoặc chữ từ 8 - 16 ký tự");
                } else {
                    if (!checkSameUserName(userName)){
                        textAlert.setText("Trùng tên đăng nhập !!!");
                    } else if (!checkEmailRegex) {
                        textAlert.setText("Sai Định Dạng Email !!!");
                    } else if (!checkPhoneNumberRegex) {
                        textAlert.setText("Sai định dạng số điện thoại !!!");
                    }else {
                        boolean check = guestAccounts.add(guestAccount);
                        if (check) {
                            writeReadFile.writerFile(guestAccounts, PATH_GUEST_ACCOUNT);
                            textAlert.setText("Tạo tài khoản Thành Công !!!");
                        } else {
                            textAlert.setText("Tạo tài khoản thất bại !!!");
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
