/*
 * Copyright 2013 Robert Theis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package translate.com.rmtheis.yandtran.translate;

import POS.SPOS;
import POS.enPos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import sentiword.SentiWordNetDemoCode;
import stopwords.RemoveStop.RemoveEn;

import translate.com.rmtheis.yandtran.*;
import translate.com.rmtheis.yandtran.YandexTranslatorAPI;
import translate.com.rmtheis.yandtran.language.Language;

/**
 * Makes calls to the Yandex machine translation web service API
 */
public final class Translate extends YandexTranslatorAPI {
    static boolean b=true;

  private static final String SERVICE_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?";
  private static final String TRANSLATION_LABEL = "text";

  //prevent instantiation
  private Translate(){};

  /**
   * Translates text from a given Language to another given Language using Yandex.
   * 
   * @param text The String to translate.
   * @param from The language code to translate from.
   * @param to The language code to translate to.
   * @return The translated String.
   * @throws Exception on error.
   */
  public static String execute(final String text, final Language from, final Language to) throws Exception {
    validateServiceState(text); 
    final String params = 
        PARAM_API_KEY + URLEncoder.encode(apiKey,ENCODING) 
        + PARAM_LANG_PAIR + URLEncoder.encode(from.toString(),ENCODING) + URLEncoder.encode("-",ENCODING) + URLEncoder.encode(to.toString(),ENCODING) 
        + PARAM_TEXT + URLEncoder.encode(text,ENCODING);
    final URL url = new URL(SERVICE_URL + params);
    return retrievePropArrString(url, TRANSLATION_LABEL).trim();
  }

  private static void validateServiceState(final String text) throws Exception {
    final int byteLength = text.getBytes(ENCODING).length;
    if(byteLength>10240) { // TODO What is the maximum text length allowable for Yandex?
      throw new RuntimeException("TEXT_TOO_LARGE");
    }
    validateServiceState();
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
     public   static void trns_read(String fName) {
        String line = null;
        String txt = null;

        try {

            FileReader fileReader = new FileReader(fName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
              
               
                trns_fun(line);
                

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
    public  static String  trns_fun(String line)
    {            String translation=null;

     
        
         Translate.setKey(ApiKeys.YANDEX_API_KEY);
        try {
             translation = Translate.execute(line, Language.ARABIC, Language.ENGLISH);
        } catch (Exception exception) {
            
        }
        return translation;
   
  
    }
     public  static String  trns_fun_arabic(String line)
    {            String translation=null;

     
        
         Translate.setKey(ApiKeys.YANDEX_API_KEY);
        try {
             translation = Translate.execute(line, Language.ENGLISH, Language.ARABIC);
        } catch (Exception exception) {
        }
        return translation;
   
  
    }
    public static String trans_to_Arabic(String   s )
    {
       
      s=trns_fun_arabic(s);
               
return s;
    }
  

     

public static void trans(String  str )
    {
            String en=trns_fun(str);
                System.out.println("TRANSLATION:     "+ en);

   en= en.replaceAll("[^a-zA-Z ]", "").toLowerCase();
     RemoveEn r=new RemoveEn();
          String ren=r.removeStop(en);
        System.out.println("REMOVED STOP WORDS:     "+ ren);
                    enPos.pos(ren);
      }
  


}
