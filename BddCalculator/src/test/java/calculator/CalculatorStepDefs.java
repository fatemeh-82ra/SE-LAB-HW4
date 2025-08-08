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

}