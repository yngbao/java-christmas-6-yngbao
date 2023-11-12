package christmas.validation;

public class Validation {
	
	public void validateInputs(String[] inputs) {
		for(String input : inputs)
		if (!input.matches("\\S+-\\d+")) {
			throw new IllegalArgumentException();
		}
	}
}
