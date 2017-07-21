/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senti_classification;

import POS.SPOS;
import POS.enPos;
import extarction.Extra.Extra;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import sentiword.SentiWordNetDemoCode;
import sentiword.pair;
import translate.com.rmtheis.yandtran.ApiKeys;
import translate.com.rmtheis.yandtran.language.Language;
import translate.com.rmtheis.yandtran.translate.Translate;

/**
 *
 * @author shenawynkov
 */
public class Senti_Classification {
    static List<pair<Double,String>> pairList;

    /**
     * @param args the command line arguments
     */
   
        
    
    public static void main(String[] args) {
        // TODO code application logic here
        Extra.extract();
       double rate=( SentiWordNetDemoCode.getRate()+1)*5/2;
      System.out.println("     : "+SentiWordNetDemoCode.getRate());
            System.out.println(rate);
        
        
        
        pairList=SentiWordNetDemoCode.getpairlist();
        
        Collections.sort(pairList, new Comparator<pair<Double, String>>() {
   

          @Override
          public int compare(pair<Double, String> o1, pair<Double, String> o2) {
              return o1.getL().compareTo(o2.getL());
          }
      });
        System.out.println("Best 5 words : ");
        String keywords="";
        if(rate>2.5){
        for (int i=1;  i<6&&i<=pairList.size();i++){
          String word=  pairList.get(pairList.size()-i).getR();        
  String trans=Translate.trns_fun_arabic(word);
keywords+=trans+" , ";
            System.out.println(trans);
        
        }
        }
        else
        {
                   for (int i=0;  i<5&&i<pairList.size();i++){
          String word=  pairList.get(i).getR(); 
          String trans=Translate.trns_fun_arabic(word);
keywords+=trans+" , ";
            System.out.println(trans);
        } 
        }
               System.out.println(keywords);

     
    }
    
}
