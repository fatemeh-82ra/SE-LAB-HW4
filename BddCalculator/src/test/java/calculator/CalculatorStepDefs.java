package calculator;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CalculatorStepDefs {

    private Calculator calculator;
    private Double operand1;
    private Double operand2;
    private Double result;
    private Exception thrownException;

    // This method runs before each scenario to ensure a clean calculator
    @Before
    public void setup() {
        calculator = new Calculator();
        operand1 = null;
        operand2 = null;
        result = null;
        thrownException = null;
    }

    // This single step handles all cases of entering a number
    @Given("I have entered {double} into the calculator")
    public void i_have_entered_into_the_calculator(Double number) {
        if (operand1 == null) {
            operand1 = number;
        } else {
            operand2 = number;
        }
    }
    @When("^I press the (.+) button$")
    public void i_press_the_button(String operator) {
        try {
            result = calculator.calculate(operand1, operand2, operator);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("the result should be {double} on the screen")
    public void the_result_should_be_on_the_screen(Double expectedResult) {
        Assert.assertEquals(expectedResult, result);
    }

    @Then("an error message {string} should be displayed")
    public void an_error_message_should_be_displayed(String expectedMessage) {
        Assert.assertNotNull("Expected an exception to be thrown", thrownException);
        Assert.assertEquals(expectedMessage, thrownException.getMessage());
    }
}