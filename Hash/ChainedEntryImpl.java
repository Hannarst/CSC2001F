import java.util.*;

public class ChainedEntryImpl implements ChainedEntry{
	
	ArrayList<Definition> definitions = new ArrayList<Definition>();
	String word;
	ChainedEntry next;
	
	
	public ChainedEntryImpl(String word){
		this(word, null);
	}
	
	public ChainedEntryImpl(String word, ChainedEntryImpl next){
		this.word = word;
		this.next = next;
	}
	    
    /**
     * Obtain the word defined in this entry.
     */
    public String getWord(){
		return word;
		
	}
    
    /**
     * Obtain the definitions for the word defined in this entry.
     */
    public List<Definition> getDefinitions(){
		ArrayList<Definition> newDef = new ArrayList<Definition>();
		for (int i=0; i<definitions.size(); i++){
			newDef.add(definitions.get(i));
		}
		//copy deeper?
		return newDef;
	}
    
    /**
     * Add a definition consisting of the given word type and word description.
     */
    public void addDefinition(WordType wordType, String description){
		//deep copy?
		Definition definition = new Definition(wordType, description);
		definitions.add(definition);
	}
	
    /**
     * Add the given definition.
     */
    public void addDefinition(Definition definition){
		//deep copy?
		if (definition!=null){
			definitions.add(definition);
		}
	}
	
	public ChainedEntry getNext(){
		return next;
	}
	
	public void setNext(ChainedEntry next){
		this.next = next;
	}
    
    /**
     * Determine whether this entry is for the given word.
     */
    public boolean isEntryFor(String word){
		return this.word==word;
	}
	
	public String toString(){
		return word + ": " +definitions;
	}
}

