import java.util.List;
/**
 * Simple hash table implementation of Dictionary using linear probing.
 * 
 * @author Stephan Jamieson 
 * @version 24/4/2015
 */
public class LPHashtable implements Dictionary
{
    private final static int DEFAULT_SIZE = 50;
 
    private Entry[] table;
    private int entries;
 
    public LPHashtable() { this(DEFAULT_SIZE); }
    
    public LPHashtable(int size) { 
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
        for (int i=0; i<table.length; i++){
			int currentIndex = key+i;
			if (currentIndex >= table.length){
				currentIndex -= table.length;
			}      
			if (table[currentIndex] == null){
				return false;
			}
			else if (table[currentIndex].getWord().equals(word)){
				return true;
			}
		}
		return false;
        
    }
    
    
    public List<Definition> getDefinitions(String word) {
        int key = hashFunction(word);
        for (int i=0; i<table.length; i++){
			int currentIndex = key+i;
			if (currentIndex >= table.length){
				currentIndex -= table.length;
			} 
			if (table[currentIndex] == null){		
				return null;
			}
			else if (table[currentIndex].getWord().equals(word)){
				
				return table[currentIndex].getDefinitions();
			}
		}
		return null;
    }
    
    public void insert(String word, Definition definition) { 
		       
        int key = hashFunction(word);

        for (int i=0; i<table.length+1; i++){
			int currentIndex = key+i;
			if (currentIndex >= table.length){
				currentIndex -= table.length;
			}
			if (table[currentIndex] == null){

				table[currentIndex] =new EntryImpl(word);
				table[currentIndex].addDefinition(definition);
				entries++;
				break;
			}
			else if (table[currentIndex].getWord().equals(word)){
				table[currentIndex].addDefinition(definition);
				break;
			}
			
		}

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
    public static void debug_print(LPHashtable hashtable) {
        Entry[] table = hashtable.table;
        for(int i=0; i<table.length; i++) {
            System.out.printf("\n%4d : %s", i, table[i]);
        }
    }
            
}
