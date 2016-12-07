package nodes;
import visitors.Visitor;

/**
 * Clasa pentru nodurile de tip Afisare ale arborelui. 
 * @author SIMION-CONSTANTINESCU ANDREI
 *
 */

public class OutputNode extends Node{
	
	/*
	 * tipul afisarii la consola 
	 */
	private String outputType;
	
	/**
	 * Constructor cu 2 parametrii
	 * @param name variabila careia tb sa ii afisam valoarea
	 * @param outputType tipul afisarii 
	 */
	public OutputNode(String name, String outputType){
		super("Output",name);
		this.outputType=outputType;
	}
	
	public String getOutputType(){
		return outputType;
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