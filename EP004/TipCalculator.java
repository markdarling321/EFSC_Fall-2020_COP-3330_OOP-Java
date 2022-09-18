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

// Fig. 12.19: TipCalculator.java
// Main app class that loads and displays the Tip Calculator's GUI

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TipCalculator extends Application
{
	@Override
	public void start(Stage stage) throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("TipCalculator.fxml"));

		Scene scene = new Scene(root); // attach scene graph to scene
		stage.setTitle("Tip Calculator"); // displayed in window's title bar
		stage.setScene(scene); // attach scene to stage
		stage.show(); // display the stage
	}

	public static void main(String[] args)
	{
		// create a TipCalculator object and call its start method
		launch(args);
	}
}