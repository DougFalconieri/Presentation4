package step_definitions;

import cucumber.api.java8.En;
import implementation.MathHelper;
import static org.junit.Assert.*;

public class MathStepDefinitions implements En {
	private int inputNumber;
	private int result;
	
	public MathStepDefinitions() {
		Given("^the number (\\d+)$", (Integer arg1) -> {
		    inputNumber = arg1.intValue();
		});

		When("^I double the number$", () -> {
		    MathHelper helper = new MathHelper();
		    result = helper.doubleValue(inputNumber);
		});

		Then("^I should get (\\d+)$", (Integer arg1) -> {
			assertEquals(arg1.intValue(), result);
		});
	}
	
}
