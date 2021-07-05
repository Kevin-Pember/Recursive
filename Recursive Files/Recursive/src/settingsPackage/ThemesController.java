package settingsPackage;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ThemesController extends Main{
	
		@FXML
		private AnchorPane themeAnchorPane; 
		
		@FXML
		private AnchorPane repAnchorPane;
	 
		@FXML
	    private RadioButton themeRadioButton;
		
		@FXML
	    private Button xButton;
		
		@FXML
	    private Text tText;
		
		@FXML
	    private ImageView xIcon;
		
		private SettingsControllClass parentClass;
		
		private ThemesObject controllerClass;
		
		private String displayColor;
		
		private String numsButtonsColor;
		
		private String funcButtonsColor;
		
		private String fontColor;
		
		private String input;
	    @FXML
	    void onThemeRadioButtonPressed(ActionEvent event) {
	    	parentClass.changeTheme(displayColor, numsButtonsColor, funcButtonsColor, fontColor);
	    	themeRadioButton.setSelected(true);
	    }
	    
	    @FXML
	    void onXButtonPressed(ActionEvent event) {
	    	String fullThing = "";
	       	try {
	       	      File themesFile = new File("c:\\ProgramData\\Recursive\\themesFileOut.txt");
	       	      Scanner myReader = new Scanner(themesFile);
	       	      while (myReader.hasNextLine()) {
	       	    	String line = new String (myReader.nextLine().getBytes(),StandardCharsets.UTF_8);
	       	    	if(!line.equals(input)) {
	       	    		if(fullThing.equals("")) {
	     	    			fullThing += line;
	     	    		}else {
	     	    			fullThing +="\n" + line;
	     	    		}
	       	    	}
	       	      }
	       	      myReader.close();
	       	    } catch (FileNotFoundException e) {
	       	      System.out.println("An error occurred.");
	       	      e.printStackTrace();
	       	    }
	       	try {
		   	      FileWriter myWriter = new FileWriter("c:\\ProgramData\\Recursive\\themesFileOut.txt",StandardCharsets.UTF_8);
		   	      myWriter.write(fullThing);
		   	      myWriter.close();
		   	      System.out.println("Successfully wrote equation to file");
		   	    } catch (IOException e) {
		   	      System.out.println("An error occurred.");
		   	      e.printStackTrace();
		   	    }
	       	parentClass.removeTheme(controllerClass);
	    }
	    
	    public void setColors(String displayColor, String numsButtonsColor, String funcButtonsColor, boolean textColor) {
	    	String fontColor;
	    	if (textColor == true) {
				fontColor = "#000000";
			}else{
				fontColor = "#FFFFFF";
			}
	    	this.displayColor = displayColor;
	    	this.numsButtonsColor = numsButtonsColor;
	    	this.funcButtonsColor = funcButtonsColor;
	    	this.fontColor = fontColor;
	    	repAnchorPane.setStyle("-fx-displayColor: "+displayColor+"; -fx-buttonsColor: "+numsButtonsColor+"; -fx-functionsColor: "+funcButtonsColor+"; -fx-textColor: "+fontColor+"; -fx-background-color: transparent;");
	    	xIcon.setImage(new Image(iconColoration("xIcon",textColor)));
	    }
	    public void removeButton(boolean enable,String input) {
	    	this.input = input;
	    	xButton.setVisible(enable);
	    	tText.setVisible(!enable);
	    }
	    public void setRadioButton(boolean setting) {
	    	themeRadioButton.setSelected(setting);
	    }
	    public void setSettingsController(SettingsControllClass parentClass,ThemesObject controllerClass) {
	    	this.parentClass = parentClass;
	    	this.controllerClass = controllerClass;
	    }
}
