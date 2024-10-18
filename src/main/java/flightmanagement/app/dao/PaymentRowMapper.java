package flightmanagement.app.dao;

import flightmanagement.app.entities.Payment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRowMapper implements RowMapper<Payment> {
    @Override
    public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Payment(
                rs.getString("transaction_id"),
                rs.getString("payment_method"),
                rs.getString("bank_name"),
                rs.getString("card_number"),
                rs.getString("expiry_date"), // Added expiry date
                rs.getString("card_holder_name"), // Updated field name
                rs.getString("upi_id"),
                rs.getString("wallet_name"),
                rs.getDouble("amount"),
                rs.getString("status")
        );
    }
}
