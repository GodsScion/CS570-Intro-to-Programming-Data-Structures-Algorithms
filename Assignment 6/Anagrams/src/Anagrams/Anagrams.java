package Anagrams;

//Sai Vignesh Golla
//CWID: 20008561



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;



public class Anagrams {
	
	final Integer[] primes={2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
	 
	 Map<Character,Integer> letterTable;
	 Map<Long,ArrayList<String>> anagramTable;
	public Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
		
	}
	
	private void buildLetterTable() {
	    letterTable= new HashMap<Character,Integer>();
	    Character[] alphabets= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	   
	    for(int i = 0; i < 26; i++) {
			letterTable.put(alphabets[i], primes[i]);
		}
	
	} 
	
	 private void addWord(String s) {	
		 Long hashVal = this.myHashCode(s);
		 if(anagramTable.get(hashVal) == null) {
			 ArrayList<String> arrayList = new ArrayList<String>();
			 arrayList.add(s);
			 anagramTable.put(hashVal, arrayList);
		} 
		else {
			anagramTable.get(hashVal).add(s);
		}
	}
	
	private Long myHashCode(String s) {	
		long keyVal=1;
		for(int i=0; i<s.length(); i++) {
			keyVal= keyVal*(long)letterTable.get(s.charAt(i));
		}
		return keyVal;
	}
	
	private void processFile(String s) throws IOException {	
		FileInputStream fStream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fStream));
        String strLine;
	 
        while((strLine = br.readLine()) != null) {
			this.addWord(strLine);
		}
		br.close();
	}

	
    private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
		ArrayList<Map.Entry<Long,ArrayList<String>>> arrlists = new ArrayList<>(); 
		int maxVal = 0;
	    
		for (Map.Entry<Long,ArrayList<String>> entries : anagramTable.entrySet()) {
			
		  if(entries.getValue().size() > maxVal) {
				arrlists.clear();
				arrlists.add(entries);
				maxVal = entries.getValue().size();
			} 
		  else if(entries.getValue().size() == maxVal) {
				arrlists.add(entries);
			}
		}
		 return arrlists;
		 
	}
    
    
	
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		Anagrams a = new Anagrams();
		
		final long startTime = System.nanoTime();
		
		try {
			a.processFile ("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries ();
		long key = maxEntries.get(0).getKey();
		int length = maxEntries.get(0).getValue().size();
		final long estimatedTime = System.nanoTime() - startTime ;
		final double seconds = ((double)estimatedTime/1000000000);
		System.out.println("Elapsed Time : "+ seconds);
		System.out.println("Key of max anagrams: "+ key);
		System.out.println("List of max anagrams: " + maxEntries.get(0).getValue());
		System.out.println("Length of list of max anagrams : "+ length);

	}

}
