package Transaction.system;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Account a1 = new Account();
        a1.name = "DJ";
        a1.balance = 1000;
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT balance FROM accounts WHERE acc_no=?"
            );

            ps.setInt(1, 101);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                a1.balance = rs.getDouble("balance");
                System.out.println("Loaded balance from DB: " + a1.balance);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        Account a2 = new Account();
        a2.name = "Aman";
        a2.balance = 500;

        int choice;

        do {
            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.println("6. View Transactions");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter amount: ");
                    double d = sc.nextDouble();
                    a1.deposit(d);

                    try {
                        Connection con = DBConnection.getConnection();

                        // UPDATE balance
                        PreparedStatement ps = con.prepareStatement(
                            "UPDATE accounts SET balance=? WHERE acc_no=?"
                        );
                        ps.setDouble(1, a1.balance);
                        ps.setInt(2, 101);
                        ps.executeUpdate();

                        // INSERT transaction
                        PreparedStatement ps2 = con.prepareStatement(
                            "INSERT INTO transactions(type, amount) VALUES (?, ?)"
                        );
                        ps2.setString(1, "DEPOSIT");
                        ps2.setDouble(2, d);
                        ps2.executeUpdate();

                        System.out.println("Balance updated & saved");

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    break;

                case 2:
                    System.out.print("Enter amount: ");
                    double w = sc.nextDouble();
                    a1.withdraw(w);

                    try {
                        Connection con = DBConnection.getConnection();

                        PreparedStatement ps = con.prepareStatement(
                            "UPDATE accounts SET balance=? WHERE acc_no=?"
                        );
                        ps.setDouble(1, a1.balance);
                        ps.setInt(2, 101);
                        ps.executeUpdate();

                        PreparedStatement ps2 = con.prepareStatement(
                            "INSERT INTO transactions(type, amount) VALUES (?, ?)"
                        );
                        ps2.setString(1, "WITHDRAW");
                        ps2.setDouble(2, w);
                        ps2.executeUpdate();

                        System.out.println("Balance updated & saved");

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    break;

                case 3:
                    System.out.print("Enter amount: ");
                    double t = sc.nextDouble();
                    a1.transfer(a2, t);

                    try {
                        Connection con = DBConnection.getConnection();

                        PreparedStatement ps = con.prepareStatement(
                            "UPDATE accounts SET balance=? WHERE acc_no=?"
                        );
                        ps.setDouble(1, a1.balance);
                        ps.setInt(2, 101);
                        ps.executeUpdate();

                        PreparedStatement ps2 = con.prepareStatement(
                            "INSERT INTO transactions(type, amount) VALUES (?, ?)"
                        );
                        ps2.setString(1, "TRANSFER");
                        ps2.setDouble(2, t);
                        ps2.executeUpdate();

                        System.out.println("Transfer saved");

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    break;

                case 4:
                    try {
                        Connection con = DBConnection.getConnection();

                        PreparedStatement ps = con.prepareStatement(
                            "SELECT balance FROM accounts WHERE acc_no=?"
                        );

                        ps.setInt(1, 101);

                        ResultSet rs = ps.executeQuery();

                        if (rs.next()) {
                            double bal = rs.getDouble("balance");
                            System.out.println("Current Balance: " + bal);
                        }

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;

                case 6:
                    try {
                        Connection con = DBConnection.getConnection();

                        PreparedStatement ps = con.prepareStatement(
                            "SELECT * FROM transactions"
                        );

                        ResultSet rs = ps.executeQuery();

                        System.out.println("\n--- Transaction History ---");

                        while (rs.next()) {
                            System.out.println(
                                rs.getInt("id") + " | " +
                                rs.getString("type") + " | " +
                                rs.getDouble("amount") + " | " +
                                rs.getTimestamp("date")
                            );
                        }

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;

                case 5:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 5);

        sc.close();
    }
}