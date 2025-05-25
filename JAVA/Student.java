public class Student {
    private String studentId;
    private String name;
    private double balance;
    private String email;
    private String className;
    private String section;

    public Student(String studentId, String name, double balance, String email, String className, String section) {
        this.studentId = studentId;
        this.name = name;
        this.balance = balance;
        this.email = email;
        this.className = className;
        this.section = section;
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public String getEmail() { return email; }
    public String getClassName() { return className; }
    public String getSection() { return section; }

    @Override
    public String toString() {
        return "Student[ID=" + studentId + ", Name=" + name + ", Email=" + email +
               ", Class=" + className + ", Section=" + section + ", Balance=" + balance + "]";
    }
}
