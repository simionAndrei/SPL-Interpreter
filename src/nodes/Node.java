package nodes;
import java.util.ArrayList;
import java.util.List;
import visitors.Visitor;

/**
 * <p>
 * Clasa <code>Node</code> reprezinta un nod generic din arborele sintatic.
 * </p> <p>
 * Aceasta retine urmatoarele informatii: 
 * <br>
 *  &nbsp; <strong>tipul</strong> nodului; <br>
 *  &nbsp; <strong>informatia</strong> retinuta in acesta; <br>
 *  &nbsp; <strong>copiii</strong> nodului curent; <br>
 *  &nbsp; o referinta la nodul <strong>parinte</strong> al nodului curent.
 * </p> 
 * 
 * @author SIMION-CONSTANTINESCU ANDREI
 *
 */
public class Node implements Visitable{

	protected String info;
	protected String type;
	protected List<Node> children;
	protected Node parent;
	
	/**
	 * Constructor default care initializeaza lista 
	 * de copii al nodului curent
	 */
	protected Node(){
		children = new ArrayList<Node>();
	}
	
	/**
	 * Constructor cu parametrii
	 * @param type tipul nodului
	 * @param info informatia retinuta in acesta
	 */
	protected Node(String type, String info){
		this();
		this.info=info;
		this.type=type;
	}
	
	/*
	 * folosita doar intern de metoda setChild()
	 */
	private void setParent(Node p){
		parent=p;
	}
	
	/**
	 * Adauga la lista copiilor nodului curent, nodul 
	 * primit ca parametru. Totodata, seteaza nodul curent
	 *  ca parinte pentru nodul primit ca argument 
	 * @param n nodul copil al nodului curent
	 */
	public void setChild(Node n){
		n.setParent(this);
		children.add(n);
	}
	
	/*
	 *  Getter-eri pentru campurile vizibile 
	 *  doar pe lantul mostenirii   
	 */

	
	public Node getParent(){
		return parent;
	}
	
	public List<Node> getChildren(){
		return children;
	}
	
	
	/**
	 * Intoarce informatia din nod
	 */
	public String getInfo(){
		return "empty";
	}
	

	/**
	 * Intoarce tipul nodului
	 */
	public String type(){
		return "Node";
	}
	
	/**
	 * Metoda prin care se accepta un Visitor
	 * @param v clasa care implementeaza interfata Visitor
	 */
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
