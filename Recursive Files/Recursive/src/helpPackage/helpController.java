package helpPackage;

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

public class HelpController extends Main{
	 @FXML
	    private AnchorPane baseHelp;

	    @FXML
	    private Button mainCalculatorButton;

	    @FXML
	    private ImageView mainCalculatorImageView;

	    @FXML
	    private Button customFunctionsButton;

	    @FXML
	    private ImageView customFunctionsImageView;

	    @FXML
	    private Button settingsButton;

	    @FXML
	    private ImageView settingsImageView;

	    @FXML
	    private Label HelpTitle;

	    @FXML
	    public AnchorPane mainCalculatorPageAnchorPane;

	    @FXML
	    public AnchorPane CustomFunctionsPageAnchorPane;

	    @FXML
	    public AnchorPane settingsPageAnchorPane;

	    @FXML
	    private Button backButton;

	    @FXML
	    private ImageView backIconImageView;
	    
	    private MainCalulatorControllClass mainCacController;
	    
	    private Scene backScene;
	    
	    private double windowWidth;
	    
	    private double windowHeight;

	    @FXML
	    void onBackButtonPressed(ActionEvent event) {
	    	if(mainCalculatorPageAnchorPane.isVisible()) {
	    		mainCalculatorPageAnchorPane.setVisible(false);
	    	}else if(CustomFunctionsPageAnchorPane.isVisible()) {
	    		CustomFunctionsPageAnchorPane.setVisible(false);
	    	}else if(settingsPageAnchorPane.isVisible()) {
	    		settingsPageAnchorPane.setVisible(false);
	    	}else {
	    		mainCacController.mainStage.setScene(backScene);
	    		stageSceneChange(windowWidth,windowHeight,mainCacController.mainStage);
	    	}
	    }

	    @FXML
	    void onCustomFunctionsButtonPressed(ActionEvent event) {
	    	CustomFunctionsPageAnchorPane.setVisible(true);
	    }

	    @FXML
	    void onMainCalculatorButtonPressed(ActionEvent event) {
	    	mainCalculatorPageAnchorPane.setVisible(true);
	    }

	    @FXML
	    void onSettingsButtonPressed(ActionEvent event) {
	    	settingsPageAnchorPane.setVisible(true);
	    }
	    
	    public void updateColor(String displayColor, String numsButtonsColor, String funcButtonsColor, boolean textColor) {
	    	String fontColor;
	    	if (textColor == true) {
				fontColor = "#000000";
			}else{
				fontColor = "#FFFFFF";
			}
	    	baseHelp.setStyle("-fx-displayColor: "+displayColor+"; -fx-buttonsColor: "+numsButtonsColor+"; -fx-functionsColor: "+funcButtonsColor+"; -fx-textColor: "+fontColor+";");
	    	mainCalculatorImageView.setImage(new Image(iconColoration("CalculatorIcon",textColor)));
	    	customFunctionsImageView.setImage(new Image(iconColoration("CustomFunctionIcon",textColor)));
	    	settingsImageView.setImage(new Image(iconColoration("SettingsIcon",textColor)));
	    	//tabs.forEach((n) -> n.setStyle(displayColor));
	    	//mainTab.setStyle(displayColor);
	    }
	    
	    public void matchMain(MainCalulatorControllClass inputController) {
	    	mainCacController = inputController;
	    }
	    
	    public void setBackScene(Scene backScene,double width,double height) {
	    	this.backScene = backScene;
	    	windowWidth = width;
	    	windowHeight = height;
	    }

}
