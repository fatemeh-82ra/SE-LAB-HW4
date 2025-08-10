package calculator;

public class Calculator {

    public Double calculate(Double operand1, Double operand2, String operator) {
        if (operand1 == null || operand2 == null || operator == null) {
            throw new IllegalArgumentException("Operands and operator must not be null");
        }

        switch (operator) {
            case "+":
                return operand1 + operand2;

            case "-":
                return operand1 - operand2;

            case "*":
                return operand1 * operand2;

            case "/":
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero is not allowed");
                }
                return operand1 / operand2;

            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

}