package flightmanagement.app.dao;

import flightmanagement.app.entities.Payment;
import java.sql.SQLException;

public interface PaymentDao {
    void savePayment(Payment payment) throws SQLException;
    Payment getPaymentById(String transactionId) throws SQLException; // Optional: Add more methods as needed
}
