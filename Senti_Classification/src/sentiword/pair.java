/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentiword;

/**
 *
 * @author Shenawynkov
 */
public class pair<L,R> {
    private L l;
    private R r;
    public pair(L l, R r){
        this.l = l;
        this.r = r;
    }
    public L getL(){ return l; }
    public R getR(){ return r; }
    public void setL(L l){ this.l = l; }
    public void setR(R r){ this.r = r; }
    public boolean equals(Object o)
   {
     if (! (o instanceof pair)) { return false; }
     pair p = (pair)o;
     return l.equals(p.l) && r.equals(p.r);
   } 

   public int hashCode()
   {
      return 7 * l.hashCode() + 13 * r.hashCode();
   } 
}