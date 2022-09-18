// NAME:     Mark Darling
// DATE:     10/25/20
// CLASS:    COP 3330-02Z  --  Object Oriented Programming
//
//								     *************************************
// PROGRAM:  Eclipse Project 4:      **        TipCalculator.zip        **
//								     *************************************
//								     *      TipCalculator.java           *
//								     *      TipCalculatorController.java *
//								     *      TipCalculator.fxml           *
//								     *************************************
////////////////////////////////////////////////////////////////////////////////

/*
PROGRAM INSTRUCTIONS:			13.6 (Tip Calculator Modification)

The Tip Calculator app from Section 12.5 does not need a Button to perform its
calculations. Reimplement this app to use property listeners to perform the
calculations whenever the user modifies the bill amount or changes the custom
tip percentage. Also use a property binding to update the Label that displays
the tip percentage.

Once your changes are complete, your interface should no longer have a Calculate
button and the tip should be displayed as you enter the amount of the bill,
changing as each digit is typed. It should also change as the tip percent slider
is moved. The displayed tip percent amount should also change as the slider is
moved (just like it already did), but with the line of code

"tipPercentageLabel.setText(percent.format(tipPercentage));"

commented out. Instead of that line of code, use property binding.

If you get an Access restriction message, try downloading an Eclipse distribution
that is set up with JavaFX. You can find one at Efxclipse install (Links to an
external site.). I have tested that one.

Submit your entire project in a zip file Your entire project includes all folders
and files. Just zip the project folder (possibly named  TipCalculator) and submit
the zip file. Do not just zip the source files.

Your name must appear at the top of all Java source files. This is worth points.

*/
////////////////////////////////////////////////////////////////////////////////

// TipCalculatorController.java
// Controller that handles calculateButton and tipPercentageSlider events

// IMPORTS
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import javafx.beans.binding.StringBinding;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

// BEGIN CLASS
public class TipCalculatorController
{
	// formatters for currency and percentages
	private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
//	private static final NumberFormat percent = NumberFormat.getPercentInstance();
	private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% default

	// GUI controls defined in FXML and used by the controller's code
	@FXML
	private TextField amountTextField;

	@FXML
	private Label tipPercentageLabel;

	@FXML
	private Slider tipPercentageSlider;

	@FXML
	private TextField tipTextField;

	@FXML
	private TextField totalTextField;

	
	// calculates and displays the tip and total amounts
	@FXML
	private void calculateButtonPressed(ActionEvent event)
	{
		try
		{
			// CALCULATE VALUES OF RESPECTIVE FIELDS
			BigDecimal amount = new BigDecimal(amountTextField.getText());
			BigDecimal tip = amount.multiply(tipPercentage);
			BigDecimal total = amount.add(tip);

			// UPDATE tipTextField
			tipTextField.setText(currency.format(tip));

			// UPDATE totalTextField
			totalTextField.setText(currency.format(total));

		} catch (NumberFormatException ex) // HANDLE EXCEPTIONS
		{
			amountTextField.setText("Enter amount");
			amountTextField.selectAll();
			amountTextField.requestFocus();
		}
	} // END calculateButtonPressed(){}


	// HELPER METHOD THAT HANDLES NO/BAD INPUT
	public static BigDecimal safeBigDecimal(String str)
	{
		try
		{
			return new BigDecimal(str);
		}
		catch(NumberFormatException nfe)
		{
			return BigDecimal.ZERO;
		}
	} // END safeBigDecimal(){}


	// called by FXMLLoader to initialize the controller
	public void initialize()
	{
		// 0-4 rounds down, 5-9 rounds up
		currency.setRoundingMode(RoundingMode.HALF_UP);

		// ESTABLISH BINDING BETWEEN SLIDER POSITION AND SLIDER LABEL
		StringBinding tipSliderStringBinding = tipPercentageSlider.valueProperty().asString("%.0f%%");
		// String tipSliderString = tipSliderStringBinding.get();
		// Double d = new Double("0.00");
		// double tipSliderValue = d.parseDouble(tipSliderString);
		tipPercentageLabel.textProperty().bind(tipSliderStringBinding);

		// listener for changes to tipPercentageSlider's value
		tipPercentageSlider.valueProperty().addListener(new ChangeListener<Number>()
		{
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue)
			{
				// SET SLIDER BEHAVIOR
				tipPercentageSlider.setBlockIncrement(1);
				tipPercentageSlider.setMajorTickUnit(1);
				tipPercentageSlider.setMinorTickCount(0);
				tipPercentageSlider.setSnapToTicks(true);

				try
				{
					tipPercentage = BigDecimal.valueOf(newValue.intValue() / 100.0);
					// COMMENT THIS LINE OUT:  tipPercentageLabel.setText(percent.format(tipPercentage));

					// CALCULATE/GET VALUES OF RESPECTIVE FIELDS
					BigDecimal amount = new BigDecimal(amountTextField.getText());
					BigDecimal tip = amount.multiply(tipPercentage);
					BigDecimal total = amount.add(tip);

					// UPDATE tipTextField
					tipTextField.setText(currency.format(tip));
					
					// UPDATE totalTextField
					totalTextField.setText(currency.format(total));
				}
				catch(NumberFormatException nfe)
				{
					//amountTextField.setText("ERROR!");
					
					// CALCULATE/GET VALUES OF RESPECTIVE FIELDS
					BigDecimal amount = safeBigDecimal(amountTextField.getText());
					BigDecimal tip = amount.multiply(tipPercentage);
					BigDecimal total = amount.add(tip);

					// UPDATE tipTextField
					tipTextField.setText(currency.format(tip));

					// UPDATE totalTextField
					totalTextField.setText(currency.format(total));

					return;	
				}
			} // END changed(){}

		}); // END tipPercentageSlider LISTENER

		// LISTEN FOR CHANGES TO amountTextField VALUE (USING LAMDA EXPRESSION)
		amountTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			try
			{
				// CALCULATE/GET VALUES OF RESPECTIVE FIELDS
				BigDecimal amount = new BigDecimal(amountTextField.getText());
				BigDecimal tip = amount.multiply(tipPercentage);
				BigDecimal total = amount.add(tip);

				// UPDATE tipTextField
				tipTextField.setText(currency.format(tip));

				// UPDATE totalTextField
				totalTextField.setText(currency.format(total));

			} catch (NumberFormatException nfe) // HANDLE EXCEPTIONS
			{
				//amountTextField.setText("ERROR!");

				// CALCULATE/GET VALUES OF RESPECTIVE FIELDS
				BigDecimal amount = safeBigDecimal(amountTextField.getText());
				BigDecimal tip = amount.multiply(tipPercentage);
				BigDecimal total = amount.add(tip);

				// UPDATE tipTextField
				tipTextField.setText(currency.format(tip));

				// UPDATE totalTextField
				totalTextField.setText(currency.format(total));

				return;
			}	
		}); // END amountTextField LISTENER
		
	} // END initialize(){}

} // END TipCalculatorController(){}