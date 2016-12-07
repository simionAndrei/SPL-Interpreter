package abstractSyntaxTree;

/**
 * <p>
 * Clasa care modeleaza personajele, mai exact variabilele, 
 * din Shakespeare Programming Language
 * </p>
 * <p>
 * Un personaj are urmatoarele atribute:
 *  <br>
 *  &nbsp; <strong>nume</strong> nodului; <br>
 *  &nbsp; <strong>valoare</strong>, initial setata la 0; 
 * 
 * @author SIMION-CONSTANTINESCU ANDREI
 *
 */

public class DramaticCharacter {

	private String name;
	private int value;
	/*
	 * variabila flag, care se seteaza in momentul 
	 * in care un personaj incepe sa spuna o replica
	 * si care este dezactivata in momentul in care 
	 * un alt personaj incepe sa spuna o alta replica 
	 * sau la terminarea scenei 
	 */
	private boolean isSpeaking;
	
	/**
	 * Constructor cu parametrii
	 * @param name numele personajului
	 */
	public DramaticCharacter(String name){
		this.name=name;
		value=0;
		isSpeaking=false;
	}
	
	/**
	 * Sterge valoarea personajului
	 */
	public void clearValue(){
		value=0;
	}
	
	/*
	 * Getteri si Setteri pentru campurile private
	 */
	
	public void setValue(int value){
		this.value=value;
	}
	
	
	public String getName(){
		return name;
	}
	
	public int getValue(){
		return value;
	}
	
	public void setIsSpeaking(boolean val){
		isSpeaking=val;
	}
	
	public boolean getIsSpeaking(){
		return isSpeaking;
	}
}
