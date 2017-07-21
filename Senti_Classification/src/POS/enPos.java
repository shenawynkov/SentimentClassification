/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POS;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sentiword.SentiWordNetDemoCode;
import translate.com.rmtheis.yandtran.translate.Translate;

/**
 *
 * @author shenawynkov
 */

public class enPos {
     private static  List<String[]> pos=new ArrayList<String[]>();

  private  static int c = 0;

  
    public static void tager(String txt) {
          String ss[]=new String[2];
        try {

            MaxentTagger tagger = new MaxentTagger("taggers/english-bidirectional-distsim.tagger");

            String tagged = tagger.tagString(txt);
            //String taggedn = tagger.tagString("دخل احمد الفصل");
            //System.out.println(taggedn);
            
            for (String retval : tagged.split(" ")) {
                //write("tagged.txt", retval, c);
                int index=retval.indexOf("_")+1;
                String taggedStr;
                String tag=retval.substring(index);
                if(index>0)
                 taggedStr=retval.substring(0, index-1);
                else
                    continue;

          
                if(taggedStr.length()<2)
                    continue;
                
              if(tag.charAt(0)=='J')
                {               
                     c++;

                   
                   pos.add(new String[]{taggedStr ,"a"}); 
                    
                }
              else if(tag.charAt(0)=='V')
              {
                                  
c++;
                   
                    pos.add(new String[]{taggedStr ,"v"});    


              }
                  else if(tag.charAt(0)=='N')
              {
                                
 c++;
                      pos.add(new String[]{taggedStr ,"n"}); 
                    

              }
                   else if(tag.charAt(0)=='R'||tag.charAt(0)=='U')
              {
                                  c++;  
                                  
    pos.add(new String[]{taggedStr ,"r"}); 
                                 
}
              

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    
    public static List tagged()
    {
        return pos;
    }
 public  static void pos(String input)
        {
            pos.clear();
         
                input = input.replaceAll("([\\p{Punct}])", "");
               input = input.replaceAll(",$", "");
                tager(input);
              
                                  System.out.println("--------------------------------------");


  SentiWordNetDemoCode.sentiword(pos);

        
           
        }

}