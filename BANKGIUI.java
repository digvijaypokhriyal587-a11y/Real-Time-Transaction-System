package Transaction.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class BANKGIUI {

    static Account a1 = new Account();

    public static void main(String[] args) {

        a1.name = "DJ";
        a1.balance = 1000;

        JFrame frame = new JFrame("Bank App");
        frame.setSize(450, 520);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(245, 245, 245));

        JLabel title = new JLabel("Bank Dashboard");
        title.setBounds(130, 20, 250, 30);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JTextField amountField = new JTextField();
        amountField.setBounds(100, 70, 250, 35);
        amountField.setBorder(BorderFactory.createTitledBorder("Enter Amount"));

        JTextArea output = new JTextArea();
        output.setBounds(50, 400, 350, 70);
        output.setEditable(false);

        // Buttons
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton transferBtn = new JButton("Transfer");
        JButton balanceBtn = new JButton("Check Balance");
        JButton historyBtn = new JButton("History");

        JButton[] btns = {depositBtn, withdrawBtn, transferBtn, balanceBtn, historyBtn};

        int y = 120;
        for (JButton b : btns) {
            b.setBounds(100, y, 250, 40);
            b.setFont(new Font("Segoe UI", Font.BOLD, 14));
            b.setBackground(new Color(98, 0, 238));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setBorder(BorderFactory.createEmptyBorder());
            y += 50;
            frame.add(b);
        }

        frame.add(title);
        frame.add(amountField);
        frame.add(output);

        // 🔥 Deposit
        depositBtn.addActionListener(e -> {
            try {
                double amt = Double.parseDouble(amountField.getText());
                a1.deposit(amt);

                Connection con = DBConnection.getConnection();

                PreparedStatement ps = con.prepareStatement(
                        "UPDATE accounts SET balance=? WHERE acc_no=?");
                ps.setDouble(1, a1.balance);
                ps.setInt(2, 101);
                ps.executeUpdate();

                PreparedStatement ps2 = con.prepareStatement(
                        "INSERT INTO transactions(type, amount) VALUES (?, ?)");
                ps2.setString(1, "DEPOSIT");
                ps2.setDouble(2, amt);
                ps2.executeUpdate();

                output.setText("✅ Deposited ₹" + amt);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input");
            }
        });

        // 🔥 Withdraw
        withdrawBtn.addActionListener(e -> {
            try {
                double amt = Double.parseDouble(amountField.getText());

                if (a1.balance < amt) {
                    JOptionPane.showMessageDialog(frame, "❌ Insufficient Balance");
                    return;
                }

                a1.withdraw(amt);

                Connection con = DBConnection.getConnection();

                PreparedStatement ps = con.prepareStatement(
                        "UPDATE accounts SET balance=? WHERE acc_no=?");
                ps.setDouble(1, a1.balance);
                ps.setInt(2, 101);
                ps.executeUpdate();

                PreparedStatement ps2 = con.prepareStatement(
                        "INSERT INTO transactions(type, amount) VALUES (?, ?)");
                ps2.setString(1, "WITHDRAW");
                ps2.setDouble(2, amt);
                ps2.executeUpdate();

                output.setText("💸 Withdrawn ₹" + amt);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input");
            }
        });

        // 🔥 Transfer (Dynamic User)
        transferBtn.addActionListener(e -> {
            try {
                double amt = Double.parseDouble(amountField.getText());

                String accInput = JOptionPane.showInputDialog("Enter Receiver Account No:");
                int receiverAcc = Integer.parseInt(accInput);

                String nameInput = JOptionPane.showInputDialog("Enter Receiver Name:");

                Connection con = DBConnection.getConnection();

                PreparedStatement check = con.prepareStatement(
                        "SELECT * FROM accounts WHERE acc_no=? AND name=?");
                check.setInt(1, receiverAcc);
                check.setString(2, nameInput);

                ResultSet rs = check.executeQuery();

                if (!rs.next()) {
                    JOptionPane.showMessageDialog(frame, "❌ User not found");
                    return;
                }

                double receiverBalance = rs.getDouble("balance");

                if (a1.balance < amt) {
                    JOptionPane.showMessageDialog(frame, "❌ Insufficient Balance");
                    return;
                }

                // Sender update
                a1.balance -= amt;

                PreparedStatement ps1 = con.prepareStatement(
                        "UPDATE accounts SET balance=? WHERE acc_no=?");
                ps1.setDouble(1, a1.balance);
                ps1.setInt(2, 101);
                ps1.executeUpdate();

                // Receiver update
                receiverBalance += amt;

                PreparedStatement ps2 = con.prepareStatement(
                        "UPDATE accounts SET balance=? WHERE acc_no=?");
                ps2.setDouble(1, receiverBalance);
                ps2.setInt(2, receiverAcc);
                ps2.executeUpdate();

                // Save transaction
                PreparedStatement ps3 = con.prepareStatement(
                        "INSERT INTO transactions(type, amount) VALUES (?, ?)");
                ps3.setString(1, "TRANSFER");
                ps3.setDouble(2, amt);
                ps3.executeUpdate();

                output.setText("🔄 Sent ₹" + amt + " to " + nameInput);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input");
            }
        });

        // 🔥 Balance
        balanceBtn.addActionListener(e -> {
            try {
                Connection con = DBConnection.getConnection();

                PreparedStatement ps = con.prepareStatement(
                        "SELECT balance FROM accounts WHERE acc_no=?");
                ps.setInt(1, 101);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    output.setText("💰 Balance ₹" + rs.getDouble("balance"));
                }

            } catch (Exception ex) {
                output.setText(ex.toString());
            }
        });

        // 🔥 History
        historyBtn.addActionListener(e -> {
            JFrame h = new JFrame("Transaction History");
            h.setSize(350, 300);

            JTextArea area = new JTextArea();
            JScrollPane sp = new JScrollPane(area);

            try {
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM transactions");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    area.append(
                            rs.getString("type") + " ₹" +
                            rs.getDouble("amount") + "\n"
                    );
                }

            } catch (Exception ex) {
                area.setText(ex.toString());
            }

            h.add(sp);
            h.setVisible(true);
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}