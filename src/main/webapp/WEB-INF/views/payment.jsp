<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Payment Flow</title>
    <link rel="stylesheet" type="text/css" href="/CSS/payment.css">
</head>
<body>
    <div class="container">
        <div class="sidebar">
            <h2>Payment Options</h2>
            <ul>
                <li onclick="showPaymentDetails('netbanking')">Net Banking</li>
                <li onclick="showPaymentDetails('upi')">UPI</li>
                <li onclick="showPaymentDetails('credit')">Credit Card</li>
                <li onclick="showPaymentDetails('debit')">Debit Card</li>
                <li onclick="showPaymentDetails('wallet')">Wallet</li>
            </ul>
        </div>
        <div class="content">
            <h1>Payment Here</h1>
            <form action="/payment/submit" method="POST">
                <input type="hidden" id="paymentMethodInput" name="paymentMethod" required>
                <div id="bankDropdown" class="hidden">
                    <label for="bankName">Select Bank:</label>
                    <select id="bankName" name="bankName">
						<option value="State Bank of India">State Bank of India</option>
						<option value="HDFC Bank">HDFC Bank</option>
						<option value="ICICI Bank">ICICI Bank</option>
						<option value="Punjab National Bank">Punjab National Bank</option>
						<option value="Axis Bank">Axis Bank</option>
						<option value="Bank of Baroda">Bank of Baroda</option>
						<option value="Kotak Mahindra Bank">Kotak Mahindra Bank</option>
						<option value="Canara Bank">Canara Bank</option>
						<option value="Union Bank of India">Union Bank of India</option>
						<option value="IndusInd Bank">IndusInd Bank</option>
						<option value="IDFC First Bank">IDFC First Bank</option>
						<option value="Bank of India">Bank of India</option>
						<option value="Central Bank of India">Central Bank of India</option>
						<option value="Indian Bank">Indian Bank</option>
						<option value="Yes Bank">Yes Bank</option>
                    </select>
                </div>
                <div id="upiInput" class="hidden">
                    <label for="upiId">Enter UPI ID:</label>
                    <input type="text" name="upiId" placeholder="example@upi">
                </div>
                <div id="cardDetails" class="hidden">
                    <label for="cardNumber">Card Number:</label>
                    <input type="text" name="cardNumber" id="cardNumber" placeholder="0000-0000-0000-0000" oninput="formatCardNumber(this)" maxlength="19" pattern="^(\d{4}-){3}\d{4}$" required>
                    <label for="expiryDate">Expiry Date (MM/YY):</label>
                    <input type="text" name="expiryDate" placeholder="MM/YY" maxlength="5" pattern="^(0[1-9]|1[0-2])\/\d{2}$" required>
                    <label for="cvv">CVV:</label>
                    <input type="password" name="cvv" placeholder="Enter CVV" maxlength="3" required>
                    <label for="cardHolderName">Card Holder Name:</label>
                    <input type="text" name="cardHolderName" placeholder="Enter Card Holder Name" required>
                </div>
                <div id="walletDropdown" class="hidden">
                    <label for="walletName">Select Wallet:</label>
                    <select name="walletName">
                        <option value="" disabled selected>Select Wallet...</option>
                        <option value="Amazon Pay">Amazon Pay</option>
                        <option value="JIO Money">JIO Money</option>
                        <option value="Freecharge">Freecharge</option>
                        <option value="Payzapp">Payzapp</option>
                        <option value="MobiKwik">MobiKwik</option>
                    </select>
                </div>
                <label for="amount">Total Amount:</label>
                <input type="number" name="amount" value="<%= request.getParameter("totalFare") != null ? request.getParameter("totalFare") : 0 %>" readonly required>
                <button type="submit">Proceed to Payment</button>
            </form>
        </div>
    </div>
    <script>
        function showPaymentDetails(paymentMethod) {
            const fields = {
                netbanking: document.getElementById("bankDropdown"),
                upi: document.getElementById("upiInput"),
                credit: document.getElementById("cardDetails"),
                debit: document.getElementById("cardDetails"),
                wallet: document.getElementById("walletDropdown"),
            };
            // Hide all fields initially
            Object.values(fields).forEach(field => field.classList.add("hidden"));
            
            // Reset fields and remove required attributes
            const inputs = {
                bankName: document.getElementById("bankName"),
                upiId: document.getElementsByName("upiId")[0],
                cardNumber: document.getElementsByName("cardNumber")[0],
                expiryDate: document.getElementsByName("expiryDate")[0],
                cvv: document.getElementsByName("cvv")[0],
                cardHolderName: document.getElementsByName("cardHolderName")[0],
                walletName: document.getElementsByName("walletName")[0],
            };
            
            Object.values(inputs).forEach(input => {
                input.value = "";
                input.removeAttribute("required");
            });
 
            // Show relevant field and set required attributes
            if (paymentMethod in fields) {
                fields[paymentMethod].classList.remove("hidden");
                document.getElementById("paymentMethodInput").value = paymentMethod;
                
                inputs[paymentMethod === "netbanking" ? "bankName" :
                       paymentMethod === "upi" ? "upiId" :
                       paymentMethod === "wallet" ? "walletName" :
                       "cardNumber"].setAttribute("required", "required");
                
                if (paymentMethod === "credit" || paymentMethod === "debit") {
                    inputs.cardHolderName.setAttribute("required", "required");
                    inputs.expiryDate.setAttribute("required", "required");
                    inputs.cvv.setAttribute("required", "required");
                }
            }
        }
    </script>
</body>
</html>