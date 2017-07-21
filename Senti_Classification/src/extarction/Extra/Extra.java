/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extarction.Extra;

import POS.SPOS;
import POS.enPos;
import egyptian_to_arabic.EgyArabic;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import stopwords.RemoveStop.Remove;
import stopwords.RemoveStop.RemoveEn;
import translate.com.rmtheis.yandtran.translate.Translate;

/**
 *
 * @author Shenawynkov
 */
public class Extra {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mohamed
 */


    /**
     * @param args the command line arguments
     */
    public  static void extract() {
        // TODO code application logic here
         org.jsoup.nodes.Document doc = null;
        try {
            doc = Jsoup.connect("https://egypt.souq.com/eg-ar/%D8%A7%D8%A8%D9%84-%D8%A7%D9%8A%D9%81%D9%88%D9%86-7-%D9%85%D8%B9-%D9%81%D9%8A%D8%B3-%D8%AA%D8%A7%D9%8A%D9%85-128-%D8%AC%D9%8A%D8%AC%D8%A7-%D8%A7%D9%84%D8%AC%D9%8A%D9%84-%D8%A7%D9%84%D8%B1%D8%A7%D8%A8%D8%B9-%D8%A7%D9%84-%D8%AA%D9%8A-%D8%A7%D9%8A-%D9%81%D8%B6%D9%8A-11526727/i/https://egypt.souq.com/eg-ar/%D8%A7%D8%A8%D9%84-%D8%A7%D9%8A%D9%81%D9%88%D9%86-6s-%D8%A8%D9%84%D8%B3-%D9%85%D8%B9-%D9%81%D9%8A%D8%B3-%D8%AA%D8%A7%D9%8A%D9%85-64-%D8%AC%D9%8A%D8%AC%D8%A7-%D8%A7%D9%84%D8%AC%D9%8A%D9%84-%D8%A7%D9%84%D8%B1%D8%A7%D8%A8%D8%B9-lte-%D8%B1%D9%85%D8%A7%D8%AF%D9%8A-9000755/i/https://egypt.souq.com/eg-ar/%D8%A7%D8%A8%D9%84-%D8%A7%D9%8A%D9%81%D9%88%D9%86-6s-%D9%85%D8%B9-%D9%81%D9%8A%D8%B3-%D8%AA%D8%A7%D9%8A%D9%85-64-%D8%AC%D9%8A%D8%AC%D8%A7-%D8%A7%D9%84%D8%AC%D9%8A%D9%84-%D8%A7%D9%84%D8%B1%D8%A7%D8%A8%D8%B9-lte-%D9%88%D8%B1%D8%AF%D9%8A-9044760/i/https://egypt.souq.com/eg-ar/%D8%A7%D8%A8%D9%84-%D8%A7%D9%8A%D9%81%D9%88%D9%86-5s-%D9%85%D8%B9-%D9%81%D9%8A%D8%B3-%D8%AA%D8%A7%D9%8A%D9%85-%D8%B3%D8%B9%D8%A9-16-%D8%AC%D9%8A%D8%AC%D8%A7-%D8%A7%D9%84%D8%AC%D9%8A%D9%84-%D8%A7%D9%84%D8%B1%D8%A7%D8%A8%D8%B9-%D8%A7%D9%84-%D8%AA%D9%8A-%D8%A7%D9%8A-%D8%B0%D9%87%D8%A8%D9%8A-5754385/i/https://egypt.souq.com/eg-ar/%D8%A7%D8%A8%D9%84-%D8%A7%D9%8A%D9%81%D9%88%D9%86-5s-%D9%85%D8%B9-%D9%81%D9%8A%D8%B3-%D8%AA%D8%A7%D9%8A%D9%85-%D8%B3%D8%B9%D8%A9-16-%D8%AC%D9%8A%D8%AC%D8%A7-%D8%A7%D9%84%D8%AC%D9%8A%D9%84-%D8%A7%D9%84%D8%B1%D8%A7%D8%A8%D8%B9-%D8%A7%D9%84-%D8%AA%D9%8A-%D8%A7%D9%8A-%D8%B0%D9%87%D8%A8%D9%8A-5754385/i/").get();
        } catch (IOException ex) {
            Logger.getLogger(Extra.class.getName()).log(Level.SEVERE, null, ex);
        }
        org.jsoup.select.Elements tag = doc.select("h5");
       
            org.jsoup.select.Elements intag =tag.select("div"); 
            String price=intag.text();
            String [] prices=price.split(" ");
            price=prices[0]+" "+prices[1];
            System.out.println(price);
            
            
             org.jsoup.select.Elements title = doc.select("article");
             if(title.size() <2)
               System.out.println("no reviews");
        for(int i=1;i<title.size();i++)
        {
            Elements rev=title.get(i).select("p");
          Element rev2=rev.first();
            String rev_text=rev2.text(); 
       
          Pattern pattern = Pattern.compile("[\\u0600-\\u06FF]");
Matcher matcher = pattern.matcher(rev_text);
if (!matcher.find()) {
             System.out.println(rev_text);
                rev_text= rev_text.replaceAll("[^a-zA-Z ]", "").toLowerCase();


 RemoveEn r=new RemoveEn();
          rev_text=r.removeStop(rev_text);
                        System.out.println("REMOVED STOP WORDS:     "+ rev_text);

enPos.pos(rev_text);
}else
{
          EgyArabic ea=new EgyArabic();
  
   
         System.out.println(rev_text);
                 String arabic= ea.toArabic(rev_text);


    
                 
         Remove r=new Remove();
          arabic=r.removeStop(arabic);
                                   System.out.println("REMOVED Arabic STOP WORDS: "+arabic);

        
        Translate.trans(arabic);

}        }

            
            

            
       
    }
    
}


