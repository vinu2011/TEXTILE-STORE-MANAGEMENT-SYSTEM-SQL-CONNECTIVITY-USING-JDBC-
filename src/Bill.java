import java.util.Date;

public class Bill {
    private int billId;
    private int purchaseId;
    private double totalAmount;
    private Date date; // Add this field

    // Getters and Setters

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", purchaseId=" + purchaseId +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
