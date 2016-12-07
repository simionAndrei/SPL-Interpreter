package nodes;
import visitors.Visitor;

/**
 * Clasa pentru nodurile de tip Variabila_Stanga ale arborelui. 
 * @author SIMION-CONSTANTINESCU ANDREI
 *
 */

public class LvalNode extends Node{
	
	/**
	 * Constructor cu parametru
	 * @param name numele variabilei caruia trebuie 
	 * sa ii atribuim o valoare
	 */
	public LvalNode(String name){
		super("Lval",name);
	}

	/*
	 * (non-Javadoc)
	 * @see nodes.Node#getInfo()
	 */
	@Override
	public String getInfo(){
		return info;
	}
	
	/*
	 * (non-Javadoc)
	 * @see nodes.Node#type()
	 */
	@Override
	public String type(){
		return type;
	}
	
	/*
	 * (non-Javadoc)
	 * @see nodes.Node#accept(visitors.Visitor)
	 */
	@Override
	public void accept(Visitor v){
		v.visit(this);
	}
}
