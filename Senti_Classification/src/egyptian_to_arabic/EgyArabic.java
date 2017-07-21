/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egyptian_to_arabic;

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
public class EgyArabic {
       static List<String[]> list=new ArrayList<String[]>();
    public   EgyArabic()
       {
           read("data_set_arabic_all.txt");
       }

         public   static void read(String fName) {
        String line = null;
        String txt = null;

        try {

            FileReader fileReader = new FileReader(fName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
    
               list.add(line.split("-"));
                  
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
          public String toArabic(String str)
         {
                          boolean b=false;

             boolean bb=false;
             String arabic=str;
               for (String retval : str.split(" "))
                   {
                       for(String []s:list)
                       {
                           if(s[0].equals(retval))
                        
                           { 
                               
                               
                            arabic= arabic.replace(retval, s[1]);
                           }
                           }
                           
                       }
                    
                   
         
               return arabic;
         }
    
}
