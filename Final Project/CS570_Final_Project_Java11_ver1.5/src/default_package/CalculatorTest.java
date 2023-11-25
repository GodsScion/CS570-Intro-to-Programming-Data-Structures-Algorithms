package default_package;
/*
 * Name: Sai Vignesh Golla
 * CWID: 20008561
 * Ver: 1.5
 * 
*/
import java.util.Scanner;

public class CalculatorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Author: Sai Vignesh Golla");
			System.out.println("Version: 1.5 \n_________________________________________________________________________________________________________\n\n");

			System.out.println("Note: As said in the class on 22-April-2022 this project includes <expression> -> <term>'+'<expression> grammar!");
			System.out.println("\nPlease read the comments in Code! Please!!!\n");
			System.out.println("Open the file CalculatorTest.java to change manual inputs! (instructions and expected answers given in comments of code!)");
			System.out.println("This code displays the calculation steps too for your convenience :D");
			System.out.println("_________________________________________________________________________________________________________________________");
			System.out.println("_________________________________________________________________________________________________________________________\n\n");
			//Manual inputs are given here!
			String[] inputStatements 
					= {
							"",//Error
							"  ",//Error
							"d23",//Error invalid
							"(233.23",//Error invalid
							"34.343.23",//Error invalid
							"24.45/",//Error invalid
							"+13",//Error invalid
							"13",//13.0
							"-12",//-12.0 Actually should be invalid as per grammar but I wantedly changed this to be an exception so we can save negative values in identifiers!
							"1+4",//5.0
							"1-4",//-3.0
							"2.09+2",//4.09
							"34.023+.32",//34.343
							"2.0+3.00",//5.0
							"2.04+2.96+5",//10.0
							"56.9-4523.9843",//-4467.0843
							"20*45",//900.0
							"29.0932*782.2",//22756.70104
							"23.44*345.2323/23.434",//Error invalid, As per given grammar this is correct result (need to add rule <term> -> <factor>'*'<term> if this needs to be valid!)
							"(23.44*345.2323)/23.434",//345.32069266877187
							" 23.44 * (345.2323 /  23.434 )",//345.32069266877187
							"( 23.44 *345.2323 /23.434 )",//Error invalid, As per given grammar this is correct result (need to add rule <term> -> <factor>'*'<term> if this needs to be valid!)
							"89.34-(4224*.3443)/(10.09*23)",//83.0732563450683
							"56.03+43.97+(140.91/10.0012)*20-10",//371.78618565772103
							"66.65+434.23*242-(451+261.645*0.7862)",//104493.604701
							"9.0 -1.0 -2.0",//Error invalid, As per given grammar this is correct result (need to add rule <expression> -> <term>'-'<expression> if this needs to be valid!)
							"(2.34*34.34)*(22.2*3.2)",//5708.461824000001
							"  x  = sd",//Error invalid
							" fi4n  = 5.0 ",//Error invalid identifier
							" home = ",//Error invalid
							" abc = 5.0",//Saved 5.0
							" bac = 4-1*34 ",//Saved -30.0
							" abc = 5-1",//Updated 4.0
							" bac = 5+5",//Updated 10.0
							"  x = 5",//Saved 5.0
							"  xTwo = -5",//Saved -5.0
							" x = bac+abc-10.0*45",//Updated -436.0
							" xTwo = ghebkqw + abc * 10.0",//Error invalid
							"xTwo = (abc*bac)+(abc-xTwo)*0.001",//(4*10)+(4-(-5))*0.001 = 40.009
							"xO = abc*bac+(abc+5)*0.001",//40.009
							"   x  ",//-436.0
							"xTwo"//40.009
					};
			//Manual inputs intake ending!
			
			
			int n = inputStatements.length;
			//Code to take inputs from console start
			boolean inputFlag = true;
			while(inputFlag) {
				try {
					System.out.println("Would you like to enter expressions? (y/n): ");
					Scanner scan = new Scanner(System.in);
					String str = scan.nextLine();
					if(str.toLowerCase().equals("y")) {
						System.out.println("How many equations or expressions? ");
						n = scan.nextInt();
						inputStatements = new String[n];
						scan.nextLine();
						for(int i=0;i<n;i++) {
							System.out.println("Please enter expression or equation "+(i+1)+" :");
							inputStatements[i] = scan.nextLine();
						}
						scan.close();
						inputFlag = false;
					}
					else if(str.toLowerCase().equals("n")) {
						scan.close();
						inputFlag = false;
					}
					else {
						System.out.println("Invalid answer. Only type y or n or Y or N. Try again...");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("ERROR: "+e.getMessage()+" Try again...");
				}
			}
			//end console inputs
			
			
			Calculator c = new Calculator();
			for(int i=0; i<n; i++) {
				try {
					System.out.println("------------------------------------------------------------------------------------------------------");
					c.Expression_validation(inputStatements[i]);
					System.out.println("------------------------------------------------------------------------------------------------------");
				} catch (Exception e) {
					//e.printStackTrace();
					System.out.println("\nERROR: "+e.getMessage()+"\n");
				}
			}
			System.out.println("------------------------------------------------------------------------------------------------------\n\n\n");
			System.out.println("			Have a nice day. Thank You!!! :)						\n\n\n\n\n\n\n\n\n\n\nn\n\n\n\n\n\n\n");
			System.out.println("_________________________________________________________________________________________________________________________");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("MAIN ERROR"+e.getMessage());
		}

		
		
		
	}

}
