package abstractSyntaxTree;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import nodes.*;

/**
 * 
 */
/**
 * Clasa <code>ASTree</code> modeleaza un arbore sintactic,
 * folosit pentru parsarea si interpretarea unui program scris
 * in Shakespeare Programming Language.
 * @author SIMION-CONSTANTINESCU ANDREI
 *
 */

/**
 * Class <code>AST
 * @author Simion-Constantinescu Andrei
 *
 */
public class ASTree {

	/*
	 * lista in care retinem toate nodurile arborelui
	 */
	private List<Node> nodes;
	
	/**
	 * Constructor default
	 */
	public ASTree(){
		nodes=new ArrayList<Node>();
	}
	
	/**
	 * Getter pentru lista de noduri a arborelui
	 * @return nodurile arborelui
	 */
	public List<Node> getNodes(){
		return nodes;
	}
	
	/*
	 * Metoda pentru setarea radacinii arborelui
	 */
	public void setRoot(ProgramNode n){
		nodes.add(n);
	}
	
	/*
	 * Functie helper pentru adaugare in structura arborelui 
	 * a unui nod de un anumit tip 
	 */
	private void helper(List<String> l, Node n){
		
		/*
		 * ne pozitionam la sfarsitul listei si cautam nodul
		 * parinte al nodului ce trebuie adaugat in lista
		 */
		ListIterator<Node> it=nodes.listIterator(nodes.size());
		
		while(it.hasPrevious()){
			Node prev=it.previous();
			
			if(l.contains(prev.type())){
				
				if(n instanceof ConstantNode ||
				   n instanceof RvalNode) 
					
					if(prev.getChildren().size()==2)
						continue;
				
				prev.setChild(n);
				break;
			}
		}
	}
	
	/**
	 * Metoda de adaugare in structura arborelui a 
	 * unui nod de tip Act
	 * @param n nodul ce trebuie adaugat
	 */
	public void add(ActNode n){
		
		nodes.get(0).setChild(n);
		nodes.add(n);
	}
	
	/**
	 * Metoda de adaugare in structura arborelui a 
	 * unui nod de tip Scena
	 * @param n nodul ce trebuie adaugat
	 */
	public void add(SceneNode n){
		
		helper(Arrays.asList("Act"),n);
		nodes.add(n);
	}
	
	/**
	 * Metoda de adaugare in structura arborelui a 
	 * unui nod de tip Atribuire
	 * @param n nodul ce trebuie adaugat
	 */
	public void add(AssignmentNode n){
		
		helper(Arrays.asList("Scene"),n);
		nodes.add(n);
	}
	
	/**
	 * Metoda de adaugare in structura arborelui a 
	 * unui nod de tip Afisare
	 * @param n nodul ce trebuie adaugat
	 */
	public void add(OutputNode n){
		helper(Arrays.asList("Scene"),n);
		nodes.add(n);
	}
	
	/**
	 * Metoda de adaugare in structura arborelui a 
	 * unui nod de tip Variabila_Stanga
	 * @param n nodul ce trebuie adaugat
	 */
	public void add(LvalNode n){
		
		helper(Arrays.asList("Assignment"),n);
		nodes.add(n);	
	}
	
	/**
	 * Metoda de adaugare in structura arborelui a 
	 * unui nod de tip Variabila_Dreapta
	 * @param n nodul ce trebuie adaugat
	 */
	public void add(RvalNode n){
		
		helper(Arrays.asList("Assignment","Difference","Square","Cube",
				             "Sum","Times","Divided","Product","Quotient"),n);
		nodes.add(n);
	}
	
	/**
	 * Metoda de adaugare in structura arborelui a 
	 * unui nod de tip Constanta
	 * @param n nodul ce trebuie adaugat
	 */
	public void add(ConstantNode n){
		
		helper(Arrays.asList("Assignment","Difference","Square","Cube",
	             			 "Sum","Times","Divided","Product","Quotient"),n);
		nodes.add(n);
	}
	
	/**
	 * Metoda de adaugare in structura arborelui a 
	 * unui nod de tip Matematic
	 * @param n nodul ce trebuie adaugat
	 */
	public void add(MathNode n){
		
		ListIterator<Node> it=nodes.listIterator(nodes.size());
		List<String> unaryOp=Arrays.asList("Square","Cube","Times");
		
		while(it.hasPrevious()){
			
			Node prev=it.previous();
			
			if(prev.type().equals("Assignment") ||
			   prev instanceof MathNode){ 
				
				/*
				 * daca este operator unar, nu poate 
				 * avea mai mult de un copil
				 */
				if(unaryOp.contains(prev.type())){
						
					if (prev.getChildren().size()==1)
						continue;
					
					prev.setChild(n);
					break;
				}

				/*
				 * daca este operator binar, nu poate 
				 * avea mai mult de 2 copii
				 */	
				if(prev.getChildren().size()!=2){
					prev.setChild(n);
					break;
				}
			}
		}
		nodes.add(n);
	}
}
