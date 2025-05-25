import java.sql.*;
//import java.util.Date;

public class SchoolPaymentSystem {

    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (student_id, name, email, class, section, balance) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getStudentId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getClassName());
            pstmt.setString(5, student.getSection());
            pstmt.setDouble(6, student.getBalance());

            pstmt.executeUpdate();
            System.out.println("Student added successfully.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
            return false;
        }
    }

    public void makePayment(Payment payment) {
        String insertSQL = "INSERT INTO payments (payment_id, student_id, payment_date, amount) VALUES (?, ?, ?, ?)";
        String updateSQL = "UPDATE students SET balance = balance - ? WHERE student_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement insertStmt = conn.prepareStatement(insertSQL);
             PreparedStatement updateStmt = conn.prepareStatement(updateSQL)) {

            conn.setAutoCommit(false);
            if (payment.getPaymentId().length() > 100) {
            throw new IllegalArgumentException("payment_id too long: " + payment.getPaymentId().length());
        }

            insertStmt.setString(1, payment.getPaymentId());
            insertStmt.setString(2, payment.getStudentId());
            insertStmt.setDate(3, new java.sql.Date(payment.getPaymentDate().getTime()));
            insertStmt.setDouble(4, payment.getAmount());
            insertStmt.executeUpdate();

            updateStmt.setDouble(1, payment.getAmount());
            updateStmt.setString(2, payment.getStudentId());
            updateStmt.executeUpdate();

            conn.commit();
            System.out.println("Payment recorded successfully.");

        } catch (SQLException e) {
            System.out.println("Error making payment: " + e.getMessage());
        }catch (IllegalArgumentException e) {
        System.out.println("Invalid payment data: " + e.getMessage());
    }
    }

    public double getBalance(String studentId) {
        String sql = "SELECT balance FROM students WHERE student_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("balance");
            } else {
                System.out.println("Student not found.");
                return 0.0;
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving balance: " + e.getMessage());
            return 0.0;
        }
    }

    public void generateReport() {
        String sql = "SELECT * FROM payments";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\nPayment Report:");
            while (rs.next()) {
                System.out.printf("Payment ID: %s, Student ID: %s, Date: %s, Amount: %.2f%n",
                        rs.getString("payment_id"),
                        rs.getString("student_id"),
                        rs.getDate("payment_date"),
                        rs.getDouble("amount"));
            }

        } catch (SQLException e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
    }

    public void listAllStudents() {
        String sql = "SELECT * FROM students";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\nStudent List:");
            while (rs.next()) {
                System.out.printf("ID: %s, Name: %s, Email: %s, Class: %s, Section: %s, Balance: %.2f%n",
                        rs.getString("student_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("className"),
                        rs.getString("section"),
                        rs.getDouble("balance"));
            }

        } catch (SQLException e) {
            System.out.println("Error listing students: " + e.getMessage());
        }
    }
}
