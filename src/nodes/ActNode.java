package nodes;
import visitors.Visitor;

/**
 * Clasa pentru nodurile de tip Act ale arborelui. 
 * @author SIMION-CONSTANTINESCU ANDREI
 *
 */

public class ActNode extends Node{

	/**
	 * Constructor cu parametru
	 * @param number numarul Actului
	 */
	public ActNode(String number){
		super("Act",number);
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
