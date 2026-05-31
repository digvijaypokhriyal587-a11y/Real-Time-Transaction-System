package Transaction.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LOGINGUI {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Login");
        frame.setSize(350, 250);
        frame.setLayout(new GridLayout(4, 1, 10, 10));
        frame.getContentPane().setBackground(new Color(30, 30, 30));

        JLabel title = new JLabel("🔐 Bank Login", JLabel.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();

        JButton loginBtn = new JButton("Login");

        frame.add(title);
        frame.add(userField);
        frame.add(passField);
        frame.add(loginBtn);

        // 🔥 LOGIN LOGIC
        loginBtn.addActionListener(e -> {

            String username = userField.getText();
            String password = new String(passField.getPassword());

            try {
                Connection con = DBConnection.getConnection();

                PreparedStatement ps = con.prepareStatement(
                        "SELECT * FROM users WHERE username=? AND password=?");

                ps.setString(1, username);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(frame, "Login Successful ✅");

                    frame.dispose(); // close login
                    BANKGIUI.main(null); // open banking app

                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Credentials ❌");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.toString());
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}