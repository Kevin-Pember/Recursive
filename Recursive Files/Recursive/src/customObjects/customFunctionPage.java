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
	import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import application.Main;
import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
	import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
	import javafx.scene.layout.GridPane;
import mainCalulatorPackage.MainCalulatorControllClass;
import processing.CalculatorProcessing;
public class customFunctionPage extends Main{

	    @FXML
	    private AnchorPane mainPane;

	    @FXML
	    private AnchorPane graphTypeAchorPane;

	    @FXML
	    private Label GraphTitleArea;

	    @FXML
	    private LineChart<Double, Double> graph;

	    @FXML
	    private NumberAxis numberXaxis;

	    @FXML
	    private NumberAxis numberYAxis;

	    @FXML
	    private Label graphEquationLabel;

	    @FXML
	    private ScrollPane graphVariableScrollPane;

	    @FXML
	    private AnchorPane graphVariableAnchorPane;

	    @FXML
	    private GridPane graphVariableGridPane;

	    @FXML
	    private AnchorPane functionTypeAchorPane;

	    @FXML
	    private Label functionName;

	    @FXML
	    private TextArea funcEquationLabel;

	    @FXML
	    private ScrollPane functionVariableScrollPane;

	    @FXML
	    private AnchorPane functionVariableAnchorPane;

	    @FXML
	    private GridPane functionVariableGridPane;
	    
	    @FXML
	    private TextField domainBottom;
	    
	    @FXML
	    private TextField domainTop;
	    
	    @FXML
	    private TextField rangeBottom;
	    
	    @FXML
	    private TextField rangeTop;
	    
	    @FXML
	    private Button functionBack;
	    
	    @FXML
	    private Button functionForward;
	    
	    @FXML
	    private ImageView functionBackImageView;
	    
	    @FXML
	    private ImageView functionForwardImageView;
	    
	    @FXML
	    private Button graphBack;
	    
	    @FXML
	    private Button graphForward;
	    
	    @FXML
	    private ImageView graphBackImageView;
	    
	    @FXML
	    private ImageView graphForwardImageView;
	    
	    @FXML
	    private Button helpButton;
	    
	    @FXML
	    private ImageView helpImageView;
	    
	    private MainCalulatorControllClass mainCacClass;
	    
	    protected String Title;
	    
	    protected String Equation;
	    
	    protected String version;
	    
	    protected String contentType;
	    
	    protected String displayColor;
	    
	    protected String numsButtonsColor;
	    
	    protected String funcButtonsColor;
	    
	    protected boolean textColor;
	    
	    private CalculatorProcessing mainProcess;
	    
	    ArrayList<FXMLLoader> variables = new ArrayList<FXMLLoader>();
	    ArrayList<variableObjectController> controllers = new ArrayList<variableObjectController>();
	    ArrayList<Parent> parents = new ArrayList<Parent>();
	    
	    @FXML
	    void onDomainBottomTyped(KeyEvent event) {
	    	if(event.toString().isEmpty() != true) {
	    		if(domainBottom.getText().equals("")) {
	    			numberXaxis.setLowerBound(mainProcess.domainBottom);
	    		}else {
	    			numberXaxis.setLowerBound(Double.parseDouble(domainBottom.getText()));
	    			mainProcess.domainBottom = Double.parseDouble(domainBottom.getText());
	    			graph();
	    		}
	    	}
	    }

	    @FXML
	    void onDomainTopTyped(KeyEvent event) {
	    	if(event.toString().isEmpty() != true) {
	    		if(domainTop.getText().equals("")) {
	    			numberXaxis.setUpperBound(mainProcess.domainTop);
	    		}else {
	    			numberXaxis.setUpperBound(Double.parseDouble(domainTop.getText()));
	    			mainProcess.domainTop = Double.parseDouble(domainTop.getText());
	    			graph();
	    		}
	    	}
	    }

	    @FXML
	    void onRangeBottomTyped(KeyEvent event) {
	    	if(event.toString().isEmpty() != true) {
	    		if(event.toString().isEmpty() != true) {
		    		if(rangeBottom.getText().equals("")) {
		    			numberYAxis.setLowerBound(mainProcess.rangeBottom);
		    		}else {
		    			numberYAxis.setLowerBound(Double.parseDouble(rangeBottom.getText()));
		    			graph();
		    		}
		    	}
	    	}
	    }

	    @FXML
	    void onRangeTopTyped(KeyEvent event) {
	    	if(event.toString().isEmpty() != true) {
	    		if(rangeTop.getText().equals("")) {
	    			numberXaxis.setUpperBound(mainProcess.rangeTop);
	    		}else {
	    			numberXaxis.setUpperBound(Double.parseDouble(rangeTop.getText()));
	    			graph();
	    		}
	    	}
	    }

	    @FXML
	    void onFunctionBackPressed(ActionEvent event) {
	    	navigate(true);
	    }
	    @FXML
	    void onFunctionForwardPressed(ActionEvent event) {
	    	navigate(false);
	    }
	    @FXML
	    void onGraphBackPressed(ActionEvent event) {
	    	navigate(true);
	    }
	    @FXML
	    void onGraphForwardPressed(ActionEvent event) {
	    	navigate(false);
	    }
	    @FXML
	    void onHelpPressed(ActionEvent event) {
	    	mainCacClass.helpPageController.setBackScene(helpButton.getScene(),mainCacClass.mainStage.getWidth(),mainCacClass.mainStage.getHeight());
	    	mainCacClass.helpPageController.updateColor(displayColor, numsButtonsColor, funcButtonsColor, textColor);
	    	mainCacClass.mainStage.setScene(mainCacClass.helpPage);
	    	stageSceneChange(mainCacClass.windowWidth+1,mainCacClass.windowHeight,mainCacClass.mainStage);
	    	mainCacClass.helpPageController.CustomFunctionsPageAnchorPane.setVisible(true);
	    }
	    public void setCalculatorProcess(CalculatorProcessing mainProcess,MainCalulatorControllClass mainClass) {
	    	this.mainProcess = mainProcess;
	    	mainCacClass = mainClass;
	    }
	    public void updateColor(String displayColor, String numsButtonsColor, String funcButtonsColor, boolean textColor) {
	    	this.displayColor = displayColor;
	    	this.numsButtonsColor = numsButtonsColor;
	    	this.funcButtonsColor = funcButtonsColor;
	    	this.textColor = textColor;
	    	functionBackImageView.setImage(new Image(iconColoration("moreFuncArrowIcon",textColor)));
	    	functionForwardImageView.setImage(new Image(iconColoration("moreFuncArrowIcon",textColor)));
	    	graphBackImageView.setImage(new Image(iconColoration("moreFuncArrowIcon",textColor)));
	    	graphForwardImageView.setImage(new Image(iconColoration("moreFuncArrowIcon",textColor)));
	    	helpImageView.setImage(new Image(iconColoration("helpIcon",textColor)));
	    }
	    public void onStartUp(String cs) {
	    	System.out.println(cs);
	    	Title = cs.substring(0,cs.indexOf("»"));
	    	cs = cs.substring(cs.indexOf("»")+1);
	    	Equation = cs.substring(0,cs.indexOf("»"));
	    	cs = cs.substring(cs.indexOf("»")+1);
	    	version = cs.substring(0,cs.indexOf("»"));
	    	cs = cs.substring(cs.indexOf("»")+1);
	    	contentType = cs.substring(0,cs.indexOf("»"));
	    	cs = cs.substring(cs.indexOf("»")+1);
	    	System.out.println(contentType);
	    	if(contentType.equals("Graph")) {
	    		graphTypeAchorPane.setVisible(true);
	    		functionTypeAchorPane.setVisible(false);
	    		GraphTitleArea.setText(Title);
	    		graphEquationLabel.setText(Equation);
	    		numberXaxis.setAutoRanging(false);
	    		numberXaxis.setLowerBound(mainProcess.domainBottom);
	    		domainBottom.setText(""+mainProcess.domainBottom);
	    		numberXaxis.setUpperBound(mainProcess.domainTop);
	    		domainTop.setText(""+mainProcess.domainTop);
	    		numberXaxis.setTickUnit(1);
	    		numberYAxis.setAutoRanging(false);
	    		numberYAxis.setLowerBound(mainProcess.rangeBottom);
	    		rangeBottom.setText(""+mainProcess.rangeBottom);
	    		numberYAxis.setUpperBound(mainProcess.rangeTop);
	    		rangeTop.setText(""+mainProcess.rangeTop);
	    		numberYAxis.setTickUnit(1);
	    		findVariables(Equation,"Graph");
	    		graph.setLegendVisible(false);;
	    		graph();
	    		
	    	}else if(contentType.equals("Function")) {
	    		functionTypeAchorPane.setVisible(true);
	    		graphTypeAchorPane.setVisible(false);
	    		functionName.setText(Title);
	    		funcEquationLabel.setText(Equation);
	    		findVariables(Equation,"Function");
	    		
	    	} 
	    }
	    public void findVariables(String equation, String contentType) {
			   equation = equation.toLowerCase();
			   
			   for (int i = 0; i < equation.length(); i++) {
				   if(equation.charAt(i) > 96 && equation.charAt(i) < 123) {
					   if(mainProcess.isVar(equation.charAt(i),i,equation) == 0) {
						   if(equation.substring(0,i).contains(equation.charAt(i)+"")) {
							   if(controllers.size() == 0) {
								   newVariable(equation.substring(i,i+1),contentType);
							   }else {
							   for(int j = 0; j < controllers.size(); j++) {
								   System.out.println(controllers.get(j).getVariableName()+" and "+equation.charAt(i));
								   if(controllers.get(j).getVariableName().equals(equation.charAt(i)+"")) {
									   i += 1;
									   break;
								   }else if(j == controllers.size()-1 || controllers.size() == 0) {
									   newVariable(equation.substring(i,i+1),contentType);
								   }
							   }
							   }
						   }else {
							   newVariable(equation.substring(i,i+1),contentType);
						   }
					   }else {
						   i += mainProcess.isVar(equation.charAt(i),i,equation);
					   }
				   }
			   }
		   }
	   public void newVariable(String variable, String ContentType) {
		    variables.add(new FXMLLoader(getClass().getResource("/variableObject.fxml")));
		    try {
			    parents.add(variables.get(variables.size()-1).load());
		    } catch (IOException e) {
		 	    e.printStackTrace();
		    }
		    controllers.add((variableObjectController) variables.get(variables.size()-1).getController());
		    controllers.get(controllers.size()-1).setVariable(variable);
		    controllers.get(controllers.size()-1).updateColors(displayColor, numsButtonsColor, funcButtonsColor, textColor);
		    controllers.get(controllers.size()-1).setReturnPage(this);
	    	int a = aCalculator();
	   		int b = parents.size()-1;
	   		if(contentType.equals("Graph")) {
	   			graphVariableGridPane.add(parents.get(parents.size()-1), a, b / 3);
	   			graphVariableAnchorPane.setPrefHeight(39 * (parents.size()/3));
	   		} else {
	   			functionVariableGridPane.add(parents.get(parents.size()-1), a, b / 3);
	   			functionVariableAnchorPane.setPrefHeight(100 * (parents.size()/3));
	   		}
	   		
	       	
	    }
	   public int aCalculator() {
	    	switch((parents.size()-1) % 3) {
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
	   public void updateLabel(String variable,String repNum) {
		   if(contentType.equals("Function")) {
		   String tempEquation = Equation;
			   for(int i = 0; i < controllers.size(); i++) {
				   if(controllers.get(i).getVariableValue() != "") {
					   tempEquation = tempEquation.replaceAll(controllers.get(i).getVariableName(), "("+controllers.get(i).getVariableValue()+")");
				   } else {
					   System.out.println("Nothing to see here");
					   tempEquation = tempEquation.replaceAll(controllers.get(i).getVariableName(), controllers.get(i).getVariableName());
				   }
			   }
			   
		   for(int i = 0; i < controllers.size();i++) {
			   if(mainProcess.isNum(controllers.get(i).getVariableValue())) {
				   if(i == controllers.size()-1) {
					   if(tempEquation.substring(0,tempEquation.indexOf('=')).length() > tempEquation.substring(tempEquation.indexOf('=')).length()) {
						   if (tempEquation.substring(tempEquation.indexOf('=')+1).equals("o")) {
							   tempEquation = tempEquation.substring(0,tempEquation.indexOf('=')+1) + mainProcess.solve(tempEquation.substring(0,tempEquation.indexOf('=')),true);
						   } else if(tempEquation.substring(tempEquation.indexOf('=')-1).equals("o")) {
							   tempEquation = mainProcess.solve(tempEquation.substring(tempEquation.indexOf('=')+1),true) + tempEquation.substring(tempEquation.indexOf('='));
						   }
					   }
				   }
			   }else {
				   break;
			   }
		   }
		   System.out.println("Function TempEquation is:"+tempEquation);
		   funcEquationLabel.setText(tempEquation);
		   }else if(contentType.equals("Graph")){
			   String tempEquation = Equation;
			   for(int i = 0; i < controllers.size(); i++) {
				   if(controllers.get(i).getVariableValue() != "") {
					   tempEquation = tempEquation.replaceAll(controllers.get(i).getVariableName(), "("+controllers.get(i).getVariableValue()+")");
				   } else {
					   System.out.println("Nothing to see here");
					   tempEquation = tempEquation.replaceAll(controllers.get(i).getVariableName(), controllers.get(i).getVariableName());
				   }
			   }
			   System.out.println("Graph TempEquation is:"+tempEquation);
			   graphEquationLabel.setText(tempEquation);
			   graph();
		   }
	   }
	   public void graph() {
		   graph.getData().removeAll(Collections.singleton(graph.getData().setAll()));
		   ArrayList<Integer> count = new ArrayList<Integer>();
		   String tempEquation = graphEquationLabel.getText();
		   for(int i = 0; i < controllers.size(); i++) {
			   if(!controllers.get(i).getVariableValue().equals("")) {
				   
			   }else {
				   count.add(i);
			   }
		   }
		   if(count.size() == 1) {
			   XYChart.Series<Double,Double> data = new XYChart.Series<Double,Double>();
			   for(double i = mainProcess.domainBottom; i <= mainProcess.domainTop; i += .1) {
				   System.out.println("Graph iteration");
				  data.getData().add(new XYChart.Data<Double,Double>((double)i,Double.parseDouble(mainProcess.solve(tempEquation.replaceAll(controllers.get(count.get(0)).getVariableName(), ""+i),true))));
			   }
			   graph.setCreateSymbols(false);
			   graph.getData().add(data);
		   }else {
			   
		   }
		   
	   }
	   int focuslocation = 0;
	   public void navigate(boolean backForward) {
		   controllers.forEach((n) -> {
			   if(n.isFocused()) {
				   System.out.println("Location is "+controllers.indexOf(n));
				   focuslocation = controllers.indexOf(n);
			   }
		   });
		   if(backForward) {
			   controllers.get(focuslocation-1).setFocused();
		   }else {
			   controllers.get(focuslocation+1).setFocused();
		   }
	   }
}
