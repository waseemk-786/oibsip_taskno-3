import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ATMGUI extends JFrame implements ActionListener {
    private JLabel welcomeLabel;
    private JLabel userIDLabel;
    private JTextField userIDField;
    private JLabel pinLabel;
    private JPasswordField pinField;
    private JButton loginButton;
    private JButton quitButton;
    private JButton withdrawButton;
    private JButton depositButton;
    private JButton transferButton;
    private JLabel balanceLabel;
    private JLabel transactionHistoryLabel;
    private JTextArea transactionHistoryArea;
    private int balance = 1000;
    private ArrayList<String> transactionHistory = new ArrayList<>();

    public ATMGUI() {
        super("ATM System");

        // create components
        welcomeLabel = new JLabel("Welcome to the ATM System");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        userIDLabel = new JLabel("User ID:");
        userIDField = new JTextField(10);
        pinLabel = new JLabel("PIN:");
        pinField = new JPasswordField(10);
        loginButton = new JButton("Login");
        quitButton = new JButton("Quit");
        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");
        transferButton = new JButton("Transfer");
        balanceLabel = new JLabel("Balance: " + balance);
        transactionHistoryLabel = new JLabel("Transaction History:");
        transactionHistoryArea = new JTextArea(10, 20);
        transactionHistoryArea.setEditable(false);

        // add components to content pane
        Container c = getContentPane();
        c.setLayout(new GridLayout(8, 2));
        c.add(welcomeLabel);
        c.add(new JLabel(""));
        c.add(userIDLabel);
        c.add(userIDField);
        c.add(pinLabel);
        c.add(pinField);
        c.add(new JLabel(""));
        c.add(loginButton);
        c.add(withdrawButton);
        c.add(depositButton);
        c.add(transferButton);
        c.add(balanceLabel);
        c.add(transactionHistoryLabel);
        c.add(new JScrollPane(transactionHistoryArea));
        c.add(quitButton);

        // add action listeners
        loginButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        depositButton.addActionListener(this);
        transferButton.addActionListener(this);
        quitButton.addActionListener(this);

        // set window properties
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            // code to handle login
            String userID = userIDField.getText();
            String pin = new String(pinField.getPassword());

            // validate user ID and PIN
            if (userID.equals("waseem") && pin.equals("1234")) {
                // show ATM functionalities
                welcomeLabel.setText("Welcome, " + userID + " !");
                userIDLabel.setVisible(false);
                userIDField.setVisible(false);
                pinLabel.setVisible(false);
                pinField.setVisible(false);
                loginButton.setVisible(false);
                withdrawButton.setVisible(true);
                depositButton.setVisible(true);
                transferButton.setVisible(true);
                balanceLabel.setVisible(true);
                transactionHistoryLabel.setVisible(true);
                transactionHistoryArea.setVisible(true);
            } else {
                // show error message
                JOptionPane.showMessageDialog(this, "Invalid user ID or PIN", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == withdrawButton) {
            // code to handle withdraw
            String input = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
            try {
                int amount = Integer.parseInt(input);
                if (amount <= 0) {
                    // show error message for invalid input
                    JOptionPane.showMessageDialog(this, "Amount must be a positive integer", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (amount > balance) {
                    // show error message for insufficient balance
                    JOptionPane.showMessageDialog(this, "Insufficient balance", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // deduct amount from balance and show updated balance
                    balance -= amount;
                    balanceLabel.setText("Balance: " + balance);

                    // add transaction to transaction history
                    String transaction = "Withdrawal of $" + amount;
                    transactionHistory.add(transaction);
                    showTransactionHistory();
                }
            } catch (NumberFormatException ex) {
                // show error message for invalid input
                JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == depositButton) {
            // code to handle deposit
            String input = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
            try {
                int amount = Integer.parseInt(input);
                if (amount <= 0) {
                    // show error message for invalid input
                    JOptionPane.showMessageDialog(this, "Amount must be a positive integer", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    // add amount to balance and show updated balance
                    balance += amount;
                    balanceLabel.setText("Balance: " + balance);

                    // add transaction to transaction history
                    String transaction = "Deposit of $" + amount;
                    transactionHistory.add(transaction);
                    showTransactionHistory();
                }
            } catch (NumberFormatException ex) {
                // show error message for invalid input
                JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == transferButton) {
            // code to handle transfer
            String input = JOptionPane.showInputDialog(this, "Enter user ID of recipient:");
            try {
                int recipientID = Integer.parseInt(input);
                input = JOptionPane.showInputDialog(this, "Enter amount to transfer:");
                int amount = Integer.parseInt(input);
                if (amount <= 0) {
                    // show error message for invalid input
                    JOptionPane.showMessageDialog(this, "Amount must be a positive integer", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (amount > balance) {
                    // show error message for insufficient balance
                    JOptionPane.showMessageDialog(this, "Insufficient balance", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // deduct amount from balance and show updated balance
                    balance -= amount;
                    balanceLabel.setText("Balance: " + balance);

                    // add transaction to transaction history
                    String transaction = "Transfer of $" + amount + " to User " + recipientID;
                    transactionHistory.add(transaction);
                    showTransactionHistory();
                }
            } catch (NumberFormatException ex) {
                // show error message for invalid input
                JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == quitButton) {
            // exit the application
            System.exit(0);
        }
    }

    private void showTransactionHistory() {
        // show transaction history in

        // transaction history area
        String history = "";
        for (int i = 0; i < transactionHistory.size(); i++) {
            history += transactionHistory.get(i) + "\n";
        }
        transactionHistoryArea.setText(history);
    }

    public static void main(String[] args) {
        new ATMGUI().setVisible(true);
    }
}