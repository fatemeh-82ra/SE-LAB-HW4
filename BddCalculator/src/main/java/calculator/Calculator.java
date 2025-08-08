package calculator;

public class Calculator {

    public Double calculate(Double operand1, Double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;

            default:
                // Handle cases where an unknown operator is used
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}