<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment Flow</title>
    <link rel="stylesheet" type="text/css" href="/CSS/payment.css">
	
	<script>
			        window.onload = function() {
			            const message = "<%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>";
			            if (message) {
			                alert(message);
			            }
			        };
			    </script>
    
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
                        <option value="Bank A">Bank A</option>
                        <option value="Bank B">Bank B</option>
                        <option value="Bank C">Bank C</option>
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
                    <input type="text" name="expiryDate" placeholder="MM/YY" maxlength="5" pattern="^(0[1-9]|1[0-2])\/\d{2}$">
                    <label for="cvv">CVV:</label>
                    <input type="password" name="cvv" placeholder="Enter CVV" maxlength="3">
					<label for="cardHolderName">Card Holder Name:</label>
                    <input type="text" name="cardHolderName" placeholder="Enter Card Holder Name">
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
                <label for="amount">Enter Amount:</label>
                <input type="number" name="amount" min="0" required>
                <button type="submit">Proceed to Payment</button>
            </form>
        </div>
    </div>
</body>
<script>
			function showPaymentDetails(paymentMethod) {
			console.log("Function called with paymentMethod:", paymentMethod);
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
				console.log("Hidden Payment Method Input Value:", document.getElementsByName("paymentMethodInput")[0].value);;
			
				
		        // Set the required attribute for the relevant field
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
</html>
