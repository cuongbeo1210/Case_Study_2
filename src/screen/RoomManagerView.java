package screen;

import IOFile.WriteReadFile;
import model.Room;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RoomManagerView extends JFrame {
    private JList listRoom;
    private JTextField textName;
    private JTextField textBed;
    private JTextField textBath;
    private JTextField textType;
    private JTextField textPrice;
    private JTextField textStatus;
    private JButton buttonAdd;
    private JButton buttonFix;
    private JButton buttonDelete;
    private JPanel panelRoom;
    private JLabel labelAlert;
    private JList listBooking;
    private DefaultListModel ListRoomModel;
    private DefaultListModel ListBookingModel;
    private ArrayList<Room> rooms;
    private ArrayList<Room> roomsBooking;
    private int roomNumber;
    private static final String PATH_ROOMS = "src/data_base/rooms";
    private static final String PATH_ROOMS_BOOKING = "src/data_base/roomsBooking";
    private static final WriteReadFile<Room> writeReadFile = new WriteReadFile<>();
    private static final ArrayList<Room> room1 = writeReadFile.readFile(PATH_ROOMS);
    private static final ArrayList<Room> room2 = writeReadFile.readFile(PATH_ROOMS_BOOKING);

    RoomManagerView() {
        super("Room Management");
        this.setContentPane(this.panelRoom);
        this.setPreferredSize(new Dimension(800, 300));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        rooms = room1;
        roomsBooking = room2;
        ListRoomModel = new DefaultListModel<>();
        ListBookingModel = new DefaultListModel<>();
        listRoom.setModel(ListRoomModel);
        listBooking.setModel(ListBookingModel);
        refreshRoom();
        refreshRoomBooking();
        listRoom.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                labelAlert.setText("Hello");
                roomNumber = listRoom.getSelectedIndex();
                if (roomNumber >= 0) {
                    Room room = rooms.get(roomNumber);
                    textName.setText(String.valueOf(room.getRoomID()));
                    textBed.setText(String.valueOf(room.getCountOfBed()));
                    textBath.setText(String.valueOf(room.getCountOfRestroom()));
                    textType.setText(room.getType());
                    textPrice.setText(String.valueOf(room.getPrice()));
                    textStatus.setText(room.getStatus());
                }
            }
        });

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkFileRooms();
                if (textName.getText().equals("") || textBed.getText().equals("") ||
                        textBath.getText().equals("") || textType.getText().equals("") || textPrice.getText().equals("") || textStatus.getText().equals("")) {
                    labelAlert.setText("Mời Điền Đầy Đủ Thông Tin !!!");
                } else {
                    if (!checkSameName(Integer.parseInt(textName.getText())) ||
                            !checkSameNameBooking(Integer.parseInt(textName.getText()))){
                        labelAlert.setText("Trùng Tên Phòng !!!");
                    } else {
                        int name = Integer.parseInt(textName.getText());
                        int bed = Integer.parseInt(textBed.getText());
                        int bath = Integer.parseInt(textBath.getText());
                        String type = textType.getText();
                        int price = Integer.parseInt(textPrice.getText());
                        String status = textStatus.getText();
                        Room room = new Room(name, bed, bath, type, price, status);
                        rooms.add(room);
                        writeReadFile.writerFile(rooms, PATH_ROOMS);
                        labelAlert.setText("Thêm Phòng Thành Công !!!");
                    }
                }
                refreshRoom();
                refreshRoomBooking();
            }
        });
        buttonFix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int roomNumber = listRoom.getSelectedIndex();
                if (roomNumber >= 0) {
                    Room room = rooms.get(roomNumber);
                    room.setRoomID(Integer.parseInt(textName.getText()));
                    room.setCountOfBed(Integer.parseInt(textBed.getText()));
                    room.setCountOfRestroom(Integer.parseInt(textBath.getText()));
                    room.setType(textType.getText());
                    room.setPrice(Integer.parseInt(textPrice.getText()));
                    room.setStatus(textStatus.getText());
                    if (!checkSameName(Integer.parseInt(textName.getText()))) {
                        labelAlert.setText("Trùng Tên Phòng");
                    } else {
                        rooms.remove(roomNumber);
                        rooms.add(roomNumber, room);
                        refreshRoom();
                        labelAlert.setText("Sửa Thành Công !!!");
                        writeReadFile.writerFile(rooms, PATH_ROOMS);
                        checkFileRooms();
                    }
                }
            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int roomNumber = listRoom.getSelectedIndex();
                if (roomNumber >= 0) {
                    rooms.remove(roomNumber);
                    refreshRoom();
                    labelAlert.setText("Xóa Thành Công !!!");
                    writeReadFile.writerFile(rooms, PATH_ROOMS);
                }
            }
        });
        listBooking.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });
        listBooking.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });
    }

    public void checkFileRooms() {
        if (writeReadFile.readFile(PATH_ROOMS) == null) {
            rooms = new ArrayList<>();
        } else {
            rooms = writeReadFile.readFile(PATH_ROOMS);
        }
    }

    public void checkFileRoomsBooking() {
        if (writeReadFile.readFile(PATH_ROOMS_BOOKING) == null) {
            roomsBooking = new ArrayList<>();
        } else {
            roomsBooking = writeReadFile.readFile(PATH_ROOMS_BOOKING);
        }
    }

    public boolean checkSameName(int IDRoom) {
        for (Room room : rooms) {
            if (room.getRoomID() == IDRoom) {
                return false;
            }
        }
        return true;
    }

    public boolean checkSameNameBooking (int IDRoom){
        for (Room bookingRoom : roomsBooking){
            if (bookingRoom.getRoomID() == IDRoom){
                return false;
            }
        }
        return true;
    }

    public void refreshRoom() {
        ListRoomModel.removeAllElements();
        for (Room room : rooms) {
            ListRoomModel.addElement(room);
        }
    }

    public void refreshRoomBooking() {
        checkFileRoomsBooking();
        ListBookingModel.removeAllElements();
        for (Room room : roomsBooking) {
            ListBookingModel.addElement(room);
        }
    }

    public void addRoom(Room room) {
        rooms.add(room);
        refreshRoom();
    }
}
