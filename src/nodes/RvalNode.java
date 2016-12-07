package nodes;
import visitors.Visitor;

/**
 * Clasa pentru nodurile de tip Variabila_Dreapta ale arborelui. 
 * @author SIMION-CONSTANTINESCU ANDREI
 *
 */

public class RvalNode extends Node{
	
	/**
	 * Constructor cu parametru
	 * @param name numele variabilei
	 */
	public RvalNode(String name){
		super("Rval",name);
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
