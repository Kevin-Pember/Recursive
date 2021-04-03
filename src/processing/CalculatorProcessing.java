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
package processing;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.LinkedList;

import javafx.application.Platform;
import javafx.stage.Stage;

public class CalculatorProcessing {
	String piCon = "3.14159265358979323846264338327950288419716939937510582097494459230781640628";
	public double domainBottom = -10;
	public double domainTop = 10;
	public double rangeBottom = -10;
	public double rangeTop = 10;
	public MathContext mc;
	public boolean cacMode;
	public CalculatorProcessing(MathContext mc, boolean cacMode){
		this.mc = mc;
		this.cacMode = cacMode;
	}
	public void updateCacMode(boolean cacMode) {
		this.cacMode = cacMode;
	}
	// Method that is the base of the Calculator that finds operators and calls the methods needed to solve them
	public String solve(String userInput) {
		 System.out.println(mc.toString());
		 System.out.println("Input math ran");
		 LinkedList<Integer> parPos = new LinkedList<Integer>();
		 String resultValue ="";
	     int frontParse = 0;
	     userInput = userInput.replaceAll(" ","");
		 for(int i = 0; i < userInput.length(); i++) {
				switch(userInput.charAt(i)) {
				  case('π'):
					  System.out.println(userInput.substring(i,i+1));
					  userInput = userInput.substring(0,i) + mutiNeeded(i,i+1,userInput,piCon) + userInput.substring(i+1);
				  break;
				  case('e'): 
					System.out.println(userInput.substring(i,i+1));
				    if(userInput.charAt(i-1) == 's') {
				    	break;
				    }
					userInput = userInput.substring(0,i) + mutiNeeded(i,i+1,userInput,"2.718281828") + userInput.substring(i+1);
				  break;
				}
		 }
	    for(int i = 0; i < userInput.length(); i++) {
	 	 
	   	 if (userInput.charAt(i) == '(') {
	   		 parPos.addFirst(i);
	   		 if(i > 0) {
	   			 if(((int) userInput.charAt(i-1)> 47 && (int) userInput.charAt(i-1) < 58) || userInput.charAt(i-1) == '.' || userInput.charAt(i-1) == ')' || userInput.charAt(i-1) == '²' || userInput.charAt(i-1) == '!') {
	   				 userInput = userInput.substring(0,i) + '*' + userInput.substring(i);
	   				 System.out.println(userInput + "( muti test");
	   				 parPos.set(0, parPos.getFirst()+1); 
	   		}
	 			 i++;
	 		 }
	 	 }
	 	 if (i ==userInput.length()-1 && parPos.size() > 0) {
	 		userInput = parMethod(userInput,i,parPos);
	 		System.out.println(userInput);
	 	 }
	  }
		 for(int i = 0; i < userInput.length(); i++) {
			switch(userInput.charAt(i)) {
			  case('²'): 
				System.out.println(Double.parseDouble(readerBack(userInput.substring(0,i)))+"²");
				userInput = userInput.substring(0,i-readerBack(userInput.substring(0,i)).length())+ mutiNeeded(i-readerBack(userInput.substring(0,i)).length(),i+1,userInput,""+ new BigDecimal(readerBack(userInput.substring(0,i))).pow(2))+userInput.substring(i+1);
				//i = 0;
			  break;
			  case('^'): 
				System.out.println(userInput.substring(i-readerBack(userInput.substring(0,i)).length(),i+userInput.substring(i+1).length()+1));
				userInput = userInput.substring(0,i-readerBack(userInput.substring(0,i)).length())+BigDecimal.valueOf(Math.pow(new BigDecimal(readerBack(userInput.substring(0,i))).doubleValue(),new BigDecimal(readerFront(userInput.substring(i+1))).doubleValue()))+userInput.substring(i+userInput.substring(i+1).length()+1);
				i = 0;
			  break;
			  case('√'):
				System.out.println(userInput.substring(i,i+readerFront(userInput.substring(i+1)).length()+1));
				userInput = userInput.substring(0,i) + mutiNeeded(i,i+readerFront(userInput.substring(i+1)).length()+1,userInput,""+BigDecimal.valueOf(Math.sqrt(Double.parseDouble(readerFront(userInput.substring(i+1)))))) + userInput.substring(i+readerFront(userInput.substring(i+1)).length()+1);
				i = 0;
			  break;
			 }
		 }
		 for(int i = 0; i < userInput.length(); i++) {
			 switch(userInput.charAt(i)) {
			 
			 case('×'):
				 System.out.println((new BigDecimal(readerBack(userInput.substring(0,i)))+" "+(new BigDecimal(readerFront(userInput.substring(i+1)),mc))));
				 userInput = userInput.substring(0,i-readerBack(userInput.substring(0,i)).length())+(new BigDecimal(readerBack(userInput.substring(0,i))).multiply(new BigDecimal(readerFront(userInput.substring(i+1)),mc),mc))+userInput.substring(i+(""+readerFront(userInput.substring(i+1))).length()+1);
				 System.out.println("This is userInput"+userInput);
				 i = 0;
			 break;
			 case('*'):
				 System.out.println(userInput.substring(i-readerBack(userInput.substring(0,i)).length(),i+userInput.substring(i+1).length()+1));
				 userInput = userInput.substring(0,i-readerBack(userInput.substring(0,i)).length())+(new BigDecimal(readerBack(userInput.substring(0,i))).multiply(new BigDecimal(readerFront(userInput.substring(i+1)),mc),mc))+userInput.substring(i+(""+readerFront(userInput.substring(i+1))).length()+1);
				 i = 0;
			 break;
			 case('÷'):
				 System.out.println(userInput.substring(i-readerBack(userInput.substring(0,i)).length(),i+userInput.substring(i+1).length()+1));
			 	 try {
			 		 resultValue = ""+(new BigDecimal(readerBack(userInput.substring(0,i)),mc).divide(new BigDecimal(readerFront(userInput.substring(i+1)),mc),mc));
			 		 System.out.println(resultValue);
			 		 frontParse = i-readerBack(userInput.substring(0,i)).length();
			 		 userInput = userInput.substring(0,frontParse)+resultValue+userInput.substring(i+(""+readerFront(userInput.substring(i+1))).length()+1);
			 		 i = frontParse + resultValue.length()-1;
			 		 System.out.println("i is :"+i);
			 	 }catch(Exception e) {
			 		System.out.println("Things wrong");
			 		if(e instanceof ArithmeticException) {
			 			 System.out.println("Divison by Zero");
			   			 userInput = "undefined";
			   		 }else if(e instanceof NumberFormatException){
			   			
			   			System.out.println("i is :"+i);
			   		 }
			 	 }
				 
			 break;
			 case('/'):
			   if(userInput.charAt(i-1) != ',') {
				   System.out.println(userInput.substring(i-readerBack(userInput.substring(0,i)).length(),i+userInput.substring(i+1).length()+1));
				 	 try {
				 		 resultValue = ""+(new BigDecimal(readerBack(userInput.substring(0,i)),mc).divide(new BigDecimal(readerFront(userInput.substring(i+1)),mc),mc));
				 		 System.out.println(resultValue);
				 		 frontParse = i-readerBack(userInput.substring(0,i)).length();
				 		 userInput = userInput.substring(0,frontParse)+resultValue+userInput.substring(i+(""+readerFront(userInput.substring(i+1))).length()+1);
				 		 i = frontParse + resultValue.length()-1;
				 		System.out.println("i is :"+i);
				 	 }catch(Exception e) {
				 		System.out.println("Things wrong");
				 		if(e instanceof ArithmeticException) {
				 			 System.out.println("Divison by Zero");
				   			 userInput = "undefined";
				   		 }else if(e instanceof NumberFormatException){
				   			 if(isNum(readerBack(userInput.substring(0,i))) != true) {
				   				 
				   			 }else if(isNum(readerFront(userInput.substring(i+1))) != true) {
				   				 
				   			 }
				   			 
				   			 System.out.println("i is :"+i);
				   		 }
				 	 }
					 
			   }
			 break;
			 case('%'):
				 System.out.println(userInput.substring(i-readerBack(userInput.substring(0,i)).length(),i+userInput.substring(i+1).length()+1));
				 userInput = userInput.substring(0,i-readerBack(userInput.substring(0,i)).length())+(new BigDecimal(readerBack(userInput.substring(0,i))).multiply(new BigDecimal(readerFront(userInput.substring(i+1)),mc)).divide(BigDecimal.valueOf(100),mc))+userInput.substring(i+(""+readerFront(userInput.substring(i+1))).length()+1);
				 i = 0;
			 break;
			 case('!'):
				 System.out.println(userInput.substring(i-readerBack(userInput.substring(0,i)).length(),i+1));
				 userInput = userInput.substring(0,i-readerBack(userInput.substring(0,i)).length())+mutiNeeded(i-readerBack(userInput.substring(0,i)).length(),i+1,userInput,""+factorialMethod(readerBack(userInput.substring(0,i))))+userInput.substring(i+1);
				 i = 0;
			 break;
			 
			 }
		  }	
		 for(int i = 1; i < userInput.length(); i++) {
			 switch(userInput.charAt(i)) {
			 	case('+'):
			 		if(userInput.charAt(i-1) != 'E') {
			 			System.out.println(userInput.substring(i-readerBack(userInput.substring(0,i)).length(),i+userInput.substring(i+1).length()+1));
			 			userInput = userInput.substring(0,i-readerBack(userInput.substring(0,i)).length())+(new BigDecimal(readerBack(userInput.substring(0,i))).add(new BigDecimal(readerFront(userInput.substring(i+1)),mc),mc))+userInput.substring(i+(""+readerFront(userInput.substring(i+1))).length()+1);
			 			i = 0;
			 		}
			 	break;
			 	case('-'):
			 		if(userInput.charAt(i-1) != 'E') {
			 			System.out.println(userInput.substring(i-readerBack(userInput.substring(0,i)).length(),i+userInput.substring(i+1).length()+1));
			 			userInput = userInput.substring(0,i-readerBack(userInput.substring(0,i)).length())+(new BigDecimal(readerBack(userInput.substring(0,i))).subtract(new BigDecimal(readerFront(userInput.substring(i+1)),mc),mc))+userInput.substring(i+(""+readerFront(userInput.substring(i+1))).length()+1);
			 			i = 0;
			 		}
			 	break;
			 }
			}
		 for(int i = 1; i < userInput.length(); i++) {
			 switch(userInput.charAt(i)) {
			 	case(','):
			 		userInput = userInput.substring(0,i) + userInput.substring(i+1);
			 	break;
			 	case(')'):
			 		userInput = userInput.substring(0,i) + userInput.substring(i+1);
			 	break;
			 }
			}
		 return userInput;
	}

	//Method used to find the number in front of an operator
	public String readerBack(String sub) {
		System.out.println("Reader Back");
		 String outputSub = "";
		 for(int i = sub.length()-1; i >= 0; i--) {
			 if(sub.charAt(i) != '×' && sub.charAt(i) != '*' && sub.charAt(i) != '÷' && sub.charAt(i) != '/' && sub.charAt(i) != '√' &&sub.charAt(i) != '²' && sub.charAt(i) != '^' && sub.charAt(i) != '(' && sub.charAt(i) != ')' &&sub.charAt(i) != '%' &&sub.charAt(i) != '!'&&sub.charAt(i) != 'π'&&sub.charAt(i) != 'e') {
				 if (i == 0 ) {
					 outputSub = sub.substring(i);
					 break; 
				 }else if(sub.charAt(i) == '+') {
					 if(sub.charAt(i-1) == 'E') {
						 
					 }else {
						 outputSub = sub.substring(i+1);
						 break;
					 }
				 }else if (sub.charAt(i) == '-') {
					 if(i ==0) {
						 
					 }else if(sub.charAt(i-1) == 'E'){
						 
					 }else {
						 outputSub = sub.substring(i+1);
						 break;
					 }
				 }
			 }else {
				 outputSub = sub.substring(i+1);
				 break;
			 }
		 }
		 System.out.println("Outputsub is "+ outputSub);
		 return outputSub;
	}
	//Method used to find the number behind an operator
	public String readerFront(String sub) {
		System.out.println("Reader Front");
		 String outputSub = "";
		 for(int i = 0; i <= sub.length()-1; i++){
	     if(sub.charAt(i) != '×' &&sub.charAt(i) != '*' &&sub.charAt(i) != '÷' &&sub.charAt(i) != '/' &&sub.charAt(i) != '√' &&sub.charAt(i) != '²' && sub.charAt(i) != '^' &&sub.charAt(i) != '(' &&sub.charAt(i) != ')' &&sub.charAt(i) != '%' &&sub.charAt(i) != '!'&&sub.charAt(i) != 'π'&&sub.charAt(i) != 'e') {
	    	 if (i == sub.length()-1) {
					 outputSub = sub.substring(0, i + 1);
					 break; 
			 }else if(sub.charAt(i) == '+') {
				 if(sub.charAt(i-1) == 'E') {
					 
				 }else {
					 outputSub = sub.substring(0,i);
					 break;
				 }
			 }else if (sub.charAt(i) == '-') {
				 if(i == 0) {
					 
				 }else if(sub.charAt(i-1) == 'E'){
					 
				 }else {
					 outputSub = sub.substring(0,i);
					 break;
				 }
			 }
		}else {
			System.out.println("Pre asignment");
			outputSub = sub.substring(0,i);
		    break;
		}
	   }
		System.out.println("Outputsub is "+ outputSub);
		return outputSub;
	}
	//Method used to complete Pars 
	 public String parMethod(String fullInput, int firstPar, LinkedList<Integer> parPos) {
	 	System.out.println("Par Method ran");
	 	String parResult = "";
	 	int loopCon = parPos.size();
	 	System.out.println("Parenthesis");
	 	parPos.forEach((n) -> System.out.print(n));
	 	for(int e = 0; e < loopCon; e++) {
	 		if(parPos.getFirst() > 0) {
	 			
	 		}
	 	for(int i = parPos.getFirst() + 1; i < fullInput.length(); i++) {
		    	 if (fullInput.charAt(i) == ')') {
		    		 System.out.println(") found run from there");
		    		 parResult = solve(fullInput.substring(parPos.getFirst() + 1, i));
		    		 System.out.println(parResult);
		    		 fullInput = frontParMethods(fullInput,parResult,i, parPos);
		    		 parPos.removeFirst();
		    		 System.out.println("after par parsing" + fullInput);
		    		 break;
		      }
		    	 if(i == fullInput.length()-1 && fullInput.charAt(i) != ')') {
		    		 System.out.println(") not found run from end");
		    		 fullInput += ")";
		    		 parResult = solve(fullInput.substring(parPos.getFirst() + 1,fullInput.length()-1));
		    		 System.out.println(parResult);
		    		 fullInput = frontParMethods(fullInput,parResult,fullInput.length()-1, parPos);
		    		 parPos.removeFirst();
		    		 System.out.println("after par parsing" + fullInput);
		    		 break;
		    	 }
		    	 
	 	}
	 	  
	   }
	 	return fullInput;
	 }
	 //Method used to check if a Par has a letter operator
	 public String frontParMethods(String fullInput,String parResult, int i, LinkedList<Integer> parPos) {
		 System.out.println(fullInput);
		  int testValue;
		 if(parPos.getFirst() > 0) {
	    	 switch(fullInput.charAt(parPos.getFirst()-1)) {
	    	 case('f'):
	    		 if(containsValue(fullInput,"d→f",testValue = parPos.getFirst()-3,parPos.getFirst())) {
				 	System.out.println(decimalParser(parResult));
				 	fullInput = fullInput.substring(0,parPos.getFirst()-3) + mutiNeeded(parPos.getFirst()-3,i+1,fullInput,decimalParser(parResult))  + fullInput.substring(i+1);	
				 }
	    	 break;
	    	 case('¹'): //sin⁻¹
	    		 //System.out.println("arcsin is found "+containsValue(fullInput,"sin⁻¹",testValue = parPos.getFirst()-5,parPos.getFirst()));
				 if(containsValue(fullInput,"sin⁻¹",testValue = parPos.getFirst()-5,parPos.getFirst())) {
					 if(cacMode == true) {
				 			System.out.println(BigDecimal.valueOf(Math.asin(Math.toRadians((new BigDecimal(parResult,mc).doubleValue())))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-5) + mutiNeeded(parPos.getFirst()-5,i+1,fullInput,""+BigDecimal.valueOf(Math.asin((new BigDecimal(parResult,mc).doubleValue()))).multiply(new BigDecimal(180,mc).divide(new BigDecimal(piCon,mc),mc),mc))  + fullInput.substring(i+1);
				 		}else {
				 			System.out.println(BigDecimal.valueOf(Math.asin((new BigDecimal(parResult,mc).doubleValue()))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-5) + mutiNeeded(parPos.getFirst()-5,i+1,fullInput,""+BigDecimal.valueOf(Math.asin((new BigDecimal(parResult,mc).doubleValue())))) + fullInput.substring(i+1);
				 		}
				 } else if(containsValue(fullInput,"cos⁻¹",testValue = parPos.getFirst()-5,parPos.getFirst())) {
					 if(cacMode == true) {
				 			System.out.println(BigDecimal.valueOf(Math.acos(Math.toRadians((new BigDecimal(parResult,mc).doubleValue())))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-5) + mutiNeeded(parPos.getFirst()-5,i+1,fullInput,""+BigDecimal.valueOf(Math.acos((new BigDecimal(parResult,mc).doubleValue()))).multiply(new BigDecimal(180,mc).divide(new BigDecimal(piCon,mc),mc),mc))  + fullInput.substring(i+1);
				 		}else {
				 			System.out.println(BigDecimal.valueOf(Math.acos((new BigDecimal(parResult,mc).doubleValue()))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-5) + mutiNeeded(parPos.getFirst()-5,i+1,fullInput,""+BigDecimal.valueOf(Math.acos((new BigDecimal(parResult,mc).doubleValue())))) + fullInput.substring(i+1);
				 		}
				 } else if(containsValue(fullInput,"tan⁻¹",testValue =parPos.getFirst()-5,parPos.getFirst())) {
					 if(cacMode == true) {
				 			System.out.println(BigDecimal.valueOf(Math.atan(Math.toRadians(Double.parseDouble(parResult)))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-5) + mutiNeeded(parPos.getFirst()-5,i+1,fullInput,""+BigDecimal.valueOf(Math.atan((new BigDecimal(parResult,mc).doubleValue()))).multiply(new BigDecimal(180,mc).divide(new BigDecimal(piCon,mc),mc),mc))  + fullInput.substring(i+1);
				 		}else {
				 			System.out.println(BigDecimal.valueOf(Math.atan((new BigDecimal(parResult,mc).doubleValue()))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-5) + mutiNeeded(parPos.getFirst()-5,i+1,fullInput,""+BigDecimal.valueOf(Math.atan((new BigDecimal(parResult,mc).doubleValue())))) + fullInput.substring(i+1);
				 		}
				 } else if(containsValue(fullInput,"csc⁻¹",testValue = parPos.getFirst()-5,parPos.getFirst())) {
					 if(cacMode == true) {
				 			System.out.println(BigDecimal.valueOf(Math.asin(1/(new BigDecimal(parResult,mc).doubleValue()))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-5) + mutiNeeded(parPos.getFirst()-5,i+1,fullInput,""+BigDecimal.valueOf(Math.asin(1/(new BigDecimal(parResult,mc).doubleValue()))).multiply(new BigDecimal(180,mc).divide(new BigDecimal(piCon,mc),mc),mc))  + fullInput.substring(i+1);
				 		}else {
				 			System.out.println(BigDecimal.valueOf(Math.asin(1/(new BigDecimal(parResult,mc).doubleValue()))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-5) + mutiNeeded(parPos.getFirst()-5,i+1,fullInput,""+BigDecimal.valueOf(Math.asin(1/(new BigDecimal(parResult,mc).doubleValue())))) + fullInput.substring(i+1);
				 		}
				 } else if(containsValue(fullInput,"sec⁻¹",testValue =parPos.getFirst()-5,parPos.getFirst())) {
					 if(cacMode == true) {
				 			System.out.println(BigDecimal.valueOf(Math.acos(1/(new BigDecimal(parResult,mc).doubleValue()))).multiply(new BigDecimal(180,mc).divide(new BigDecimal(piCon,mc),mc),mc));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-5) + mutiNeeded(parPos.getFirst()-5,i+1,fullInput,""+BigDecimal.valueOf(Math.acos(1/(new BigDecimal(parResult,mc).doubleValue()))).multiply(new BigDecimal(180,mc).divide(new BigDecimal(piCon,mc),mc),mc))  + fullInput.substring(i+1);
				 		}else {
				 			System.out.println(BigDecimal.valueOf(Math.acos(1/(new BigDecimal(parResult,mc).doubleValue()))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-5) + mutiNeeded(parPos.getFirst()-5,i+1,fullInput,""+BigDecimal.valueOf(Math.acos(1/(new BigDecimal(parResult,mc).doubleValue())))) + fullInput.substring(i+1);
				 		}
				 } else if(containsValue(fullInput,"cot⁻¹",testValue = parPos.getFirst()-5,parPos.getFirst())) {
					 if(cacMode == true) {
				 			System.out.println(((new BigDecimal(piCon,mc).divide(BigDecimal.valueOf(2),mc)).subtract(BigDecimal.valueOf(Math.atan((new BigDecimal(parResult,mc).doubleValue()))))).multiply(new BigDecimal(180,mc).divide(new BigDecimal(piCon,mc),mc),mc));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-5) + mutiNeeded(parPos.getFirst()-5,i+1,fullInput,""+((new BigDecimal(piCon,mc).divide(BigDecimal.valueOf(2),mc)).subtract(BigDecimal.valueOf(Math.atan((new BigDecimal(parResult,mc).doubleValue()))))).multiply(new BigDecimal(180,mc).divide(new BigDecimal(piCon,mc),mc),mc))  + fullInput.substring(i+1);
				 		}else {
				 			System.out.println(BigDecimal.valueOf(Math.acos(Double.parseDouble(parResult))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-5) + mutiNeeded(parPos.getFirst()-5,i+1,fullInput,""+((new BigDecimal(piCon,mc).divide(BigDecimal.valueOf(2),mc)).subtract(BigDecimal.valueOf(Math.atan((new BigDecimal(parResult,mc).doubleValue())))))) + fullInput.substring(i+1);
				 		}
				 }
			 break;
			 case('n'):
				 System.out.println("n found");
				 if(containsValue(fullInput,"ln",parPos.getFirst()-2,parPos.getFirst())) {
					 System.out.println(BigDecimal.valueOf(Math.log(Double.parseDouble(parResult))));
					 fullInput = fullInput.substring(0,parPos.getFirst()-2) + mutiNeeded(parPos.getFirst()-2,i+1,fullInput,""+BigDecimal.valueOf(Math.log((new BigDecimal(parResult,mc).doubleValue())))) + fullInput.substring(i+1);
				 
				 }else if(containsValue(fullInput,"arcsin",parPos.getFirst()-6,parPos.getFirst())) {
					 System.out.println("arcsin Found");
					 if(cacMode == true) {
				 			System.out.println(BigDecimal.valueOf(Math.asin(Math.toRadians(Double.parseDouble(parResult)))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-6) + mutiNeeded(parPos.getFirst()-6,i+1,fullInput,""+BigDecimal.valueOf(Math.asin((new BigDecimal(parResult,mc).doubleValue()))).multiply(new BigDecimal(180,mc).divide(new BigDecimal(piCon,mc),mc),mc))  + fullInput.substring(i+1);
				 		}else {
				 			System.out.println(BigDecimal.valueOf(Math.asin(Double.parseDouble(parResult))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-6) + mutiNeeded(parPos.getFirst()-6,i+1,fullInput,""+BigDecimal.valueOf(Math.asin((new BigDecimal(parResult,mc).doubleValue())))) + fullInput.substring(i+1);
				 		}
				 }else if(containsValue(fullInput,"arctan",parPos.getFirst()-6,parPos.getFirst())) {
					 if(cacMode == true) {
				 			System.out.println(BigDecimal.valueOf(Math.atan(Math.toRadians(Double.parseDouble(parResult)))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-6) + mutiNeeded(parPos.getFirst()-6,i+1,fullInput,""+BigDecimal.valueOf(Math.atan((new BigDecimal(parResult,mc).doubleValue()))).multiply(new BigDecimal(180,mc).divide(new BigDecimal(piCon,mc),mc),mc))  + fullInput.substring(i+1);
				 		}else {
				 			System.out.println(BigDecimal.valueOf(Math.atan(Double.parseDouble(parResult))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-6) + mutiNeeded(parPos.getFirst()-6,i+1,fullInput,""+BigDecimal.valueOf(Math.atan((new BigDecimal(parResult,mc).doubleValue())))) + fullInput.substring(i+1);
				 		}
				 } else if(containsValue(fullInput,"tan",parPos.getFirst()-3,parPos.getFirst())) {
					 if(cacMode == true) {
						    System.out.println(BigDecimal.valueOf(Math.tan(Math.toRadians(Double.parseDouble(parResult)))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-3) + mutiNeeded(parPos.getFirst()-3,i+1,fullInput,""+BigDecimal.valueOf(Math.tan((new BigDecimal(parResult,mc).multiply(new BigDecimal(piCon,mc).divide(new BigDecimal(180,mc),mc),mc)).doubleValue()))) + fullInput.substring(i+1);
				 			break;
				 		}else {
				 			System.out.println(BigDecimal.valueOf(Math.tan(Double.parseDouble(parResult))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-3) + mutiNeeded(parPos.getFirst()-3,i+1,fullInput,""+BigDecimal.valueOf(Math.tan((new BigDecimal(parResult,mc).doubleValue())))) + fullInput.substring(i+1);
				 			break;
				 		}
					 
				 } else if(containsValue(fullInput,"sin",parPos.getFirst()-3,parPos.getFirst())) {
					 System.out.println("sin found");
					 if(cacMode == true) {
						    System.out.println(BigDecimal.valueOf(Math.sin(Math.toRadians(Double.parseDouble(parResult)))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-3) + mutiNeeded(parPos.getFirst()-3,i+1,fullInput,""+BigDecimal.valueOf(Math.sin((new BigDecimal(parResult,mc).multiply(new BigDecimal(piCon,mc).divide(new BigDecimal(180,mc),mc),mc)).doubleValue()))) + fullInput.substring(i+1);
				 			System.out.println("This full input after sin interp "+ fullInput);
				 			break;
					    }else {
				 			System.out.println(BigDecimal.valueOf(Math.sin(Double.parseDouble(parResult))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-3) + mutiNeeded(parPos.getFirst()-3,i+1,fullInput,""+BigDecimal.valueOf(Math.sin((new BigDecimal(parResult,mc).doubleValue())))) + fullInput.substring(i+1);
				 			System.out.println("This full input after sin interp "+ fullInput);
				 			break;
				 		}
				 } 
			 break;
			 case('₀'): 
				 if (containsValue(fullInput,"log₁₀",parPos.getFirst()-5,parPos.getFirst())) {
					 System.out.println(BigDecimal.valueOf(Math.log10(Double.parseDouble(parResult))));
					 fullInput = fullInput.substring(0,parPos.getFirst()-5) + mutiNeeded(parPos.getFirst()-5,i+1,fullInput,""+BigDecimal.valueOf(Math.log10((new BigDecimal(parResult,mc).doubleValue())))) + fullInput.substring(i+1);
				 }
			 break;
			 case('s'):
				 if(containsValue(fullInput,"cos",parPos.getFirst()-3,parPos.getFirst())) {
					 if(cacMode == true) {
						    System.out.println(BigDecimal.valueOf(Math.cos(Math.toRadians(Double.parseDouble(parResult)))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-3) + mutiNeeded(parPos.getFirst()-3,i+1,fullInput,""+BigDecimal.valueOf(Math.cos((new BigDecimal(parResult,mc).multiply(new BigDecimal(piCon,mc).divide(new BigDecimal(180,mc),mc),mc)).doubleValue()))) + fullInput.substring(i+1);
				 		}else {
				 			System.out.println(BigDecimal.valueOf(Math.cos(Double.parseDouble(parResult))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-3) + mutiNeeded(parPos.getFirst()-3,i+1,fullInput,""+BigDecimal.valueOf(Math.cos((new BigDecimal(parResult,mc).doubleValue())))) + fullInput.substring(i+1);
				 		}
				 } else if(containsValue(fullInput,"arccos",parPos.getFirst()-6,parPos.getFirst())) {
					 if(cacMode == true) {
				 			System.out.println(BigDecimal.valueOf(Math.acos(Math.toRadians(Double.parseDouble(parResult)))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-6) + mutiNeeded(parPos.getFirst()-6,i+1,fullInput,""+BigDecimal.valueOf(Math.acos((new BigDecimal(parResult,mc).doubleValue()))).multiply(new BigDecimal(180,mc).divide(new BigDecimal(piCon,mc),mc),mc))  + fullInput.substring(i+1);
				 		}else {
				 			System.out.println(BigDecimal.valueOf(Math.acos(Double.parseDouble(parResult))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-6) + mutiNeeded(parPos.getFirst()-6,i+1,fullInput,""+BigDecimal.valueOf(Math.acos((new BigDecimal(parResult,mc).doubleValue())))) + fullInput.substring(i+1);
				 		}
				 }
			 break;
			 case('c'):
				 if(containsValue(fullInput,"arcsec",parPos.getFirst()-6,parPos.getFirst())) {
					 if(cacMode == true) {
				 			System.out.println(BigDecimal.valueOf(Math.acos(1/(new BigDecimal(parResult,mc).doubleValue()))).multiply(new BigDecimal(180,mc).divide(new BigDecimal(piCon,mc),mc),mc));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-6) + mutiNeeded(parPos.getFirst()-6,i+1,fullInput,""+BigDecimal.valueOf(Math.acos(1/(new BigDecimal(parResult,mc).doubleValue()))).multiply(new BigDecimal(180,mc).divide(new BigDecimal(piCon,mc),mc),mc))  + fullInput.substring(i+1);
				 		}else {
				 			System.out.println(BigDecimal.valueOf(Math.acos(1/(new BigDecimal(parResult,mc).doubleValue()))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-6) + mutiNeeded(parPos.getFirst()-6,i+1,fullInput,""+BigDecimal.valueOf(Math.acos(1/(new BigDecimal(parResult,mc).doubleValue())))) + fullInput.substring(i+1);
				 		}
				 }else if(containsValue(fullInput,"arccsc",parPos.getFirst()-6,parPos.getFirst())) {
					 if(cacMode == true) {
				 			System.out.println(BigDecimal.valueOf(Math.asin(1/(new BigDecimal(parResult,mc).doubleValue()))).multiply(new BigDecimal(180,mc).divide(new BigDecimal(piCon,mc),mc),mc));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-6) + mutiNeeded(parPos.getFirst()-6,i+1,fullInput,""+BigDecimal.valueOf(Math.asin(1/(new BigDecimal(parResult,mc).doubleValue()))).multiply(new BigDecimal(180,mc).divide(new BigDecimal(piCon,mc),mc),mc))  + fullInput.substring(i+1);
				 		}else {
				 			System.out.println(BigDecimal.valueOf(Math.asin(1/(new BigDecimal(parResult,mc).doubleValue()))));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-6) + mutiNeeded(parPos.getFirst()-6,i+1,fullInput,""+BigDecimal.valueOf(Math.asin(1/(new BigDecimal(parResult,mc).doubleValue())))) + fullInput.substring(i+1);
				 		}
				 }else if(containsValue(fullInput,"sec",parPos.getFirst()-3,parPos.getFirst())) {
					 if(cacMode == true) {
						    System.out.println(BigDecimal.valueOf(1).divide(new BigDecimal(Math.cos((new BigDecimal(parResult,mc).multiply(new BigDecimal(piCon,mc).divide(new BigDecimal(180,mc),mc),mc)).doubleValue())),mc));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-3) + mutiNeeded(parPos.getFirst()-3,i+1,fullInput,""+BigDecimal.valueOf(1).divide(new BigDecimal(Math.cos((new BigDecimal(parResult,mc).multiply(new BigDecimal(piCon,mc).divide(new BigDecimal(180,mc),mc),mc)).doubleValue())),mc)) + fullInput.substring(i+1);
				 		}else {
				 			System.out.println(BigDecimal.valueOf(1).divide(new BigDecimal(Math.cos((new BigDecimal(parResult,mc).doubleValue()))),mc));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-3) + mutiNeeded(parPos.getFirst()-3,i+1,fullInput,""+BigDecimal.valueOf(1).divide(new BigDecimal(Math.cos((new BigDecimal(parResult,mc).doubleValue()))),mc)) + fullInput.substring(i+1);
				 		}
				 }else if(containsValue(fullInput,"csc",parPos.getFirst()-3,parPos.getFirst())) {
					 if(cacMode == true) {
						    System.out.println(BigDecimal.valueOf(1).divide(new BigDecimal(Math.sin((new BigDecimal(parResult,mc).multiply(new BigDecimal(piCon,mc).divide(new BigDecimal(180,mc),mc),mc)).doubleValue())),mc));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-3) + mutiNeeded(parPos.getFirst()-3,i+1,fullInput,""+BigDecimal.valueOf(1).divide(new BigDecimal(Math.sin((new BigDecimal(parResult,mc).multiply(new BigDecimal(piCon,mc).divide(new BigDecimal(180,mc),mc),mc)).doubleValue())),mc)) + fullInput.substring(i+1);
				 		}else {
				 			System.out.println(BigDecimal.valueOf(1).divide(new BigDecimal(Math.sin((new BigDecimal(parResult,mc).doubleValue()))),mc));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-3) + mutiNeeded(parPos.getFirst()-3,i+1,fullInput,""+BigDecimal.valueOf(1).divide(new BigDecimal(Math.sin((new BigDecimal(parResult,mc).doubleValue()))),mc)) + fullInput.substring(i+1);
				 		}
				 }
			 break;
			 case('t'):
				 if(containsValue(fullInput,"cot",parPos.getFirst()-3,parPos.getFirst())) {
					 if(cacMode == true) {
				 			System.out.println((new BigDecimal(Math.cos((new BigDecimal(parResult,mc).doubleValue()))).divide(new BigDecimal(Math.sin((new BigDecimal(parResult,mc).doubleValue()))),mc)).multiply(new BigDecimal(180,mc).divide(new BigDecimal(piCon,mc),mc),mc));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-3) + mutiNeeded(parPos.getFirst()-3,i+1,fullInput,""+(new BigDecimal(Math.cos((new BigDecimal(parResult,mc).doubleValue()))).divide(new BigDecimal(Math.sin((new BigDecimal(parResult,mc).doubleValue()))),mc)).multiply(new BigDecimal(180,mc).divide(new BigDecimal(piCon,mc),mc),mc))  + fullInput.substring(i+1);
				 		}else {
				 			System.out.println((new BigDecimal(Math.cos((new BigDecimal(parResult,mc).doubleValue()))).divide(new BigDecimal(Math.sin((new BigDecimal(parResult,mc).doubleValue()))),mc)));
				 			fullInput = fullInput.substring(0,parPos.getFirst()-3) + mutiNeeded(parPos.getFirst()-3,i+1,fullInput,""+(new BigDecimal(Math.cos((new BigDecimal(parResult,mc).doubleValue()))).divide(new BigDecimal(Math.sin((new BigDecimal(parResult,mc).doubleValue()))),mc))) + fullInput.substring(i+1);
				 		}
				 }
			 break;
			 default:
				 fullInput = fullInput.substring(0,parPos.getFirst()) + mutiNeeded(parPos.getFirst(),i+1,fullInput,""+new BigDecimal(parResult,mc)) + fullInput.substring(i+1);
	    	 }
	    	 return fullInput;
		 }else {
			 fullInput = fullInput.substring(0,parPos.getFirst()) + mutiNeeded(parPos.getFirst(),i+1,fullInput,""+new BigDecimal(parResult,mc)) + fullInput.substring(i+1);
			 return fullInput;
		 }
	 }
	 
	 //uses hasNumValue to add * if needed
	 public String mutiNeeded(int front,int back, String fullString,String finalValue) {
		     //System.out.println("MutiNeeded ran, front is: " + fullString.substring(front,front+1)+" Back is: "+fullString.substring(back,back+1));
			 if (front > 0) {
				 if(isOperator(fullString.charAt(front-1),true)) {
					 
				 }else {
					 finalValue = "*"+finalValue;
				 }
			 
			 }
			if(back < fullString.length()) {
				if(isOperator(fullString.charAt(back),false)) {
					
				}else {
					finalValue = finalValue+"*";
				}
			}
			return finalValue;
		
	 }
	 //Method that is used to check letter values match parts of the string
	 public boolean containsValue(String fullInput, String checkValue, int parPos, int contain) {
		if(fullInput.length()-1 > contain-parPos && parPos >= 0) {
			 if(fullInput.substring(parPos,contain).equals(checkValue)) {
				System.out.println(fullInput.substring(parPos,contain)+"Equals"+ checkValue);
				return true;
			 }else {
				return false;
			 }
			 
		 } else {
			 return false;
		 }
	 }
	 //Method for basic factorial Math
	 public BigDecimal factorialMethod(String value) {
		 BigDecimal e = new BigDecimal(value,mc);
			for(int i = e.intValue()-1; i > 0; i--) {
				 e = e.multiply(BigDecimal.valueOf(i),mc);
			 }
			 return e;
		}
	 public String decimalParser(String value) {
		 BigDecimal input = new BigDecimal(value,mc);
		 if(decimalToFraction(input.divide(new BigDecimal(piCon,mc),mc).doubleValue()).length() < decimalToFraction(input.doubleValue()).length()) {
			 String fraction = decimalToFraction(input.divide(new BigDecimal(piCon,mc),mc).doubleValue());
			 return fraction.substring(0,fraction.indexOf(',')) + "π" + fraction.substring(fraction.indexOf(','));
		 }else {
			 return decimalToFraction(input.doubleValue());
		 }
	 } 
	 public String decimalToFraction(double x){
		    if (x < 0){
		        return "-" + decimalToFraction(-x);
		    }
		    double tolerance = 1.0E-6;
		    double h1=1, h2=0, k1=0, k2=1, b = x;
		    do {
		        double a = Math.floor(b);
		        double aux = h1; 
		        h1 = a*h1+h2; 
		        h2 = aux;
		        aux = k1; 
		        k1 = a*k1+k2; 
		        k2 = aux;
		        b = 1/(b-a);
		    } while (Math.abs(x-h1/k1) > x*tolerance);

		    return (int) h1+",/"+(int)k1;
		}
	 public String inverse(String baseFunc, String inversePart, int variablePos) {
		 for(int i = 0; i < inversePart.length();i++){
			 
		 }
		 return baseFunc;
	 }
	 public int isVar(char entry, int charPos, String fullInput) {
		 if(entry == 'e') {
			 return 1;
		 }else if(entry == 'o'){
			 return 1;
		 }else if (entry == 's') {
			 if(containsValue(fullInput,"sin",charPos,charPos+3)) {
				 return 3;
			 }else if(containsValue(fullInput,"sec",charPos,charPos+3)) {
				 return 3;
			 }
		 }else if (entry == 'c') {
			 if(containsValue(fullInput,"cos",charPos,charPos+3)) {
				 return 3;
			 }else if(containsValue(fullInput,"csc",charPos,charPos+3)) {
				 return 3;
			 }else if(containsValue(fullInput,"cot",charPos,charPos+3)) {
				 return 3;
			 }
		 }else if (entry == 't') {
			 if(containsValue(fullInput,"tan",charPos,charPos+3)) {
				 return 3;
			 }
		 }else if (entry == 'l') {
			 if(containsValue(fullInput,"ln",charPos,charPos+2)) {
				 return 2;
			 }else if(containsValue(fullInput,"log₁₀",charPos,charPos+5)) {
				 return 5;
			 }
		 }
		 return 0;
		 
	 }
	//Checks if a value has an operator 
		 public boolean isOperator(char i, boolean frontBack) {
			 if(frontBack == true) {
				 if (i == '+'|| i == '-'|| i == '/'|| i == '*'|| i == '×'|| i == '÷'|| i == '%'|| i == '+'|| i == '-' || i == '('|| i == '√') {
					 return true;
				 } else {
					 return false;
				 }
			 } else {
				 if (i == '+'|| i == '-'|| i == '/'|| i == '*'|| i == '×'|| i == '÷'|| i == '%'|| i == '+'|| i == '-' || i == ')') {
					 return true;
				 } else {
					 return false;
				 }
			 }
		 }
		 public boolean isNum(String entry) {
			 for(int i = 0; i < entry.length(); i++) {
				 if(i == 0 && entry.charAt(0) == '-') {
					if(i == entry.length()-1) {
						return true;
					} 
				 }else if(((int) entry.charAt(i) > 47 && (int) entry.charAt(i) < 58) || entry.charAt(i) == '.' || i == 0 && entry.charAt(0) == '-'|| (entry.charAt(i) == '+' && entry.charAt(i-1) == 'E') ||(entry.charAt(i+1) == '+' && entry.charAt(i) == 'E')) {
					 if(i == entry.length()-1) {
						 return true;
					 }
				 }else {
					 return false;
				 }
			 }
			 return false;
		 }
}
