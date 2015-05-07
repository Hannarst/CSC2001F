import java.util.*;
import java.lang.*;
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
		
		for (double loadFactor = 0.5; loadFactor<=1; loadFactor+=0.25){
			
			System.out.println("\n**********************************");
			System.out.println("Calculating for load factor " +loadFactor+"...");
			System.out.println("**********************************\n");

			
			
			Scanner s = new Scanner(new File("lexicon.txt"));
			String[] fullFile = new String[3759];
			int i=0;
			while(s.hasNext()&&i<3759){
				String[] entry = s.nextLine().split(" :");
				fullFile[i] = entry[1].trim();
				i++;
			}

			System.out.println("***Loading Dictionaries***");
			System.out.println();
			//load dictionaries
			String intSize = Integer.toString((int)(3759/loadFactor));
			BigInteger bigSize = new BigInteger(intSize);
			int size = (bigSize.nextProbablePrime()).intValue();
			
			LPHashtable dictionaryLP = new LPHashtable(size);

			QPHashtable dictionaryQP = new QPHashtable(size);
			//3739
			SCHashtable dictionarySC = new SCHashtable(size);

			FileUtil.load(dictionaryLP, "lexicon.txt");
			FileUtil.load(dictionaryQP, "lexicon.txt");
			FileUtil.load(dictionarySC, "lexicon.txt");
			
			//print out loading data
			int loadProbesLP = dictionaryLP.getLoadProbes();
			int loadProbesQP = dictionaryQP.getLoadProbes();
			int loadProbesSC = dictionarySC.getLoadProbes();
			
			System.out.println("*Number of load probes:");
			System.out.println(" LP: " + loadProbesLP );
			System.out.println(" QP: " + loadProbesQP );
			System.out.println(" SC: " + loadProbesSC );
			
			System.out.println("*Percentage difference between loading probes:");
			System.out.println(" QP and LP: " + ((loadProbesQP-loadProbesLP)*100/(double)loadProbesQP) );
			System.out.println(" SC and LP: " + ((loadProbesSC-loadProbesLP)*100/(double)loadProbesSC));
			System.out.println(" SC and QP: " + ((loadProbesSC-loadProbesQP)*100/(double)loadProbesSC));
			
			
			int minLoad = Math.min(Math.min(loadProbesLP,loadProbesQP),Math.min(loadProbesLP,loadProbesSC));
			if(minLoad == loadProbesLP){
				System.out.println("\n***LP wins load!***\n");
			}
			else if (minLoad == loadProbesQP){
				System.out.println("\n***QP wins load!***\n");
			}
			else{System.out.println("\n***SC wins load!***\n");}
			
			//create j=20 random tests
			System.out.println("***************************************");
			System.out.println("***Creating 1000 random search tests***\n");
			
			int j;
			for (j = 0; j<1000; j++){
				//create random test array consisting of 100 words (20 made up words)
				ArrayList<String> testArray = new ArrayList(100);
				testArray = randomTestArray(fullFile);
				//search dictionaries for words in the array
				for (int k = 0; k<100; k++){
					dictionaryLP.getDefinitions(testArray.get(k));
					dictionaryQP.getDefinitions(testArray.get(k));
					dictionarySC.getDefinitions(testArray.get(k));
					//internal search probe counter gets incremented
				}
			}
			
			int searchProbesLP = dictionaryLP.getSearchProbes()/j;
			int searchProbesQP = dictionaryQP.getSearchProbes()/j;
			int searchProbesSC = dictionarySC.getSearchProbes()/j;
			
			System.out.println("*Average number of search probes:");
			System.out.println(" LP: " + searchProbesLP );
			System.out.println(" QP: " + searchProbesQP );
			System.out.println(" SC: " + searchProbesSC );
			
			System.out.println("*Percentage difference between:");
			System.out.println(" QP and LP: " + ((searchProbesQP-searchProbesLP)*100/(double)searchProbesQP) );
			System.out.println(" SC and LP: " + ((searchProbesSC-searchProbesLP)*100/(double)searchProbesSC));
			System.out.println(" SC and QP: " + ((searchProbesSC-searchProbesQP)*100/(double)searchProbesSC));
			
			
			int minSearch = Math.min(Math.min(searchProbesLP,searchProbesQP),Math.min(searchProbesLP,searchProbesSC));
			if(minSearch == searchProbesLP){
				System.out.println("\n***LP wins search!***\n");
			}
			else if (minSearch == searchProbesQP){
				System.out.println("\n***QP wins search!***\n");
			}
			else{System.out.println("\n***SC wins search!***\n");}
			
			
			
			
		}	
	}
	public static ArrayList<String> randomTestArray(String[] fullFile){
		Random rand = new Random();
		ArrayList<String> searchList = new ArrayList(100);
		for (int j = 0; j<80; j++){
			int index = rand.nextInt(3759);
			if (searchList.contains(fullFile[index])){
				j--;
			}
			else{
				searchList.add(fullFile[index]);
			}
		}
		
		for (int j = 0; j<20; j++){
			String text = "";
			Random r = new Random();
			for (int k = 0; k< 6; k++){
				text += (char)(r.nextInt(26) + 'a');
			}
			searchList.add(text);
			
		}
		return searchList;
	}
        
}

