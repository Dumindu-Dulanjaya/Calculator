import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {
    private CalculatorView theView;
    private CalculatorModel theModel;

    public CalculatorController(CalculatorView theView, CalculatorModel theModel) {
        this.theView = theView;
        this.theModel = theModel;

        // Calculate Button Listener
        theView.addCalculateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = theView.getFirstNumber();
                    double num2 = theView.getSecondNumber();
                    String op = theView.getOperation();

                    theModel.calculate(num1, num2, op);
                    double result = theModel.getAnswer();
                    theView.setCalcSolution(result);
                    
                    // Add to history
                    theView.addHistoryEntry(num1 + " " + op + " " + num2 + " = " + result);
                } catch (NumberFormatException ex) {
                    theView.displayErrorMessage("Invalid input! Enter numbers.");
                } catch (ArithmeticException ex) {
                    theView.displayErrorMessage(ex.getMessage());
                } catch (IllegalArgumentException ex) {
                    theView.displayErrorMessage(ex.getMessage());
                }
            }
        });

        // Clear Button Listener
        theView.addClearListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theView.clearInputs();
            }
        });

        // Dark/Light Mode Toggle
        theView.addThemeToggleListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isDark = theView.getThemeToggle().isSelected();
                theView.setTheme(isDark);
            }
        });
    }
}