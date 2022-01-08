package screen;

import IOFile.WriteReadFile;
import account.GuestAccount;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class AccountManagerView extends JFrame{
    private JList listAccount;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton buttonFix;
    private JButton buttonDelete;
    private JPanel panelAccount;
    private DefaultListModel ListAccountModel;
    private static final WriteReadFile<GuestAccount> writeReadFile = new WriteReadFile<>();
    private static final String PATH_GUEST_ACCOUNT = "src/data_base/guestAccount";
    private static final ArrayList<GuestAccount> guestAccounts = writeReadFile.readFile(PATH_GUEST_ACCOUNT);
    private ArrayList<GuestAccount> guestAccounts1 ;



    AccountManagerView(){
        super("Account Management");
        this.setContentPane(this.panelAccount);
        this.setPreferredSize(new Dimension(800, 300));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        ListAccountModel = new DefaultListModel<>();
        guestAccounts1 = guestAccounts;
        listAccount.setModel(ListAccountModel);
        refreshGuestAccounts();
        listAccount.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
//                checkFileGuestAccount();
            }
        });
    }
    public void checkFileGuestAccount() {
        if (writeReadFile.readFile(PATH_GUEST_ACCOUNT) == null) {
            guestAccounts1 = new ArrayList<>();
        } else {
            guestAccounts1 = writeReadFile.readFile(PATH_GUEST_ACCOUNT);
        }
    }
    public void refreshGuestAccounts() {
        ListAccountModel.removeAllElements();
        for (GuestAccount guestAccount : guestAccounts1) {
            ListAccountModel.addElement(guestAccount);
        }
    }
}
