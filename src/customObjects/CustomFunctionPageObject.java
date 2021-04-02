package customObjects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import mainCalulatorPackage.MainCalulatorControllClass;
import processing.CalculatorProcessing;

public class CustomFunctionPageObject {
	protected FXMLLoader functionPageElementLoader;
	public Parent functionPageElementRoot;
	protected MainCalulatorControllClass mainRoot;
	protected customFunctionPage elementController;
	public String compileString;
	private CalculatorProcessing mainProcess;
	public CustomFunctionPageObject(String compile) {
		compileString = compile;
	}
	public void setCalculatorProcess(CalculatorProcessing mainProcess) {
    	this.mainProcess = mainProcess;
    	loadElement();
    }
	public void loadElement() {
		try {
			functionPageElementLoader = new FXMLLoader(getClass().getResource("/CustomFunctionV1.fxml"));
			functionPageElementRoot = functionPageElementLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		elementController = (customFunctionPage) functionPageElementLoader.getController();
		elementController.setCalculatorProcess(mainProcess);
		elementController.onStartUp(compileString);
	}
	public Parent getRoot() {
		return functionPageElementRoot;
	}
	public String updateColors(String displayColor, String numsButtonsColor, String funcButtonsColor, boolean textColor) {
		elementController.updateColor(displayColor, numsButtonsColor, funcButtonsColor, textColor);
		return funcButtonsColor;
	}
}
