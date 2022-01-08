package screen;

import IOFile.WriteReadFile;
import account.AdminAccount;
import account.GuestAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Screen extends JFrame {
    private JButton buttonSignIn;
    private JButton buttonSignUp;
    private JPasswordField textPassword;
    private JTextField textUserName;
    private JPanel panelScreen;
    private JLabel loginAlert;
    private static final Screen screen = new Screen();
    private static final GuestAccountView guestAccountView = new GuestAccountView();
    private static final AdminAccount adminAccounts = new AdminAccount();
    private static final GuestAccountBooking guestAccountBooking = new GuestAccountBooking();
    private static final AdminView adminView = new AdminView();
    private static final String PATH_GUEST_ACCOUNT = "src/data_base/guestAccount";
    private static final WriteReadFile<GuestAccount> writeReadFile = new WriteReadFile<>();
    private static final ArrayList<GuestAccount> guestAccounts = writeReadFile.readFile(PATH_GUEST_ACCOUNT);


    Screen(){
        super("Hotel");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - panelScreen.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - panelScreen.getHeight()) / 2);
        this.setLocation(x,y);
        this.setContentPane(this.panelScreen);
        this.setPreferredSize(new Dimension(400, 300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();


        buttonSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guestAccountView.setVisible(true);
            }
        });

        buttonSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login(e);
            }
        });
    }
    public static void main(String[] args) {
        screen.setVisible(true);
    }

    public void Login (ActionEvent e){
        if (textUserName.getText().equals("") || textPassword.getText().equals("")){
            loginAlert.setText("Xin điền đầy đủ !!!");
        } else if (!CheckLogin()){
                loginAlert.setText("Tài Khoản Hoặc Mật Khẩu Không Đúng !!!");

        }
    }

    public boolean CheckLogin(){
        for (AdminAccount a : adminAccounts.getListAdminAccounts()){
            if (a.getAdminAccount().equals(textUserName.getText()) && a.getAdminPassword().equals(textPassword.getText())){
                adminView.setVisible(true);
                screen.setVisible(false);
                return true;
            }
        }
        for (GuestAccount acc : guestAccounts){
            if (acc.getGuestUserName().equals(textUserName.getText()) && acc.getGuestPassword().equals(textPassword.getText())){
                guestAccountBooking.setVisible(true);
                screen.setVisible(false);
                return true;
            }
        }
        return false;
    }
}
