package christmas.domain;

public class ReceiptDTO {
	private int count;
	private int paymentPrice;
	
	ReceiptDTO(int count, int paymentPrice){
		this.count = count;
		this.paymentPrice = paymentPrice;
	}
	
	public int getCount() {
		return count;
	}
	
	public int getPaymentPrice() {
		return paymentPrice;
	}

}
