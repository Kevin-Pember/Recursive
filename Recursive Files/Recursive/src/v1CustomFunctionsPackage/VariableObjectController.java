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

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class VariableObjectController {
	public VariableObjectController() {
		
	}
	@FXML
    private AnchorPane variableAnchorPane;

    @FXML
    private Label varLabel;

    @FXML
    private TextArea entryField;
    
    private V1CustomFunctionPage page;
    
    private boolean onEdge = false;
    
    @FXML
    void onNavgation(KeyEvent event) {
    	if(event.getCode() == KeyCode.LEFT) {
    		if(entryField.getCaretPosition() == 0 && onEdge == true) {
    			page.navigate(true);
    		}else if(entryField.getCaretPosition() == 0) {
    			onEdge = true;
    		}else {
    			onEdge = false;
    		}
    	}else if(event.getCode() == KeyCode.RIGHT) {
    		if(entryField.getCaretPosition() == entryField.getText().length() && onEdge == true) {
    			page.navigate(false);
    		}else if(entryField.getCaretPosition() == entryField.getText().length()) {
    			onEdge = true;
    		}else {
    			onEdge = false;
    		}
    	}
    }
    
    @FXML
    void onVariableTyped(KeyEvent event) {
    	if(event.toString().isEmpty() != true) {
    		if(entryField.getText().equals("")) {
    			page.updateLabel(varLabel.getText(), "x");
    		}else {
    			page.updateLabel(varLabel.getText(), entryField.getText());
    			if(entryField.getCaretPosition() == entryField.getLength()) {
    				onEdge = true;
    			}
    		}
    		System.out.println("The Variable Label focus is " + isFocused());
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
    	
    }
    public void setReturnPage(V1CustomFunctionPage page) {
    	this.page = page;
    }
    public String getVariableName() {
    	return varLabel.getText();
    }
    public String getVariableValue() {
    	return entryField.getText();
    }
    public boolean isFocused() {
    	return entryField.isFocused();
    }
    public void setFocused() {
    	entryField.requestFocus();
    	entryField.positionCaret(entryField.getText().length());
    }
}
