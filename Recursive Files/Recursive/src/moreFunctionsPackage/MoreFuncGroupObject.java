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
package moreFunctionsPackage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import mainCalulatorPackage.MainCalulatorControllClass;
import processing.CalculatorProcessing;

public class MoreFuncGroupObject {
	protected FXMLLoader buttonElementLoader;
	public Parent buttonElementRoot;
	protected MoreFuncGroup MoreFuncElementController;
	public String compileString;
	public MainCalulatorControllClass mainClass;
	public Scene main;
	private CalculatorProcessing mainProcess;
	private boolean removeVisablity = true;
	private MoreFunctionsPage extendFuncsController;
	//Constructor for this class that takes a string
	public MoreFuncGroupObject(String cs,MainCalulatorControllClass mainClass, Scene main) {
		compileString = cs;
		this.mainClass = mainClass;
		this.main = main;
		this.extendFuncsController = mainClass.extendFuncsController;
		loadElement();
	}
	// The accessor method that returns the button element the object holds
	public Parent buttonLoader() {
		return buttonElementRoot;
	}
	public void setCalculatorProcess(CalculatorProcessing mainProcess) {
    	this.mainProcess = mainProcess;
    }
	//Used to send color variables to the element Controller
	public void updateColors(String displayColor, String numsButtonsColor, String funcButtonsColor, boolean textColor) {
		MoreFuncElementController.updateColor(displayColor, numsButtonsColor, funcButtonsColor, textColor);
	}
	public String returnElementID() {
		System.out.println(buttonElementRoot.getId());
		return buttonElementRoot.getId();
	}
	public void setRemoveVisability() {
		if(removeVisablity) {
			MoreFuncElementController.removeButton(true);
			removeVisablity = false;
		}else {
			MoreFuncElementController.removeButton(false);
			removeVisablity = true;
		}
	}
	public void loadElement() {
		try {
			buttonElementLoader = new FXMLLoader(getClass().getResource("/MoreFuncGroup.fxml"));
			System.out.println("Element Loaded");
			buttonElementRoot = buttonElementLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		MoreFuncElementController = (MoreFuncGroup) buttonElementLoader.getController();
		MoreFuncElementController.textOfElement(compileString);
		MoreFuncElementController.mainAdd(mainClass);
		MoreFuncElementController.mainSceneAdd(main);
		MoreFuncElementController.setController(this);
		//MoreFuncElementController.setCalculatorProcess(mainProcess);
	}
	public void removeMoreFuncGroup(String compileString){
		extendFuncsController.removeFunctionFileOut(compileString);
	}
}
