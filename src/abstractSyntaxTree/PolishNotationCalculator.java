package abstractSyntaxTree;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

/**
 * <p>
 * Clasa pentru evaluarea unei expresii in forma poloneza
 * </p>
 * Expresia de input poate contine orice numar intreg si 
 * urmatorii operatori:
 * <br>
 *  &nbsp; <strong>"+"</strong> operatorul binar plus; <br>
 *  &nbsp; <strong>"-"</strong> operatorul binar minus; <br>
 *  &nbsp; <strong>"*"</strong> operatorul binar inmultire;<br>
 *  &nbsp; <strong>"div"</strong> operatorul binar impartire de intregi
 *  (se retine doar catul);<br>
 *  &nbsp; <strong>"^2"</strong> operatorul unar la patrat; <br>
 *  &nbsp; <strong>"^3"</strong> operatorul unar la cub; 
 * </p> 
 * @author SIMION-CONSTANTINESCU ANDREI
 *
 */

public class PolishNotationCalculator {

	/*
	 * verifica daca Stringul primit defineste un operator
	 */
	private static boolean isOperator(String op){
		return op.equals("+")   || op.equals("-") || op.equals("*") || 
			   op.equals("div") || op.equals("^2")|| op.equals("^3"); 
			 
	}
	
	/*
	 * intoarce un rezultat intreg, in functie de operatorul si
	 * operanzii primiti
	 */
	private static int computeBinaryOperator(int op1,String operator,int op2){
		
		switch(operator){
		
		case "+":
			return op1+op2;
		case "-":
			return op1-op2;
		case "*":
			return op1*op2;
		case "div":
			return op1/op2;
		default:
			return 0;
		}
	}
	
	/*
	 * intoarce un rezultat intreg, in functie de operatorul si
	 * operandul primit
	 */
	private static int computeUnaryOperator(int op,String operator){
		
		switch(operator){
		
		case "^2":
			return op*op;
		case "^3":
			return op*op*op;
		default:
			return 0;
		}
	}
	
	/*
	 * verifica daca Stringul primit defineste un operator unar
	 */
	private static boolean isUnaryOperator(String op){
		return op.equals("^2")|| op.equals("^3"); 
	}
	
	/**
	 * Evalueaza o expresie in forma poloneza
	 * @param input expresia de evaluat
	 * @return un numar intreg, reprezentand rezultatul operatiilor
	 */
	public static int calculate(String input){
		
		if(input.length()==0)
			return 0;
		
		Stack<Integer> stack=new Stack<Integer>();
		List<String> tokens=Arrays.asList(input.split(" "));
		ListIterator<String> reverseIt=tokens.listIterator(tokens.size());
		
		while(reverseIt.hasPrevious()){
			String prev=reverseIt.previous();
			
			if(!isOperator(prev)){
				stack.push(Integer.parseInt(prev));
			}
			else{
				
				if(isUnaryOperator(prev)){
					int op= stack.pop();
					stack.push(computeUnaryOperator(op, prev));
					continue;
				}
				
				int op1,op2;
				op1=stack.pop();
				op2=stack.pop();
				stack.push(computeBinaryOperator(op1, prev , op2));
			}
		}
		
		return stack.pop();
	}
}
