package screen;
import IOFile.WriteReadFile;
import model.Room;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GuestAccountBooking extends JFrame {
    private JList listRoom;
    private JList listBooking;
    private JButton bookButton;
    private JButton checkinButton;
    private JButton checkoutButton;
    private JPanel panelBooking;
    private ArrayList<Room> rooms;
    private DefaultListModel ListRoomModel;
    private DefaultListModel listBookingRoomModel;
    private ArrayList<Room> bookingRooms;
    private int roomNumber;
    private static final String PATH_ROOMS = "src/data_base/rooms";
    private static final String PATH_ROOMS_BOOKING = "src/data_base/roomsBooking";
    private static final WriteReadFile<Room> writeReadFile = new  WriteReadFile<>();
    private static final ArrayList<Room> room1 = writeReadFile.readFile(PATH_ROOMS);
    private static final ArrayList<Room> room2 = writeReadFile.readFile(PATH_ROOMS_BOOKING);
    GuestAccountBooking(){
        super("Hotel");
        this.setContentPane(panelBooking);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(570, 250));
        this.pack();
        rooms = room1;
//        bookingRooms = new ArrayList<>();
        bookingRooms = room2;
        ListRoomModel = new DefaultListModel<>();
        listBookingRoomModel = new DefaultListModel<>();
        listRoom.setModel(ListRoomModel);
        listBooking.setModel(listBookingRoomModel);
        refreshRoom();
        refreshRoomBooking();

        listRoom.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                roomNumber = listRoom.getSelectedIndex();
                bookButton.setVisible(true);
            }
        });
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < rooms.size(); i++){
                    if (i == roomNumber){
                        bookingRooms.add(rooms.get(i));
                        break;
                    }
                }
                rooms.remove(roomNumber);
                refreshRoom();
                listBookingRoomModel.removeAllElements();
                for (Room room : bookingRooms) {
                    room.setStatus("Booking");
                    listBookingRoomModel.addElement(room);
                    writeReadFile.writerFile(bookingRooms, PATH_ROOMS_BOOKING);
                }
                writeReadFile.writerFile(rooms, PATH_ROOMS);
            }
        });
        listBooking.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                bookButton.setVisible(false);
            }
        });
    }
    public void refreshRoom(){
        ListRoomModel.removeAllElements();
        for(Room room : rooms){
            ListRoomModel.addElement(room);
        }
    }
    public void refreshRoomBooking(){
        checkFileRoomsBooking();
        listBookingRoomModel.removeAllElements();
        for (Room room : bookingRooms){
            listBookingRoomModel.addElement(room);
        }
    }
    public void checkFileRoomsBooking(){
        if (writeReadFile.readFile(PATH_ROOMS_BOOKING) == null) {
            bookingRooms = new ArrayList<>();
        } else {
            bookingRooms = writeReadFile.readFile(PATH_ROOMS_BOOKING);
        }
    }
    public void addRoom(Room room) {
        rooms.add(room);
        refreshRoom();
    }

}
