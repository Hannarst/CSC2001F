
import java.util.List;
/**
 * Simple hash table implementation of Dictionary using quadratic probing.
 * 
 * @author Stephan Jamieson 
 * @version 24/4/2015
 */
public class QPHashtable implements Dictionary
{
    private final static int DEFAULT_SIZE = 53;
 
    private Entry[] table;
    private int entries;
 
    public QPHashtable() { this(DEFAULT_SIZE); }
    
    public QPHashtable(int size) { 
        this.table = new Entry[size];
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
        System.out.println(key);
        
        for (int i=0; i<entries; i++){
			if (table[key+i*i] == null){
				return false;
			}
			else if (table[key+i*i].getWord().equals(word)){
				return true;
			}
		}
		return false;
        
    }
    
    
    public List<Definition> getDefinitions(String word) {
        
        int key = hashFunction(word);
        for (int i=0; i<entries; i++){
			if (table[key+i*i] == null){
				
				return null;
			}
			else if (table[key+i*i].getWord().equals(word)){
				
				return table[key+i*i].getDefinitions();
			}
		}
		return null;
    }
    
    public void insert(String word, Definition definition) {        
        int key = hashFunction(word);
        for (int i=0; i<entries+1; i++){
			if (table[key+i*i] == null){
				table[key+i*i] =new EntryImpl(word);
				table[key+i*i].addDefinition(definition);
				break;
			}
			else if (table[key+i*i].getWord().equals(word)){
				table[key+i*i].addDefinition(definition);
				break;
			}
			if (i==entries){
				throw new IllegalStateException();
			}
		}
		entries++;
    }
        
    public boolean isEmpty() { return entries == 0; }
    
    public void empty() { this.table = new Entry[this.table.length]; this.entries=0; }
    
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
    public static void debug_print(QPHashtable hashtable) {
        Entry[] table = hashtable.table;
        for(int i=0; i<table.length; i++) {
            System.out.printf("\n%4d : %s", i, table[i]);
        }
    }
            
}
