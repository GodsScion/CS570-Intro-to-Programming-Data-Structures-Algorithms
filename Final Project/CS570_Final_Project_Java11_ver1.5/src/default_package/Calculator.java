package default_package;

import java.util.Hashtable;

/*
 * Name: Sai Vignesh Golla
 * CWID: 20008561
 * Ver: 1.5
 * 
*/


public class Calculator {
	
	Hashtable<String, Double> hmap = new Hashtable<String, Double>();
	
	
	
	public void Expression_validation (String expression) throws Exception {
		String oldExp = expression;
		if(expression.isBlank()) throw new Exception("Empty strings and pure white space strings are not allowed as inputs!");
		expression = expression.strip();
		if(!checkIdentifierSetter(expression)) {
			double[] output = checkExpression(expression);
			if(output[0] != 0.0) {
				System.out.println("\nFor given exp: "+oldExp+" it's answer is:\n "+output[1]);
				return;
			}else {
				throw new Exception("Given expression "+expression+" isn't valid!");
			}
		}
	}
	
	
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	public boolean checkIdentifierSetter(String exp) throws Exception {
		int opPos = exp.indexOf("=");
		//checking <identifier>'='<expression>
		if(opPos>-1) {
			String identifier = exp.substring(0,opPos).strip();
			if(!(identifier != null && identifier.length()>0 && identifier.matches("^[a-zA-Z]*$"))) {
				throw new Exception("Given equation "+exp+" is Invalid! Identifier "+identifier+" must only contain alphabets!");	
			}
			
			if(opPos == (exp.length()-1)) {
				throw new Exception("Given equation "+exp+" is invalid! Cause there's no value after '='!");
			}
			
			double[] expression = checkExpression(exp.substring(opPos+1));
			if(expression[0] == 0.0) {
				throw new Exception("Given equation "+exp+" is invalid! Cause "+exp.substring(opPos+1)+" is NOT a <expression> !");
			}
			System.out.println("\n");
			if(hmap.containsKey(identifier)) {
				System.out.println("Updating value...");
			}
			hmap.put(identifier, expression[1]);
			System.out.println("Value of "+identifier+" = "+hmap.get(identifier));
			return true;
		}
		return false;
	}
	
	
	
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	public double[] checkExpression(String exp) throws Exception {
		exp = exp.strip();
		if(exp.isBlank()) {
			return new double[]{0.0,0.0};
		}
//		System.out.println("Checking if "+exp+" is <expression> or not:");
		
		
		//Case1: checking	<expression> -> <term>
		double term[] = checkTerm(exp);
		if(term[0] != 0.0) {
			System.out.println(exp+" is a <expression> -> <term> ! and value is: "+term[1]);
			return term;
		}
		System.out.println("As "+exp+" is NOT a term, it is NOT a <expression> -> <term>");
		
		int plusPos = exp.indexOf("+");
		int minusPos = exp.indexOf("-");
		
	
		if (plusPos > -1) {
			
			//Case 2: checking	<expression> -> <term>'+'<expression>
			term = checkTerm(exp.substring(0, plusPos));
			if (term[0] == 0.0) {
//				System.out.println("As " + exp.substring(0, plusPos) + " is NOT a <term>. " + exp + " is NOT a <expression> -> <term>'+'<expression> OR <expression> -> <term>'+'<term>");
			} 
			else {
				
				double expOrTerm[] = checkExpression(exp.substring(plusPos + 1));
				if (expOrTerm[0] == 0.0) {
//				System.out.println("As " + exp.substring(plusPos+1) + " is NOT a <expression>. " + exp + " is NOT a <expression> -> <term>'+'<expression>");
				
				//Case 3: checking <expression> -> <term>'+'<term>
					expOrTerm = checkTerm(exp.substring(plusPos + 1));
					if (expOrTerm[0] == 0.0) {
//						System.out.println("As " + exp.substring(plusPos+1) + "is NOT a <term> either, "+exp+" is NOT a <expression> -> <term>'+'<term>");
					}
					else {
						System.out.println(exp + "is a <expression> -> <term>'+'<term> ! and value is: "+term[1]+"+"+expOrTerm[1]+" = "+(term[1] + expOrTerm[1]));
						return new double[] { 1.0, term[1] + expOrTerm[1] };
					}
					
				} 
				else {
					System.out.println(exp + " is a <expression> -> <term>'+'<expression> ! and value is: "+term[1]+"+"+expOrTerm[1]+" = "+(term[1] + expOrTerm[1]));
					return new double[] { 1.0, (double)(term[1] + expOrTerm[1]) };
				}
			}
			 
		}
		
//Beta1------------------------------------------(Don't forget to toggle commenting!)
//		if(minusPos > -1){
//			//Case 4: checking	<expression> -> <term>'-'<expression>
//			term = checkTerm(exp.substring(0, minusPos));
//			if (term[0] == 0.0) {
//				System.out.println("As " + exp.substring(0, minusPos) + " is NOT a <term>. " + exp + " is NOT a <expression> -> <term>'-'<expression> OR <expression> -> <term>'-'<term>"); 
//			} 
//			else {
//				
//				double expOrTerm[] = checkExpression(exp.substring(minusPos + 1));
//				if (expOrTerm[0] == 0.0) {
//				System.out.println("As " + exp.substring(minusPos+1) + " is NOT a <expression>. " + exp + " is NOT a <expression> -> <term>'-'<expression>");
//				
//				//Case 5: checking	<expression> -> <term>'-'<term>
//					expOrTerm = checkTerm(exp.substring(minusPos + 1));
//					if (expOrTerm[0] == 0.0) {
//						System.out.println("As " + exp.substring(minusPos+1) + "is NOT a <term> either. So, "+exp+" is NOT a <expression> -> <term>'-'<term>");
//					}
//					else {
//						System.out.println(exp + "is a <expression> -> <term>'-'<term>");
//						return new double[] { 1.0, term[1] - expOrTerm[1] };
//					}
//					
//				} 
//				else {
//					System.out.println(exp + "is a <expression> -> <term>'-'<expression>");
//					return new double[] { 1.0, term[1] - expOrTerm[1] };
//				}
//			}
//		}
//Beta1 end--------------------------------------		
			
		
//toogle Beta1-----------------------------------
		if(minusPos > -1){
			//Case 4: checking	<expression> -> <term>'-'<term>
			term = checkTerm(exp.substring(0, minusPos));
			if(exp.substring(0, minusPos).length()==0) {
				term[0] = 1.0;
			}
			if (term[0] == 0.0) {
//					System.out.println("As " + exp.substring(0, minusPos) + " is NOT a <term>. " + exp + " is NOT a <expression> -> <term>'-'<term>"); 
			} 
			else {
				double expOrTerm[] = checkTerm(exp.substring(minusPos + 1));
				if (expOrTerm[0] == 0.0) {
//						System.out.println("As " + exp.substring(minusPos+1) + "is NOT a <term>. "+exp+" is NOT a <expression> -> <term>'-'<term>");
				}
				else {
					System.out.println(exp + " is a <expression> -> <term>'-'<term> ! and value is: "+term[1]+"-"+expOrTerm[1]+" = "+(term[1] - expOrTerm[1]));
					return new double[] { 1.0, term[1] - expOrTerm[1] };
				}

			}
		}
//toggle Beta1 end-------------------------------- 
		

//		System.out.println("As all conditions failed, "+exp+" is NOT a <expression> !");
		return new double[]{0.0,0.0};
	}
	
	
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	public double[] checkTerm(String exp) throws Exception {
		exp = exp.strip();
		if(exp.isBlank()) {
			//System.out.println(exp+" is blank !");
			return new double[]{0.0,0.0};
		}
//		System.out.println("Checking if "+exp+" is <term> or not:");
		
		//Case 1: checking	<term> -> <factor>
		double[] factor = checkFactor(exp);
		if(factor[0] != 0.0) {
			System.out.println(exp+" is a <term> -> <factor> ! and value is: "+factor[1]);
			return factor;
		}		
//		System.out.println("As "+exp+" is NOT a <factor>, it is NOT a <term> -> <factor>");		
		
		
		
		//Case 2: checking	<term> -> <factor>'*'<factor>
		int opPos=exp.indexOf("*");
		int opPosMax = exp.lastIndexOf("*");
		while(opPos>-1 && opPos<=opPosMax ) {
			factor = checkFactor(exp.substring(0,opPos));
			if(factor[0] != 0.0) {
				double factor2[] = checkFactor(exp.substring(opPos+1));
				if(factor2[0] != 0.0) {
					System.out.println(exp+" is a <term> -> <factor>'*'<factor> ! and value is: "+factor[1]+"*"+factor2[1]+"="+(factor[1]*factor2[1]));
					return new double[] {1.0,factor[1]*factor2[1]};
				}
					System.out.println("As " + exp.substring(opPos+1) + " is NOT a <factor>. " + exp + " is NOT a <term> -> <factor>'*'<factor>");
			} 
//			else {
//				System.out.println("As " + exp.substring(0, opPos) + " is NOT a <factor>. " + exp + " is NOT a <term> -> <factor>'*'<factor>");
//			}
			opPos = exp.indexOf("*", opPos+1);			
		}

		//Case 3: checking	<term> -> <factor>'/'<factor>
		opPos=exp.indexOf("/");
		opPosMax=exp.lastIndexOf("/"); 
		while(opPos>-1 && opPos<=opPosMax) {
			factor = checkFactor(exp.substring(0,opPos));
			if(factor[0] != 0.0) {
				double factor2[] = checkFactor(exp.substring(opPos+1));
				if(factor2[0] != 0.0) {
					System.out.println(exp+" is a <term> -> <factor>'/'<factor> ! and value is: "+factor[1]+"/"+factor2[1]+" = "+(factor[1]/factor2[1]));
					if(factor2[1]!=0.0) {
						return new double[] {1.0,factor[1]/factor2[1]};
					}
					throw new Exception("As the value of "+exp.substring(opPos+1)+" (denominator) is 0.0. We cannot perform the / operation, cause that will throw error!");							
				}
					System.out.println("As " + exp.substring(opPos+1) + " is NOT a <factor>. " + exp + " is NOT a <term> -> <factor>'/'<factor>");
				
			} 
//			else {
//				System.out.println("As " + exp.substring(0, opPos) + " is NOT a <factor>. " + exp + " is NOT a <term> -> <factor>'/'<factor>");
//			}		
			opPos = exp.indexOf("/",opPos+1);
		}

				
				
		//Beta Case 4: checking <term> -> <factor>'^'<factor>    
				
				
//		System.out.println("As all conditions failed, "+exp+" is NOT a <term> !");
		return new double[] {0.0,0.0};
	}
	
	
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	public double[] checkFactor(String exp) throws Exception {
		exp = exp.strip();
		if(exp.isBlank()) {
			//System.out.println(exp+" is blank !");
			return new double[]{0.0,0.0};
		}
//		System.out.println("Checking if "+exp+" is <factor> or not:");
		
		//Case 1: checking	<factor> -> <number>
		String[] number = checkNumber(exp);
		if(number[0].equals("t")) {
//			System.out.println(exp+" is a <factor> -> <number> ! and value is: "+number[1]);
			return new double[] {1.0,(double) Double.valueOf(number[1])};
		}
//		System.out.println(exp+" is NOT a <factor> -> <number>");

		
//Extra marks		//Case 1.5: checking <term> -> <identifier>
		if(hmap.containsKey(exp)) {
			System.out.println(exp+" is a <identifier> ! and value is: "+hmap.get(exp));
			return new double[] {1.0,hmap.get(exp)};
		};
//Extra marks---end!
		
		
		//Case 2: checking	<factor> -> '('<expression>')' 
		if(exp.indexOf("(") == 0 && exp.indexOf(")") == exp.length()-1) {
			double[] expression = checkExpression(exp.substring(1,exp.length()-1));
			if(expression[0] != 0.0) {
//				System.out.println(exp+" is a <factor> -> '('<expression>')' ! and value is: "+expression[1]);
				return expression;
			}
//			System.out.println(exp+" is NOT a <factor> -> '('<expression>')' ");
		}
//		else {
//			System.out.println(exp+" is NOT a <factor> -> '('<expression>')' as we didn't find '(' & ')' in the right positions");
//		}
		
//		System.out.println("As all conditions failed, "+exp+" is NOT a <factor> !");
		return new double[] {0.0,0.0};
	}
	
	
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	public String[] checkNumber(String exp) throws Exception {
		exp = exp.strip();
		if(exp.isBlank()) {
			//System.out.println(exp+" is blank !");
			return new String[]{"f",""};
		}
//		System.out.println("Checking if "+exp+" is <number> or not:");
		
		//Case 1: checking	<number> -> <number>'.'<number>
		int decimalPos = exp.indexOf(".");
		if(decimalPos>-1) {
			String[] number1 = new String[] {"f",""};
			if(decimalPos==0) {
				number1[0] = "t";
			}
			else {
				number1 = checkNumber(exp.substring(0,decimalPos));
			}
			
			
			if(number1[0].equals("f")) {
//				System.out.println("As "+exp.substring(0,decimalPos)+" is NOT a <number>. "+exp+" is NOT a <number> -> <number>'.'<number>");
			}
			else {
				if(decimalPos == exp.length()-1) {	throw new Exception("As "+exp+" has no digits after '.', This can't be a valid expression!");}
				String[] number2 = checkNumber(exp.substring(decimalPos+1));
				if(number2[0].equals("t")) {
					String output = number1[1]+"."+number2[1];
//					System.out.println(exp+" is a <number> -> <number>'.'<number> ! and value is: "+output);
					return new String[] {"t",output};
				}
//				else {
//					System.out.println("As "+exp.substring(decimalPos+1)+" is NOT a <number>. "+exp+" is NOT a <number> -> <number>'.'<number>");
//				}
			}
			
		}
//		else {
//			System.out.println(exp+" is NOT a <number> -> <number>'.'<number> as no '.' is found");
//		}
		
		
		//Case 2: checking	<number> -> <digit><number>
		int digit = checkDigit(exp.substring(0,1));
		if(digit > -1) {
			//Beta Case 3: checking	<number> -> <digit> (needed!)
			if(exp.length()==1) {
//				System.out.println(exp+" is a <number> -> <digit>! and value is: "+digit);
				return new String[] {"t",String.valueOf(digit)};
			}
			//Case 3 end
			String[] number = checkNumber(exp.substring(1));
			if(number[0].equals("t")) {
				String val = String.valueOf(digit) + number[1];
//				System.out.println(exp+" is a <number> -> <digit><number> ! and value is: "+val);
				return new String[] {"t",val};
			}
//			else {
//				System.out.println("As "+exp.substring(1)+" is NOT a <number>. "+exp+" is NOT a <number> -> <digit><number>");
//			}
		}
		
//		System.out.println("As "+exp.substring(0,1)+" is NOT a <digit>. "+exp+" is NOT a <number> -> <digit><number> ");		
		return new String[] {"f",""};
	}
	
	
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	public int checkDigit(String exp) {
		//Case 1: checking	<digit> -> '0', '1', '2' ...'9'
		if(exp.isBlank()) {return -1;}
		String val = "0123456789";
		return val.indexOf(exp.strip());
	}
}
