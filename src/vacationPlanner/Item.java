package vacationPlanner;

public class Item {
	private String itemName;
	private double price;
	private int quantity;
	
	public Item() {
		itemName = "unknown";
		price = 0;
		quantity = 0;
	}
	
	public Item(String itemName, double price, int quantity) {
		this.itemName = itemName;
		if (price < 0) {
			throw new NegativeDataException("Invalid. The price cannot be a negative number.");
		}
		this.price = price;
		if (quantity < 1) {
			throw new NegativeDataException("Invalid. The quantity has to be at least a value of 1.");
		}
		this.quantity = quantity;
	}
	
	public Item(String itemName, double price) {
		this.itemName = itemName;
		if (price < 0) {
			throw new NegativeDataException("Invalid. The price cannot be a negative number.");
		}
		this.price = price;
	}
	
	public String getItemName() {
	    return itemName;
	}
	
	public double getTotalPrice(String item) {
		return price * quantity;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Item: " + quantity + " " + itemName + ", Price: $");
		str.append(String.format("%.2f", getTotalPrice(itemName)));
		return str.toString();
	}
}
