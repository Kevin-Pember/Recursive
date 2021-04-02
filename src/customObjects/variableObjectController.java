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
