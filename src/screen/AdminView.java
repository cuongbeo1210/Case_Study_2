package screen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminView extends JFrame{
    private JButton quảnLýPhòngButton;
    private JButton quảnLýTàiKhoảnButton;
    private JButton quảnLýHóaĐơnButton;
    private JPanel panelAdmin;
    private RoomManagerView roomManagerView = new RoomManagerView();
    private AccountManagerView accountManagerView = new AccountManagerView();
    private BillsManagerView BillsManagerView = new BillsManagerView();
    AdminView(){
        super("Admin");
        this.setContentPane(this.panelAdmin);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        quảnLýPhòngButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomManagerView.setVisible(true);
            }
        });
        quảnLýTàiKhoảnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accountManagerView.setVisible(true);
            }
        });
        quảnLýHóaĐơnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BillsManagerView.setVisible(true);
            }
        });
    }
}
