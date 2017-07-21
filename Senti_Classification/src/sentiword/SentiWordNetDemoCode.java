    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentiword;

import POS.SPOS;
import edu.stanford.nlp.util.Pair;
/**
 *
 * @author shenawynkov
 */import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static translate.com.rmtheis.yandtran.translate.Translate.trns_fun;

public class SentiWordNetDemoCode {
static double allavg=0;
static double allavgcount=0;
 static boolean  b=true;
static double total=0;
 static double count =0;
static Map<String,Integer> map = new HashMap<String,Integer>() {{
    put("n",1 );
    put("v",2 );
    put("a",3 );
    put("r",4 );
}};
static List<pair<Double,String>> pairList = new ArrayList<pair<Double,String>>();
	private Map<String, Double> dictionary;

	public SentiWordNetDemoCode(String pathToSWN) throws IOException {
		// This is our main dictionary representation
		dictionary = new HashMap<String, Double>();

		// From String to list of doubles.
		HashMap<String, HashMap<Integer, Double>> tempDictionary = new HashMap<String, HashMap<Integer, Double>>();

		BufferedReader csv = null;
		try {
			csv = new BufferedReader(new FileReader(pathToSWN));
			int lineNumber = 0;

			String line;
			while ((line = csv.readLine()) != null) {
				lineNumber++;

				// If it's a comment, skip this line.
				if (!line.trim().startsWith("#")) {
					// We use tab separation
					String[] data = line.split("\t");
					String wordTypeMarker = data[0];

					// Example line:
					// POS ID PosS NegS SynsetTerm#sensenumber Desc
					// a 00009618 0.5 0.25 spartan#4 austere#3 ascetical#2
					// ascetic#2 practicing great self-denial;...etc

					// Is it a valid line? Otherwise, through exception.
					if (data.length != 6) {
						throw new IllegalArgumentException(
								"Incorrect tabulation format in file, line: "
										+ lineNumber);
					}

					// Calculate synset score as score = PosS - NegS
					Double synsetScore = Double.parseDouble(data[2])
							- Double.parseDouble(data[3]);

					// Get all Synset terms
					String[] synTermsSplit = data[4].split(" ");

					// Go through all terms of current synset.
					for (String synTermSplit : synTermsSplit) {
						// Get synterm and synterm rank
						String[] synTermAndRank = synTermSplit.split("#");
						String synTerm = synTermAndRank[0] + "#"
								+ wordTypeMarker;

						int synTermRank = Integer.parseInt(synTermAndRank[1]);
						// What we get here is a map of the type:
						// term -> {score of synset#1, score of synset#2...}

						// Add map to term if it doesn't have one
						if (!tempDictionary.containsKey(synTerm)) {
							tempDictionary.put(synTerm,
									new HashMap<Integer, Double>());
						}

						// Add synset link to synterm
						tempDictionary.get(synTerm).put(synTermRank,
								synsetScore);
					}
				}
			}

			// Go through all the terms.
			for (Map.Entry<String, HashMap<Integer, Double>> entry : tempDictionary
					.entrySet()) {
				String word = entry.getKey();
				Map<Integer, Double> synSetScoreMap = entry.getValue();

				// Calculate weighted average. Weigh the synsets according to
				// their rank.
				// Score= 1/2*first + 1/3*second + 1/4*third ..... etc.
				// Sum = 1/1 + 1/2 + 1/3 ...
				double score = 0.0;
				double sum = 0.0;
				for (Map.Entry<Integer, Double> setScore : synSetScoreMap
						.entrySet()) {
					score += setScore.getValue() / (double) setScore.getKey();
					sum += 1.0 / (double) setScore.getKey();
				}
				score /= sum;

				dictionary.put(word, score);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (csv != null) {
				csv.close();
			}
		}
	}

	public double extract(String word, String pos) {
            
            
           if(dictionary.get(word + "#" + pos)==null||dictionary.get(word + "#" + pos)==0)
                pos="a";
           
                  if(dictionary.get(word + "#" + pos)==null||dictionary.get(word + "#" + pos)==0)
                  {
                      pos="r";
                  }
                  if(dictionary.get(word + "#" + pos)==null||dictionary.get(word + "#" + pos)==0)
                  {
                      pos="v";
                       
                              
                  }
                      
                  if(dictionary.get(word + "#" + pos)==null||dictionary.get(word + "#" + pos)==0)
                  {
                      pos="n";
                        if(dictionary.get(word + "#" + pos)==null||dictionary.get(word + "#" + pos)==0)
                            return 0;
                              
                  }
            double g=dictionary.get(word + "#" + pos);
        if(!pairList.contains(new pair(g,word)))
          pairList.add(new pair(g,word));
       //System.out.println(word+"   "+g+"  "+pos);

         total+= g*map.get(pos);          if(g!=0){

           count++;
	}
         

              	return g ;
   }
	
           public static void write(String fileName, String txt, int f) {
        try {
            BufferedWriter bufferedWriter;
            FileWriter fileWriter;
            if (f == 1) {
                fileWriter = new FileWriter(fileName);
            } else {
                fileWriter = new FileWriter(fileName, true);
            }

            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(txt);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");

        }
    }
     public   static void senti_read(String fName) {
        String line = null;
        String txt = null;

        try {

            FileReader fileReader = new FileReader(fName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
          

            }
            
           
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '"
                    + fName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                    + fName + "'");
        }
    }
 public  static void senti_fun(String words[])
 {
    
      String pathToSWN = "SentiWordNet.txt";
     SentiWordNetDemoCode sentiwordnet = null;
     try {
         sentiwordnet = new SentiWordNetDemoCode(pathToSWN);
     } catch (IOException iOException) {
     }
    
      double g=0;
                   for (String retval : words[0].split(" "))
                   {
                       g+=sentiwordnet.extract(retval.toLowerCase(), words[1]);
                   }
                        
  
 }
 
 public static List getpairlist ()
 {
     return pairList;
 }
 public static Double getRate()
 {
     return allavg/allavgcount;
 }
 
 
 
 public static void sentiword(List pos)
         
 {
     allavgcount++;
      total=0;
      count=0;
     double averageScore;
              List <String[]>list=new ArrayList<String[]>();
 list=pos;
        
        for(String s[]:list)
        {
         senti_fun(s); 
       
         
        }
        if(count==0)
        {
            averageScore = 0.2;
            
        }
        else
        {
    averageScore = total/count;
        }

if(averageScore>=0.75)
{ System.out.println("very positive"); 
if(averageScore>1)
    averageScore=1;
  allavg+=averageScore;

}
else if(averageScore > 0.25 && averageScore<0.5)
{ System.out.println(" positive"); 
  allavg+=averageScore;



}
else if(averageScore>=0.5){
    

         System.out.println(" positive +"); 
  allavg+=averageScore;

}
else if(averageScore < 0 && averageScore>=-0.25)
{ System.out.println(" negative");
  allavg+=averageScore;

}

else if(averageScore < -0.25 && averageScore>=-0.5)
{ System.out.println(" negative -"); 
  allavg+=averageScore;
}
        else if(averageScore<=-0.75)
        {  System.out.println(" very negative"); 
        if(averageScore<-1)
    averageScore=-1;
  allavg+=averageScore;

        }
        else 
        { System.out.println(" neutral"); 
  allavg+=averageScore;
        }



    
    
           System.out.println(" -------------------------------------"); 

     
 }
    
}