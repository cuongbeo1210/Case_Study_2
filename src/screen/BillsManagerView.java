package screen;

import IOFile.WriteReadFile;
import account.GuestAccount;
import model.Bill;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BillsManagerView extends JFrame{
    private JLabel labelUserName;
    private JLabel labelTimeStart;
    private JLabel labelTimeOut;
    private JLabel labelPrice;
    private JList listBill;
    private JPanel panelBillManager;
    int billNumber;
    private DefaultListModel ListBillModel;
    private static final String PATH_BILLS = "src/data_base/bills";
    private WriteReadFile<Bill> writeReadFile = new WriteReadFile<>();
    private ArrayList<Bill> bills = writeReadFile.readFile(PATH_BILLS);

    BillsManagerView(){
        super("Bill Management");
        this.setContentPane(this.panelBillManager);
        this.setPreferredSize(new Dimension(500, 300));
        this.setLocationRelativeTo(null);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        ListBillModel = new DefaultListModel<>();
        listBill.setModel(ListBillModel);
        refreshBills();

        listBill.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                billNumber = listBill.getSelectedIndex();
                Bill bill = bills.get(billNumber);
                labelUserName.setText(bill.getGuestName());
                labelTimeStart.setText(bill.getTimeStart().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                labelTimeOut.setText(bill.getTimeEnd().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                labelPrice.setText(String.valueOf(bill.getPrice()));
            }
        });

    }
    public void checkFileBills() {
        if (writeReadFile.readFile(PATH_BILLS) == null) {
            bills = new ArrayList<>();
        } else {
            bills = writeReadFile.readFile(PATH_BILLS);
        }
    }
    public void refreshBills(){
        checkFileBills();
        ListBillModel.removeAllElements();
        for(Bill bill : bills){
            ListBillModel.addElement(bill);
        }
    }
}
