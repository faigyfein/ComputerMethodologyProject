package vacationPlanner;
import java.util.*;

public class Friend implements Comparable<Friend>{
	private String friendName;
	private ArrayList<Item> items;
	private ArrayList<Activity> activites;
	private String zelleInfo;
	
	public Friend(String name, String zelleInfo) {
		this(name, new ArrayList<Item>(),new ArrayList<Activity>(), zelleInfo );
	}
	public Friend(String name) {
		this(name, new ArrayList<Item>(), new ArrayList<Activity>(), null);
	}
	
	public Friend(String name, ArrayList<Item> items, ArrayList<Activity> activities, String zelleInfo) { //deep copy
		ArrayList<Item> items2 = new ArrayList<>();
		for(int i = 0; i < items.size(); i++) {
			items2.add(new Item(items.get(i)));
		}
		ArrayList<Activity> activities2 = new ArrayList<>();
		for(int i = 0; i < activities.size(); i++) {
			activities2.add(new Activity(activities.get(i)));
		}
		this.friendName = name;
		this.activites = activities2;
		this.items = items2;
		this.zelleInfo=zelleInfo;
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
	
	public ArrayList<Activity> getActivities() {
		// return a deep copy of the ArrayList
		ArrayList<Activity> copy = new ArrayList<>();
		for(int i = 0; i < activites.size(); i++) {
			copy.add(new Activity(this.activites.get(i)));
		}
		return copy;
	}
	
	public String getZelleInfo() {
		return zelleInfo;
	}
	
	public void addItem(Item item) {
		Item itemCopy = new Item(item);
		items.add(itemCopy);
	}
	public void addActivity(Activity activity) {
		Activity activityCopy = new Activity(activity);
		activites.add(activityCopy);
	}
	
	public double getTotalSpent() {
		 double totalSpent = 0;
		    for (Item item : items) {
		        totalSpent += item.getTotalPrice();
		    }
		    for(Activity activity : activites) {
		    	totalSpent += activity.getPrice();
		    }
		    return totalSpent;
	}
	
	@Override
	public int compareTo(Friend other) {
		Double thisTotal = this.getTotalSpent();
		Double otherTotal = other.getTotalSpent();
		return thisTotal.compareTo(otherTotal);
	}
	
	
}