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
        for (int i=key; i<table.length; i++){
			if (table[i] == null){
				return false;
			}
			else if (table[i].getWord().equals(word)){
				return true;
			}
		}
		for (int i=0; i<key;i++){
			if (table[i] == null){
				return false;
			}
			else if (table[i].getWord().equals(word)){
				return true;
			}
		}
		return false;
        
    }
    
    
    public List<Definition> getDefinitions(String word) {
       // LPHashtable.debug_print(this);//
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
        System.out.println("inserting: "+ word +" key: " + key);//


        for (int i=0; i<table.length+1; i++){
			int currentIndex = key+i;
			if (currentIndex >= table.length){
				System.out.println("looping");//
				currentIndex -= table.length;
			}
			if (table[currentIndex] == null){
				System.out.println("adding new entry");//
				System.out.println("cuurent index: "+ currentIndex);//
				System.out.println("key: " + key);//
				System.out.println("i: " + i);//
				table[currentIndex] =new EntryImpl(word);
				table[currentIndex].addDefinition(definition);
				entries++;
				break;
			}
			else if (table[currentIndex].getWord().equals(word)){
				System.out.println("adding new definition to old entry");//
				table[currentIndex].addDefinition(definition);
				break;
			}
			System.out.println(currentIndex+" unavailable");//
			
		}
		System.out.println("inserted");//
		//LPHashtable.debug_print(this);

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
