package vacationPlanner;

public class NegativeDataException extends RuntimeException {
	public NegativeDataException() {
		super("Invalid input.");
	}
	
	public NegativeDataException(String message) {
		super(message);
	}

}
