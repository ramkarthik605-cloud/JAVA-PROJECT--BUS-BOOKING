package busbookingsystem;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class BusBookingSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new LoginPage();
        });
    }
}

// Custom Rounded Button Class
class RoundedButton extends JButton {
    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (getModel().isPressed()) {
            g2.setColor(getBackground().darker());
        } else if (getModel().isRollover()) {
            g2.setColor(getBackground().brighter());
        } else {
            g2.setColor(getBackground());
        }
        
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        g2.dispose();
        
        super.paintComponent(g);
    }
}

// ==================== LOGIN PAGE ====================
class LoginPage extends JFrame implements ActionListener {
    
    private Container container;
    private JTextField userTextField;
    private JPasswordField passwordField;
    private RoundedButton loginButton, resetButton, signUpButton;
    private JCheckBox showPassword;
    
    public LoginPage() {
        setTitle("APS RTC Bus Booking System - Login");
        setBounds(100, 50, 1000, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, 1000, 650, 50, 50));
        
        container = getContentPane();
        container.setLayout(null);
        
        // Main Panel with Gradient Background (Saffron and Green for APS RTC theme)
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, new Color(255, 140, 0), // Saffron
                                                       getWidth(), getHeight(), new Color(0, 128, 0)); // Green
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setBounds(0, 0, 1000, 650);
        mainPanel.setLayout(null);
        container.add(mainPanel);
        
        // Left Panel with Image and Text
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 450, 650);
        leftPanel.setBackground(new Color(255, 255, 255, 30));
        leftPanel.setLayout(null);
        mainPanel.add(leftPanel);
        
        JLabel busIcon = new JLabel("🚌");
        busIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 100));
        busIcon.setForeground(Color.WHITE);
        busIcon.setBounds(150, 120, 150, 150);
        leftPanel.add(busIcon);
        
        JLabel welcomeText = new JLabel("APS RTC");
        welcomeText.setFont(new Font("Segoe UI", Font.BOLD, 42));
        welcomeText.setForeground(Color.WHITE);
        welcomeText.setBounds(100, 270, 300, 50);
        leftPanel.add(welcomeText);
        
        JLabel subText = new JLabel("Andhra Pradesh State Road Transport");
        subText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subText.setForeground(new Color(255, 255, 255, 200));
        subText.setBounds(70, 320, 350, 30);
        leftPanel.add(subText);
        
        JLabel subText2 = new JLabel("Book your journey across Andhra Pradesh");
        subText2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subText2.setForeground(new Color(255, 255, 255, 200));
        subText2.setBounds(70, 350, 350, 30);
        leftPanel.add(subText2);
        
        // Right Panel with Login Form
        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(450, 50, 500, 550);
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(null);
        rightPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 100), 1));
        mainPanel.add(rightPanel);
        
        // Form Title
        JLabel loginTitle = new JLabel("Login to Account");
        loginTitle.setFont(new Font("Segoe UI", Font.BOLD, 32));
        loginTitle.setForeground(new Color(255, 140, 0)); // Saffron
        loginTitle.setBounds(100, 80, 300, 50);
        rightPanel.add(loginTitle);
        
        JLabel subtitle = new JLabel("Please enter your credentials to continue");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(0, 128, 0)); // Green
        subtitle.setBounds(100, 130, 300, 30);
        rightPanel.add(subtitle);
        
        // Username Field
        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userLabel.setForeground(new Color(52, 73, 94));
        userLabel.setBounds(100, 180, 300, 20);
        rightPanel.add(userLabel);
        
        userTextField = new JTextField();
        userTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        userTextField.setBounds(100, 205, 300, 45);
        userTextField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 140, 0)), // Saffron border
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        rightPanel.add(userTextField);
        
        // Password Field
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordLabel.setForeground(new Color(52, 73, 94));
        passwordLabel.setBounds(100, 270, 300, 20);
        rightPanel.add(passwordLabel);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordField.setBounds(100, 295, 300, 45);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 128, 0)), // Green border
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        rightPanel.add(passwordField);
        
        // Show Password Checkbox
        showPassword = new JCheckBox("Show Password");
        showPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        showPassword.setBounds(100, 345, 150, 30);
        showPassword.setBackground(Color.WHITE);
        showPassword.setForeground(new Color(52, 73, 94));
        showPassword.addActionListener(this);
        rightPanel.add(showPassword);
        
        // Login Button
        loginButton = new RoundedButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        loginButton.setBounds(100, 390, 300, 50);
        loginButton.setBackground(new Color(255, 140, 0)); // Saffron
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this);
        rightPanel.add(loginButton);
        
        // Reset Button
        resetButton = new RoundedButton("Reset");
        resetButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        resetButton.setBounds(200, 450, 100, 35);
        resetButton.setBackground(new Color(231, 76, 60));
        resetButton.setForeground(Color.WHITE);
        resetButton.addActionListener(this);
        rightPanel.add(resetButton);
        
        // Sign Up Link
        signUpButton = new RoundedButton("Create New Account");
        signUpButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        signUpButton.setBounds(150, 500, 200, 40);
        signUpButton.setBackground(new Color(0, 128, 0)); // Green
        signUpButton.setForeground(Color.WHITE);
        signUpButton.addActionListener(this);
        rightPanel.add(signUpButton);
        
        // Close Button
        JButton closeButton = new JButton("✕");
        closeButton.setFont(new Font("Arial", Font.BOLD, 18));
        closeButton.setBounds(950, 10, 40, 40);
        closeButton.setBackground(new Color(255, 255, 255, 50));
        closeButton.setForeground(Color.WHITE);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> System.exit(0));
        mainPanel.add(closeButton);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = userTextField.getText();
            String password = new String(passwordField.getPassword());
            
            if (username.equals("admin") && password.equals("admin123")) {
                JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new DashboardPage(username);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        
        if (e.getSource() == signUpButton) {
            new SignUpPage();
            dispose();
        }
        
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('•');
            }
        }
    }
}

// ==================== SIGN UP PAGE ====================
class SignUpPage extends JFrame implements ActionListener {
    
    private Container container;
    private JTextField nameField, emailField, phoneField, usernameField;
    private JPasswordField passwordField, confirmField;
    private RoundedButton registerButton, backButton;
    
    public SignUpPage() {
        setTitle("APS RTC Bus Booking System - Sign Up");
        setBounds(100, 50, 1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, 1000, 700, 50, 50));
        
        container = getContentPane();
        container.setLayout(null);
        
        // Gradient Background (Green to Saffron)
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, new Color(0, 128, 0), // Green
                                                       getWidth(), getHeight(), new Color(255, 140, 0)); // Saffron
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setBounds(0, 0, 1000, 700);
        mainPanel.setLayout(null);
        container.add(mainPanel);
        
        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setBounds(250, 80, 500, 550);
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(null);
        formPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 100), 1));
        mainPanel.add(formPanel);
        
        // Title
        JLabel titleLabel = new JLabel("Create Account");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(0, 128, 0)); // Green
        titleLabel.setBounds(150, 30, 300, 50);
        formPanel.add(titleLabel);
        
        // Name Field
        addFormField(formPanel, "Full Name", nameField = new JTextField(), 80);
        nameField.setBounds(100, 105, 300, 40);
        
        // Email Field
        addFormField(formPanel, "Email", emailField = new JTextField(), 145);
        emailField.setBounds(100, 170, 300, 40);
        
        // Phone Field
        addFormField(formPanel, "Phone Number", phoneField = new JTextField(), 210);
        phoneField.setBounds(100, 235, 300, 40);
        
        // Username Field
        addFormField(formPanel, "Username", usernameField = new JTextField(), 275);
        usernameField.setBounds(100, 300, 300, 40);
        
        // Password Field
        addFormField(formPanel, "Password", passwordField = new JPasswordField(), 340);
        passwordField.setBounds(100, 365, 300, 40);
        
        // Confirm Password Field
        addFormField(formPanel, "Confirm Password", confirmField = new JPasswordField(), 405);
        confirmField.setBounds(100, 430, 300, 40);
        
        // Register Button
        registerButton = new RoundedButton("Register");
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        registerButton.setBounds(150, 480, 200, 45);
        registerButton.setBackground(new Color(255, 140, 0)); // Saffron
        registerButton.setForeground(Color.WHITE);
        registerButton.addActionListener(this);
        formPanel.add(registerButton);
        
        // Back Button
        backButton = new RoundedButton("Back to Login");
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        backButton.setBounds(180, 535, 140, 35);
        backButton.setBackground(new Color(0, 128, 0)); // Green
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        formPanel.add(backButton);
        
        // Close Button
        JButton closeButton = new JButton("✕");
        closeButton.setFont(new Font("Arial", Font.BOLD, 18));
        closeButton.setBounds(950, 10, 40, 40);
        closeButton.setBackground(new Color(255, 255, 255, 50));
        closeButton.setForeground(Color.WHITE);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> System.exit(0));
        mainPanel.add(closeButton);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void addFormField(JPanel panel, String labelText, JComponent field, int y) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label.setForeground(new Color(149, 165, 166));
        label.setBounds(100, y - 20, 300, 20);
        panel.add(label);
        
        if (field instanceof JTextField || field instanceof JPasswordField) {
            field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 140, 0)), // Saffron border
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
            panel.add(field);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String password = new String(passwordField.getPassword());
            String confirm = new String(confirmField.getPassword());
            
            if (nameField.getText().isEmpty() || emailField.getText().isEmpty() || 
                phoneField.getText().isEmpty() || usernameField.getText().isEmpty() ||
                password.isEmpty() || confirm.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!password.equals(confirm)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Registration Successful!\nPlease login with your credentials.", 
                                            "Success", JOptionPane.INFORMATION_MESSAGE);
                new LoginPage();
                dispose();
            }
        }
        
        if (e.getSource() == backButton) {
            new LoginPage();
            dispose();
        }
    }
}

// ==================== DASHBOARD PAGE ====================
class DashboardPage extends JFrame implements ActionListener {
    
    private String username;
    private Container container;
    
    public DashboardPage(String username) {
        this.username = username;
        
        setTitle("APS RTC Bus Booking System - Dashboard");
        setBounds(100, 50, 1200, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        container = getContentPane();
        container.setLayout(null);
        container.setBackground(new Color(236, 240, 241));
        
        setLocationRelativeTo(null);
        
        // Top Bar (Saffron)
        JPanel topBar = new JPanel();
        topBar.setBounds(0, 0, 1200, 70);
        topBar.setBackground(new Color(255, 140, 0)); // Saffron
        topBar.setLayout(null);
        container.add(topBar);
        
        JLabel appTitle = new JLabel("🚌 APS RTC");
        appTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        appTitle.setForeground(Color.WHITE);
        appTitle.setBounds(30, 15, 300, 40);
        topBar.add(appTitle);
        
        JLabel userWelcome = new JLabel("Welcome, " + username + "!");
        userWelcome.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        userWelcome.setForeground(Color.WHITE);
        userWelcome.setBounds(900, 20, 200, 30);
        topBar.add(userWelcome);
        
        // Stats Cards
        createStatCard(container, "Total Bookings", "24", new Color(0, 128, 0), 50, 100); // Green
        createStatCard(container, "Upcoming Trips", "3", new Color(255, 140, 0), 320, 100); // Saffron
        createStatCard(container, "Reward Points", "1250", new Color(155, 89, 182), 590, 100);
        
        // Menu Panel
        JPanel menuPanel = new JPanel();
        menuPanel.setBounds(50, 220, 1100, 400);
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setLayout(new GridLayout(2, 3, 20, 20));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        container.add(menuPanel);
        
        // Menu Items
        addMenuItem(menuPanel, "🚌", "Book a Bus", "Search and book bus tickets across AP", new Color(255, 140, 0), e -> {
            new BookBusPage(username);
            dispose();
        });
        
        addMenuItem(menuPanel, "📋", "View Bookings", "Check your booking history", new Color(0, 128, 0), e -> {
            new ViewBookingsPage(username);
            dispose();
        });
        
        addMenuItem(menuPanel, "❌", "Cancel Ticket", "Cancel your booking", new Color(231, 76, 60), e -> {
            new CancelTicketPage(username);
            dispose();
        });
        
        addMenuItem(menuPanel, "🔍", "Search Bus", "Find buses by route in AP", new Color(241, 196, 15), e -> {
            new SearchBusPage(username);
            dispose();
        });
        
        addMenuItem(menuPanel, "⭐", "APS RTC Offers", "Check special deals", new Color(155, 89, 182), e -> {
            JOptionPane.showMessageDialog(this, "Special Offers:\n• 20% off on first booking\n• Free cancellation\n• APS RTC Loyalty points doubled!");
        });
        
        addMenuItem(menuPanel, "⚙️", "Settings", "Account settings", new Color(149, 165, 166), e -> {
            JOptionPane.showMessageDialog(this, "Settings panel coming soon!");
        });
        
        // Logout Button
        RoundedButton logoutBtn = new RoundedButton("Logout");
        logoutBtn.setBounds(1000, 600, 150, 40);
        logoutBtn.setBackground(new Color(231, 76, 60));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        logoutBtn.addActionListener(e -> {
            new LoginPage();
            dispose();
        });
        container.add(logoutBtn);
        
        setVisible(true);
    }
    
    private void createStatCard(Container container, String title, String value, Color color, int x, int y) {
        JPanel card = new JPanel();
        card.setBounds(x, y, 250, 100);
        card.setBackground(Color.WHITE);
        card.setLayout(null);
        card.setBorder(BorderFactory.createLineBorder(color, 2));
        container.add(card);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        titleLabel.setForeground(new Color(149, 165, 166));
        titleLabel.setBounds(20, 15, 200, 20);
        card.add(titleLabel);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        valueLabel.setForeground(color);
        valueLabel.setBounds(20, 35, 200, 40);
        card.add(valueLabel);
    }
    
    private void addMenuItem(JPanel panel, String icon, String title, String desc, Color color, ActionListener listener) {
        JButton item = new JButton();
        item.setLayout(null);
        item.setBackground(Color.WHITE);
        item.setBorder(BorderFactory.createLineBorder(new Color(236, 240, 241), 2));
        item.setFocusPainted(false);
        item.setCursor(new Cursor(Cursor.HAND_CURSOR));
        item.addActionListener(listener);
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        iconLabel.setBounds(30, 20, 60, 50);
        item.add(iconLabel);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(color);
        titleLabel.setBounds(100, 20, 200, 25);
        item.add(titleLabel);
        
        JLabel descLabel = new JLabel(desc);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descLabel.setForeground(new Color(149, 165, 166));
        descLabel.setBounds(100, 45, 200, 20);
        item.add(descLabel);
        
        panel.add(item);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {}
}

// ==================== BOOK BUS PAGE ====================
class BookBusPage extends JFrame implements ActionListener {
    
    private String username;
    private JComboBox<String> fromCombo, toCombo, busTypeCombo;
    private JTextField dateField;
    private JSpinner seatsSpinner;
    private RoundedButton searchButton, backButton;
    
    // Andhra Pradesh Cities
    private String[] apCities = {
        "Visakhapatnam", "Vijayawada", "Guntur", "Tirupati", 
        "Kurnool", "Nellore", "Rajamahendravaram (Rajahmundry)", 
        "Kadapa", "Anantapur"
    };
    
    public BookBusPage(String username) {
        this.username = username;
        
        setTitle("APS RTC - Book Bus in Andhra Pradesh");
        setBounds(100, 50, 1000, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        getContentPane().setBackground(new Color(236, 240, 241));
        setLayout(null);
        
        setLocationRelativeTo(null);
        
        // Title
        JLabel titleLabel = new JLabel("Book Your Journey in Andhra Pradesh");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(255, 140, 0)); // Saffron
        titleLabel.setBounds(250, 30, 600, 50);
        add(titleLabel);
        
        // Main Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setBounds(250, 100, 500, 400);
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(null);
        formPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 128, 0), 2)); // Green border
        add(formPanel);
        
        // From
        JLabel fromLabel = new JLabel("From (City in Andhra Pradesh)");
        fromLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        fromLabel.setForeground(new Color(255, 140, 0)); // Saffron
        fromLabel.setBounds(50, 30, 400, 20);
        formPanel.add(fromLabel);
        
        fromCombo = new JComboBox<>(apCities);
        fromCombo.setBounds(50, 55, 400, 40);
        fromCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        fromCombo.setBackground(Color.WHITE);
        formPanel.add(fromCombo);
        
        // To
        JLabel toLabel = new JLabel("To (City in Andhra Pradesh)");
        toLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        toLabel.setForeground(new Color(0, 128, 0)); // Green
        toLabel.setBounds(50, 110, 400, 20);
        formPanel.add(toLabel);
        
        toCombo = new JComboBox<>(apCities);
        toCombo.setBounds(50, 135, 400, 40);
        toCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        toCombo.setBackground(Color.WHITE);
        formPanel.add(toCombo);
        
        // Date
        JLabel dateLabel = new JLabel("Journey Date");
        dateLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        dateLabel.setForeground(new Color(255, 140, 0)); // Saffron
        dateLabel.setBounds(50, 190, 180, 20);
        formPanel.add(dateLabel);
        
        dateField = new JTextField(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        dateField.setBounds(50, 215, 180, 40);
        dateField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateField.setEditable(false);
        dateField.setBackground(new Color(236, 240, 241));
        formPanel.add(dateField);
        
        // Bus Type
        JLabel busTypeLabel = new JLabel("Bus Type");
        busTypeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        busTypeLabel.setForeground(new Color(0, 128, 0)); // Green
        busTypeLabel.setBounds(270, 190, 180, 20);
        formPanel.add(busTypeLabel);
        
        String[] busTypes = {"AC Sleeper", "AC Seater", "Non-AC Sleeper", "Non-AC Seater", "Luxury", "APS RTC Garuda", "APS RTC Super Luxury"};
        busTypeCombo = new JComboBox<>(busTypes);
        busTypeCombo.setBounds(270, 215, 180, 40);
        busTypeCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        busTypeCombo.setBackground(Color.WHITE);
        formPanel.add(busTypeCombo);
        
        // Seats
        JLabel seatsLabel = new JLabel("Number of Seats");
        seatsLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        seatsLabel.setForeground(new Color(255, 140, 0)); // Saffron
        seatsLabel.setBounds(50, 270, 400, 20);
        formPanel.add(seatsLabel);
        
        SpinnerModel seatModel = new SpinnerNumberModel(1, 1, 10, 1);
        seatsSpinner = new JSpinner(seatModel);
        seatsSpinner.setBounds(50, 295, 100, 40);
        seatsSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(seatsSpinner);
        
        // APS RTC Logo/Text
        JLabel apsrtcLabel = new JLabel("APS RTC - Serving Andhra Pradesh");
        apsrtcLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        apsrtcLabel.setForeground(new Color(149, 165, 166));
        apsrtcLabel.setBounds(150, 350, 250, 20);
        formPanel.add(apsrtcLabel);
        
        // Search Button
        searchButton = new RoundedButton("Search Available Buses");
        searchButton.setBounds(350, 530, 250, 50);
        searchButton.setBackground(new Color(0, 128, 0)); // Green
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        searchButton.addActionListener(this);
        add(searchButton);
        
        // Back Button
        backButton = new RoundedButton("← Back");
        backButton.setBounds(50, 550, 120, 35);
        backButton.setBackground(new Color(255, 140, 0)); // Saffron
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        backButton.addActionListener(this);
        add(backButton);
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String from = (String) fromCombo.getSelectedItem();
            String to = (String) toCombo.getSelectedItem();
            String date = dateField.getText();
            String busType = (String) busTypeCombo.getSelectedItem();
            int seats = (int) seatsSpinner.getValue();
            
            if (from.equals(to)) {
                JOptionPane.showMessageDialog(this, "Source and Destination cannot be same!", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                new BusListPage(username, from, to, date, busType, seats);
                dispose();
            }
        }
        
        if (e.getSource() == backButton) {
            new DashboardPage(username);
            dispose();
        }
    }
}

// ==================== BUS LIST PAGE ====================
class BusListPage extends JFrame implements ActionListener {
    
    private String username, from, to, date, busType;
    private int seats;
    private JTable busTable;
    private RoundedButton bookButton, backButton;
    private DefaultTableModel tableModel;
    
    public BusListPage(String username, String from, String to, String date, String busType, int seats) {
        this.username = username;
        this.from = from;
        this.to = to;
        this.date = date;
        this.busType = busType;
        this.seats = seats;
        
        setTitle("APS RTC - Available Buses in Andhra Pradesh");
        setBounds(100, 50, 1100, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        getContentPane().setBackground(new Color(236, 240, 241));
        setLayout(null);
        
        setLocationRelativeTo(null);
        
        // Title
        JLabel titleLabel = new JLabel("APS RTC - Available Buses");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(0, 128, 0)); // Green
        titleLabel.setBounds(350, 20, 500, 50);
        add(titleLabel);
        
        // Search Info Panel
        JPanel infoPanel = new JPanel();
        infoPanel.setBounds(300, 80, 500, 60);
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        infoPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0), 2)); // Saffron border
        add(infoPanel);
        
        infoPanel.add(new JLabel("📍 " + from + " → " + to));
        infoPanel.add(new JLabel("📅 " + date));
        infoPanel.add(new JLabel("👥 " + seats + " seats"));
        
        // Table
        String[] columns = {"Bus ID", "Bus Name", "Type", "Departure", "Arrival", "Price (₹)", "Available"};
        tableModel = new DefaultTableModel(columns, 0);
        busTable = new JTable(tableModel);
        busTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        busTable.setRowHeight(35);
        busTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        busTable.getTableHeader().setBackground(new Color(255, 140, 0)); // Saffron
        busTable.getTableHeader().setForeground(Color.WHITE);
        busTable.setSelectionBackground(new Color(0, 128, 0, 50)); // Light Green
        
        addSampleData();
        
        JScrollPane scrollPane = new JScrollPane(busTable);
        scrollPane.setBounds(50, 160, 1000, 400);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0, 128, 0), 2)); // Green border
        add(scrollPane);
        
        // Book Button
        bookButton = new RoundedButton("Book Selected Bus");
        bookButton.setBounds(400, 580, 250, 45);
        bookButton.setBackground(new Color(0, 128, 0)); // Green
        bookButton.setForeground(Color.WHITE);
        bookButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        bookButton.addActionListener(this);
        add(bookButton);
        
        // Back Button
        backButton = new RoundedButton("← Back");
        backButton.setBounds(50, 580, 120, 40);
        backButton.setBackground(new Color(255, 140, 0)); // Saffron
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        backButton.addActionListener(this);
        add(backButton);
        
        setVisible(true);
    }
    
    private void addSampleData() {
        // Sample bus data for Andhra Pradesh routes
        Object[][] data = {
            {"AP01", "APS RTC Garuda", busType, "06:00 AM", "11:00 AM", "₹450", "15"},
            {"AP02", "APS RTC Super Luxury", busType, "08:00 AM", "01:00 PM", "₹550", "8"},
            {"AP03", "APS RTC Express", busType, "10:00 PM", "03:00 AM", "₹350", "12"},
            {"AP04", "Royal Travels", "Luxury", "09:00 PM", "02:00 AM", "₹650", "5"},
            {"AP05", "Green Line", busType, "07:00 AM", "12:00 PM", "₹480", "20"},
            {"AP06", "Fast Track", busType, "05:00 AM", "10:00 AM", "₹380", "25"},
            {"AP07", "Visakha Express", busType, "11:00 PM", "04:00 AM", "₹420", "18"},
            {"AP08", "Tirupati Travels", busType, "09:00 AM", "02:00 PM", "₹520", "10"}
        };
        
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookButton) {
            int selectedRow = busTable.getSelectedRow();
            if (selectedRow >= 0) {
                String busId = tableModel.getValueAt(selectedRow, 0).toString();
                String busName = tableModel.getValueAt(selectedRow, 1).toString();
                String price = tableModel.getValueAt(selectedRow, 5).toString();
                
                new PaymentPage(username, busId, busName, from, to, date, price, seats, busType);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a bus to book!", 
                                            "No Selection", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        if (e.getSource() == backButton) {
            new BookBusPage(username);
            dispose();
        }
    }
}

// ==================== PAYMENT PAGE ====================
class PaymentPage extends JFrame implements ActionListener {
    
    private String username, busId, busName, from, to, date, price, busType;
    private int seats;
    private JRadioButton cardButton, upiButton;
    private JTextField cardNumberField, cardNameField, expiryField, cvvField;
    private RoundedButton payButton, backButton;
    private JPanel cardPanel;
    
    public PaymentPage(String username, String busId, String busName, String from, 
                      String to, String date, String price, int seats, String busType) {
        this.username = username;
        this.busId = busId;
        this.busName = busName;
        this.from = from;
        this.to = to;
        this.date = date;
        this.price = price;
        this.seats = seats;
        this.busType = busType;
        
        setTitle("APS RTC - Payment");
        setBounds(100, 50, 1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        getContentPane().setBackground(new Color(236, 240, 241));
        setLayout(null);
        
        setLocationRelativeTo(null);
        
        // Title
        JLabel titleLabel = new JLabel("Complete Payment - APS RTC");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(255, 140, 0)); // Saffron
        titleLabel.setBounds(300, 20, 500, 50);
        add(titleLabel);
        
        // Booking Summary
        JPanel summaryPanel = new JPanel();
        summaryPanel.setBounds(250, 80, 500, 170);
        summaryPanel.setBackground(Color.WHITE);
        summaryPanel.setLayout(new GridLayout(6, 2, 10, 5));
        summaryPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(0, 128, 0), 2), "Booking Summary"));
        add(summaryPanel);
        
        int total = Integer.parseInt(price.replace("₹", "")) * seats;
        
        addSummaryRow(summaryPanel, "Bus:", busName);
        addSummaryRow(summaryPanel, "Route:", from + " → " + to);
        addSummaryRow(summaryPanel, "Date:", date);
        addSummaryRow(summaryPanel, "Bus Type:", busType);
        addSummaryRow(summaryPanel, "Seats:", String.valueOf(seats));
        addSummaryRow(summaryPanel, "Total Amount:", "₹" + total);
        
        // Payment Methods
        JLabel paymentLabel = new JLabel("Select Payment Method");
        paymentLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        paymentLabel.setForeground(new Color(0, 128, 0)); // Green
        paymentLabel.setBounds(250, 270, 300, 30);
        add(paymentLabel);
        
        cardButton = new JRadioButton("Credit/Debit Card");
        cardButton.setBounds(250, 310, 200, 30);
        cardButton.setBackground(new Color(236, 240, 241));
        cardButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cardButton.addActionListener(this);
        add(cardButton);
        
        upiButton = new JRadioButton("UPI");
        upiButton.setBounds(470, 310, 100, 30);
        upiButton.setBackground(new Color(236, 240, 241));
        upiButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        upiButton.addActionListener(this);
        add(upiButton);
        
        ButtonGroup paymentGroup = new ButtonGroup();
        paymentGroup.add(cardButton);
        paymentGroup.add(upiButton);
        
        // Card Details Panel
        cardPanel = new JPanel();
        cardPanel.setBounds(250, 350, 500, 180);
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setLayout(null);
        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0), 2)); // Saffron border
        cardPanel.setVisible(false);
        add(cardPanel);
        
        // Card Number
        JLabel cardNumLabel = new JLabel("Card Number");
        cardNumLabel.setBounds(30, 20, 200, 20);
        cardNumLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cardPanel.add(cardNumLabel);
        
        cardNumberField = new JTextField();
        cardNumberField.setBounds(30, 45, 250, 35);
        cardNumberField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cardPanel.add(cardNumberField);
        
        // Card Holder
        JLabel cardNameLabel = new JLabel("Card Holder Name");
        cardNameLabel.setBounds(30, 90, 200, 20);
        cardNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cardPanel.add(cardNameLabel);
        
        cardNameField = new JTextField();
        cardNameField.setBounds(30, 115, 250, 35);
        cardNameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cardPanel.add(cardNameField);
        
        // Expiry and CVV
        JLabel expiryLabel = new JLabel("Expiry (MM/YY)");
        expiryLabel.setBounds(300, 20, 150, 20);
        expiryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cardPanel.add(expiryLabel);
        
        expiryField = new JTextField();
        expiryField.setBounds(300, 45, 80, 35);
        expiryField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cardPanel.add(expiryField);
        
        JLabel cvvLabel = new JLabel("CVV");
        cvvLabel.setBounds(400, 20, 80, 20);
        cvvLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cardPanel.add(cvvLabel);
        
        cvvField = new JTextField();
        cvvField.setBounds(400, 45, 60, 35);
        cvvField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cardPanel.add(cvvField);
        
        // Pay Button
        payButton = new RoundedButton("Pay ₹" + total);
        payButton.setBounds(400, 550, 200, 50);
        payButton.setBackground(new Color(0, 128, 0)); // Green
        payButton.setForeground(Color.WHITE);
        payButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        payButton.addActionListener(this);
        add(payButton);
        
        // Back Button
        backButton = new RoundedButton("← Back");
        backButton.setBounds(50, 600, 120, 35);
        backButton.setBackground(new Color(255, 140, 0)); // Saffron
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        backButton.addActionListener(this);
        add(backButton);
        
        setVisible(true);
    }
    
    private void addSummaryRow(JPanel panel, String label, String value) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(lbl);
        
        JLabel val = new JLabel(value);
        val.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(val);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cardButton) {
            cardPanel.setVisible(true);
        } else if (e.getSource() == upiButton) {
            cardPanel.setVisible(false);
            JOptionPane.showMessageDialog(this, "UPI Payment: Please pay to APS RTC UPI ID: apsrtc@okhdfcbank", 
                                        "UPI Payment", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == payButton) {
            if (!cardButton.isSelected() && !upiButton.isSelected()) {
                JOptionPane.showMessageDialog(this, "Please select a payment method!", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (cardButton.isSelected()) {
                if (cardNumberField.getText().isEmpty() || cardNameField.getText().isEmpty() ||
                    expiryField.getText().isEmpty() || cvvField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all card details!", 
                                                "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            String bookingId = "APS" + System.currentTimeMillis() % 10000;
            int total = Integer.parseInt(price.replace("₹", "")) * seats;
            
            // Success Dialog
            JOptionPane.showMessageDialog(this, 
                "✅ Payment Successful!\n\n" +
                "APS RTC Booking ID: " + bookingId + "\n" +
                "Bus: " + busName + "\n" +
                "Route: " + from + " → " + to + "\n" +
                "Date: " + date + "\n" +
                "Amount Paid: ₹" + total + "\n\n" +
                "Thank you for traveling with APS RTC!", 
                "Booking Confirmed", JOptionPane.INFORMATION_MESSAGE);
            
            new DashboardPage(username);
            dispose();
        } else if (e.getSource() == backButton) {
            new BusListPage(username, from, to, date, busType, seats);
            dispose();
        }
    }
}

// ==================== VIEW BOOKINGS PAGE ====================
class ViewBookingsPage extends JFrame implements ActionListener {
    
    private String username;
    private JTable bookingsTable;
    private RoundedButton backButton;
    private DefaultTableModel tableModel;
    
    public ViewBookingsPage(String username) {
        this.username = username;
        
        setTitle("APS RTC - My Bookings");
        setBounds(100, 50, 1100, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        getContentPane().setBackground(new Color(236, 240, 241));
        setLayout(null);
        
        setLocationRelativeTo(null);
        
        // Title
        JLabel titleLabel = new JLabel("My APS RTC Bookings");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(new Color(255, 140, 0)); // Saffron
        titleLabel.setBounds(400, 20, 400, 50);
        add(titleLabel);
        
        // Table
        String[] columns = {"Booking ID", "Bus Name", "From", "To", "Date", "Seats", "Amount", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        bookingsTable = new JTable(tableModel);
        bookingsTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        bookingsTable.setRowHeight(35);
        bookingsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        bookingsTable.getTableHeader().setBackground(new Color(0, 128, 0)); // Green
        bookingsTable.getTableHeader().setForeground(Color.WHITE);
        
        addSampleBookings();
        
        JScrollPane scrollPane = new JScrollPane(bookingsTable);
        scrollPane.setBounds(50, 100, 1000, 350);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0), 2)); // Saffron border
        add(scrollPane);
        
        // Back Button
        backButton = new RoundedButton("← Back to Dashboard");
        backButton.setBounds(450, 480, 200, 45);
        backButton.setBackground(new Color(0, 128, 0)); // Green
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.addActionListener(this);
        add(backButton);
        
        setVisible(true);
    }
    
    private void addSampleBookings() {
        Object[][] bookings = {
            {"APS1234", "APS RTC Garuda", "Visakhapatnam", "Vijayawada", "15/03/2024", "2", "₹900", "✅ Confirmed"},
            {"APS5678", "APS RTC Express", "Tirupati", "Nellore", "20/03/2024", "1", "₹350", "✅ Confirmed"},
            {"APS9012", "Royal Travels", "Guntur", "Kurnool", "25/03/2024", "3", "₹1050", "⏳ Pending"},
            {"APS3456", "Visakha Express", "Rajahmundry", "Kadapa", "28/03/2024", "2", "₹840", "❌ Cancelled"}
        };
        
        for (Object[] booking : bookings) {
            tableModel.addRow(booking);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new DashboardPage(username);
            dispose();
        }
    }
}

// ==================== CANCEL TICKET PAGE ====================
class CancelTicketPage extends JFrame implements ActionListener {
    
    private String username;
    private JTextField bookingIdField;
    private JTextArea reasonArea;
    private RoundedButton cancelButton, backButton;
    
    public CancelTicketPage(String username) {
        this.username = username;
        
        setTitle("APS RTC - Cancel Ticket");
        setBounds(100, 50, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        getContentPane().setBackground(new Color(236, 240, 241));
        setLayout(null);
        
        setLocationRelativeTo(null);
        
        // Title
        JLabel titleLabel = new JLabel("Cancel APS RTC Ticket");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(231, 76, 60));
        titleLabel.setBounds(300, 30, 400, 50);
        add(titleLabel);
        
        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setBounds(200, 120, 500, 300);
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(null);
        formPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0), 2)); // Saffron border
        add(formPanel);
        
        // Booking ID
        JLabel bookingIdLabel = new JLabel("APS RTC Booking ID");
        bookingIdLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bookingIdLabel.setForeground(new Color(0, 128, 0)); // Green
        bookingIdLabel.setBounds(50, 40, 400, 20);
        formPanel.add(bookingIdLabel);
        
        bookingIdField = new JTextField();
        bookingIdField.setBounds(50, 65, 400, 40);
        bookingIdField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        bookingIdField.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0))); // Saffron border
        formPanel.add(bookingIdField);
        
        // Reason
        JLabel reasonLabel = new JLabel("Reason for Cancellation");
        reasonLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        reasonLabel.setForeground(new Color(0, 128, 0)); // Green
        reasonLabel.setBounds(50, 120, 400, 20);
        formPanel.add(reasonLabel);
        
        reasonArea = new JTextArea();
        reasonArea.setBounds(50, 145, 400, 80);
        reasonArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        reasonArea.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0))); // Saffron border
        reasonArea.setLineWrap(true);
        formPanel.add(reasonArea);
        
        // Cancel Button
        cancelButton = new RoundedButton("Cancel Booking");
        cancelButton.setBounds(350, 460, 200, 45);
        cancelButton.setBackground(new Color(231, 76, 60));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        cancelButton.addActionListener(this);
        add(cancelButton);
        
        // Back Button
        backButton = new RoundedButton("← Back");
        backButton.setBounds(50, 500, 120, 35);
        backButton.setBackground(new Color(0, 128, 0)); // Green
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        backButton.addActionListener(this);
        add(backButton);
        
        // Refund Policy
        JPanel policyPanel = new JPanel();
        policyPanel.setBounds(50, 120, 130, 200);
        policyPanel.setBackground(Color.WHITE);
        policyPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(255, 140, 0)), "APS RTC Policy"));
        policyPanel.setLayout(new GridLayout(4, 1));
        add(policyPanel);
        
        policyPanel.add(new JLabel("• Before 24h: 100%"));
        policyPanel.add(new JLabel("• 12-24h: 50%"));
        policyPanel.add(new JLabel("• Less than 12h: 0%"));
        policyPanel.add(new JLabel("• Fee: ₹50"));
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            String bookingId = bookingIdField.getText();
            
            if (bookingId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Booking ID!", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int confirm = JOptionPane.showConfirmDialog(this, 
                    "Are you sure you want to cancel APS RTC booking: " + bookingId + "?", 
                    "Confirm Cancellation", JOptionPane.YES_NO_OPTION);
                    
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(this, 
                        "✅ Booking cancelled successfully!\nRefund will be processed within 5-7 working days.", 
                        "Cancellation Successful", JOptionPane.INFORMATION_MESSAGE);
                    new DashboardPage(username);
                    dispose();
                }
            }
        }
        
        if (e.getSource() == backButton) {
            new DashboardPage(username);
            dispose();
        }
    }
}

// ==================== SEARCH BUS PAGE ====================
class SearchBusPage extends JFrame implements ActionListener {
    
    private String username;
    private JComboBox<String> fromCombo, toCombo;
    private JTextField dateField;
    private JTextArea resultArea;
    private RoundedButton searchButton, backButton;
    
    // Andhra Pradesh Cities
    private String[] apCities = {
        "Visakhapatnam", "Vijayawada", "Guntur", "Tirupati", 
        "Kurnool", "Nellore", "Rajamahendravaram (Rajahmundry)", 
        "Kadapa", "Anantapur"
    };
    
    public SearchBusPage(String username) {
        this.username = username;
        
        setTitle("APS RTC - Search Buses in Andhra Pradesh");
        setBounds(100, 50, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        getContentPane().setBackground(new Color(236, 240, 241));
        setLayout(null);
        
        setLocationRelativeTo(null);
        
        // Title
        JLabel titleLabel = new JLabel("Search APS RTC Buses");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(255, 140, 0)); // Saffron
        titleLabel.setBounds(300, 20, 400, 50);
        add(titleLabel);
        
        // Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setBounds(200, 90, 500, 140);
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setLayout(null);
        searchPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 128, 0), 2)); // Green border
        add(searchPanel);
        
        // From
        JLabel fromLabel = new JLabel("From (AP City):");
        fromLabel.setBounds(50, 20, 120, 30);
        fromLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        fromLabel.setForeground(new Color(255, 140, 0)); // Saffron
        searchPanel.add(fromLabel);
        
        fromCombo = new JComboBox<>(apCities);
        fromCombo.setBounds(170, 20, 150, 30);
        fromCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchPanel.add(fromCombo);
        
        // To
        JLabel toLabel = new JLabel("To (AP City):");
        toLabel.setBounds(50, 60, 120, 30);
        toLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        toLabel.setForeground(new Color(0, 128, 0)); // Green
        searchPanel.add(toLabel);
        
        toCombo = new JComboBox<>(apCities);
        toCombo.setBounds(170, 60, 150, 30);
        toCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchPanel.add(toCombo);
        
        // Date
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(330, 20, 50, 30);
        dateLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        dateLabel.setForeground(new Color(255, 140, 0)); // Saffron
        searchPanel.add(dateLabel);
        
        dateField = new JTextField("DD/MM/YYYY");
        dateField.setBounds(380, 20, 100, 30);
        dateField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchPanel.add(dateField);
        
        // Search Button
        searchButton = new RoundedButton("Search");
        searchButton.setBounds(380, 60, 100, 35);
        searchButton.setBackground(new Color(0, 128, 0)); // Green
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        searchButton.addActionListener(this);
        searchPanel.add(searchButton);
        
        // Result Area
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(200, 250, 500, 220);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(255, 140, 0), 2), "APS RTC Search Results"));
        add(scrollPane);
        
        // Back Button
        backButton = new RoundedButton("← Back");
        backButton.setBounds(50, 500, 120, 35);
        backButton.setBackground(new Color(255, 140, 0)); // Saffron
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        backButton.addActionListener(this);
        add(backButton);
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String from = (String) fromCombo.getSelectedItem();
            String to = (String) toCombo.getSelectedItem();
            String date = dateField.getText();
            
            if (from.equals(to)) {
                JOptionPane.showMessageDialog(this, "Source and Destination cannot be same!");
            } else {
                String results = "🔍 APS RTC SEARCH RESULTS\n\n" +
                               "From: " + from + "\n" +
                               "To: " + to + "\n" +
                               "Date: " + date + "\n\n" +
                               "Available Buses in Andhra Pradesh:\n" +
                               "────────────────────────────────\n" +
                               "1. APS RTC Garuda - 06:00 AM - ₹450\n" +
                               "2. APS RTC Super Luxury - 08:00 AM - ₹550\n" +
                               "3. APS RTC Express - 10:00 PM - ₹350\n" +
                               "4. Visakha Express - 07:00 AM - ₹420\n" +
                               "5. Tirupati Travels - 09:00 AM - ₹520\n\n" +
                               "👉 Go to 'Book a Bus' to make a reservation.";
                
                resultArea.setText(results);
            }
        }
        
        if (e.getSource() == backButton) {
            new DashboardPage(username);
            dispose();
        }
    }
}