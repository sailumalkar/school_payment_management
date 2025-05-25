import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SchoolPaymentSystem system = new SchoolPaymentSystem();

        while (true) {
            System.out.println("\n--- School Payment Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Make Payment");
            System.out.println("3. Check Balance");
            System.out.println("4. Generate Payment Report");
            System.out.println("5. List All Students");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.nextLine();

                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter Class: ");
                    String className = scanner.nextLine();

                    System.out.print("Enter Section: ");
                    String section = scanner.nextLine();

                    System.out.print("Enter Initial Balance: ");
                    double balance = scanner.nextDouble();
                    scanner.nextLine(); // consume newline

                    Student student = new Student(studentId, name, balance,email, className, section);
                    system.addStudent(student);
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    String payStudentId = scanner.nextLine();

                    System.out.print("Enter Payment Amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline

                    String paymentId = UUID.randomUUID().toString();
                    Payment payment = new Payment(paymentId, payStudentId, new Date(), amount);
                    system.makePayment(payment);
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    String balStudentId = scanner.nextLine();
                    double currentBalance = system.getBalance(balStudentId);
                    System.out.println("Current Balance: " + currentBalance);
                    break;

                case 4:
                    system.generateReport();
                    break;

                case 5:
                    system.listAllStudents();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}

