package vacationPlanner;

public class DuplicateNameException extends RuntimeException {

	public DuplicateNameException() {
		super("This name already exists.");
	}

	public DuplicateNameException(String message) {
		super(message);
	}
}
