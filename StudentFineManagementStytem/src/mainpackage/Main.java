package mainpackage;
import java.util.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<User> allUsers = new ArrayList<>();
    public static ArrayList<Payment> allPayments = new ArrayList<>();
    static User currentUser = null;

    public static void main(String args[]) {
        defaultAdmin();
        while(true) {
            if(currentUser == null) {
                currentUser = authUser();
            } else {
                String role = currentUser.getRole();
                switch(role) {
                    case "ADMIN": adminPanel(currentUser); break;
                    case "CASHIER": cashierPanel(currentUser); break;
                    case "STUDENT": studentPanel(currentUser); break;
                }
            }
        }
    }

    private static void defaultAdmin() {
        User dAdmin = new User("admin@gmail.com", "admin", "Admin", "ADMIN");
        allUsers.add(dAdmin);
        System.out.println("Default Admin: admin@gmail.com / admin");
    }

    private static User authUser() {
        System.out.print("\nRegister(1)/Login(2): ");
        int input = sc.nextInt();
        sc.nextLine();
        if(input == 1) return registerUser();
        else return loginUser();
    } 

    private static User registerUser() {
        String email, name, pass, role;
        System.out.print("Enter Email: ");
        email = sc.nextLine();
        System.out.print("Enter Name: ");
        name = sc.nextLine();
        System.out.print("Enter Password: ");
        pass = sc.nextLine();
        System.out.print("Enter Role (ADMIN/CASHIER/STUDENT): ");
        role = sc.nextLine();

        User user;
        if(role.equalsIgnoreCase("STUDENT")) {
            System.out.println("=== Student Details ===");
            System.out.print("Student ID: ");
            String sid = sc.nextLine();
            System.out.print("Student Name: ");
            String sname = sc.nextLine();
            System.out.print("Department: ");
            String sdep = sc.nextLine();
            System.out.print("Age: ");
            int sage = sc.nextInt();
            sc.nextLine();
            user = new User(email.trim(), name.trim(), pass.trim(), role.trim(), new Student(sid, sname, sdep, sage));
        } else {
            user = new User(email.trim(), name.trim(), pass.trim(), role.trim());
        }
        allUsers.add(user);
        System.out.println("User registered. Please login.");
        return loginUser();
    }

    private static User loginUser() {
        String email, pass;
        System.out.print("Email: ");
        email = sc.nextLine();
        System.out.print("Password: ");
        pass = sc.nextLine();

        for(User u : allUsers) {
            if(u.getEmail().equalsIgnoreCase(email.trim()) && u.getPass().equals(pass.trim())) {
                System.out.println("Login successful: " + u.getName());
                return u;
            }
        }
        System.out.println("Invalid credentials!");
        return null;
    }

    private static void adminPanel(User user) {
        System.out.println("\n=== ADMIN PANEL ===");
        while(true) {
            System.out.println("1.Add 2.Update 3.Delete 4.View All 5.Filter 6.Logout");
            int op = sc.nextInt(); sc.nextLine();
            switch(op) {
                case 1: { Payment p = user.addPayment(); if(p!=null) allPayments.add(p); break; }
                case 2: { 
                    System.out.print("Payment ID: "); 
                    String pid = sc.nextLine();
                    for(Payment p : allPayments) {
                        if(p.getPId().equals(pid)) { user.updatePayment(p); break; }
                    }
                    break;
                }
                case 3: {
                    System.out.print("Payment ID: "); 
                    String pid = sc.nextLine();
                    allPayments.removeIf(p -> p.getPId().equals(pid));
                    System.out.println("Payment removed");
                    break;
                }
                case 4: user.viewPayment(allPayments); break;
                case 5: filterPaymentsMenu(); break;
                case 6: {
                    currentUser = null;
                    System.out.println("Logged out successfully");
                    return;
                }

            }
            System.out.print("Press Enter...");
            sc.nextLine();
        }
    }

    private static void cashierPanel(User user) {
        System.out.println("\n=== CASHIER PANEL ===");
        while(true) {
            System.out.println("1.Add 2.Update 3.Delete 4.Logout");
            int op = sc.nextInt(); sc.nextLine();
            switch(op) {
                case 1: { Payment p = user.addPayment(); if(p!=null) allPayments.add(p); break; }
                case 2: { 
                    System.out.print("Payment ID: "); 
                    String pid = sc.nextLine();
                    for(Payment p : allPayments) {
                        if(p.getPId().equals(pid)) { user.updatePayment(p); break; }
                    }
                    break;
                }
                case 3: {
                    System.out.print("Payment ID: "); 
                    String pid = sc.nextLine();
                    allPayments.removeIf(p -> p.getPId().equals(pid));
                    break;
                }
                case 4: {
                    currentUser = null; 
                    System.out.println("Logged out successfully");
                    return;
                }

            }
            System.out.print("Press Enter...");
            sc.nextLine();
        }
    }

    private static void studentPanel(User user) {
        System.out.println("\n=== STUDENT PANEL ===");
        while(true) {
            System.out.println("1.View Profile 2.Edit Profile 3.Logout");
            int op = sc.nextInt(); sc.nextLine();
            switch(op) {
                case 1: user.display(); break;
                case 2: user.edit(); break;
                case 3: {
                    currentUser = null;
                    System.out.println("Logged out successfully");
                    return;
                }

            }
            System.out.print("Press Enter...");
            sc.nextLine();
        }
    }

    private static void filterPaymentsMenu() {
        System.out.println("1.Fine Type 2.Student 3.Date 4.Fine+Date");
        int choice = sc.nextInt(); sc.nextLine();
        ArrayList<Payment> filtered = new ArrayList<>();
        
        switch(choice) {
            case 1:
                System.out.print("Fine Type: "); String ftype = sc.nextLine();
                filtered = getPaymentsByFineType(ftype);
                break;
            case 2:
                System.out.print("Student ID: "); String sid = sc.nextLine();
                filtered = getPaymentsByStudent(sid);
                break;
            case 3:
                System.out.print("Date part: "); String datePart = sc.nextLine();
                filtered = getPaymentsByDate(datePart);
                break;
            case 4:
                System.out.print("Fine Type: "); String ftype2 = sc.nextLine();
                System.out.print("Start Date: "); String start = sc.nextLine();
                filtered = getPaymentsByFineDate(ftype2, start);
                break;
        }
        currentUser.viewPayment(filtered);
    }

    private static ArrayList<Payment> getPaymentsByFineType(String type) {
        ArrayList<Payment> result = new ArrayList<>();
        for(Payment p : allPayments) {
            if(p.getFineType().equalsIgnoreCase(type)) result.add(p);
        }
        return result;
    }

    private static ArrayList<Payment> getPaymentsByStudent(String id) {
        ArrayList<Payment> result = new ArrayList<>();
        for(Payment p : allPayments) {
            if(p.getPayerId().equals(id)) result.add(p);
        }
        return result;
    }

    private static ArrayList<Payment> getPaymentsByDate(String datePart) {
        ArrayList<Payment> result = new ArrayList<>();
        for(Payment p : allPayments) {
            if(p.getDate().toString().contains(datePart)) result.add(p);
        }
        return result;
    }

    private static ArrayList<Payment> getPaymentsByFineDate(String type, String datePart) {
        ArrayList<Payment> result = new ArrayList<>();
        for(Payment p : allPayments) {
            if(p.getFineType().equalsIgnoreCase(type) && p.getDate().toString().contains(datePart)) 
                result.add(p);
        }
        return result;
    }
}
