package visitors;
import nodes.*;

/**
 * <p>
 * Clasa Visitor concret, care parcurge si afiseaza
 * arberele sintactic generat.
 * </p>
 *  <p>
 * Clasa <code>PrintVisitor</code> genereaza Stringul
 * ce trebuie scris in fisierele <strong>.ast</strong>, rezultat
 * in urma vizitarii nodurilor din arbore.
 * </p>
 * @author SIMION-CONSTANTINESCU ANDREI
 *
 */
public class PrintVisitor implements Visitor {

	/*
	 * retine reprezentarea arborelui, cu o identare
	 * tab a nodului copil fata de parinte
	 */
	private StringBuilder tree=new StringBuilder();
	
	/*
	 * Calculeaza identarea nodului primit ca argument,
	 * fata de radacina
	 */
	private int calculateIndent(Node n){
		
		Node current=n;
		int indent=0;
		
		while(!current.type().equals("Program")){
			indent++;
			current=current.getParent();
		}
		
		return indent;		
	}
	
	/*
	 * Functie helper pentru vizitarea unui nod de un anumit
	 * tip. Declanseaza visitarea nodurilor copil, prin apelul 
	 * metodei accept pentru fiecare copil al nodului visitat initial
	 */
	private void helper(Node n, String s){
		int i;
		
		int indent=calculateIndent(n);
		for(i=0;i<indent;i++)
			tree.append("\t");
		
		tree.append(s);
		tree.append(n.getInfo());
		tree.append(System.getProperty("line.separator"));
		
		for(i=0;i<n.getChildren().size();i++)
			n.getChildren().get(i).accept(this);
	}
	
	@Override
	public void visit(Node n) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Functie pentru afisarea unui nod e tip:
	 * Program
	 */
	@Override
	public void visit(ProgramNode n) {
		
		helper(n,"ProgramNode ");
	}

	/**
	 * Functie pentru afisarea unui nod e tip:
	 * Act
	 */
	@Override
	public void visit(ActNode n) {
		
		helper(n,"ActNode ");
	}

	/**
	 * Functie pentru afisarea unui nod e tip:
	 * Scena
	 */
	@Override
	public void visit(SceneNode n) {
		
		helper(n,"SceneNode ");
	}
	
	/**
	 * Functie pentru afisarea unui nod e tip:
	 * Atribuire
	 */
	@Override
	public void visit(AssignmentNode n) {
		
		helper(n,"AssignmentNode");
	}
	
	/**
	 * Functie pentru afisarea unui nod e tip:
	 * Variabila_Stanga
	 */
	@Override
	public void visit(LvalNode n) {
		
		helper(n,"LvalNode ");
	}
	
	/**
	 * Functie pentru afisarea unui nod e tip:
	 * Variabila_Dreapta
	 */
	@Override
	public void visit(RvalNode n) {
		
		helper(n,"RValNode ");
	}

	/**
	 * Functie pentru afisarea unui nod e tip:
	 * Constant
	 */
	@Override
	public void visit(ConstantNode n) {
		
		helper(n,"ConstantNode ");
	}

	/**
	 * Functie pentru afisarea unui nod e tip:
	 * Afisare
	 */
	@Override
	public void visit(OutputNode n) {
		
		helper(n,"OutputNode ");
	}
	
	/**
	 * Functie pentru afisarea unui nod e tip:
	 * Matematic
	 */
	@Override
	public void visit(MathNode n) {
		
		helper(n,n.type()+"Node ");
	}
	
	/**
	 * Metoda care intoarce Stringul care trebuie scris
	 * in fisierul <strong>.ast</strong>
	 * @return Stringul ce defineste reprezentarea arborelui
	 */
	public String getString(){
		return tree.toString();
	}
	
}
