/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stopwords.RemoveStop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shenawynkov
 */
public class RemoveEn {
    
    static List<String> list=new ArrayList<String>();
  public  RemoveEn()
   {
       read("list_en.txt");
   }
         public   static void read(String fName) {
        String line = null;
        String txt = null;

        try {

            FileReader fileReader = new FileReader(fName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
            for (String retval : line.split("[ \t]+"))
                   {
                               list.add(new String (retval));

                   }
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
          public String removeStop(String str)
         {
             String removed="";
               for (String retval : str.split(" "))
                   {
                     if(! list.contains(retval))
                     {
                         removed+=retval+" ";
                     }
                   }
         
               return removed;
         }

      
}
