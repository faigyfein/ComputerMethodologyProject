package vacationPlanner;

public class Item {
	private String itemName;
	private double price;
	private int quantity;
	
	// Do we want to have the possiblity of making an item that is "unknown"?
	public Item() {
		this("unknown", 0, 0);
	}
	
	public Item(String itemName, double price, int quantity) {
		if (price < 0) {
			throw new NegativeDataException("Invalid. The price cannot be a negative number.");
		}
		if (quantity < 1) {
			throw new NegativeDataException("Invalid. The quantity has to be at least a value of 1.");
		}
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
	
	public Item(String itemName, double price) {
		this(itemName, price, 1);
	}
	
	// Copy Constructor
	public Item(Item item) {
		this(item.getItemName(), item.price, item.quantity);
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
