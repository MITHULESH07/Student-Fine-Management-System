package mainpackage;
import java.util.*;
public class Payment {
    private String pId, payerId, payer;
    private double amount;
    private String fineType;
    private Date dt;

    public Payment(String pid, String payerId, String payer, double amount, String fineType) {
        this.pId = pid; this.payerId = payerId; this.payer = payer;
        this.amount = amount; this.fineType = fineType; this.dt = new Date();
    }

    public String getPId() { return pId; }
    public String getPayerId() { return payerId; }
    public String getPayerName() { return payer; }
    public double getAmount() { return amount; }
    public String getFineType() { return fineType; }
    public Date getDate() { return dt; }

    public void setPayerId(String payerId) { this.payerId = payerId; }
    public void setPayerName(String payerName) { this.payer = payerName; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setFineType(String fineType) { this.fineType = fineType; }
    public void setDate(Date dt) { this.dt = dt; }
}