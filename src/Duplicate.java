/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NewUser
 */
public class Duplicate {
    static void check(String s)
    {
        int len,l1;
        len=s.length();
    while(len>0)
    { 
     
    char c=s.charAt(0);
    s=s.replaceAll(c+"", "");
    l1=s.length();
    if(len-l1>1)
        System.out.println("Duplicate character "+c+(len-l1)+"times");
    len=s.length();
    }
    }
    public static void main(String arg[])
    {
    check("   www");
    
    }
}
