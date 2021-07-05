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

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import mainCalulatorPackage.MainCalulatorControllClass;

public class MoreFuncGroup extends Main{
	@FXML
	private AnchorPane mainAchor;
    @FXML
    private Button buttonElement;

    @FXML
    private Label buttonEquationLabel;
    
    @FXML
    private Label nameLabel;
    
    @FXML
    private Button xButton;
    
    @FXML
    private ImageView xIcon;
    
    private MainCalulatorControllClass mainClass; 
    private String compileString;
    private String Name;
    private Scene main;
    private MoreFuncGroupObject controller;
    /*private CalculatorProcessing mainProcess;
    public void setCalculatorProcess(CalculatorProcessing mainProcess) {
    	this.mainProcess = mainProcess;
    }*/
    @FXML
    void onElementPressed(ActionEvent event) {
    	mainClass.newTab(Name,compileString);
		//stageSceneChange(mainClass.windowWidth-1,mainClass.windowHeight,mainClass.mainStage);
		mainClass.mainStage.setScene(main);
		stageSceneChange(mainClass.windowWidth-1,mainClass.windowHeight,mainClass.mainStage);
    }
    @FXML
    void onXButtonPressed(ActionEvent event) {
    	controller.removeMoreFuncGroup(compileString);
    }
    //used to break down instruction set
    public void textOfElement(String ts) {
    	compileString = ts;
    	nameLabel.setText(ts.substring(0,ts.indexOf('»')));
    	Name = ts.substring(0,ts.indexOf('»'));
    	ts = ts.substring(ts.indexOf('»')+1);
    	buttonEquationLabel.setText(ts.substring(0,ts.indexOf('»')));
    }
    //used to set the theme Colors when loading
    public void updateColor(String displayColor, String numsButtonsColor, String funcButtonsColor, boolean textColor) {
    	String fontColor;
    	if (textColor == true) {
			fontColor = "#000000";
		}else{
			fontColor = "#FFFFFF";
		}
    	mainAchor.setStyle("-fx-displayColor: "+displayColor+"; -fx-buttonsColor: "+numsButtonsColor+"; -fx-functionsColor: "+funcButtonsColor+"; -fx-textColor: "+fontColor+"; -fx-background-color: transparent;");
    	xIcon.setImage(new Image(iconColoration("xIcon",textColor)));
    }
    public void mainAdd(MainCalulatorControllClass mainClass) {
    	this.mainClass = mainClass;
    }
    public void mainSceneAdd(Scene main) {
    	this.main = main;
    }
    public void removeButton(boolean visablitiy) {
    	if(visablitiy) {
    		xButton.setVisible(true);
    	}else {
    		xButton.setVisible(false);
    	}
    }
    public void setController(MoreFuncGroupObject controller) {
    	this.controller = controller;
    }
}
