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
package application;

import helpPackage.HelpController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mainCalulatorPackage.MainCalulatorControllClass;
import moreFunctionsPackage.MoreFunctionsPage;
import processing.CalculatorProcessing;
import settingsPackage.SettingsControllClass;

import java.io.File;
import java.math.MathContext;
import java.math.RoundingMode;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
		
	}
	
	public String displayColor = "#fefefe";
	String numsColor = "#f2f2f2";
	String functionsColor = "#ff4a4a";
	String fontColor = "#FFFFFF";
	String numsFontSize = "3";
	boolean textColor = true;
	protected boolean cacMode = true;
	protected boolean extendedFuncOpen = false;
	protected Group root = new Group();
	protected Scene CalulatorScene = new Scene(root, Color.web(displayColor));
	protected Scene settingsScene;
	protected Scene mainCalulatorScene;
	protected Scene extendFuncsScene;
	protected Scene helpScene;
	protected Parent settingsRoot;
	protected Parent mainCalulatorRoot;
	protected Parent extendFuncsRoot;
	protected Parent helpPageRoot;
	protected FXMLLoader settingsLoader;
	protected FXMLLoader mainCalulatorLoader;
	protected FXMLLoader extendFunctionsLoader;
	protected FXMLLoader helpPageLoader;
	protected MainCalulatorControllClass mainCalulatorController;
	protected SettingsControllClass settingsController;
	protected MoreFunctionsPage extendFunctionsController;
	protected HelpController helpPageController;
	protected final String piCon = "3.14159265358979323846264338327950288419716939937510582097494459230781640628";
	public MathContext mc = new MathContext(16,RoundingMode.HALF_DOWN);
	protected CalculatorProcessing mainProcess = new CalculatorProcessing(mc,cacMode);
	public Stage thisStage = null;
	
	@Override
public void start(Stage primaryStage) throws Exception{
		if (textColor == true) {
			fontColor = "#000000";
		}else if (!textColor) {
			fontColor = "#FFFFFF";
		}
		System.out.println("entering");
		//CalulatorScene.getStylesheets().add(getClass().getResource("applicationSkin.css").toExternalForm()); 
		
		mainCalulatorLoader = new FXMLLoader(getClass().getResource("/MainCalulator.fxml"));
		mainCalulatorRoot = mainCalulatorLoader.load();
		mainCalulatorScene = new Scene(mainCalulatorRoot,311.2,505.6);
		
		settingsLoader = new FXMLLoader(getClass().getResource("/SettingsPage.fxml"));
		settingsRoot = settingsLoader.load();
		settingsScene = new Scene(settingsRoot);
		
		extendFunctionsLoader = new FXMLLoader(getClass().getResource("/MoreFunctionsPage.fxml"));
		extendFuncsRoot = extendFunctionsLoader.load();
	    extendFuncsScene = new Scene(extendFuncsRoot,311.2,505.6);
	    
	    helpPageLoader = new FXMLLoader(getClass().getResource("/HelpPage.fxml"));
	    helpPageRoot = helpPageLoader.load();
	    helpScene = new Scene(helpPageRoot);
		
		primaryStage.setScene(mainCalulatorScene);
		primaryStage.show();
		primaryStage.setMinHeight(579);
		primaryStage.setMinWidth(330);
		primaryStage.setTitle("Recursive");
		Image mainIcon = new Image("icons/mainIcon.png");
		primaryStage.getIcons().add(mainIcon);
		thisStage = primaryStage;
			
		extendFunctionsController = (MoreFunctionsPage) extendFunctionsLoader.getController();
		mainCalulatorController = (MainCalulatorControllClass) mainCalulatorLoader.getController();
		settingsController = (SettingsControllClass) settingsLoader.getController();
		helpPageController = (HelpController) helpPageLoader.getController();
			
		mainCalulatorController.setSettingsScene(settingsScene,thisStage);
		mainCalulatorController.setExtendFuncsScene(extendFuncsScene);
		mainCalulatorController.matchControlClass(settingsController);
        mainCalulatorController.matchExtendedControlClass(extendFunctionsController);
        mainCalulatorController.setCalculatorProcess(mainProcess);
        mainCalulatorController.matchHelp(helpPageController, helpScene);
			
		settingsController.setMainCalulatorScene(mainCalulatorScene);
		settingsController.matchControlClass(mainCalulatorController);
		settingsController.setCalculatorProcess(mainProcess);
			
		extendFunctionsController.setMainCalulatorScene(mainCalulatorScene);
	    extendFunctionsController.matchExtendedControlClass(mainCalulatorController);
	    
	    helpPageController.matchMain(mainCalulatorController);
	        
	       primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override public void handle(WindowEvent t) {
		    	mainCalulatorController.onWindowClosed();
		    }
		});
	    System.out.println("filepath created"+fileOutDiector());
}
public void updateMainCacColors(String displayColor, String numsButtonsColor, String funcButtonsColor, boolean textColor) {
	 mainCalulatorController.updateColor(displayColor, numsButtonsColor, funcButtonsColor, textColor);
}
public boolean fileOutDiector() {
	try {
        File file = new File("c:\\ProgramData\\Recursive");
        boolean flag = file.mkdirs();
        return flag;
    } catch(Exception e) {
        e.printStackTrace();
        return false;
    }	
}
// Method use to assign the image resources base on the text color
public String iconColoration(String moreFuncIcon, boolean color) {
	 switch(moreFuncIcon) {
	   case("moreFuncIcon"):
		   if (color) {
			   return "icons/MoreFunc.png";
		   }else {
			   return "icons/MoreFuncWhite.png";
		   }
	   case("moreFuncArrowIcon"):
		   if (color) {
			   return "icons/MoreFuncArrow.png";
		   }else {
			   return "icons/MoreFuncArrowWhite.png";
		   }
	   case("backIcon"):
		   if (color) {
			   return "icons/backIcon.png";
		   }else {
			   return "icons/backIconWhite.png";
		   }
	   case("enterIcon"):
		   if (color) {
			   return "icons/enterIcon.png";
		   }else {
			   return "icons/enterIconWhite.png";
		   }
	   case("settingsCogIcon"):
		   if (color) {
			   return "icons/SettingsCogIcon.png";
		   }else {
			   return "icons/SettingsCogIconWhite.png";
		   }
	   case("historyThrowIcon"):
		   if (color) {
			   return "icons/HistoryIcon.png";
		   }else {
			   return "icons/HistoryIconWhite.png";
		   }
	   case("addObject"):
		   if (color) {
			   return "icons/addObject.png";
		   }else {
			   return "icons/addObjectWhite.png";
		   }
	   case("minusIcon"):
		   if (color) {
			   return "icons/minusIcon.png";
		   }else {
			   return "icons/minusIconWhite.png";
		   }
	   case("xIcon"):
		   if (color) {
			   return "icons/xIcon.png";
		   }else {
			   return "icons/xIconWhite.png";
		   }
	   case("lockIcon"):
		   if(color) {
			   return "icons/lockIcon.png";
		   }else {
			   return "icons/lockIconWhite.png";
		   }
	   case("helpIcon"):
		   if(color) {
			   return "icons/help.png";
		   }else {
			   return "icons/helpWhite.png";
		   }
	   case("CalculatorIcon"):
		   if(color) {
			   return "icons/CalculatorIcon.png";
		   }else {
			   return "icons/CalculatorIconWhite.png";
		   }
	   case("CustomFunctionIcon"):
		   if(color) {
			   return "icons/customFunctionIcon.png";
		   }else {
			   return "icons/customFunctionIconWhite.png";
		   }
	   case("SettingsIcon"):
		   if(color) {
			   return "icons/settingsPageIcon.png";
		   }else {
			   return "icons/settingsPageIconWhite.png";
		   }
	   default:
		   return "Unresolved";
	 }
}

 public void stageSceneChange(double stageX, double stageY, Stage stage) {
	 double xConstant = stage.getWidth();
	 double yConstant = stage.getHeight();
	 double xDistance = (stageX-stage.getWidth())/10;
	 double yDistance = (stageY-stage.getHeight())/10;
	 Thread thread = new Thread(new Runnable() {
         @Override
         public void run() {
             Runnable updater = new Runnable() {
                 @Override
                 public void run() {}};

             for(int i = 0; i <= 10; i++) {
                 	try {
                     Thread.sleep(8);
                 	} catch (InterruptedException ex) {
                 }
                 stage.setWidth(xConstant + xDistance * i);
                 stage.setHeight(yConstant + yDistance * i);
                 Platform.runLater(updater);
             }
         }
     });
     thread.setDaemon(true);
     thread.start();
 }
}