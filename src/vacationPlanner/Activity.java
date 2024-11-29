package vacationPlanner;

public class Activity {
		private String activityName;
		private double price;
		
		// Do we want to have the possibility of making an Activity that is "unknown"?
		public Activity() {
			this("unknown", 0);
		}
		
		public Activity(String activityName, double price) {
			if (price < 0) {
				throw new NegativeDataException("Invalid. The price cannot be a negative number.");
			}
			this.activityName = activityName;
			this.price = price;
		}
		
		// Copy Constructor
		public Activity(Activity activity) {
			this(activity.getActivityName(), activity.price);
		}
		
		public String getActivityName() {
		    return activityName;
		}
		public double getPrice() {
			return price;
		}
		
		public String toString() {
			StringBuilder str = new StringBuilder();
			str.append("- Activity: " + activityName);
			str.append(", Price: " + String.format("$%.2f", getPrice()));
			return str.toString();
		}
}
