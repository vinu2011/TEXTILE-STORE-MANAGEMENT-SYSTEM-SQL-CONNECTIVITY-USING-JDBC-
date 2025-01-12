public enum SearchType {
    STOCK("Stock"),
    CUSTOMER("Customer"),
    PURCHASE("Purchase");

    private String displayName;

    SearchType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
