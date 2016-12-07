package nodes;
import visitors.Visitor;

/**
 * <p>
 * Clasa pentru nodurile de tip matematic, precum:
 * <br>
 *  &nbsp; <strong>SumNode</strong>; <br>
 *  &nbsp; <strong>DifferenceNode</strong>; <br>
 *  &nbsp; <strong>ProductNode</strong>; <br>
 *  &nbsp; <strong>QuotientNode</strong>; <br>
 *  &nbsp; <strong>SquareNode</strong>; <br>
 *  &nbsp; <strong>CubeNode</strong>; 
 * </p> 
 * 
 * @author SIMION-CONSTANTINESCU ANDREI
 *
 */

public class MathNode extends Node{
	
	/**
	 * Constructor cu parametru
	 * @param mathType tipul nodului matematic
	 */
	public MathNode(String mathType){
		super(mathType,"");
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