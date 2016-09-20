package com.mark.frame.hash;

public class DBHashTable {

	public static void main(String[] args) {
		String[] ids1 = new String[]{"186977967","186977967","186977971","186977974","186977982"};
		for (String string : ids1) {
			System.out.println("select * from table_" + (((int)(Math.abs(MurmurHash.hash(string)) % 256)) + 1) + " where id = " + string + ";");
		}
	}
	

	
	public void test2(){
		String string = "1";
		System.out.println(((int)(Math.abs(MurmurHash.hash(string)) % 256)) + 1) ;
	}
	

}
