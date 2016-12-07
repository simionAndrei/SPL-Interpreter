package visitors;
import nodes.ActNode;
import nodes.*;

/**
 * <p>
 * Interfata <code>Visitor</code> va fi implementata de 
 * 2 vizatori concreti:
 * <br>
 *  &nbsp; <strong>InterpreterVisitor</strong> <br>
 *  &nbsp; <strong>PrintVisitor</strong>
 *  </p>
 *  <p>
 * Interfata pentru algoritmi de prelucrare asupra
 * unor obiecte de tip Visitable
 * </p>
 * @author SIMION-CONSTANTINESCU ANDREI
 *
 */

public interface Visitor {

	public void visit(Node n);
	public void visit(ProgramNode n);
	public void visit(ActNode n);
	public void visit(SceneNode n);
	public void visit(AssignmentNode n);
	public void visit(LvalNode n);
	public void visit(ConstantNode n);
	public void visit(OutputNode n);
	public void visit(MathNode n);
	public void visit(RvalNode n);
}
