import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import abstractSyntaxTree.ASTree;
import abstractSyntaxTree.DramaticCharacter;
import abstractSyntaxTree.Stage;
import nodes.*;
import visitors.*;

/**
 * Class <code>Interpreter</code> parses an input file
 * that meets Shakespeare Programming Language format.
 * <p>
 * This class generates an Abstract Syntax Tree which through
 * two visitors:
 *  <br> 
 *  &nbsp; will be displayed in a <strong>.ast</strong> file; <br>
 *  &nbsp; will be interpreted, the output being genereated 
 *  in a <strong>.out</strong> file;
 * @author Simion-Constantinescu Andrei
 *
 */

public class Interpreter {
	
	private ASTree tree;
	private List<DramaticCharacter> characters;
	private String inputFile;
	
	/**
	 * Constructor with parameter
	 * @param input the path to the input file
	 */
	public Interpreter(String inputFile) {
		characters = new ArrayList<DramaticCharacter>();
		tree = new ASTree();
		this.inputFile = inputFile;
	}

	/**
	 * Method for parsing a <strong>.spl>/strong> file and 
	 * generating an abstract syntax tree
	 */
	void parse(){
		
		Parser p = null;
		try {
			p = new Parser("wordlists", inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		Parser.Token t;
		
		Stage stage = new Stage();
		int adjNumber = 0;
			
		/*
		 * Parse and save the characters declared in the .spl file
		 */
		while(true){
			t = p.getNext();
			
			if(t == null)
				break;
				
			if(t.type == TYPE.keyword && t.value.equals("Act"))
					break;
				
			if(t.type == TYPE.character && p.getNext().value.equals(","))
				characters.add(new DramaticCharacter(t.value));		
		}
		
		/*
		 * Set the root node of the AST
		 */
		tree.setRoot(new ProgramNode());
		
		if(t == null)
			return;
		
		/*
		 * Introducing the first Act node in the tree.
		 */
		tree.add(new ActNode(p.getNext().value));
		
		
		/*
		 * Start parsing the whole file
		 */
		while(true){
			t = p.getNext();
				
			if(t == null) 
				break;
					
			switch(t.value){
				/* If we found an Act, we add an ActNode in the tree */
				case "Act":
					t = p.getNext();
					
					if(p.getNext().value.equals(":"))
						tree.add(new ActNode(t.value));
					break;
				
				/* If we found a Scene, we add a SceneNode in the tree */
				case "Scene":
					t = p.getNext();
					
					if(p.getNext().value.equals(":"))
						tree.add(new SceneNode(t.value));
					break;
				
				/*
				 * We store the characters that are on the scene
				 */
				case "Enter":
					
					t = p.getNext();
					while(true){
						
						if(t.value.equals("]"))
							break;
						
						if(t.type!=TYPE.character){
							t = p.getNext();
							continue;
						}
						
						/*
						 * Check if the character is in the characters list
						 * from the beginning 
						*/
						for(DramaticCharacter e : characters){
							if(e.getName().equals(t.value)){
								stage.add(e);
								break;
							}
						}	
						
						t = p.getNext();
					}
					break;
				
				/*
				 * Eliminate only one character from the stage
				 */
				case "Exit":
					
					stage.clearSpeaker();
					
					t = p.getNext();
					
					while(true){
						
						if(t.value.equals("]"))
							break;
						
						if(t.type != TYPE.character){
							t = p.getNext();
							continue;
						}
						
						stage.clear(t.value);
						t = p.getNext();
					}	
					break;
					
				/*
				* Eliminate one or more characters from the stage
				*/
				case "Exeunt":
					
					stage.clearSpeaker();
					t = p.getNext();
					
					if(t.value.equals("]")){
						stage.clearAll();
						break;
					}
					
					while(true){
						
						if(t.value.equals("]"))
							break;
						
						if(t.type != TYPE.character){
							t = p.getNext();
							continue;
						}
						
						stage.clear(t.value);
						t = p.getNext();
					}	
					break;	
				
				/*
				 * Parse characters lines
				 */
				default:
					
					if(t.value.endsWith(":") && t.value.length()>1){
						
						/*
						 * Select the Stage Speaker
						 */
						t.value = t.value.replace(":","");
						if(!stage.isOnStage(t.value))
							break;
						stage.setSpeaker(t.value);
						
						t=p.getNext();
						/*
						 * Process the character line 
						 */
						while(!t.value.equals("[")){
							
								/*
								 * assignment operation
								 */
								if(t.type == TYPE.second_person){
									
									String aux = stage.speakTo();
									tree.add(new AssignmentNode());
									tree.add(new LvalNode(aux));
									t = p.getNext();
									continue;
								}
								
								/*
								 * We found an adjective
								 */
								if(t.type == TYPE.positive_adjective ||
								   t.type == TYPE.negative_adjective ||
								   t.type == TYPE.neutral_adjective ){
									
									adjNumber++;
									t = p.getNext();
									continue;
								}
						
								/*
								 * I discovered a noun positive / neutral: 1
								 */
								if(t.type == TYPE.positive_noun ||
								   t.type == TYPE.neutral_noun){
									
									String aux = Integer.toString(1 << adjNumber);
									tree.add(new ConstantNode(aux));
									adjNumber = 0;
									t = p.getNext();
									continue;
								}
								
								/*
								 * I discovered a negative noun: -1
								 */
								if(t.type == TYPE.negative_noun){
									
									int aux = 1 << adjNumber;
									aux *= -1;
									tree.add(new ConstantNode(Integer.toString(aux)));
									adjNumber = 0;
									t = p.getNext();
									continue;
								}
								
								/*
								 * Add a Mathematic Node
								 */
								if(t.type == TYPE.math){
						
									String aux = t.value;
									aux = Character.toUpperCase(aux.charAt(0)) + aux.substring(1);
									tree.add(new MathNode(aux));
									adjNumber = 0;
									t = p.getNext();
									continue;
								}
							
								/*
								 * Add a Right Variable Node, equal to the value of the character
								 * whom the speaker addresses
								 * 
								 */
								if(t.type == TYPE.second_person_reflexive){
								   
									String aux = stage.speakTo();
									tree.add(new RvalNode(aux));
									adjNumber = 0;
								    t = p.getNext();
									continue;
								}
								
								/*
								 * Add a Right Variable Node, equal to the value of the speaker
								 */
								if(t.type == TYPE.first_person_reflexive){
									
									String aux = stage.getSpeaker();
									tree.add(new RvalNode(aux));
									adjNumber = 0;
									t = p.getNext();
									continue;
								}
								
								/*
								 * Add a Right Variable Node, equal to the specified character
								 */
								if(t.type == TYPE.character){
									tree.add(new RvalNode(t.value));
									adjNumber = 0;
								}
								
								
								/*
								 * Add an Output Node, storing the type of display 
								 */
								if(t.type == TYPE.speak){
									String aux = stage.speakTo();
									tree.add(new OutputNode(aux,t.value));
									adjNumber = 0;
								}
								
								/*
								 * In Case there is more then one line in a scene, we set
								 * another speaker, in order to process his line
								 */
								if(t.value.endsWith(":") && t.value.length() > 1){
									
									t.value = t.value.replace(":","");
										
									if(!stage.isOnStage(t.value)){
										t = p.getNext();
										continue;
									}
				
									stage.clearSpeaker();
									stage.setSpeaker(t.value);
								}
							t = p.getNext();
						}
					}
					
					break;
				}
		}
	}
		
	/**
	 * Method that returns the string to be written in the
	 * <strong>.ast</strong> file
	 * <p>
	 * Here we use the <strong>PrintVisitor</strong>
	 * </p>
	 * @return a string that represents the AST
	 */
	String getAST(){
		
		PrintVisitor v = new PrintVisitor();
		tree.getNodes().get(0).accept(v);

		return v.getString();
	}
	
	
	/**
	 * Method that returns the string to be written in the
	 * <strong>.out</strong> file
	 * <p>
	 *  Here we use the <strong>InterpreterVisitor</strong>
	 * </p>
	 * @return a string that represents the result of the 
	 * AST interpretation
	 */
	String getOutput(){
		
		InterpreterVisitor v = new InterpreterVisitor(characters);
		tree.getNodes().get(0).accept(v);
		
		return v.getOutput();
	}
	
	/**
	 * In main we will generate noTests <strong>.ast</strong> files and 
	 * noTests <strong>.out</strong> files
	 */
	public static void main(String[] args){
		
		Interpreter interpreter;
		String input;
		String output1,output2;
		BufferedWriter writer1 = null;
		BufferedWriter writer2 = null;
		
		int noTests = 10;
		for(int i = 1 ; i <= noTests ;i++){
			 
			input = "tests/test"+ i + ".spl";
			interpreter = new Interpreter(input);
			output1 = "output/test" + i + ".ast";
			output2 = "output/test" + i + ".out";
			
			interpreter.parse();
			
			try{
				writer1=new BufferedWriter(new FileWriter(output1));
				writer2=new BufferedWriter(new FileWriter(output2));
				writer1.write(interpreter.getAST());
				writer2.write(interpreter.getOutput());
				writer1.close();
				writer2.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
