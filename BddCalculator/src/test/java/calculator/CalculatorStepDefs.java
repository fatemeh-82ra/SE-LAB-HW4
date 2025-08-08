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
}