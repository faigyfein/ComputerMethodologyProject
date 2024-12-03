package vacationPlanner;

import java.time.LocalDateTime;

public class Activity implements Comparable<Activity> {
		private String activityName;
		private LocalDateTime activityTime;
		private double price;
		
		
		public Activity(String activityName, double price, LocalDateTime time) {
			if (price < 0) {
				throw new NegativeDataException("Invalid. The price cannot be a negative number.");
			}
			this.activityName = activityName;
			this.price = price;
			this.activityTime = time;
		}
		
		public Activity(String activityName, double price, int year, int month, int day, int hour, int minute) {
			this(activityName, price, LocalDateTime.of(year, month, day, hour, minute));
		}
		
		
		// Copy Constructor
		public Activity(Activity activity) {
			this(activity.getActivityName(), activity.price, activity.activityTime);
		}
		
		public String getActivityName() {
		    return activityName;
		}
		public double getPrice() {
			return price;
		}
		
		public String toString() {
			StringBuilder str = new StringBuilder();
			str.append("- Time: " + activityTime);
			str.append(", Activity: " + activityName);
			str.append(", Price: " + String.format("$%.2f", getPrice()));
			return str.toString();
		}

		@Override
		public int compareTo(Activity o) {
			return this.activityTime.compareTo(o.activityTime);
		}
}
