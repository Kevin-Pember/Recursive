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
package customObjects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class variableObjectController {
	public variableObjectController() {
		
	}
	@FXML
    private AnchorPane variableAnchorPane;

    @FXML
    private Label varLabel;

    @FXML
    private TextArea entryField;
    
    private customFunctionPage page;
    
    @FXML
    void onVariableTyped(KeyEvent event) {
    	if(event.toString().isEmpty() != true) {
    		if(entryField.getText().equals("")) {
    			page.updateLabel(varLabel.getText(), "x");
    		}else {
    			page.updateLabel(varLabel.getText(), entryField.getText());
    		}
    	}
    }
    public void setVariable(String name) {
    	varLabel.setText(name);
    }
    public void updateColors(String displayColor, String numsButtonsColor, String funcButtonsColor, boolean textColor) {
    	String fontColor;
    	if (textColor == true) {
			fontColor = "#000000";
		}else{
			fontColor = "#FFFFFF";
		}
    	variableAnchorPane.setStyle("-fx-control-inner-background: transparent;-fx-background-color: -fx-control-inner-background;-fx-text-fill: "+fontColor+";");
    	//variableAnchorPane.setStyle("-fx-background-color: "+funcButtonsColor+";");
    }
    public void setReturnPage(customFunctionPage page) {
    	this.page = page;
    }
    public String getVariableName() {
    	return varLabel.getText();
    }
    public String getVariableValue() {
    	return entryField.getText();
    }
}
