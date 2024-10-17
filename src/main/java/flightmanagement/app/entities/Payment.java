package flightmanagement.app.entities;

public class Payment {
  
    private String transactionId;
    private String paymentMethod;
    private String bankName;
    private String cardNumber;
    private String expiryDate; // New field for expiry date
    private String cardHolderName; // New field for card holder name
    private String upiId;
    private String walletName;
    private double amount;
    private String status;

    public Payment() {}

    public Payment(String transactionId, String paymentMethod, String bankName, String cardNumber, 
                   String expiryDate, String cardHolderName, String upiId, String walletName, 
                   double amount, String status) {
        this.transactionId = transactionId;
        this.paymentMethod = paymentMethod;
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate; 
        this.cardHolderName = cardHolderName; 
        this.upiId = upiId;
        this.walletName = walletName;
        this.amount = amount;
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate; // Getter for expiry date
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate; // Setter for expiry date
    }

    public String getCardHolderName() {
        return cardHolderName; // Getter for card holder name
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName; // Setter for card holder name
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }
}
