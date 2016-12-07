package abstractSyntaxTree;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Clasa <code>Stage</code> modeleaza scenele din
 * Shakespeare Programming Language. </p>
 * <p>
 * Contine o lista cu toate personajele care se afla
 * la un moment dat pe scena, acestea fiind singurele 
 * care pot participa la dialogul din acea scena
 * </p>
 * 
 * @author SIMION-CONSTANTINESCU ANDREI
 *
 */

public class Stage {

	/*
	 * lista in care retinem personajele 
	 * din cadrul unei scene
	 */
	private List<DramaticCharacter> l;
	
	/**
	 * Constructor default
	 */
	public Stage(){
		l=new ArrayList<DramaticCharacter>();
	}
	
	/**
	 * Adauga un personaj pe Scena
	 * @param c personajul ce trebuie adaugat
	 */
	public void add(DramaticCharacter c){
		l.add(c);
	}
	
	/**
	 * Scoate personajul de pe scena.
	 * @param name numele personajului care iese de pe Scena
	 */
	public void clear(String name){
		
		for(DramaticCharacter e:l){
			if(e.getName().equals(name)){
				l.remove(e);
				break;
			}
		}
	}
	
	/**
	 * Metoda care scoate toate personajele de pe Scena.
	 */
	public void clearAll(){
		l.clear();
	}
	
	/**
	 * Metoda care cauta un personaj, dupa nume, pe Scena
	 * @param name numele personajului cautat
	 * @return true daca personajul este pe Scena, false altfel
	 */
	public boolean isOnStage(String name){
		for(DramaticCharacter e:l){
			if(e.getName().equals(name))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Seteaza un personaj de pe Scena ca vorbitor curent
	 * @param name numele acestuia
	 */
	public void setSpeaker(String name){
		for(DramaticCharacter e:l)
			if(e.getName().equals(name))
				e.setIsSpeaking(true);
	}
	
	/**
	 * Dezactiveaza vorbitorul din lista de personaje
	 * de pe Scena
	 */
	public void clearSpeaker(){
		for(DramaticCharacter e:l)
			e.setIsSpeaking(false);
	}
	
	/**
	 * Metoda care cauta si intoarce vorbitorul curent
	 * @return personajul-vorbitor curent
	 */
	public String getSpeaker(){
		for(DramaticCharacter e:l){
			if(e.getIsSpeaking())
				return e.getName();
		}
		return null;
	}
	
	/**
	 * Metoda care cauta si intoarce personajul caruia ii sunt
	 * adresate replicile vorbitorului curent
	 * @return numele personajului caruia i se adreseaza vorbitorul
	 */
	public String speakTo(){
		for(DramaticCharacter e:l){
			if(!e.getIsSpeaking())
				return e.getName();
		}
		return null;
	}
}
