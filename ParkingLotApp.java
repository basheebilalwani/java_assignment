import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParkingLotApp extends JFrame {
    private static final int STUDENT_SLOTS = 54;
    private static final int FACULTY_SLOTS = 36;
    private static final int EMERGENCY_SLOTS = 3;
    private static final int VIP_SLOTS = 7;

    private String[] studentSlots = new String[STUDENT_SLOTS];
    private String[] facultySlots = new String[FACULTY_SLOTS];
    private String[] emergencySlots = new String[EMERGENCY_SLOTS];
    private String[] vipSlots = new String[VIP_SLOTS];

    private JLabel studentStatusLabel, facultyStatusLabel, emergencyStatusLabel, vipStatusLabel;
    private JPanel mainPanel;

    public ParkingLotApp() {
        setTitle("Smart Parking App");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set Layout for the main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 2, 15, 15));
        mainPanel.setBackground(new Color(240, 240, 240));  // Light grey background

        // Create buttons with custom icons and hover effects
        JButton studentButton = createButton("Park as Student", null); // Change null with image path if needed
        JButton facultyButton = createButton("Park as Faculty", null); // Change null with image path if needed
        JButton emergencyButton = createButton("Park as Emergency", null); // Change null with image path if needed
        JButton vipButton = createButton("Park as VIP", null); // Change null with image path if needed
        JButton exitButton = createExitButton();
		JButton viewStatusButton = createViewStatusButton(); // New button to view parking status
		JButton searchButton = createSearchButton(); 

        // Status labels with improved styling
        studentStatusLabel = createStatusLabel("Student Slots Available: " + getAvailableSlots(studentSlots));
        facultyStatusLabel = createStatusLabel("Faculty Slots Available: " + getAvailableSlots(facultySlots));
        emergencyStatusLabel = createStatusLabel("Emergency Slots Available: " + getAvailableSlots(emergencySlots));
        vipStatusLabel = createStatusLabel("VIP Slots Available: " + getAvailableSlots(vipSlots));

        // Adding Components to the panel
        mainPanel.add(studentButton);
        mainPanel.add(studentStatusLabel);
        mainPanel.add(facultyButton);
        mainPanel.add(facultyStatusLabel);
        mainPanel.add(emergencyButton);
        mainPanel.add(emergencyStatusLabel);
        mainPanel.add(vipButton);
        mainPanel.add(vipStatusLabel);
        mainPanel.add(exitButton);
		mainPanel.add(viewStatusButton); 
		mainPanel.add(searchButton);

        // Action Listeners
        studentButton.addActionListener(e -> park("Student", studentSlots));
        facultyButton.addActionListener(e -> park("Faculty", facultySlots));
        emergencyButton.addActionListener(e -> park("Emergency", emergencySlots));
        vipButton.addActionListener(e -> park("VIP", vipSlots));
        exitButton.addActionListener(e -> exitParking());
		viewStatusButton.addActionListener(e -> viewParkingStatus());
		searchButton.addActionListener(e -> searchVehicleLocation()); 

        add(mainPanel);
    }
	
	    private JButton createSearchButton() {
        JButton button = new JButton("Search Parking");
        button.setBackground(new Color(100, 149, 237));
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(200, 50));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(65, 105, 225));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237));
            }
        });

        return button;
    }

//new
   private JButton createViewStatusButton() {
        JButton button = new JButton("View Parking Status");
        button.setBackground(new Color(70, 130, 180));  // Steel Blue color
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(200, 50));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(65, 105, 225));  // Darker blue on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));  // Original blue
            }
        });

        return button;
    }
	//new
    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setBackground(new Color(60, 179, 113));  // Green color for the buttons
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);

        // If icon path is not null, set the icon
        if (iconPath != null) {
            button.setIcon(new ImageIcon(iconPath));
            button.setHorizontalTextPosition(SwingConstants.RIGHT);
        }

        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        button.setPreferredSize(new Dimension(200, 50));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(85, 107, 47));  // Darker green on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(60, 179, 113));  // Original green
            }
        });

        return button;
    }

    private JButton createExitButton() {
        JButton exitButton = new JButton("Exit Parking");
        exitButton.setBackground(new Color(255, 99, 71));
        exitButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitButton.setPreferredSize(new Dimension(200, 50));
        exitButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        // Hover effect
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButton.setBackground(new Color(255, 69, 0));  // Darker red on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButton.setBackground(new Color(255, 99, 71));  // Original red
            }
        });

        return exitButton;
    }

    private JLabel createStatusLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(Color.BLACK);
        return label;
    }
	private String getParkingStatus(String[] slots) {
        StringBuilder status = new StringBuilder();
        for (int i = 0; i < slots.length; i++) {
            status.append("Slot ").append(i + 1).append(": ");
            if (slots[i] == null) {
                status.append("Empty\n");
            } else {
                status.append("Occupied by ").append(slots[i]).append("\n");
            }
        }
        return status.toString();
    }
	//new
	private void viewParkingStatus() {
    // Create a main panel to hold all parking areas (Student, Faculty, Emergency, VIP)
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  // Use vertical layout to stack areas
    panel.setBackground(Color.WHITE);

    // Add title label
    JLabel titleLabel = new JLabel("Parking Lot Status", JLabel.CENTER);
    titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
    panel.add(titleLabel);
    panel.add(Box.createVerticalStrut(10));  // Add spacing after the title

    // Add graphical parking layouts for each parking area (Student, Faculty, Emergency, VIP)
    panel.add(createParkingAreaPanel("Student Parking", studentSlots, 10));  // 10 columns for Student area
    panel.add(createParkingAreaPanel("Faculty Parking", facultySlots, 8));  // 8 columns for Faculty area
    panel.add(createParkingAreaPanel("Emergency Parking", emergencySlots, 3));  // 3 columns for Emergency area
    panel.add(createParkingAreaPanel("VIP Parking", vipSlots, 5));  // 5 columns for VIP area

    // Wrap the panel inside a JScrollPane to make it scrollable
    JScrollPane scrollPane = new JScrollPane(panel);
    scrollPane.setPreferredSize(new Dimension(600, 400));  // Set preferred size for the scrollable area

    // Display the graphical parking status in a pop-up dialog
    JOptionPane.showMessageDialog(this, scrollPane, "Parking Area Status", JOptionPane.INFORMATION_MESSAGE);
}

// Helper method to create a graphical layout for each parking area
private JPanel createParkingAreaPanel(String areaName, String[] slots, int columns) {
    JPanel areaPanel = new JPanel();
    areaPanel.setLayout(new GridLayout(0, columns, 5, 5));  // Use GridLayout with the specified number of columns
    areaPanel.setBorder(BorderFactory.createTitledBorder(areaName));  // Add title to each area panel

    // Iterate over the slots and create a colored box for each slot
    for (String slot : slots) {
        JLabel slotLabel = new JLabel();
        slotLabel.setPreferredSize(new Dimension(50, 50));
        slotLabel.setHorizontalAlignment(SwingConstants.CENTER);
        slotLabel.setVerticalAlignment(SwingConstants.CENTER);
        slotLabel.setOpaque(true);
        slotLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        if (slot == null) {
            // Empty slot - use a light green box
            slotLabel.setBackground(new Color(144, 238, 144));  // Light green color
            slotLabel.setText("Empty");
        } else {
            // Occupied slot - use a light red box
            slotLabel.setBackground(new Color(255, 182, 193));  // Light red color
            slotLabel.setText(slot);  // Display the vehicle number
        }

        areaPanel.add(slotLabel);
    }
    return areaPanel;
}

	//new

    private void park(String userType, String[] slots) {
        String vehicleNumber = JOptionPane.showInputDialog(this, "Enter your vehicle number:");
        if (vehicleNumber == null || vehicleNumber.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vehicle number cannot be empty!");
            return;
        }

        if (isVehicleAlreadyParked(vehicleNumber)) {
            JOptionPane.showMessageDialog(this, "Vehicle No." + vehicleNumber + " is already parked.");
            return;
        }

		int slot = findAvailableSlot(slots);
			if (slot != -1) {
				// Park in designated area
				slots[slot] = vehicleNumber;
				JOptionPane.showMessageDialog(this, " Your vehicle is parked in Slot " + (slot + 1) + " with Vehicle No: " + vehicleNumber);
			} else if (userType.equals("Emergency")) {
				// Overflow parking logic for emergency vehicles
				String[] overflowAreas = {"Student", "Faculty", "VIP"};
				boolean parkedInOverflow = false;

				for (String overflowArea : overflowAreas) {
					String[] overflowSlots = getSlotsArray(overflowArea);
					int overflowSlot = findAvailableSlot(overflowSlots);

					if (overflowSlot != -1) {
						// Park in overflow area
						overflowSlots[overflowSlot] = vehicleNumber;
						JOptionPane.showMessageDialog(this, "Your vehicle is parked in " + overflowArea + " Block, Slot " + (overflowSlot + 1) +
								" with Vehicle No: " + vehicleNumber);
						parkedInOverflow = true;
						break;
					}
				}

				if (!parkedInOverflow) {
					JOptionPane.showMessageDialog(this, "No available slots for Emergency vehicles, including overflow areas.");
				}
			} else {
				JOptionPane.showMessageDialog(this, "No available slots for " + userType + ".");
			}
			updateStatusLabels();
		}

	private int findOverflowSlot() {
		int slot = findAvailableSlot(studentSlots);
		if (slot == -1) slot = findAvailableSlot(facultySlots);
		if (slot == -1) slot = findAvailableSlot(vipSlots);
		return slot;
	}

	private String[] getOverflowSlots(int slot) {
		if (slot < STUDENT_SLOTS) return studentSlots;
		else if (slot < STUDENT_SLOTS + FACULTY_SLOTS) return facultySlots;
		else return vipSlots;
	}

    private boolean isVehicleAlreadyParked(String vehicleNumber) {
        return containsVehicle(studentSlots, vehicleNumber) ||
               containsVehicle(facultySlots, vehicleNumber) ||
               containsVehicle(emergencySlots, vehicleNumber) ||
               containsVehicle(vipSlots, vehicleNumber);
    }

    private boolean containsVehicle(String[] slots, String vehicleNumber) {
        for (String slot : slots) {
            if (vehicleNumber.equals(slot)) {
                return true;
            }
        }
        return false;
    }

    private int findAvailableSlot(String[] slots) {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null) return i;
        }
        return -1;
    }
	
	private String[] getSlotsArray(String userType) {
    switch (userType) {
        case "Student": return studentSlots;
        case "Faculty": return facultySlots;
        case "Emergency": return emergencySlots;
        case "VIP": return vipSlots;
        default: return null; // return null if no match
    }
}


   private void exitParking() {
    String vehicleNumber = JOptionPane.showInputDialog(this, "Enter your vehicle number to exit:");
    if (vehicleNumber == null || vehicleNumber.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vehicle number cannot be empty!");
        return;
    }

    String[][] slotArrays = {studentSlots, facultySlots, emergencySlots, vipSlots};
    String[] designations = {"Student", "Faculty", "Emergency", "VIP"};
    
    boolean vehicleFound = false;
    for (int i = 0; i < slotArrays.length; i++) {
        int slot = findOccupiedSlot(slotArrays[i], vehicleNumber);
        if (slot != -1) {
            String userType = determineUserType(vehicleNumber); // Find user type (e.g., Emergency)
            slotArrays[i][slot] = null;
            String message =" Vehicle No. " + vehicleNumber + " exited from Slot " + (slot + 1) + " of " + designations[i] + " block";
            JOptionPane.showMessageDialog(this, message);
            vehicleFound = true;
            break;
        }
    }

    if (!vehicleFound) {
        JOptionPane.showMessageDialog(this, "No slot is occupied by this vehicle.");
    }
    
    updateStatusLabels();
}

	private void searchVehicleLocation() {
		// Ask for the vehicle number directly
		String vehicleNumber = JOptionPane.showInputDialog(this, "Enter your vehicle number:");

		if (vehicleNumber == null || vehicleNumber.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vehicle number cannot be empty!");
			return;
		}

		// Check the vehicle number in all designations
		String[] designationTypes = {"Student", "Faculty", "Emergency", "VIP"};
		String[][] slotArrays = {studentSlots, facultySlots, emergencySlots, vipSlots};
		boolean vehicleFound = false;

		for (int i = 0; i < slotArrays.length; i++) {
			// Get the slots array for the selected designation
			String[] slots = slotArrays[i];
			int slot = findOccupiedSlot(slots, vehicleNumber);

			if (slot != -1) {
				// Show the designation (block) where the vehicle is parked
				JOptionPane.showMessageDialog(this, "Vehicle No. " + vehicleNumber + " is parked in Slot " + (slot + 1) + " of " + designationTypes[i] + " block.");
				vehicleFound = true;
				break;
			}
		}

		if (!vehicleFound) {
			JOptionPane.showMessageDialog(this, "Vehicle No. " + vehicleNumber + " not found in any parking.");
		}
	}


private String determineUserType(String vehicleNumber) {
    if (containsVehicle(studentSlots, vehicleNumber)) return "Student";
    if (containsVehicle(facultySlots, vehicleNumber)) return "Faculty";
    if (containsVehicle(emergencySlots, vehicleNumber)) return "Emergency";
    if (containsVehicle(vipSlots, vehicleNumber)) return "VIP";
    return "Unknown";
}



    private int findOccupiedSlot(String[] slots, String vehicleNumber) {
        for (int i = 0; i < slots.length; i++) {
            if (vehicleNumber.equals(slots[i])) return i;
        }
        return -1;
    }

    private void updateStatusLabels() {
        studentStatusLabel.setText("Student Slots Available: " + getAvailableSlots(studentSlots));
        facultyStatusLabel.setText("Faculty Slots Available: " + getAvailableSlots(facultySlots));
        emergencyStatusLabel.setText("Emergency Slots Available: " + getAvailableSlots(emergencySlots));
        vipStatusLabel.setText("VIP Slots Available: " + getAvailableSlots(vipSlots));
    }

    private int getAvailableSlots(String[] slots) {
        int count = 0;
        for (String slot : slots) {
            if (slot == null) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ParkingLotApp app = new ParkingLotApp();
            app.setVisible(true);
        });
    }
} 