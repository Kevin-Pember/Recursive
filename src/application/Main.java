package application;
import java.awt.Font; 
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mainCalulatorPackage.MainCalulatorControllClass;
import mainCalulatorPackage.MoreFunctionsPage;
import processing.CalculatorProcessing;
import settingsPackage.SettingsControllClass;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.robot.Robot;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

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
	//protected Group root = new Group();
	//protected Scene CalulatorScene = new Scene(root, Color.web(displayColor));
	protected Scene settingsScene;
	protected Scene mainCalulatorScene;
	protected Scene extendFuncsScene;
	protected Parent settingsRoot;
	protected Parent mainCalulatorRoot;
	protected Parent extendFuncsRoot;
	protected FXMLLoader settingsLoader;
	protected FXMLLoader mainCalulatorLoader;
	protected FXMLLoader extendFunctionsLoader;
	protected MainCalulatorControllClass mainCalulatorController;
	protected SettingsControllClass settingsController;
	protected MoreFunctionsPage extendFunctionsController;
	protected final String piCon = "3.14159265358979323846264338327950288419716939937510582097494459230781640628";
	public MathContext mc = new MathContext(16,RoundingMode.HALF_DOWN);
	protected CalculatorProcessing mainProcess = new CalculatorProcessing(mc,cacMode);
	
	@Override
public void start(Stage primaryStage) throws Exception{
		if (textColor == true) {
			fontColor = "#000000";
		}else if (textColor == false) {
			fontColor = "#FFFFFF";
		}
		System.out.println("entering");
		//CalulatorScene.getStylesheets().add(getClass().getResource("applicationSkin.css").toExternalForm()); 
		
		mainCalulatorLoader = new FXMLLoader(getClass().getResource("/MainCalulator.fxml"));
		mainCalulatorRoot = mainCalulatorLoader.load();
		mainCalulatorScene = new Scene(mainCalulatorRoot,733,732);
		
		settingsLoader = new FXMLLoader(getClass().getResource("/SettingsPage.fxml"));
		settingsRoot = settingsLoader.load();
		settingsScene = new Scene(settingsRoot,600,400);
		
		extendFunctionsLoader = new FXMLLoader(getClass().getResource("/moreFunctionsPage.fxml"));
		extendFuncsRoot = extendFunctionsLoader.load();
	    extendFuncsScene = new Scene(extendFuncsRoot);
		
		primaryStage.setScene(mainCalulatorScene);
		primaryStage.show();
		primaryStage.setResizable(false);
		primaryStage.setWidth(428);
		primaryStage.setHeight(762);
		primaryStage.setTitle("Recursive");
		Image mainIcon = new Image("mainIcon.png");
		primaryStage.getIcons().add(mainIcon);
		//root.getStylesheets().add("@application/applicationSkin.css");
			
		extendFunctionsController = (MoreFunctionsPage) extendFunctionsLoader.getController();
		mainCalulatorController = (MainCalulatorControllClass) mainCalulatorLoader.getController();
		settingsController = (SettingsControllClass) settingsLoader.getController();
			
		mainCalulatorController.setSettingsScene(settingsScene);
		mainCalulatorController.setExtendFuncsScene(extendFuncsScene);
		mainCalulatorController.matchControlClass(settingsController);
        mainCalulatorController.matchExtendedControlClass(extendFunctionsController);
        mainCalulatorController.setCalculatorProcess(mainProcess);
			
		settingsController.setMainCalulatorScene(mainCalulatorScene);
		settingsController.matchControlClass(mainCalulatorController);
		settingsController.setCalculatorProcess(mainProcess);
			
		extendFunctionsController.setMainCalulatorScene(mainCalulatorScene);
	    extendFunctionsController.matchExtendedControlClass(mainCalulatorController);
	        
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
        //file.createNewFile();
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
		   if (color == true) {
			   return "MoreFunc.png";
		   }else {
			   return "MoreFuncWhite.png";
		   }
	   case("moreFuncArrowIcon"):
		   if (color == true) {
			   return "MoreFuncArrow.png";
		   }else {
			   return "MoreFuncArrowWhite.png";
		   }
	   case("backIcon"):
		   if (color == true) {
			   return "backIcon.png";
		   }else {
			   return "backIconWhite.png";
		   }
	   case("enterIcon"):
		   if (color == true) {
			   return "enterIcon.png";
		   }else {
			   return "enterIconWhite.png";
		   }
	   case("settingsCogIcon"):
		   if (color == true) {
			   return "SettingsCogIcon.png";
		   }else {
			   return "SettingsCogIconWhite.png";
		   }
	   case("historyThrowIcon"):
		   if (color == true) {
			   return "HistoryIcon.png";
		   }else {
			   return "HistoryIconWhite.png";
		   }
	   case("addObject"):
		   if (color == true) {
			   return "addObject.png";
		   }else {
			   return "addObjectWhite.png";
		   }
	   case("minusIcon"):
		   if (color == true) {
			   return "minusIcon.png";
		   }else {
			   return "minusIconWhite.png";
		   }
	   case("xIcon"):
		   if (color == true) {
			   return "xIcon.png";
		   }else {
			   return "xIconWhite.png";
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