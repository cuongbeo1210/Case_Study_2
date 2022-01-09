package screen;

import IOFile.WriteReadFile;
import account.GuestAccount;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AccountManagerView extends JFrame{
    private JTextField textName;
    private JTextField textTel;
    private JTextField textDate;
    private JList listAccount;
    private JTextField textAddress;
    private JTextField textEmail;
    private JTextField textUserName;
    private JPasswordField textPassword;
    private JButton buttonFix;
    private JButton buttonDelete;
    private JPanel panelAccount;
    private JLabel labelAlert;
    private ArrayList<GuestAccount> guestAccounts;
    private DefaultListModel ListAccountModel;
    private static final String PATH_GUEST_ACCOUNT = "src/data_base/guestAccount";
    private WriteReadFile<GuestAccount> writeReadFile = new WriteReadFile<>();
    private ArrayList<GuestAccount> guestAccounts1 = writeReadFile.readFile(PATH_GUEST_ACCOUNT);

    AccountManagerView(){
        super("Room Management");
        this.setContentPane(this.panelAccount);
        this.setPreferredSize(new Dimension(500, 300));
        this.setLocationRelativeTo(null);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        ListAccountModel = new DefaultListModel<>();
        guestAccounts = guestAccounts1;
        listAccount.setModel(ListAccountModel);
        refreshAccount();
        listAccount.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int numberAccount = listAccount.getSelectedIndex();
                if (numberAccount >= 0){
                    GuestAccount guestAccount = guestAccounts.get(numberAccount);
                    textName.setText(guestAccount.getGuest_Name());
                    textTel.setText(guestAccount.getGuest_PhoneNumber());
                    textDate.setText(guestAccount.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    textAddress.setText(guestAccount.getAddress());
                    textEmail.setText(guestAccount.getGuest_Email());
                    textUserName.setText(guestAccount.getGuestUserName());
                    textPassword.setText(guestAccount.getGuestPassword());
                }

            }
        });
        buttonFix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberAccount = listAccount.getSelectedIndex();
                if (numberAccount >= 0){
                    GuestAccount guestAccount = guestAccounts.get(numberAccount);
                    guestAccount.setGuestPassword(textPassword.getText());
                    labelAlert.setText("Sửa Thành Công !!!");
                }
            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberAccount = listAccount.getSelectedIndex();
                if (numberAccount >= 0) {
                    guestAccounts.remove(numberAccount);
                    refreshAccount();
                    labelAlert.setText("Xóa Thành Công !!!");
                    writeReadFile.writerFile(guestAccounts, PATH_GUEST_ACCOUNT);
                }
            }
        });
    }
    public void refreshAccount(){
        ListAccountModel.removeAllElements();
        for(GuestAccount guestAccount : guestAccounts){
            ListAccountModel.addElement(guestAccount);
        }
    }
}
