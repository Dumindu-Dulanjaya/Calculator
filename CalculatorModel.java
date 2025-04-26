public class CalculatorModel {
    private double answer;

    public void calculate(double firstNumber, double secondNumber, String operation) {
        switch (operation) {
            case "+": answer = firstNumber + secondNumber; break;
            case "-": answer = firstNumber - secondNumber; break;
            case "*": answer = firstNumber * secondNumber; break;
            case "/": 
                if (secondNumber == 0) throw new ArithmeticException("Division by zero!");
                answer = firstNumber / secondNumber; 
                break;
            case "%": answer = firstNumber % secondNumber; break;
            case "^": answer = Math.pow(firstNumber, secondNumber); break;
            default: throw new IllegalArgumentException("Invalid operation!");
        }
    }

    public double getAnswer() { return answer; }
}