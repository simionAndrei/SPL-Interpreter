package nodes;
import visitors.Visitor;

/**
 * Clasa pentru nodurile de tip Program ale arborelui. 
 * @author SIMION-CONSTANTINESCU ANDREI
 *
 */


public class ProgramNode extends Node{
	
	/**
	 * Constructor default, care defineste radacina arborelui
	 */
	public ProgramNode(){
		super("root","empty");
	}
	
	/*
	 * (non-Javadoc)
	 * @see nodes.Node#getInfo()
	 */
	@Override
	public String getInfo(){
		return "";
	}
	
	/*
	 * (non-Javadoc)
	 * @see nodes.Node#type()
	 */
	@Override
	public String type(){
		return "Program";
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
