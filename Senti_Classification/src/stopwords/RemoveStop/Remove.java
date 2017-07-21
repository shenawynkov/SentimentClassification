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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Shenawynkov
 */
public class Remove {
   static List<String> list=new ArrayList<String>();
  public  Remove()
   {
       read("list.txt");
   }
         public   static void read(String fName) {
        String line = null;
        String txt = null;

        try {

            FileReader fileReader = new FileReader(fName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
          
             list.add(new String (line));
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
               for (String retval : str.split("[ \\t]+"))
                   {
                     if(! list.contains(retval))
                     {
                         removed+=retval+" ";
                     }
                   }
             
               return removed;
         }

      
}
