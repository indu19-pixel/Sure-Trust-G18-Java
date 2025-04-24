import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Food implements Serializable {
    int itemno;
    int quantity;   
    float price;
    
    Food(int itemno, int quantity) {
        this.itemno = itemno;
        this.quantity = quantity;
        switch(itemno) {
            case 1: price = quantity * 50; break;
            case 2: price = quantity * 60; break;
            case 3: price = quantity * 70; break;
            case 4: price = quantity * 30; break;
        }
    }
}

class Singleroom implements Serializable {
    String name;
    String contact;
    String gender;   
    ArrayList<Food> food = new ArrayList<>();
   
    Singleroom() {
        this.name = "";
    }
    
    Singleroom(String name, String contact, String gender) {
        this.name = name;
        this.contact = contact;
        this.gender = gender;
    }
}

class Doubleroom extends Singleroom implements Serializable { 
    String name2;
    String contact2;
    String gender2;  
    
    Doubleroom() {
        this.name = "";
        this.name2 = "";
    }
    
    Doubleroom(String name, String contact, String gender, String name2, String contact2, String gender2) {
        this.name = name;
        this.contact = contact;
        this.gender = gender;
        this.name2 = name2;
        this.contact2 = contact2;
        this.gender2 = gender2;
    }
}

class NotAvailable extends Exception {
    @Override
    public String toString() {
        return "Not Available!";
    }
}

class Holder implements Serializable {
    Doubleroom luxury_doublerrom[] = new Doubleroom[10]; // Luxury
    Doubleroom deluxe_doublerrom[] = new Doubleroom[20]; // Deluxe
    Singleroom luxury_singleerrom[] = new Singleroom[10]; // Luxury
    Singleroom deluxe_singleerrom[] = new Singleroom[20]; // Deluxe
}

class Hotel {
    static Holder hotel_ob = new Holder();
    
    static void custDetails(int i, int rn, String name, String contact, String gender, String name2, String contact2, String gender2) {
        switch (i) {
            case 1: hotel_ob.luxury_doublerrom[rn] = new Doubleroom(name, contact, gender, name2, contact2, gender2); break;
            case 2: hotel_ob.deluxe_doublerrom[rn] = new Doubleroom(name, contact, gender, name2, contact2, gender2); break;
            case 3: hotel_ob.luxury_singleerrom[rn] = new Singleroom(name, contact, gender); break;
            case 4: hotel_ob.deluxe_singleerrom[rn] = new Singleroom(name, contact, gender); break;
        }
    }
    
    static String getAvailableRooms(int i) {
        StringBuilder sb = new StringBuilder();
        switch (i) {
            case 1:
                sb.append("Available Luxury Double Rooms (1-10): ");
                for (int j = 0; j < hotel_ob.luxury_doublerrom.length; j++) {
                    if (hotel_ob.luxury_doublerrom[j] == null) {
                        sb.append(j+1).append(", ");
                    }
                }
                break;
            case 2:
                sb.append("Available Deluxe Double Rooms (11-30): ");
                for (int j = 0; j < hotel_ob.deluxe_doublerrom.length; j++) {
                    if (hotel_ob.deluxe_doublerrom[j] == null) {
                        sb.append(j+11).append(", ");
                    }
                }
                break;
            case 3:
                sb.append("Available Luxury Single Rooms (31-40): ");
                for (int j = 0; j < hotel_ob.luxury_singleerrom.length; j++) {
                    if (hotel_ob.luxury_singleerrom[j] == null) {
                        sb.append(j+31).append(", ");
                    }
                }
                break;
            case 4:
                sb.append("Available Deluxe Single Rooms (41-60): ");
                for (int j = 0; j < hotel_ob.deluxe_singleerrom.length; j++) {
                    if (hotel_ob.deluxe_singleerrom[j] == null) {
                        sb.append(j+41).append(", ");
                    }
                }
                break;
        }
        return sb.toString();
    }
    
    static boolean bookRoom(int i, int rn, String name, String contact, String gender, String name2, String contact2, String gender2) {
        try {
            switch (i) {
                case 1:
                    if (rn < 1 || rn > 10) return false;
                    if (hotel_ob.luxury_doublerrom[rn-1] != null) return false;
                    custDetails(i, rn-1, name, contact, gender, name2, contact2, gender2);
                    break;
                case 2:
                    if (rn < 11 || rn > 30) return false;
                    if (hotel_ob.deluxe_doublerrom[rn-11] != null) return false;
                    custDetails(i, rn-11, name, contact, gender, name2, contact2, gender2);
                    break;
                case 3:
                    if (rn < 31 || rn > 40) return false;
                    if (hotel_ob.luxury_singleerrom[rn-31] != null) return false;
                    custDetails(i, rn-31, name, contact, gender, "", "", "");
                    break;
                case 4:
                    if (rn < 41 || rn > 60) return false;
                    if (hotel_ob.deluxe_singleerrom[rn-41] != null) return false;
                    custDetails(i, rn-41, name, contact, gender, "", "", "");
                    break;
                default: return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    static String getRoomFeatures(int i) {
        switch (i) {
            case 1: return "Luxury Double Room\nNumber of double beds: 1\nAC: Yes\nFree breakfast: Yes\nCharge per day: 4000";
            case 2: return "Deluxe Double Room\nNumber of double beds: 1\nAC: No\nFree breakfast: Yes\nCharge per day: 3000";
            case 3: return "Luxury Single Room\nNumber of single beds: 1\nAC: Yes\nFree breakfast: Yes\nCharge per day: 2200";
            case 4: return "Deluxe Single Room\nNumber of single beds: 1\nAC: No\nFree breakfast: Yes\nCharge per day: 1200";
            default: return "Invalid room type";
        }
    }
    
    static int getAvailableCount(int i) {
        int count = 0;
        switch (i) {
            case 1:
                for (int j = 0; j < 10; j++) {
                    if (hotel_ob.luxury_doublerrom[j] == null) count++;
                }
                break;
            case 2:
                for (int j = 0; j < hotel_ob.deluxe_doublerrom.length; j++) {
                    if (hotel_ob.deluxe_doublerrom[j] == null) count++;
                }
                break;
            case 3:
                for (int j = 0; j < hotel_ob.luxury_singleerrom.length; j++) {
                    if (hotel_ob.luxury_singleerrom[j] == null) count++;
                }
                break;
            case 4:
                for (int j = 0; j < hotel_ob.deluxe_singleerrom.length; j++) {
                    if (hotel_ob.deluxe_singleerrom[j] == null) count++;
                }
                break;
        }
        return count;
    }
    
    static String generateBill(int rn, int rtype) {
        double amount = 0;
        String[] items = {"Sandwich", "Pasta", "Noodles", "Coke"};
        StringBuilder bill = new StringBuilder();
        bill.append("\n*******\n Bill:\n*******\n");
        
        switch(rtype) {
            case 1:
                amount += 4000;
                bill.append("\nRoom Charge - ").append(4000);
                bill.append("\n\nFood Charges:\n");
                bill.append("Item\t\tQuantity\tPrice\n");
                if (hotel_ob.luxury_doublerrom[rn] != null) {
                    for (Food obb : hotel_ob.luxury_doublerrom[rn].food) {
                        amount += obb.price;
                        bill.append(items[obb.itemno-1]).append("\t\t").append(obb.quantity).append("\t\t").append(obb.price).append("\n");
                    }
                }
                break;
            case 2:
                amount += 3000;
                bill.append("Room Charge - ").append(3000);
                bill.append("\n\nFood Charges:\n");
                bill.append("Item\t\tQuantity\tPrice\n");
                if (hotel_ob.deluxe_doublerrom[rn] != null) {
                    for (Food obb : hotel_ob.deluxe_doublerrom[rn].food) {
                        amount += obb.price;
                        bill.append(items[obb.itemno-1]).append("\t\t").append(obb.quantity).append("\t\t").append(obb.price).append("\n");
                    }
                }
                break;
            case 3:
                amount += 2200;
                bill.append("Room Charge - ").append(2200);
                bill.append("\n\nFood Charges:\n");
                bill.append("Item\t\tQuantity\tPrice\n");
                if (hotel_ob.luxury_singleerrom[rn] != null) {
                    for (Food obb : hotel_ob.luxury_singleerrom[rn].food) {
                        amount += obb.price;
                        bill.append(items[obb.itemno-1]).append("\t\t").append(obb.quantity).append("\t\t").append(obb.price).append("\n");
                    }
                }
                break;
            case 4:
                amount += 1200;
                bill.append("Room Charge - ").append(1200);
                bill.append("\n\nFood Charges:\n");
                bill.append("Item\t\tQuantity\tPrice\n");
                if (hotel_ob.deluxe_singleerrom[rn] != null) {
                    for (Food obb : hotel_ob.deluxe_singleerrom[rn].food) {
                        amount += obb.price;
                        bill.append(items[obb.itemno-1]).append("\t\t").append(obb.quantity).append("\t\t").append(obb.price).append("\n");
                    }
                }
                break;
        }
        bill.append("\nTotal Amount: ").append(amount);
        return bill.toString();
    }
    
    static String deallocate(int rn, int rtype) {
        switch (rtype) {
            case 1:               
                if (hotel_ob.luxury_doublerrom[rn] != null) {
                    String bill = generateBill(rn, rtype);
                    hotel_ob.luxury_doublerrom[rn] = null;
                    return bill + "\nDeallocated successfully";
                } else {
                    return "Room already empty";
                }
            case 2:
                if (hotel_ob.deluxe_doublerrom[rn] != null) {
                    String bill = generateBill(rn, rtype);
                    hotel_ob.deluxe_doublerrom[rn] = null;
                    return bill + "\nDeallocated successfully";
                } else {
                    return "Room already empty";
                }
            case 3:
                if (hotel_ob.luxury_singleerrom[rn] != null) {
                    String bill = generateBill(rn, rtype);
                    hotel_ob.luxury_singleerrom[rn] = null;
                    return bill + "\nDeallocated successfully";
                } else {
                    return "Room already empty";
                }
            case 4:
                if (hotel_ob.deluxe_singleerrom[rn] != null) {
                    String bill = generateBill(rn, rtype);
                    hotel_ob.deluxe_singleerrom[rn] = null;
                    return bill + "\nDeallocated successfully";
                } else {
                    return "Room already empty";
                }
            default: return "Invalid room type";
        }
    }
    
    static boolean orderFood(int rn, int rtype, int item, int quantity) {
        try {
            switch(rtype) {
                case 1: 
                    if (hotel_ob.luxury_doublerrom[rn] == null) return false;
                    hotel_ob.luxury_doublerrom[rn].food.add(new Food(item, quantity));
                    break;
                case 2: 
                    if (hotel_ob.deluxe_doublerrom[rn] == null) return false;
                    hotel_ob.deluxe_doublerrom[rn].food.add(new Food(item, quantity));
                    break;
                case 3: 
                    if (hotel_ob.luxury_singleerrom[rn] == null) return false;
                    hotel_ob.luxury_singleerrom[rn].food.add(new Food(item, quantity));
                    break;
                case 4: 
                    if (hotel_ob.deluxe_singleerrom[rn] == null) return false;
                    hotel_ob.deluxe_singleerrom[rn].food.add(new Food(item, quantity));
                    break;
                default: return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

class WriteThread implements Runnable {
    Holder hotel_ob;
    WriteThread(Holder hotel_ob) {
        this.hotel_ob = hotel_ob;
    }
    
    @Override
    public void run() {
        try {
            FileOutputStream fout = new FileOutputStream("backup");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(hotel_ob);
            oos.close();
            fout.close();
        } catch (Exception e) {
            System.out.println("Error in writing: " + e);
        }
    }
}

public class HotelManagementSystem extends JFrame {
    private JTabbedPane tabbedPane;
    
    public HotelManagementSystem() {
        super("Hotel Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // Load data if backup exists
        try {
            File f = new File("backup");
            if (f.exists()) {
                FileInputStream fin = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fin);
                Hotel.hotel_ob = (Holder) ois.readObject();
                ois.close();
                fin.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        // Create tabs
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Room Details", new RoomDetailsPanel());
        tabbedPane.addTab("Availability", new AvailabilityPanel());
        tabbedPane.addTab("Booking", new BookingPanel());
        tabbedPane.addTab("Order Food", new OrderFoodPanel());
        tabbedPane.addTab("Checkout", new CheckoutPanel());
        
        add(tabbedPane);
        
        // Add shutdown hook to save data
        Runtime.getRuntime().addShutdownHook(new Thread(new WriteThread(Hotel.hotel_ob)));
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HotelManagementSystem system = new HotelManagementSystem();
            system.setVisible(true);
        });
    }
    
    // Panel for Room Details
    class RoomDetailsPanel extends JPanel {
        public RoomDetailsPanel() {
            setLayout(new BorderLayout());
            
            JLabel titleLabel = new JLabel("Room Details", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            add(titleLabel, BorderLayout.NORTH);
            
            JPanel roomPanel = new JPanel(new GridLayout(4, 1, 10, 10));
            roomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            JButton luxuryDoubleBtn = new JButton("Luxury Double Room");
            JButton deluxeDoubleBtn = new JButton("Deluxe Double Room");
            JButton luxurySingleBtn = new JButton("Luxury Single Room");
            JButton deluxeSingleBtn = new JButton("Deluxe Single Room");
            
            luxuryDoubleBtn.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, Hotel.getRoomFeatures(1), "Room Details", JOptionPane.INFORMATION_MESSAGE);
            });
            
            deluxeDoubleBtn.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, Hotel.getRoomFeatures(2), "Room Details", JOptionPane.INFORMATION_MESSAGE);
            });
            
            luxurySingleBtn.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, Hotel.getRoomFeatures(3), "Room Details", JOptionPane.INFORMATION_MESSAGE);
            });
            
            deluxeSingleBtn.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, Hotel.getRoomFeatures(4), "Room Details", JOptionPane.INFORMATION_MESSAGE);
            });
            
            roomPanel.add(luxuryDoubleBtn);
            roomPanel.add(deluxeDoubleBtn);
            roomPanel.add(luxurySingleBtn);
            roomPanel.add(deluxeSingleBtn);
            
            add(roomPanel, BorderLayout.CENTER);
        }
    }
    
    // Panel for Availability
    class AvailabilityPanel extends JPanel {
        public AvailabilityPanel() {
            setLayout(new BorderLayout());
            
            JLabel titleLabel = new JLabel("Room Availability", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            add(titleLabel, BorderLayout.NORTH);
            
            JPanel roomPanel = new JPanel(new GridLayout(4, 1, 10, 10));
            roomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            JButton luxuryDoubleBtn = new JButton("Luxury Double Room");
            JButton deluxeDoubleBtn = new JButton("Deluxe Double Room");
            JButton luxurySingleBtn = new JButton("Luxury Single Room");
            JButton deluxeSingleBtn = new JButton("Deluxe Single Room");
            
            luxuryDoubleBtn.addActionListener(e -> {
                int count = Hotel.getAvailableCount(1);
                JOptionPane.showMessageDialog(this, 
                    "Available Luxury Double Rooms: " + count + "\n" + Hotel.getAvailableRooms(1),
                    "Availability", JOptionPane.INFORMATION_MESSAGE);
            });
            
            deluxeDoubleBtn.addActionListener(e -> {
                int count = Hotel.getAvailableCount(2);
                JOptionPane.showMessageDialog(this, 
                    "Available Deluxe Double Rooms: " + count + "\n" + Hotel.getAvailableRooms(2),
                    "Availability", JOptionPane.INFORMATION_MESSAGE);
            });
            
            luxurySingleBtn.addActionListener(e -> {
                int count = Hotel.getAvailableCount(3);
                JOptionPane.showMessageDialog(this, 
                    "Available Luxury Single Rooms: " + count + "\n" + Hotel.getAvailableRooms(3),
                    "Availability", JOptionPane.INFORMATION_MESSAGE);
            });
            
            deluxeSingleBtn.addActionListener(e -> {
                int count = Hotel.getAvailableCount(4);
                JOptionPane.showMessageDialog(this, 
                    "Available Deluxe Single Rooms: " + count + "\n" + Hotel.getAvailableRooms(4),
                    "Availability", JOptionPane.INFORMATION_MESSAGE);
            });
            
            roomPanel.add(luxuryDoubleBtn);
            roomPanel.add(deluxeDoubleBtn);
            roomPanel.add(luxurySingleBtn);
            roomPanel.add(deluxeSingleBtn);
            
            add(roomPanel, BorderLayout.CENTER);
        }
    }
    
    // Panel for Booking
    class BookingPanel extends JPanel {
        private JComboBox<String> roomTypeCombo;
        private JTextField roomField, nameField, contactField, genderField;
        private JTextField name2Field, contact2Field, gender2Field;
        
        public BookingPanel() {
            setLayout(new BorderLayout());
            
            JLabel titleLabel = new JLabel("Book a Room", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            add(titleLabel, BorderLayout.NORTH);
            
            JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));
            formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            roomTypeCombo = new JComboBox<>(new String[]{
                "Luxury Double Room", "Deluxe Double Room", 
                "Luxury Single Room", "Deluxe Single Room"
            });
            
            roomField = new JTextField();
            nameField = new JTextField();
            contactField = new JTextField();
            genderField = new JTextField();
            name2Field = new JTextField();
            contact2Field = new JTextField();
            gender2Field = new JTextField();
            
            formPanel.add(new JLabel("Room Type:"));
            formPanel.add(roomTypeCombo);
            formPanel.add(new JLabel("Room Number:"));
            formPanel.add(roomField);
            formPanel.add(new JLabel("Primary Guest Name:"));
            formPanel.add(nameField);
            formPanel.add(new JLabel("Primary Guest Contact:"));
            formPanel.add(contactField);
            formPanel.add(new JLabel("Primary Guest Gender:"));
            formPanel.add(genderField);
            formPanel.add(new JLabel("Secondary Guest Name:"));
            formPanel.add(name2Field);
            formPanel.add(new JLabel("Secondary Guest Contact:"));
            formPanel.add(contact2Field);
            formPanel.add(new JLabel("Secondary Guest Gender:"));
            formPanel.add(gender2Field);
            
            JButton bookBtn = new JButton("Book Room");
            bookBtn.addActionListener(e -> bookRoom());
            
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(bookBtn);
            
            add(formPanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);
            
            // Add listener to room type combo to show available rooms
            roomTypeCombo.addActionListener(e -> {
                int selected = roomTypeCombo.getSelectedIndex() + 1;
                JOptionPane.showMessageDialog(this, Hotel.getAvailableRooms(selected), "Available Rooms", JOptionPane.INFORMATION_MESSAGE);
            });
        }
        
        private void bookRoom() {
            try {
                int roomType = roomTypeCombo.getSelectedIndex() + 1;
                int roomNumber = Integer.parseInt(roomField.getText());
                String name = nameField.getText();
                String contact = contactField.getText();
                String gender = genderField.getText();
                String name2 = name2Field.getText();
                String contact2 = contact2Field.getText();
                String gender2 = gender2Field.getText();
                
                if (name.isEmpty() || contact.isEmpty() || gender.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Primary guest details are required", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (roomType == 1 || roomType == 2) {
                    if (name2.isEmpty() || contact2.isEmpty() || gender2.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Secondary guest details are required for double rooms", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                
                boolean success = Hotel.bookRoom(roomType, roomNumber, name, contact, gender, name2, contact2, gender2);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Room booked successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to book room. Room may be already booked or invalid room number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid room number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        private void clearFields() {
            roomField.setText("");
            nameField.setText("");
            contactField.setText("");
            genderField.setText("");
            name2Field.setText("");
            contact2Field.setText("");
            gender2Field.setText("");
        }
    }
    
    // Panel for Ordering Food
    class OrderFoodPanel extends JPanel {
        private JTextField roomField;
        private JComboBox<String> itemCombo;
        private JTextField quantityField;
        
        public OrderFoodPanel() {
            setLayout(new BorderLayout());
            
            JLabel titleLabel = new JLabel("Order Food", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            add(titleLabel, BorderLayout.NORTH);
            
            JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
            formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            roomField = new JTextField();
            itemCombo = new JComboBox<>(new String[]{"Sandwich (Rs.50)", "Pasta (Rs.60)", "Noodles (Rs.70)", "Coke (Rs.30)"});
            quantityField = new JTextField();
            
            formPanel.add(new JLabel("Room Number:"));
            formPanel.add(roomField);
            formPanel.add(new JLabel("Food Item:"));
            formPanel.add(itemCombo);
            formPanel.add(new JLabel("Quantity:"));
            formPanel.add(quantityField);
            
            JButton orderBtn = new JButton("Place Order");
            orderBtn.addActionListener(e -> placeOrder());
            
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(orderBtn);
            
            add(formPanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);
        }
        
        private void placeOrder() {
            try {
                int roomNumber = Integer.parseInt(roomField.getText());
                int item = itemCombo.getSelectedIndex() + 1;
                int quantity = Integer.parseInt(quantityField.getText());
                
                if (quantity <= 0) {
                    JOptionPane.showMessageDialog(this, "Quantity must be positive", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                int rtype;
                int rn;
                
                if (roomNumber > 60) {
                    JOptionPane.showMessageDialog(this, "Room doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (roomNumber > 40) {
                    rtype = 4;
                    rn = roomNumber - 41;
                } else if (roomNumber > 30) {
                    rtype = 3;
                    rn = roomNumber - 31;
                } else if (roomNumber > 10) {
                    rtype = 2;
                    rn = roomNumber - 11;
                } else if (roomNumber > 0) {
                    rtype = 1;
                    rn = roomNumber - 1;
                } else {
                    JOptionPane.showMessageDialog(this, "Room doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                boolean success = Hotel.orderFood(rn, rtype, item, quantity);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Food order placed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    roomField.setText("");
                    quantityField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to place order. Room may not be booked.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // Panel for Checkout
    class CheckoutPanel extends JPanel {
        private JTextField roomField;
        
        public CheckoutPanel() {
            setLayout(new BorderLayout());
            
            JLabel titleLabel = new JLabel("Checkout", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            add(titleLabel, BorderLayout.NORTH);
            
            JPanel formPanel = new JPanel(new GridLayout(1, 2, 10, 10));
            formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            roomField = new JTextField();
            
            formPanel.add(new JLabel("Room Number:"));
            formPanel.add(roomField);
            
            JButton checkoutBtn = new JButton("Checkout");
            checkoutBtn.addActionListener(e -> checkout());
            
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(checkoutBtn);
            
            add(formPanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);
        }
        
        private void checkout() {
            try {
                int roomNumber = Integer.parseInt(roomField.getText());
                
                int rtype;
                int rn;
                
                if (roomNumber > 60) {
                    JOptionPane.showMessageDialog(this, "Room doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (roomNumber > 40) {
                    rtype = 4;
                    rn = roomNumber - 41;
                } else if (roomNumber > 30) {
                    rtype = 3;
                    rn = roomNumber - 31;
                } else if (roomNumber > 10) {
                    rtype = 2;
                    rn = roomNumber - 11;
                } else if (roomNumber > 0) {
                    rtype = 1;
                    rn = roomNumber - 1;
                } else {
                    JOptionPane.showMessageDialog(this, "Room doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                String result = Hotel.deallocate(rn, rtype);
                JOptionPane.showMessageDialog(this, result, "Checkout", JOptionPane.INFORMATION_MESSAGE);
                roomField.setText("");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid room number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}