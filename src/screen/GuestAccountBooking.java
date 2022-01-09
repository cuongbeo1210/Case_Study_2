package screen;

import IOFile.WriteReadFile;
import model.Bill;
import model.Room;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.DAYS;

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
    private ArrayList<Room> checkInRoom;
    private int roomNumber;
    private int roomNumber1;
    private static final String PATH_ROOMS = "src/data_base/rooms";
    private static final String PATH_ROOMS_BOOKING = "src/data_base/roomsBooking";
    private static final String PATH_BILLS = "src/data_base/bills";
    private static final WriteReadFile<Room> writeReadFile = new WriteReadFile<>();
    private static final WriteReadFile<Bill> writeReadFile1 = new WriteReadFile<>();
    private static final ArrayList<Room> room1 = writeReadFile.readFile(PATH_ROOMS);
    private static final ArrayList<Room> room2 = writeReadFile.readFile(PATH_ROOMS_BOOKING);
    private static final ArrayList<Bill> room3 = writeReadFile1.readFile(PATH_BILLS);
    private ArrayList<Bill> bills;
    private BillView billView = new BillView();

    GuestAccountBooking() {
        super("Hotel");
        this.setContentPane(panelBooking);
        this.setLocationRelativeTo(null);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(570, 250));
        this.pack();
        Bill bill = new Bill();
        rooms = room1;
        bookingRooms = room2;
        bills = room3;
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
                for (int i = 0; i < rooms.size(); i++) {
                    if (i == roomNumber) {
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
                roomNumber1 = listBooking.getSelectedIndex();
                bookButton.setVisible(false);
            }
        });
        checkinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookingRooms.get(roomNumber1).setStatus("Using");
                bill.setTimeStart(LocalDate.now());
                listBookingRoomModel.removeAllElements();
                for (Room room : bookingRooms) {
                    listBookingRoomModel.addElement(room);
                    writeReadFile.writerFile(bookingRooms, PATH_ROOMS_BOOKING);
                }
            }
        });
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Room room = bookingRooms.get(roomNumber1);
                room.setStatus("Đang Trống");
                bill.setTimeEnd(LocalDate.now());
                bill.setPrice(room.getPrice() * (getDaysCountBetweenDates(bill.getTimeStart(), bill.getTimeEnd()) + 1));
                bill.setGuestName("cuongbeo");
                if (writeReadFile1.readFile(PATH_BILLS) == null) {
                    bills = new ArrayList<>();
                    bills.add(bill);
                } else {
                    bills.add(bill);
                    bills = writeReadFile1.readFile(PATH_BILLS);
                }
                writeReadFile1.writerFile(bills, PATH_BILLS);
                rooms.add(room);
                refreshRoom();
                bookingRooms.remove(roomNumber1);
                listBookingRoomModel.removeAllElements();
                for (Room room1 : bookingRooms) {
                    listBookingRoomModel.addElement(room1);
                }
                writeReadFile.writerFile(bookingRooms, PATH_ROOMS_BOOKING);
                writeReadFile.writerFile(rooms, PATH_ROOMS);
                billView.setVisible(true);
            }
        });
    }

    public void refreshRoom() {
        ListRoomModel.removeAllElements();
        for (Room room : rooms) {
            ListRoomModel.addElement(room);
        }
    }

    public void refreshRoomBooking() {
        checkFileRoomsBooking();
        listBookingRoomModel.removeAllElements();
        for (Room room : bookingRooms) {
            listBookingRoomModel.addElement(room);
        }
    }

    public void checkFileRoomsBooking() {
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

    public long getDaysCountBetweenDates(LocalDate dateBefore, LocalDate dateAfter) {
        return DAYS.between(dateBefore, dateAfter);
    }
}
