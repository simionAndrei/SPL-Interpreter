package nodes;
import visitors.Visitor;

/**
 * Clasa pentru nodurile de tip Constanta ale arborelui. 
 * @author SIMION-CONSTANTINESCU ANDREI
 *
 */

public class ConstantNode extends Node{
	
	/**
	 * Constructor cu parametru
	 * @param value valoarea constantei
	 */
	public ConstantNode(String value){
		super("Constant",value);
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
