package vacationPlanner;

import java.util.ArrayList;

public class Payment {
	Friend payer;
	Friend recipient;
	double amount;

	public Payment(Friend payer, Friend reciever, double amount) {
		this.payer=payer;
		this.recipient=reciever;
		this.amount=amount;
	}
	public Friend getPayer() {
		return payer;
	}
	public Friend getRecipient() {
		return recipient;
	}
	public double getAmount() {
		return amount;
	}
	public void setPayer(Friend payer) {
		this.payer=payer;
	}
	public void setRecipient(Friend recipient) {
		this.recipient=recipient;
	}
	public void setAmount(double amount) {
		this.amount=amount;
	}
	@Override
	public String toString(){
	        StringBuilder str=new StringBuilder(payer.getFriendName()+" owes "+ recipient.getFriendName()+" $"+amount);//create StringBuilder object with info
	        return str.toString();//returns str
	    }

}
