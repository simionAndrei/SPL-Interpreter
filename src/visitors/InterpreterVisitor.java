package visitors;
import java.util.List;
import abstractSyntaxTree.DramaticCharacter;
import abstractSyntaxTree.PolishNotationCalculator;
import nodes.*;

/**
 * <p>
 * Clasa Visitor concret, care parcurge si interpreteaza 
 * arberele sintactic generat
 * </p>
 * <p>
 * Clasa <code>InterpreterVisitor</code> genereaza Stringul
 * ce trebuie scris in fisierele <strong>.out</strong>, rezultat
 * in urma vizitarii nodurilor din arbore
 * </p>
 * @author SIMION-CONSTANTINESCU ANDREI
 *
 */

public class InterpreterVisitor implements Visitor{
	
	/*
	 * variabila in care retinem expresia matematica de 
	 * evaluat, in forma poloneza
	 */
	private StringBuilder expression;
	
	/*
	 * String in care retinem rezultatul comenzilor de
	 * afisare la consola
	 */
	private StringBuilder output;
	
	private List<DramaticCharacter> characters;
	private String currentLval;
	
	
	/**
	 * Constructor cu parametru
	 * @param characters personajele din piesa care trebuie parsata
	 */
	public InterpreterVisitor(List<DramaticCharacter> characters) {
		this.characters=characters;
		output=new StringBuilder();
		expression=new StringBuilder();
	}

	@Override
	public void visit(Node n) {
		throw new UnsupportedOperationException();
	}

	/**
	 * <p>
	 * Viziteaza nodul root/Program al arborelui
	 * </p>
	 * Declanseaza visitarea nodurilor copil, prin
	 * apelul metodei accept pentru fiecare copil
	 * al nodului visitat initial
	 */
	@Override
	public void visit(ProgramNode n) {
		
		for(int i=0;i<n.getChildren().size();i++)
			n.getChildren().get(i).accept(this);
	}

	/**
	 * <p>
	 * Viziteaza un nod Act al arborelui
	 * </p>
	 * Declanseaza visitarea nodurilor copil, prin
	 * apelul metodei accept pentru fiecare copil
	 * al nodului visitat initial
	 */
	@Override
	public void visit(ActNode n) {
		
		for(int i=0;i<n.getChildren().size();i++)
			n.getChildren().get(i).accept(this);
	}

	/**
	 * <p>
	 * Viziteaza un nod Scena al arborelui
	 * </p>
	 * Declanseaza visitarea nodurilor copil, prin
	 * apelul metodei accept pentru fiecare copil
	 * al nodului visitat initial
	 */
	@Override
	public void visit(SceneNode n) {
		
		for(int i=0;i<n.getChildren().size();i++)
			n.getChildren().get(i).accept(this);
	}

	/**
	 * <p>
	 * Viziteaza un nod de Atribuire al arborelui
	 * </p>
	 * <p>
	 * Se evalueaza expresia retinuta in forma poloneza.
	 * Este atribuita Variabilei_Stanga curenta, rezultatul expresiei
	 * </p>
	 * Declanseaza visitarea nodurilor copil, prin
	 * apelul metodei accept pentru fiecare copil
	 * al nodului visitat initial
	 */
	@Override
	public void visit(AssignmentNode n) {
		
		
		int value=PolishNotationCalculator.calculate(expression.toString());
		
		for(DramaticCharacter c:characters){
			if(c.getName().equals(currentLval)){
				c.setValue(value);
				break;
			}
		}
		
		expression = new StringBuilder();
		
		for(int i=0;i<n.getChildren().size();i++)
			n.getChildren().get(i).accept(this);
	}

	/**
	 * <p>
	 * Viziteaza un nod Variabila_Stanga al arborelui
	 * </p>
	 * Deoarece acesta este un nod frunza, nu mai este necesar apelul
	 * metodei accept pentru copiii nodului vizitat
	 */
	@Override
	public void visit(LvalNode n) {
		
		currentLval=n.getInfo();
	}

	/**
	 * <p>
	 * Viziteaza un nod Constanta al arborelui
	 * </p>
	 * Deoarece acesta este un nod frunza, nu mai este necesar apelul
	 * metodei accept pentru copiii nodului vizitat
	 */
	@Override
	public void visit(ConstantNode n) {
		
		expression.append(n.getInfo());
		expression.append(" ");
	}

	/**
	 * <p>
	 * Viziteaza un nod de Afisare al arborelui
	 * </p>
	 * <p>
	 * Se evalueaza expresia retinuta in forma poloneza.
	 * Este atribuita Variabilei_Stanga curenta, rezultatul expresiei
	 * </p>
	 * <p>
	 * In functie de tipul afisarii la consola, va scrie un String in 
	 * output
	 * </p>
	 * Deoarece acesta este un nod frunza, nu mai este necesar apelul
	 * metodei accept pentru copiii nodului vizitat
	 */
	@Override
	public void visit(OutputNode n) {
		
		int value=PolishNotationCalculator.calculate(expression.toString());
		
		for(DramaticCharacter c:characters){
			if(c.getName().equals(currentLval)){
				c.setValue(value);
				break;
			}
		}
		
		for(DramaticCharacter c:characters){
			
			if(c.getName().equals(n.getInfo())){
				
				if(n.getOutputType().equalsIgnoreCase("Open"))
					output.append(c.getValue());
				else
					output.append((char)(c.getValue()));
				
				output.append(System.getProperty("line.separator"));
				break;
			}
		}
		
	}

	/**
	 * <p>
	 * Viziteaza un nod Matematic al arborelui
	 * </p>
	 * <p>
	 * Se adauga un String la expresia in forma poloneza
	 *  </p>
	 * Declanseaza visitarea nodurilor copil, prin
	 * apelul metodei accept pentru fiecare copil
	 * al nodului visitat initial
	 */
	@Override
	public void visit(MathNode n) {
		
		switch(n.type()){
		
		case "Sum":
			expression.append("+");
			expression.append(" ");
			break;
		
		case "Difference":
			expression.append("-");
			expression.append(" ");
			break;
			
		case "Product":
			expression.append("*");
			expression.append(" ");
			break;
			
		case "Square":
			expression.append("^2");
			expression.append(" ");
			break;
			
		case "Cube":
			expression.append("^3");
			expression.append(" ");
			break;
			
		case "Quotient":
			expression.append("div");
			expression.append(" ");
			break;
		}
		
		for(int i=0;i<n.getChildren().size();i++)
			n.getChildren().get(i).accept(this);
	}

	/**
	 * <p>
	 * Viziteaza un nod Variabila_Dreapta al arborelui
	 * </p>
	 * <p>
	 * Se adauga un String la expresia in forma poloneza, 
	 * reprezentand valoarea Variabilei_Dreapta
	 * </p>
	 * Deoarece acesta este un nod frunza, nu mai este necesar apelul
	 * metodei accept pentru copiii nodului vizitat
	 */
	@Override
	public void visit(RvalNode n) {
		
		for(DramaticCharacter c:characters){
		
			if(n.getInfo().equals(c.getName())){
				expression.append(c.getValue());
				expression.append(" ");
				break;
			}
		}
	}
	
	/**
	 * Metoda care intoarce Stringul care trebuie scris
	 * in fisierul <strong>.out</strong>
	 * @return Stringul de output
	 */
	public String getOutput(){
		return output.toString();
	}
}
