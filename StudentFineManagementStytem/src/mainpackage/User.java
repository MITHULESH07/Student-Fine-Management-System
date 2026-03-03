package mainpackage;
import java.util.*;

public class User {
    private String email, name, pass, role;
    private Student st;

    public User(String email, String name, String pass, String role) {
        this.email = email; this.name = name; this.pass = pass; this.role = role;
    }

    public User(String email, String name, String pass, String role, Student st) {
        this.email = email; this.name = name; this.pass = pass; this.role = role; this.st = st;
    }

    public String getRole() { return role; }
    public String getEmail() { return email; }
    public String getPass() { return pass; }
    public String getName() { return name; }
    public Student getStudent() { return st; }

    public void display() {
        if(st != null) st.display();
        else System.out.println("Name: " + name + " | Role: " + role);
    }

    public void edit() {
        if(st == null) return;
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("1.ID 2.Name 3.Dept 4.Age 0.Exit");
            int op = sc.nextInt(); sc.nextLine();
            switch(op) {
                case 1: System.out.print("ID: "); st.setId(sc.nextLine()); break;
                case 2: System.out.print("Name: "); st.setName(sc.nextLine()); break;
                case 3: System.out.print("Dept: "); st.setDep(sc.nextLine()); break;
                case 4: System.out.print("Age: "); st.setAge(sc.nextInt()); sc.nextLine(); break;
                default: return;
            }
        }
    }

    public Payment addPayment() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Payment ID: "); String pid = sc.nextLine();
        System.out.print("Student ID: "); String sid = sc.nextLine();
        System.out.print("Student Name: "); String sname = sc.nextLine();
        System.out.print("Amount: "); double amt = sc.nextDouble(); sc.nextLine();
        System.out.print("Fine Type: "); String ftype = sc.nextLine();
        
        return new Payment(pid, sid, sname, amt, ftype);
    }

    public void updatePayment(Payment p) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Payer ID: "); p.setPayerId(sc.nextLine());
        System.out.print("Payer Name: "); p.setPayerName(sc.nextLine());
        System.out.print("Amount: "); p.setAmount(sc.nextDouble()); sc.nextLine();
        System.out.print("Fine Type: "); p.setFineType(sc.nextLine());
        p.setDate(new Date());
        System.out.println("Payment updated!");
    }

    public void viewPayment(ArrayList<Payment> payments) {
        if(payments.isEmpty()) {
            System.out.println("No payments found!");
            return;
        }
        System.out.println("\n=== PAYMENTS ===");
        System.out.printf("%-12s %-12s %-15s %10s %-12s %s%n", 
            "PaymentID", "StudentID", "StudentName", "Amount", "FineType", "Date");
        System.out.println("-------------------------------------------------------------------");
        for(Payment p : payments) {
            System.out.printf("%-12s %-12s %-15s %10.2f %-12s %s%n",
                p.getPId(), p.getPayerId(), p.getPayerName(), p.getAmount(),
                p.getFineType(), p.getDate());
        }
    }
}
