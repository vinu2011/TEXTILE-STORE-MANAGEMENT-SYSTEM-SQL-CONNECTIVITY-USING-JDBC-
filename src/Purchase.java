public class Purchase {
    private int purchaseId;
    private int customerId;
    private int stockId;
    private int quantity;
    private double totalAmount;

    // Getters and Setters
    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String toString() {
        return "Purchase ID: " + purchaseId + ", Customer ID: " + customerId + ", Stock ID: " + stockId + ", Quantity: "
                + quantity;
    }
}
