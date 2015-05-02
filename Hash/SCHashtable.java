
import java.util.List;
/**
 * Simple hash table implementation of Dictionary using quadratic probing.
 * 
 * @author Stephan Jamieson 
 * @version 24/4/2015
 */
public class SCHashtable implements Dictionary
{
    private final static int DEFAULT_SIZE = 53;
 
    private ChainedEntry[] table;
    private int entries;
 
    public SCHashtable() { this(DEFAULT_SIZE); }
    
    public SCHashtable(int size) { 
        this.table = new ChainedEntry[size];
        this.entries = 0;
    }
    

    private int hashFunction(String key) {
		
		int hashVal = 0;
		for( int i = 0; i < key.length( ); i++){
			hashVal = 37 * hashVal + key.charAt( i );
		}
		hashVal %= table.length;
		if( hashVal < 0 ){
			hashVal += table.length;
		}
		return hashVal;
	}	
		
    
    //incorrect search algorithm?
    public boolean containsWord(String word) {
        int key = hashFunction(word);
        if (table[key]!=null){
			ChainedEntry entry = table[key];
			while (entry!=null){
				if (entry.getWord().equals(word)){
					return true;
				}
				entry = entry.getNext();
			}
			return false;
		}
		else{
			return false;
        }
    }
    
    
    public List<Definition> getDefinitions(String word) {
		SCHashtable.debug_print(this);//
        int key = hashFunction(word);
        if (table[key]!=null){
			
			ChainedEntry entry = table[key];
			while (entry!=null){
				if (entry.getWord().equals(word)){
					return entry.getDefinitions();
				}
				entry = entry.getNext();

			}
			return null;
		}
		else{
			return null;
        }		
    }
    
    public void insert(String word, Definition definition) {        
        int key = hashFunction(word);
        ChainedEntry entry = table[key];
		
		if (entry == null){
			entry = new ChainedEntryImpl(word);
			entry.addDefinition(definition);
			table[key]=entry;

		}
		else{
			
			while (entry!=null){
				if (entry.getWord().equals(word)){
					entry.addDefinition(definition);
					entries++;
					return;
				}
				entry = entry.getNext();
			}
			
			ChainedEntryImpl newEntry = new ChainedEntryImpl(word);
			newEntry.addDefinition(definition);
			newEntry.setNext(entry);
			table[key]=newEntry;
		}
		
		entries++;
    }
        
    public boolean isEmpty() { return entries == 0; }
    
    public void empty() { this.table = new ChainedEntry[this.table.length]; this.entries=0; }
    
    public int size() { return this.entries; }
    
    /* Hash Table Functions */
    
    /**
     * Obtain the current load factor (entries / table size).
     */
    public double loadFactor() { return entries/(double)table.length; }
        
    
    /* DEBUGGING CODE */
    /**
     * Print the contents of the given hashtable.
     */
    public static void debug_print(SCHashtable hashtable) {
        ChainedEntry[] table = hashtable.table;
        for(int i=0; i<table.length; i++) {
			ChainedEntry entry = table[i];
			if (entry==null){
				System.out.println(i+ " : " + entry);
			}
			else{
				while(entry!=null){
					System.out.println(i+ " : " + entry);
					entry = entry.getNext();
				}
			}
        }
    }
            
}

