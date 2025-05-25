 import java.util.Date;

public class Payment {
    private String paymentId;
    private String studentId;
    private Date paymentDate;
    private double amount;

    public Payment(String paymentId, String studentId, Date paymentDate, double amount) {
        this.paymentId = paymentId;
        this.studentId = studentId;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    public String getPaymentId() { return paymentId; }
    public String getStudentId() { return studentId; }
    public Date getPaymentDate() { return paymentDate; }
    public double getAmount() { return amount; }
} 

