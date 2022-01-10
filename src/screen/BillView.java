package screen;

import IOFile.WriteReadFile;
import model.Bill;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class BillView extends JFrame{
    private JLabel labelPrice;
    private JPanel panelBillView;
    private static final String PATH_BILLS = "src/data_base/bills";
    private static final WriteReadFile<Bill> writeReadFile1 = new WriteReadFile<>();
    private  ArrayList<Bill> bills;

    BillView(){
        super("Bill");
        this.setContentPane(panelBillView);
        this.setLocationRelativeTo(null);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500, 250));
        this.pack();
//            checkFileBills();
//            if (bills.size() >= 0){
//                labelGuestName.setText(bills.get(0).getGuestName());
//                labelTimeIn.setText(String.valueOf(bills.get(0).getTimeStart()));
//                labelTimeOut.setText(String.valueOf(bills.get(0).getTimeEnd()));
//                labelPrice.setText(String.valueOf(bills.get(0).getPrice()));
//            }


    }
    public void checkFileBills() {
        if (writeReadFile1.readFile(PATH_BILLS) == null) {
            bills = new ArrayList<>();
        } else {
            bills = writeReadFile1.readFile(PATH_BILLS);
        }
    }
}
