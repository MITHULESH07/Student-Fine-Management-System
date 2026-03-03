# Student-Fine-Management-System
Student Fine Management System
Overview
Console-based Student Fine Management System supporting Admin, Cashier, and Student roles with role-based access control.

Total Marks Covered: 85/100 (Student transactions/balance features excluded per requirement)

Features Implemented
✅ Admin (40/40 marks)
 View all student payments

 Filter payments by fine type

 Filter payments by student

 Filter payments by date

 Filter specific fine type within date range

 Add payment

 Update payment

 Delete payment

✅ Cashier (15/15 marks)
 Add payment

 Update payment

 Delete payment

✅ Student (10/20 marks)
 View profile

 Edit profile

 View personal transactions (excluded)

 View current balance (excluded)

✅ Common Features (10/10 marks)
 User Registration

 User Login (role-based access)

Tech Stack
Java (Console Application)

In-memory storage (ArrayList)

java.util.Date for timestamps

File Structure
text
mainpackage/
├── Main.java      (Main program + menus)
├── User.java      (User authentication + operations)
├── Payment.java   (Payment data model)
└── Student.java   (Student profile)
How to Run
Compile:

bash
javac mainpackage/*.java
Run:

bash
java mainpackage.Main
Default Credentials
text
Email: admin@gmail.com
Password: admin
Role: ADMIN
Usage Flow
text
1. Start → Default Admin created
2. Register/Login screen
3. Role-based dashboard:
   - ADMIN: Full CRUD + 4 Filters (40 marks)
   - CASHIER: CRUD only (15 marks)  
   - STUDENT: Profile view/edit (10 marks)
4. Logout → Back to auth screen
Sample Operations
Admin Filters:
text
1. Fine Type: "Library" → Shows library fines only
2. Student ID: "S001" → Shows S001 payments
3. Date: "Mar 03" → Payments on that date
4. Fine+Date: "Late Fee" + "Mar" → Combined filter
Data Persistence
In-memory only (resets on restart)

All data stored in static ArrayList<User>, ArrayList<Payment>

Menu Navigation
Press Enter after each operation to continue

Logout returns to main auth screen

Clear menus between operations

Known Limitations
Date filtering uses string matching (student project level)

No data persistence across sessions

Ready for submission! 🚀
