// base code for student budget assessment
// Students do not need to use this code in their assessment, fine to junk it and do something different!

// user can enter in wages and loans and calculate total income
//
// To see GUI, run with java and select Box Url from Codio top line menu
//
// Layout - Uses GridBag layout in a straightforward way, every component has a (column, row) position in the UI grid
// Not the prettiest layout, but relatively straightforward
// Students who use IntelliJ or Eclipse may want to use the UI designers in these IDEs , instead of GridBagLayout

// Ehud Reiter Aug 204

package Budget;

// Swing imports
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

// class definition
public class BudgetBase extends JPanel {    // based on Swing JPanel

    // high level UI stuff
    JFrame topLevelFrame;  // top-level JFrame
    GridBagConstraints layoutConstraints = new GridBagConstraints(); // used to control layout

    // widgets which may have listeners and/or values
    private JButton calculateButton;   // Calculate button
    private JButton exitButton;        // Exit button
    private JTextField wagesField;     // Wages text field
    private JTextField loansField;     // Loans text field
    private JTextField otherField;     // Other income field
    private JTextField totalIncomeField; // Total Income field
    private JTextField foodExpense; // Food expense field
    private JTextField rentExpense; // Rent expense field
    private JTextField restaurantExpense; // Restaurant expense field
    private JTextField totalExpenseField;

    // constructor - create UI  (dont need to change this)
    public BudgetBase(JFrame frame) {
        topLevelFrame = frame; // keep track of top-level frame
        setLayout(new GridBagLayout());  // use GridBag layout
        initComponents();  // initalise components
    }

    // initialise componenents
    // Note that this method is quite long.  Can be shortened by putting Action Listener stuff in a separate method
    // will be generated automatically by IntelliJ, Eclipse, etc
    private void initComponents() { 

        // Top row (0) - "INCOME" label & "EXPENSE" label
        JLabel incomeLabel = new JLabel("INCOME");
        addComponent(incomeLabel, 0, 0);
        JLabel expenseLabel = new JLabel("EXPENSE");
        addComponent(expenseLabel, 0, 2);

        // Row 1 - Wages label followed by wages textbox and food label followed by food textbox
        JLabel wagesLabel = new JLabel("Wages");
        addComponent(wagesLabel, 1, 0);
        JLabel foodLabel = new JLabel("Food");
        addComponent(foodLabel, 1, 2);

        // set up text field for entering wages
        // Could create method to do below (since this is done several times)
        wagesField = new JTextField("", 10);   // blank initially, with 10 columns
        wagesField.setHorizontalAlignment(JTextField.RIGHT) ;    // number is at right end of field
        addComponent(wagesField, 1, 1); // blank initially, with 10 columns  
        foodExpense = new JTextField("",10); 
        foodExpense.setHorizontalAlignment(JTextField.RIGHT) ;    // number is at right end of field
        addComponent(foodExpense, 1, 3);

        // Row 2 - Loans label followed by loans textbox and rent label followed by rent textbox
        JLabel loansLabel = new JLabel("Loans");
        addComponent(loansLabel, 2, 0);
        JLabel rentLabel = new JLabel("Rent");
        addComponent(rentLabel,2,2);

        // set up text box for entering loans and rent
        loansField = new JTextField("", 10);   // blank initially, with 10 columns
        loansField.setHorizontalAlignment(JTextField.RIGHT) ;    // number is at right end of field
        addComponent(loansField, 2, 1); 
        rentExpense = new JTextField("", 10);   // blank initially, with 10 columns
        rentExpense.setHorizontalAlignment(JTextField.RIGHT) ;    // number is at right end of field
        addComponent(rentExpense, 2, 3);

        //other label row 3 and text box and same for restaurant expense
        JLabel otherLabel = new JLabel("Other");
        addComponent(otherLabel,3,0);
        JLabel restaurantLabel = new JLabel("Restaurant");
        addComponent(restaurantLabel,3,2);

        //set up text field for entering other income and restaurant
        otherField = new JTextField("", 10); // blank initially, with 10 columns
        otherField.setHorizontalAlignment(JTextField.RIGHT); // number is at right end of field
        addComponent(otherField,3,1);
        restaurantExpense = new JTextField("", 10); // blank initially, with 10 columns
        restaurantExpense.setHorizontalAlignment(JTextField.RIGHT); // number is at right end of field
        addComponent(restaurantExpense,3,3);

        // Row 3 - Total Income label followed by total income field and total expense label and fields
        JLabel totalIncomeLabel = new JLabel("Total Income");
        addComponent(totalIncomeLabel, 4, 0);
        JLabel totalExpenseLabel = new JLabel("Total Expense");
        addComponent(totalExpenseLabel, 4, 2);

        // set up text boxes for displaying total income and expense.  Users cam view, but cannot directly edit it
        totalIncomeField = new JTextField("0", 10);   // 0 initially, with 10 columns
        totalIncomeField.setHorizontalAlignment(JTextField.RIGHT) ;    // number is at right end of field
        totalIncomeField.setEditable(false);    // user cannot directly edit this field (ie, it is read-only)
        addComponent(totalIncomeField, 4, 1);  
        totalExpenseField = new JTextField("0", 10);   // 0 initially, with 10 columns
        totalExpenseField.setHorizontalAlignment(JTextField.RIGHT) ;    // number is at right end of field
        totalExpenseField.setEditable(false);    // user cannot directly edit this field (ie, it is read-only)
        addComponent(totalExpenseField, 4, 3);

        // Row 4 - Calculate Button
        calculateButton = new JButton("Calculate");
        addComponent(calculateButton, 5, 0);  

        // Row 5 - Exit Button
        exitButton = new JButton("Exit");
        addComponent(exitButton, 6, 0);  

        // set up  listeners (in a spearate method)
        initListeners();
    }

    // set up listeners
    // initially just for buttons, can add listeners for text fields
    private void initListeners() {

        // exitButton - exit program when pressed
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // calculateButton - call calculateTotalIncome() when pressed
        calculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateTotalIncome();
                calculateTotalExpense();
            }
        });
                // wagewsField - call calculateTotalIncome() when pressed
        wagesField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateTotalIncome();
            }
        });

    }

    // add a component at specified row and column in UI.  (0,0) is top-left corner
    private void addComponent(Component component, int gridrow, int gridcol) {
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;   // always use horixontsl filll
        layoutConstraints.gridx = gridcol;
        layoutConstraints.gridy = gridrow;
        add(component, layoutConstraints);

    }

    // update totalExpenseField (eg, when Calculate is pressed)
    // use double to hold numbers, so user can type fractional amounts such as 134.50
    public double calculateTotalExpense(){
        // get values from income text fields.  valie is NaN if an error occurs
        double food = getTextFieldValue(foodExpense);
        double rent = getTextFieldValue(rentExpense);
        double restaurant = getTextFieldValue(restaurantExpense);

        if (Double.isNaN(food) || Double.isNaN(rent) || Double.isNaN(restaurant)) {
            totalExpenseField.setText("");  // clear total expense field
            food = 0.0;
            return food;   // exit method and do nothing
        }

        double totalExpense = food + rent + restaurant;

        totalExpenseField.setText(String.format("%.2f",totalExpense));  // format with 2 digits after the .
        return totalExpense;
    }
    

    // update totalIncomeField (eg, when Calculate is pressed)
    // use double to hold numbers, so user can type fractional amounts such as 134.50
    public double calculateTotalIncome() {

        // get values from income text fields.  valie is NaN if an error occurs
        double wages = getTextFieldValue(wagesField);
        double loans = getTextFieldValue(loansField);
        double other = getTextFieldValue(otherField);
        double tax = 0;

        // clear total field and return if any value is NaN (error)
        if (Double.isNaN(wages) || Double.isNaN(loans) || Double.isNaN(other)) {
            totalIncomeField.setText("");  // clear total income field
            wages = 0.0;
            return wages;   // exit method and do nothing
        }

        // otherwise calculate total income and update text field
        double taxable = wages + other; //seperate taxable income.

        //tax calculation assuming 0 starting point for the tax year
        if (taxable > 12570) { //>12570, <=16537 tax band
            if (taxable <= 16537) {
                tax = tax + ((taxable-12570)*0.19); //tax calculation if taxable is less than or = 16537
            } else{
                tax = tax + ((16537-12570)*0.19); //tax calculation if taxable is more than 16537
            }
        }
        if (taxable > 16537) { //>16537, <=29526 tax band
            if (taxable <= 29526) {
                tax = tax + ((taxable-16537)*0.2); //tax calculation if taxable is less than or = 29526
            } else{
                tax = tax + ((29526-16537)*0.2); //tax calculation if taxable is more than 29526
            }
        }
        if (taxable > 29526) { //>29526, <=43662 tax band
            if (taxable <= 43662) {
                tax = tax + ((taxable-29526)*0.21); //tax calculation if taxable is less than or = 43662
            } else{
                tax = tax + ((43662-29526)*0.21); //tax calculation if taxable is more than 43662
            }
        }
        if (taxable > 43662) { //>43662, <=75000 tax band
            if (taxable <= 75000) {
                tax = tax + ((taxable-43662)*0.42); //tax calculation if taxable is less than or = 75000
            } else{
                tax = tax + ((75000-43662)*0.42); //tax calculation if taxable is more than 75000
            }
        }
        if (taxable > 75000) { //>75000, <=125140 tax band
            if (taxable <= 125140) {
                tax = tax + ((taxable-75000)*0.45); //tax calculation if taxable is less than or = 125140
            } else{
                tax = tax + ((125140-75000)*0.45); //tax calculation if taxable is more than 125140
            }
        }
        if (taxable > 125140){
            tax = tax + ((taxable-125140)*0.48); //tax calculation if taxable> max tax bracket
        }

        double totalIncome = (taxable-tax) + loans;

        totalIncomeField.setText(String.format("%.2f",totalIncome));  // format with 2 digits after the .
        return totalIncome;
    }

    // return the value if a text field as a double
    // --return 0 if field is blank
    // --return NaN if field is not a number
    private double getTextFieldValue(JTextField field) {

        // get value as String from field
        String fieldString = field.getText();  // get text from text field

        if (fieldString.isBlank()) {   // if text field is blank, return 0
            return 0;
        }

        else {  // if text field is not blank, parse it into a double
            //return Double.parseDouble(fieldString);
            try {
                return Double.parseDouble(fieldString);  // parse field number into a double
             } catch (java.lang.NumberFormatException ex) {  // catch invalid number exception
                JOptionPane.showMessageDialog(topLevelFrame, "Please enter a valid number");  // show error message
                return Double.NaN;  // return NaN to show that field is not a number
            }
        }
    }


// below is standard code to set up Swing, which students shouldnt need to edit much
    // standard mathod to show UI
    private static void createAndShowGUI() {
 
        //Create and set up the window.
        JFrame frame = new JFrame("Budget Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        BudgetBase newContentPane = new BudgetBase(frame);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    // standard main class to set up Swing UI
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }


}