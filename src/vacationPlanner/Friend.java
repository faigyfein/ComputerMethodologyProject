package vacationPlanner;
import java.util.*;

public class Friend implements Comparable<Friend>{
	private String friendName;
	private ArrayList<Item> items;
	
	public Friend(String name) {
		this(name, new ArrayList<Item>());
	}
	
	public Friend(String name, ArrayList<Item> items) { //deep copy
		ArrayList<Item> items2 = new ArrayList<>();
		for(int i = 0; i < items.size(); i++) {
			items2.add(new Item(items.get(i)));
		}
		this.friendName = name;
		this.items = items2;
	}
	
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}
	
	public String getFriendName() {
		return friendName;
	}
	
	public ArrayList<Item> getItems() {
		// return a deep copy of the ArrayList
		ArrayList<Item> copy = new ArrayList<>();
		for(int i = 0; i < items.size(); i++) {
			copy.add(new Item(this.items.get(i)));
		}
		return copy;
	}
	
	public void addItem(Item item) {
		Item itemCopy = new Item(item);
		items.add(itemCopy);
	}
	
	
	public double getTotalSpent() {
		 double totalSpent = 0;
		    for (Item item : items) {
		        totalSpent += item.getTotalPrice(item.getItemName());
		    }
		    return totalSpent;
	}
	
	@Override
	public int compareTo(Friend other) {
		//TODO
		return 0;
	}
	
	
}
