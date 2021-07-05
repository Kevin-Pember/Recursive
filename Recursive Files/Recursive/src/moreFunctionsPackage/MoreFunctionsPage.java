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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import mainCalulatorPackage.MainCalulatorControllClass;
import processing.CalculatorProcessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoreFunctionsPage extends Main{
	
	@FXML
    private AnchorPane mainPane;
	
	@FXML
	private AnchorPane functionsSearchPage;
	
	@FXML
    private TextField searchBar;

    @FXML
    private Button newFunctionButton;

    @FXML
    private Button backButton;
    
    @FXML
    private Button removeEntryButton;
    
    @FXML
    private GridPane functionsGrid;
    
    @FXML
    private AnchorPane GridAnchorPane;

    @FXML
    private ScrollPane objectsScrollPane;
    
    @FXML
    private ImageView addObjectImageView;
    
    @FXML
    private ImageView backIconImageView;
    
    @FXML
    private Line splitLine;
    
    @FXML
    private AnchorPane addFunctionAnchorPane;
    
    @FXML
    private TextField addFunctionTitle;

    @FXML
    private TextArea addEquationTextPane;

    @FXML
    private Button addEquationFunction;

    @FXML
    private Button addEquationGraph;
    
    @FXML
    private Button removeFunctionButton;
    
    @FXML
    private ImageView removeObjectImageView;
    
    private MainCalulatorControllClass mainCac;
    
    private Scene mainCalulatorScene;
    
    ArrayList<MoreFuncGroupObject> buttons = new ArrayList<MoreFuncGroupObject>();
    
    String displayColor;
    
    String numsButtonsColor;
    
    String funcButtonsColor;
    
    boolean textColor;
    
    private CalculatorProcessing mainProcess;
    
    public void matchExtendedControlClass(MainCalulatorControllClass inputController) {
    	mainCac = inputController;
    	buttonsFileMemeber();
    }
    public void setMainCalulatorScene(Scene scene) {
    	mainCalulatorScene = scene;
    }
    public void setCalculatorProcess(CalculatorProcessing mainProcess) {
    	this.mainProcess = mainProcess;
    }
    public void updateColor(String displayColor, String numsButtonsColor, String funcButtonsColor, boolean textColor) {
    	this.displayColor = displayColor;
    	this.numsButtonsColor = numsButtonsColor;
    	this.funcButtonsColor = funcButtonsColor;
    	this.textColor = textColor;
    	String fontColor = "";
    	if (textColor == true) {
			fontColor = "#000000";
		}else{
			fontColor = "#FFFFFF";
		}
    	mainPane.setStyle("-fx-displayColor: "+displayColor+"; -fx-buttonsColor: "+numsButtonsColor+"; -fx-functionsColor: "+funcButtonsColor+"; -fx-textColor: "+fontColor+";");
    	backIconImageView.setImage(new Image(iconColoration("moreFuncArrowIcon",textColor)));
    	addObjectImageView.setImage(new Image(iconColoration("addObject",textColor)));
    	removeObjectImageView.setImage(new Image(iconColoration("minusIcon",textColor)));
    	buttons.forEach((n) -> n.updateColors(displayColor, numsButtonsColor, funcButtonsColor, textColor));
    	
    }

    @FXML
    void onBackButtonPressed(ActionEvent event) {
    	if(addFunctionAnchorPane.isVisible()) {
    		addFunctionAnchorPane.setVisible(false);
    		functionsSearchPage.setVisible(true);
    	} else {
    		stageSceneChange(mainCac.windowWidth-1,mainCac.windowHeight,mainCac.mainStage);
    		mainCac.mainStage.setScene(mainCalulatorScene);
    	}
    }

    @FXML
    void onNewFunctionButtonPressed(ActionEvent event) {
    	functionsSearchPage.setVisible(false);
    	addFunctionAnchorPane.setVisible(true);
    }
    @FXML
    void onRemoveEntryButtonPressed(ActionEvent event) {
    	searchBar.setText("");
    	removeEntryButton.setVisible(false);
    	searchMatcher();
    }
    
    @FXML
    void onSearchBarTyped(KeyEvent event) {
    	if(event.toString().isEmpty() != true) {
    		if(searchBar.getText().isEmpty()) {
    			removeEntryButton.setVisible(false);
    		}else {
    			removeEntryButton.setVisible(true);
    		}
    	}
    	searchMatcher();
    }
    @FXML
    void onAddEquationFunctionPressed(ActionEvent event) {
 	   String newEntry = addFunctionTitle.getText()+"»";
 	   if(addEquationTextPane.getText().contains("=")) {
 		  newEntry +=  addEquationTextPane.getText() + "»";
 	   }else {
 		  newEntry +=  addEquationTextPane.getText() + "=o" + "»";
 	   }
 	   newEntry += "v1»";
 	   newEntry += "Function»";
 	   newFunction(newEntry);
 	   addFunctionAnchorPane.setVisible(false);
 	   functionsSearchPage.setVisible(true);
    }
    @FXML
    void onRemoveFunctionButtonPressed(ActionEvent event) {
    	buttons.forEach((n) -> n.setRemoveVisability());
    }
    @FXML
    void onAddEquationGraphButtonPressed(ActionEvent event) {
       String newEntry = addFunctionTitle.getText()+"»";
       newEntry +=  addEquationTextPane.getText() + "»";
  	   newEntry += "v1»";
  	   newEntry += "Graph»";
  	   newFunction(newEntry);
  	   addFunctionAnchorPane.setVisible(false);
  	   functionsSearchPage.setVisible(true);
    }
    public void newFunction(String cs) {
    	try {
	   	      FileWriter myWriter = new FileWriter("c:\\ProgramData\\Recursive\\extraButtonsFileOut.txt",StandardCharsets.UTF_8,true);
	   	      if(buttons.size() != 0) {
	   	    	  myWriter.append("\n"+cs);
	   	      }else {
	   	    	  myWriter.append(cs);
	   	      }
	   	      myWriter.close();
	   	      System.out.println("Successfully wrote equation to file");
	   	    } catch (IOException e) {
	   	      System.out.println("An error occurred.");
	   	      e.printStackTrace();
	   	    }
	 newButton(cs);
	 searchMatcher();
    }
    public void removeFunctionFileOut(String compileString) {
    	String fullThing = "";
    	
    	try {
     	      File extraButtonsFile = new File("c:\\ProgramData\\Recursive\\extraButtonsFileOut.txt");
     	      Scanner myReader = new Scanner(extraButtonsFile);
     	      while (myReader.hasNextLine()) {
     	    	String line = new String (myReader.nextLine().getBytes(),StandardCharsets.UTF_8);
     	    	if(line.equals(compileString)) {
     	    		
     	    	}else {
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
    	System.out.println("Here is the Full thig "+fullThing);
    	try {
	   	      FileWriter myWriter = new FileWriter("c:\\ProgramData\\Recursive\\extraButtonsFileOut.txt",StandardCharsets.UTF_8);
	   	      myWriter.write(fullThing);
	   	      myWriter.close();
	   	      System.out.println("Successfully wrote equation to file");
	   	    } catch (IOException e) {
	   	      System.out.println("An error occurred.");
	   	      e.printStackTrace();
	   	    }
    	for(int i = 0; i < buttons.size(); i++) {
   			if(buttons.get(i).compileString.equals(compileString)) {
   				functionsGrid.getChildren().remove(buttons.get(i).buttonElementRoot);
   				buttons.remove(i);
   			}
   		}
    	searchMatcher();
    }
    public void buttonsFileMemeber() {
    	String fullThing = "";
       	try {
       	      File extraButtonsFile = new File("c:\\ProgramData\\Recursive\\extraButtonsFileOut.txt");
       	      Scanner myReader = new Scanner(extraButtonsFile);
       	      while (myReader.hasNextLine()) {
       	    	String line = new String (myReader.nextLine().getBytes(),StandardCharsets.UTF_8);
       	    	fullThing += line;
       	        newButton(line);
       	      }
       	      System.out.println("This is the full buttons Fileout "+fullThing);
       	      myReader.close();
       	    } catch (FileNotFoundException e) {
       	      System.out.println("An error occurred.");
       	      e.printStackTrace();
       	    }
       }
    public void newButton(String cs) {
    	buttons.add(new MoreFuncGroupObject(cs,mainCac,mainCalulatorScene));
    	buttons.get(buttons.size()-1).updateColors(displayColor, numsButtonsColor, funcButtonsColor, textColor);
    	buttons.get(buttons.size()-1).setCalculatorProcess(mainProcess);
    	int a = aCalculator();
   		int b = buttons.size()-1;
   		functionsGrid.add(buttons.get(buttons.size()-1).buttonLoader(), a, b / 3);
   		GridAnchorPane.setPrefHeight(138 * ((buttons.size()/3)+1));
       	System.out.println(GridAnchorPane.getPrefHeight() + " " + objectsScrollPane.getPrefHeight());
       	if(GridAnchorPane.getPrefHeight() > objectsScrollPane.getPrefHeight()) {
       		GridAnchorPane.setPrefWidth(390);
       	} else {
       		GridAnchorPane.setPrefWidth(409);
       	}
    }
       protected void searchMatcher() {
    	   System.out.println("Matcher Ran");
   		for(int i = 0; i < buttons.size(); i++) {
   			functionsGrid.getChildren().remove(buttons.get(i).buttonElementRoot);
   		}
   		Pattern searchPattern = Pattern.compile(searchBar.getText(),Pattern.CASE_INSENSITIVE);
   		ArrayList<Integer> buttonsIndex = new ArrayList<Integer>();
   		for(int i = 0; i < buttons.size(); i++) {
   			String id = buttons.get(i).compileString;
   			Matcher matcher = searchPattern.matcher(id);
   			if(matcher.find()) {
   				buttons.get(i).loadElement();
   				buttonsIndex.add(i);
   			}
   		}
   		buttonsIndex.forEach((n) -> System.out.print(n+","));
   		for(int i = 0; i < buttonsIndex.size(); i++) {
          		int a = 0;
          		int b = i;
          		switch(i % 3) {
          			case(1):
          				a = 1;
          			break;
          			case(2):
          				a = 2;
          			break;
          			case(0):
          				a = 0;
          			break;
          		}
          			functionsGrid.add(buttons.get(buttonsIndex.get(i)).buttonLoader(), a, b / 3);
          			buttons.get(buttonsIndex.get(i)).updateColors(displayColor, numsButtonsColor, funcButtonsColor, textColor);
          	}
   		GridAnchorPane.setPrefHeight(138 * ((buttonsIndex.size()/3)));
   	}
    public int aCalculator() {
    	switch((buttons.size()-1) % 3) {
			case(1):
				return 1;
		case(2):
				return 2;
		case(0):
				return 0;
		default:
				return 0;
		}
    }
}
