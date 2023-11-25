package main;

import java.util.Scanner;

public class main {

	public static int recursionCount = 0;
	
	
	//function to check validity of inputs
	public static void checkValidInputs(int[][] array) throws Exception {
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Checking the validity of inputs...");
		
		if(array.length != 7) {
			throw new Exception("Invalid inputs! Exactly 7 integer arrays are expected but "+array.length+" arrays were given.");
		}
		
		for(int i=0; i<7; i++) {
			if(array[i].length != 6) {
				throw new Exception("Invalid inputs! Each array must have exactly 6 integers in it. But "+array[i].length+" integers were given in Array h"+i+": "+arraytoString(array[i])+".");
			}
			
			for(int j=0; j<6; j++) {
				if(!( array[i][j]==1 || array[i][j]==2 || array[i][j]==3 || array[i][j]==4 || array[i][j]==5 || array[i][j]==6 ) ) {
					throw new Exception("Invalid inputs! Each array must have only whole numbers ranging from 1 to 6 in it. Array h"+i+": "+arraytoString(array[i])+" contains invalid number(s).");
				}
				for(int k=j+1; k<6; k++) {
					if(array[i][j] == array[i][k]) {
						throw new Exception("Invalid inputs! Each array must have distinct numbers. Array h"+i+": "+arraytoString(array[i])+" contains duplicate numbers.");
					}
				}
			}	
		}
		System.out.println("Given inputs: "+toStringfor2dArray(array,true)+" are valid!");
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
	}
	
	
	
	//function to print 2d arrays
	public static String toStringfor2dArray(int[][] array,boolean nextline) {
		if(nextline == true) {
			String str = "\n{ \n";
			for(int i=0; i<array.length; i++) {
				str+="  h"+(i)+": [";
				for(int j=0; j<array[i].length; j++) {
					str=str+array[i][j]+", ";
				}
				str = str.substring(0,str.length()-2) + "],\n";
			}
			return str.substring(0,str.length()-2) + "\n}\n";
		}
		String str = "{ ";
		for(int i=0; i<array.length; i++) {
			str+="[";
			for(int j=0; j<array[i].length; j++) {
				str=str+array[i][j]+",";
			}
			str = str.substring(0,str.length()-1) + "], ";
		}
		return str.substring(0,str.length()-2) + " }";
	}
	
	//function to convert arrays into strings
	public static String arraytoString(int[] array) {
		String str="[ ";
		for(int j=0; j<array.length; j++) {
			str=str+array[j]+", ";
		}
		str = str.substring(0,str.length()-2) + "]";
		return str;
	}
	
	
	public static int CheckIfHelperFuncAddition(int i, int j) {
		if(i <= j) {
			return j+1;
		}
		return j;
	}
	
	//function to check if the conditions of side walls are satisfied or not
	public static int CheckIfTheyFit(int[] h1, int[] h2, int[] h3, int[] h4, int[] h5, int[] h6, int[] h7, int i0, int i1, int i2, int i3, int i4, int i5, int i6){
		//here h1 is the center hexagon, h2 is the top one. While others relatively follow counter clock wise.
		//side names start from top and go counter clock wise respectively inside a hexagon.
		if(!(h1[0]==h2[3] && h1[1]==h3[4] && h1[2]==h4[5] && h1[3]==h5[0] && h1[4]==h6[1] && h1[5]==h7[2]   &&   h2[2]==h3[5]   &&   h3[3]==h4[0]   &&   h4[4]==h5[1]   &&   h5[5]==h6[2]   &&   h6[0]==h7[3]   &&   h7[1]==h2[4])) {
			return 0;
		}
		
		i1 = CheckIfHelperFuncAddition(i0, i1);
		i2 = CheckIfHelperFuncAddition(i0, i2);
		i3 = CheckIfHelperFuncAddition(i0, i3);
		i4 = CheckIfHelperFuncAddition(i0, i4);
		i5 = CheckIfHelperFuncAddition(i0, i5);
		i6 = CheckIfHelperFuncAddition(i0, i6);
		
		printHexagons(h1, h2, h3, h4, h5, h6, h7, i0, i1, i2, i3, i4, i5, i6);
		return 1;
	}
	
	
	//function to print hexagons
	public static void printHexagons(int[] h1, int[] h2, int[] h3, int[] h4, int[] h5, int[] h6, int[] h7, int i0, int i1, int i2, int i3, int i4, int i5, int i6) {
		System.out.println("\n                             _______________\n                            /       "+h2[0]+"       \\\n                           /                 \\\n                          /  "+h2[1]+"             "+h2[5]+"  \\\n                         /                     \\\n                        /          h"+i1+"           \\\n     _______________    \\                       /    _______________\n    /       "+h3[0]+"       \\    \\                     /    /       "+h7[0]+"       \\\n   /                 \\    \\  "+h2[2]+"             "+h2[4]+"  /    /                 \\\n  /  "+h3[1]+"             "+h3[5]+"  \\    \\        "+h2[3]+"        /    /  "+h7[1]+"             "+h7[5]+"  \\\n /                     \\    \\_______________/    /                     \\\n/          h"+i2+"           \\                       /          h"+i6+"           \\\n\\                       /    _______________    \\                       /\n \\                     /    /       "+h1[0]+"       \\    \\                     /\n  \\  "+h3[2]+"             "+h3[4]+"  /    /                 \\    \\  "+h7[2]+"             "+h7[4]+"  /\n   \\        "+h3[3]+"        /    /  "+h1[1]+"             "+h1[5]+"  \\    \\        "+h7[3]+"        /\n    \\_______________/    /                     \\    \\_______________/\n                        /          h"+i0+"           \\\n     _______________    \\                       /    _______________\n    /       "+h4[0]+"       \\    \\                     /    /       "+h6[0]+"       \\\n   /                 \\    \\  "+h1[2]+"             "+h1[4]+"  /    /                 \\\n  /  "+h4[1]+"             "+h4[5]+"  \\    \\        "+h1[3]+"        /    /  "+h6[1]+"             "+h6[5]+"  \\\n /                     \\    \\_______________/    /                     \\\n/          h"+i3+"           \\                       /          h"+i5+"           \\\n\\                       /    _______________    \\                       /\n \\                     /    /       "+h5[0]+"       \\    \\                     /\n  \\  "+h4[2]+"             "+h4[4]+"  /    /                 \\    \\  "+h6[2]+"             "+h6[4]+"  /\n   \\        "+h4[3]+"        /    /  "+h5[1]+"             "+h5[5]+"  \\    \\        "+h6[3]+"        /\n    \\_______________/    /                     \\    \\_______________/\n                        /          h"+i4+"           \\\n                        \\                       /\n                         \\                     /\n                          \\  "+h5[2]+"             "+h5[4]+"  /\n                           \\        "+h5[3]+"        /\n                            \\_______________/\n\n");
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
	}
	
	
	//Heap algorithm usage start (comment this if custom permutations algorithm is used, don't forget to uncomment Heap algorithm if you want to use heap algorithm)
//	public static int recursiveChecker(int[] h1, int[] h2, int[] h3, int[] h4, int[] h5, int[] h6, int[] h7) {
//		if(recursionCount > 7) {
//			recursionCount = 1;
//			return 0;
//		}
//		recursionCount = recursionCount+1;
//		
//		int arr[][] = {h2,h3,h4,h5,h6,h7};
//		
//		int count = printAllRecursive(6, arr, h1);
//		
//		return count + recursiveChecker(h2, h3, h4, h5, h6, h7, h1);
//	}
	//Heap algorithm usage ending
	
	
	
	//Custom algorithm for permutations (comment from here to end of custom algorithm if heap algorithm is being used) 
	public static int recursiveChecker(int[] h1, int[] h2, int[] h3, int[] h4, int[] h5, int[] h6, int[] h7) {
		if(recursionCount > 6) {
			recursionCount = 0;
			return 0;
		}
		recursionCount = recursionCount+1;
		
		int count = 0;
		
		int arr[][] = {h2,h3,h4,h5,h6,h7};
		
		first:
		for(int i=123456; i<=654321; i++) {
			String str= String.valueOf(i);
			if(str.contains("9") || str.contains("8") || str.contains("7") || str.contains("0")) {
				continue;
			}
			str = str.replace("6", "0");
			int l =str.length();
			for(int j=0; j<l; j++) {
				for(int k=j+1; k<l ; k++) {
					if(str.charAt(j)==str.charAt(k)) {
						continue first;
					}
				}
			}
			
			int i1 = Character.getNumericValue(str.charAt(0));
			int i2 = Character.getNumericValue(str.charAt(1));
			int i3 = Character.getNumericValue(str.charAt(2));
			int i4 = Character.getNumericValue(str.charAt(3));
			int i5 = Character.getNumericValue(str.charAt(4));
			int i6 = Character.getNumericValue(str.charAt(5));
			
			
			//count+= recursiveRotater(h7, arr, recursionCount-1, i1, i2, i3, i4, i5, i6, 1, 6);
			
			for(int a1=0; a1<6; a1++) {
				for(int a2=0; a2<6; a2++) {
					for(int a3=0; a3<6; a3++) {
						for(int a4=0; a4<6; a4++) {
							for(int a5=0; a5<6; a5++) {
								for(int a6=0; a6<6; a6++) {
									count += CheckIfTheyFit(h1, arr[i1], arr[i2], arr[i3], arr[i4], arr[i5], arr[i6], recursionCount-1, i1, i2, i3, i4, i5, i6);									
									//System.out.println("Current center hexagon: "+(recursionCount-1)+", current loop: "+a1+". Just checked: \n"+toStringfor2dArray(arr, true));
									arr[5] = rotate(arr[5]);
								}
								arr[4] = rotate(arr[4]);
							}
							arr[3] = rotate(arr[3]);
						}
						arr[2] = rotate(arr[2]);
					}
					arr[1] = rotate(arr[1]);
				}
				arr[0] = rotate(arr[0]);
			}

		}
		
		return count + recursiveChecker(h2, h3, h4, h5, h6, h7, h1);
	}
	//Custom algorithm for permutations ending
	
	
	
	public static int recursiveRotater(int[] CenterHexagon, int[][] arr, int i0, int i1, int i2, int i3, int i4, int i5, int i6, int currentDepth, int maxDepth) {
		int count = 0;
		if(maxDepth<=currentDepth) {
			for(int i=0; i<6; i++) {
				count += CheckIfTheyFit(CenterHexagon, arr[i1], arr[i2], arr[i3], arr[i4], arr[i5], arr[i6], recursionCount-1, i1, i2, i3, i4, i5, i6);
				//System.out.println(toStringfor2dArray(arr, false));
				arr[currentDepth-1] = rotate(arr[currentDepth-1]);
			}
			return count;
		}
		
		for(int i=0; i<6; i++) {
			count+=recursiveRotater(CenterHexagon, arr, i0, i1, i2, i3, i4, i5, i6, currentDepth+1, maxDepth);						
			arr[currentDepth-1] = rotate(arr[currentDepth-1]);
		}
		return count;
	}
	
	
	
	public static int[] rotate(int[] arr) {
		int[] temp = {arr[5],arr[0],arr[1],arr[2],arr[3],arr[4]};
		return temp;
	}

	
	
	//Heap algorithm
//	public static int printAllRecursive(int n, int[][] elements, int[] centerHexagon) {
//		int count = 0;
//	    if(n == 1) {
//	        count += CheckIfTheyFit(centerHexagon,elements[0],elements[1],elements[2],elements[3],elements[4],elements[5]);
//	    } else {
//	        for(int i = 0; i < n-1; i++) {
//	            count = count + printAllRecursive(n - 1, elements, centerHexagon);
//	            if(n % 2 == 0) {
//	                swap(elements, i, n-1);
//	            } else {
//	                swap(elements, 0, n-1);
//	            }
//	        }
//	        count = count + printAllRecursive(n - 1, elements, centerHexagon);
//	    }
//	    return count;
//	}

	
	//helper function for heap algorithm to swap elements 
	public static <T> void swap(int[][] input, int a, int b) {
		int[] tmp = input[a];
		input[a] = input[b];
		input[b] = tmp;
	}
		
	

	public static void main(String[] args) {
		
		try {
			System.out.println("_________________________________________________________________________________________________________________________________\n\n");
			
			
			
			int[] array = {1,2,3,4,5,6};
			System.out.println("The naming conventions of the positions are as follows: ");
			printHexagons(array,array,array,array,array,array,array,0,1,2,3,4,5,6);
			
			System.out.println("Except for central hexagon this algorithm will rotate all hexagons and check too\n"
					+ "Rotation of central hexagon will only result in duplicate solutions (6 duplicates for every possible combination)\n"
					+ "So don't worry about orientation of hexagons!");
			
			
			int h[][] = new int[7][6];
			
			
			
			//Please give your manual inputs here {Manual inputs}
			h[0] = new int[]{4,5,6,1,2,3};
			h[1] = new int[]{4,5,3,1,2,6};
			h[2] = new int[]{5,1,6,4,2,3};
			h[3] = new int[]{2,3,6,4,5,1};
			h[4] = new int[]{4,1,6,2,5,3};
			h[5] = new int[]{3,5,6,4,1,2};
			h[6] = new int[]{4,2,6,3,5,1};
			
			
			
			
			//Code to take inputs from console start
			boolean flag = true;
			while(flag) {
				System.out.println("Would you like to enter inputs? (y/n): ");
				Scanner scan = new Scanner(System.in);
				String str = scan.next();
				if(str.toLowerCase().equals("y")) {
					for(int i=0; i<7; i++) {
						System.out.println("Enter the side values for heaxgon "+i);
						for(int j=1; j<=6; j++) {
						    System.out.println("Enter value for side "+j+": ");
						    h[i][j-1] = scan.nextInt();
						}
					}
					scan.close();
					System.out.println("Given inputs are: "+toStringfor2dArray(h,false));
					flag = false;
				}
				else if(str.toLowerCase().equals("n")) {
					flag = false;
				}
				else {
					System.out.println("Invalid answer. Only type y or n or Y or N. Try again...");
				}
			}
			//end console inputs
			

			
			
			int[][] inputs = {h[0], h[1], h[2], h[3], h[4], h[5], h[6]};
			
			checkValidInputs(inputs);
			
			
			System.out.println("Checking for feasible arrangments...");
			int solubleConfigs = recursiveChecker(h[0], h[1], h[2], h[3], h[4], h[5], h[6]);
			
			if(solubleConfigs == 0) {
				System.out.println("Sorry no solutions found for given inputs:"+toStringfor2dArray(inputs,true)+"Try different inputs.");
			}
			else {
				System.out.println("So total number of compatible arrangements found = "+solubleConfigs+".");
			}
			
			
			System.out.println("_________________________________________________________________________________________________________________________________\n\n");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR:  "+e.getMessage());
			//e.printStackTrace();
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("_________________________________________________________________________________________________________________________________");
		}
		
	}

	
}
