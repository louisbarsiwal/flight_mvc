package flightmanagement.app.dao;

import flightmanagement.app.entities.Payment;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public interface PaymentDao {
    int savePayment(Payment payment) throws IOException, SerialException, SQLException;
    Payment getPaymentById(String transactionId) throws SQLException; // Optional: Add more methods as needed
}
