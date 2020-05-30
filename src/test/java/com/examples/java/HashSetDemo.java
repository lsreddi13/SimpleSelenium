package com.examples.java;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetDemo {

	public static void main(String[] args) {
		HashSet<String> set=new HashSet();  
		
		System.out.println(set.size());
//        set.add("One");    
//        set.add("Two");    
//        set.add("Three");   
//        set.add("Four");  
//        set.add("Five");  
        Iterator<String> i=set.iterator();  
        while(i.hasNext())  
        {  
        System.out.println("---"+i.next());  
        }  
        
        
        
	}
}
