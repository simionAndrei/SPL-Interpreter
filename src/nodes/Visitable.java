package nodes;
import visitors.Visitor;

/**
 * <p> 
 * Interfata ce va fi implementata de clasa <code>Node</code>. 
 * </p>
 * <p>
 * Interfata pentru obiectele pe care poti fi aplicate diverse
 * operatii, folosind design patternul <strong>Visitor</strong>
 * </p>
 * 
 * @author SIMION-CONSTANTINESCU ANDREI
 *
 */


public interface Visitable {
	
	/**
	 * Metoda independenta de tipul concret al Visitor-ului
	 * @param v un Visitor concret 
	 */
	public void accept(Visitor v);

}
