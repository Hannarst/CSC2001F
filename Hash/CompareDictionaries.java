import java.util.List;
import java.util.Scanner;
import java.math.*;
import java.io.*;
/**
 * Compare time/memory usage of various Dictionaries
 * 
 * @author Anna Borysova
 * @version 2015-05-04
 */
public class CompareDictionaries {
    
    private CompareDictionaries() {}
    
    public static void main(String[] args) throws java.io.FileNotFoundException, java.io.IOException {
		testLoad(0.5);
		testLoad(0.75);
		testLoad(1);
		
		testSearch(0.5);
		testSearch(0.75);
		testSearch(1);
	
	}
	
	 private static void testLoad(double loadFactor) throws java.io.FileNotFoundException, java.io.IOException {	
		System.out.println("Calculating for load factor " +loadFactor+"...");
		
		String intSize = Integer.toString((int)(3759/loadFactor));
		BigInteger bigSize = new BigInteger(intSize);
		int size = (bigSize.nextProbablePrime()).intValue();
		
		LPHashtable dictionaryLP = new LPHashtable(size);

		QPHashtable dictionaryQP = new QPHashtable(size);
		//3739
		SCHashtable dictionarySC = new SCHashtable(3759);

		FileUtil.load(dictionaryLP, "lexicon.txt");
		FileUtil.load(dictionaryQP, "lexicon.txt");
		FileUtil.load(dictionarySC, "lexicon.txt");
        
        int loadProbesLP = dictionaryLP.getLoadProbes();
        int loadProbesQP = dictionaryQP.getLoadProbes();
        int loadProbesSC = dictionarySC.getLoadProbes();
        
        System.out.println("Number of probes:");
        System.out.println("LP: " + loadProbesLP );
        System.out.println("QP: " + loadProbesQP );
        System.out.println("SC: " + loadProbesSC );
        
        System.out.println("Percentage difference between:");
        System.out.println("QP and LP: " + ((loadProbesQP-loadProbesLP)*100/(double)loadProbesQP) );
        System.out.println("SC and LP: " + ((loadProbesSC-loadProbesLP)*100/(double)loadProbesSC));
        System.out.println("SC and QP: " + ((loadProbesSC-loadProbesQP)*100/(double)loadProbesSC));
        
        
     }
     
     private static void testSearch(double precision) throws java.io.FileNotFoundException, java.io.IOException {
		 
		 
	 }
}

