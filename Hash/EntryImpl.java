import java.util.*;

public class EntryImpl implements Entry{
	
	ArrayList<Definition> definitions = new ArrayList<Definition>();
	String word;
	
	
	public EntryImpl(String word){
		this.word = word;
	}
	
	//~ public EntryImpl(String word, List<Definition> definitions){
		//~ this.word=word;
		//~ for (int i=0; i<definitions.size(); i++){
			//~ this.definitions.add(definitions.get(i));
		//~ }
	//~ }
	 
	//~ public EntryImpl(String word, Definition definition){
		//~ this.word = word;
		//~ definitions.add(definition);
	//~ }
	    
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
