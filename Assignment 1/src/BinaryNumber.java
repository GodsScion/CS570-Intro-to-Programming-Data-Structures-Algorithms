
//  author: Sai Vignesh Golla
//	CWID: 20008561
//	Binary notations are little-endian format


import java.util.*;
import java.lang.Math.*;

public class BinaryNumber{
	private int data[];
	private boolean overflow = false;
	
	
	
	public BinaryNumber(int length){ // Checked in personal test!
		//Checking for valid inputs
		if(length < 0) {
			System.out.println("length input must be greater than 0");
			data = new int[0];
		}
		else {			
			//Creating binary with 0's
			data = new int[length];
			for(int i=0; i<length; i++) {
				data[i]=0;
			}
		}
	}
	
	
	
	public BinaryNumber(String  str)  { // Checked in personal test!
		boolean validInputFlag = true;
		
		//Checking for valid inputs
		str = str.trim();
		if(str.equals("")) {
			System.out.println("Empty strings and blank spaces are not allowed!");
			validInputFlag = false;
		}
		
		//Checking for valid inputs
		data = new int[str.length()];
		
		for(int i=0; i< str.length(); i++) {
			if(str.substring(i, i+1).equals("1") || str.substring(i, i+1).equals("0")) {
				data[i] = Character.getNumericValue(str.charAt(i));
			}
			else{
				System.out.println("Given "+str+" string is invalid! Only 1 and 0 are allowed in the string input Ex: 100101");
				validInputFlag = false;
				i=str.length()+1;
			}
		}
			
		//Checking if inputs are valid, if not emptying the data
		if(!validInputFlag) {
			data = new int[0];
		}
		
		/*//for debugging
		for(int i=0; i<data.length; i++) {
			System.out.print(data[i]); 
		}
		System.out.println("\n");*/

	}
	
	
	
	public int getLength() { // Checked in personal test!
		return data.length; //Gives length of data
	}
	
	
	
	public int getDigit(int  index) { // Checked in personal test!
		
		//Checking for input validity
		if(index<0 || index>=data.length) {
			System.out.println("Index must be less than Length of data and greater than or equal to 0");
			System.out.println("Current length of data is " + data.length);
			return -1; //-1 means invalid inputs
		}
		else {
			//Inputs are valid so gives the num at asked index
			return data[index];
		}
	}
	
	
	
	public void shiftR(int amount) { // Checked in personal test!
		
		//Checking for input validity
		if(amount<0) {
			System.out.println("Amount must be greater than or equal to 0!");
		}
		
		//Storing original length of the array
		int originalLength = data.length;
		
		//Increasing the length
		data = Arrays.copyOf(data, originalLength+amount);
		
		//Shifting numbers to right
		for(int i=originalLength-1; i>=0; i--) {
			data[i+amount] = data[i];
		}
		
		//Filling initial places with zeros
		for(int i=0; i<amount; i++) {
			data[i] = 0;
		}	
	}
	
	
	
	public void add(BinaryNumber aBinaryNumber) { // Checked in personal test!
		
		//checking for lengths of data
		if(data.length != aBinaryNumber.data.length) {
			System.out.println("Lengths of the binary numbers are not matching!");
		}
		else {			
			//Adding the binary numbers
			for(int i=0; i<data.length; i++) {
				if(!overflow) {
					aBinaryNumber.data[i] += data[i];
				}
				else {
					aBinaryNumber.data[i] = aBinaryNumber.data[i] + data[i] + 1;
				}
				
				//checking for carry
				if(aBinaryNumber.data[i] > 1) {
					overflow = true;
					aBinaryNumber.overflow = true;
					aBinaryNumber.data[i] = 1; //Binary numbers only have 1's and 0's!
				}
				else {
					overflow = false;
					aBinaryNumber.overflow = false;
				}
			}
			
			// Checking for final overflow to send message (not needed anymore I guess)
	 		/*if(overflow) {
	 			System.out.println("The addition caused overflow!");
	 		}*/	
		}	
	}
	
	
	
	public String toString() { // Checked in personal test!
		
		// Checking for final overflow to send message
		if(overflow) {
			return "Overflow";
		}
		
		//Converting to string
		String str = "";
		for(int i=0; i<data.length; i++) {
			str = str + data[i];
		}
		return str;
	}
	
	
	
	public int toDecimal() { // Checked in personal test!
		//Converting to decimal
		int num = 0; 
		for(int i=0; i<data.length; i++) {
			if(data[i] == 1) {
				num = (int) (num + Math.pow(2, i));  //little-endian format!
			}
		}
		return num;
	}
	
	
	
	public void clearOverflow() { // Checked in personal test!
		overflow = false;
	}
	
	
/*	
//________________________________Testing Purpose____________________________________________________________________________________	
	
	
	
	public static void main(String[] args) {
		try {
			
			BinaryNumber a = new BinaryNumber("100000000");
			BinaryNumber b = new BinaryNumber("000010101");
			BinaryNumber c = new BinaryNumber(" 00010101");
			BinaryNumber d = new BinaryNumber("111111111");
			BinaryNumber e = new BinaryNumber(9);
			BinaryNumber f = new BinaryNumber("   ");
			BinaryNumber g = new BinaryNumber("111sv04w");
			BinaryNumber h = new BinaryNumber(-1);
			
			System.out.println("\n\n getLength--------------------------------------\n");
			System.out.println(b.getLength()); //9
			System.out.println(c.getLength()); //8
			System.out.println(e.getLength()); //9
			System.out.println(f.getLength()); //0
			System.out.println(g.getLength()); //0
			System.out.println(h.getLength()+"\n\n getDigit--------------------------------------\n"); //0
			
			System.out.println(c.getDigit(-8));
			System.out.println(h.getDigit(5));
			System.out.println(b.getDigit(5));
			System.out.println(c.getDigit(5)+"\n\n toDecimal--------------------------------------\n");
			
			System.out.println(a.toDecimal());
			System.out.println(b.toDecimal());
			a.add(b);
			System.out.println(a.toDecimal());
			System.out.println(b.toDecimal()+"\n\n toString and shiftR--------------------------------------\n");

			System.out.println(a.toString());
			a.shiftR(0);
			System.out.println(a.toString());
			
			System.out.println(b.toString());
			b.shiftR(3);
			System.out.println(b.toString()+"\n\n add and clearOverflow--------------------------------------\n");
			
			
			d.add(a);
			System.out.println(a.toString());
			a.clearOverflow();
			System.out.println(a.toString());
			a.add(c);
			c.add(b);
			
		}
		catch(Error e) {
			System.out.println(e);
		}
		
	}
*/		
}


