Feature: Calculator Operations

  As a user
  I want to perform basic mathematical calculations
  So that I can get the correct result

  Scenario Outline: Perform a mathematical calculation
    Given I have entered <operand1> into the calculator
    And I have entered <operand2> into the calculator
    When I press the <operator> button
    Then the result should be <result> on the screen

    Examples:
      | operand1 | operator | operand2 | result |
      # Addition cases
      | 5.0      | +        | 3.0      | 8.0    |
      | 10.0     | +        | -3.0     | 7.0    |
      | 2.5      | +        | 2.5      | 5.0    |
      # Subtraction cases
      | 5.0      | -        | 3.0      | 2.0    |
      | 10.0     | -        | -3.0     | 13.0   |
      | 2.5      | -        | 2.5      | 0.0    |
      # Multiplication cases
      | 5.0      | *        | 3.0      | 15.0   |
      | 10.0     | *        | -3.0     | -30.0  |
      | 2.5      | *        | 2.0      | 5.0    |
      # Division cases
      | 6.0      | /        | 3.0      | 2.0    |
      | 10.0     | /        | -2.0     | -5.0   |
      | 5.0      | /        | 2.0      | 2.5    |


  Scenario Outline: Attempting to divide by zero with various numbers
    Given I have entered <dividend> into the calculator
    And I have entered 0.0 into the calculator
    When I press the / button
    Then an error message "Division by zero is not allowed" should be displayed

    Examples:
      | dividend |
      | 10.0     |
      | -8.0     |
      | 0.0      |
      | 15.5     |