//Copyright 2020, 2021 Kevin Pember
/*
 	This file is part of Recursive.

	Recursive is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Recursive is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Recursive.  If not, see <https://www.gnu.org/licenses/>.
*/
package v1CustomFunctionsPackage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import mainCalulatorPackage.MainCalulatorControllClass;
import processing.CalculatorProcessing;

public class V1CustomFunctionPageObject {
	protected FXMLLoader functionPageElementLoader;
	public Parent functionPageElementRoot;
	protected MainCalulatorControllClass mainRoot;
	protected V1CustomFunctionPage elementController;
	public String compileString;
	private CalculatorProcessing mainProcess;
	public V1CustomFunctionPageObject(String compile) {
		compileString = compile;
	}
	public void setCalculatorProcess(CalculatorProcessing mainProcess,MainCalulatorControllClass mainRoot) {
    	this.mainProcess = mainProcess;
    	this.mainRoot = mainRoot;
    	loadElement();
    }
	public void loadElement() {
		try {
			functionPageElementLoader = new FXMLLoader(getClass().getResource("/CustomFunctionV1.fxml"));
			functionPageElementRoot = functionPageElementLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		elementController = (V1CustomFunctionPage) functionPageElementLoader.getController();
		elementController.setCalculatorProcess(mainProcess,mainRoot);
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
