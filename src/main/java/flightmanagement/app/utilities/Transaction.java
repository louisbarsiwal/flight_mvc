package flightmanagement.app.utilities;

import java.util.Random;

public class Transaction {

	public static String generateNumericTransactionId(int length) {
        Random random = new Random();
        StringBuilder transactionId = new StringBuilder(length);
        
        for (int i = 0; i < length; i++) {
            transactionId.append(random.nextInt(10)); // Append a random digit (0-9)
        }
        
        return transactionId.toString();
    }
}
