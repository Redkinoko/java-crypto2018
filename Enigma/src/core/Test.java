/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author Red
 */
public class Test {

    public static void justDoIt()
    {
        int t = toInt('T');
        int e = toInt('E');
        int s = toInt('S');
        int r = toInt('R');
        int n = toInt('N');
        int i = toInt('I');
        System.out.println();
        System.out.println("T=" + t);
        System.out.println("E=" + e);
        System.out.println("S=" + s);
        System.out.println("T=" + t);
        System.out.println();
        System.out.println("R=" + r);
        System.out.println("N=" + n);
        System.out.println("I=" + i);
        System.out.println("E=" + e);
        int un = mod27(t+r);
        int de = mod27(e+n);
        int tr = mod27(s+i);
        int qu = mod27(t+e);

        char un_ = toChar(un);
        char de_ = toChar(de);
        char tr_ = toChar(tr);
        char qu_ = toChar(qu);
        
        System.out.println();
        System.out.println(un_ + "=" + un);
        System.out.println(de_ + "=" + de);
        System.out.println(tr_ + "=" + tr);
        System.out.println(qu_ + "=" + qu);
        
        int u_ = up(un-r);
        int d_ = up(de-n);
        int t_ = up(tr-i);
        int q_ = up(qu-e);
        
        un_ = toChar(u_);
        de_ = toChar(d_);
        tr_ = toChar(t_);
        qu_ = toChar(q_);
        
        System.out.println();
        System.out.println(u_ + "=" + un_);
        System.out.println(d_ + "=" + de_);
        System.out.println(t_ + "=" + tr_);
        System.out.println(q_ + "=" + qu_);
    }
    
    public static int toInt(char c)
    {
        return 1+(int)(c - 'A');
    }
    
    public static int remove26(int i)
    {
        return (i>26)?(remove26(i-26)):(i);
    }
    
    public static int mod27(int i)
    {
        return remove26(i);
    }
    
    public static int up(int i)
    {
        return(i<=0)?i+26:i;
    }
    
    public static char toChar(int i)
    {
        return (char)('A'+(i-1));
    }
}
