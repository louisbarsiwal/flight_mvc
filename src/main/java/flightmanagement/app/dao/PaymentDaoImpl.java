package flightmanagement.app.dao;

import flightmanagement.app.entities.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

@Repository
public class PaymentDaoImpl implements PaymentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int savePayment(Payment payment)throws IOException, SerialException, SQLException {
        String sql = "INSERT INTO payment_details (transaction_id, payment_method, bank_name, " +
                     "card_number, expiry_date, card_holder_name, upi_id, wallet_name, amount, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

 
            return jdbcTemplate.update(sql, payment.getTransactionId(), payment.getPaymentMethod(),
                    payment.getBankName(), payment.getCardNumber(), 
                    payment.getExpiryDate(), // Assuming you have this field in your Payment entity
                    payment.getCardHolderName(), // Updated field
                    payment.getUpiId(),
                    payment.getWalletName(), 
                    payment.getAmount(), 
                    payment.getStatus());
		
    }

    @Override
    public Payment getPaymentById(String transactionId) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }
}
