package nodes;
import visitors.Visitor;

/**
 * Clasa pentru nodurile de tip Scena ale arborelui. 
 * @author SIMION-CONSTANTINESCU ANDREI
 *
 */


public class SceneNode extends Node {
	
	/**
	 * Constructor cu parametru
	 * @param number numarul Scenei
	 */
	public SceneNode(String number){
		super("Scene",number);
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
	 *  (non-Javadoc)
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
