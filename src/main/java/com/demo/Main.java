package com.demo;

public class Main {

	public static void main(String[] args) {
		
		byte[] b = new byte[1];
		
		byte a = (byte)-1; 
		
		b[0] = a;

		
		System.out.println(b[0] & 0xff);	
	}

}
