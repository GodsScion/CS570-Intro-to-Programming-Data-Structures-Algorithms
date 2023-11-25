package hw2_Golla;



public class Complexity {
	
	public static void method1(int n) { // O(n^2)
		System.out.println("\nStarting method 1 \n"); 
		int c=1;
		for(int i=1; i<=n; i++) {
			 for(int j=1; j<=n; j++) {
				 System.out.println("This is loop number "+j+" inside subloop "+i+" and counter is "+c);
				 c++;
			 }
		 }
		System.out.println("\nEnding method 1 \n-----------------------------------------------------------------------------");
	 }
	
	 public static void method2(int n) { // O(n^3)
		System.out.println("\nStarting method 2 \n");
		int c = 0;
		for(int i=1; i<=n; i++) {
			 for(int j=1; j<=n; j++) {
				 for(int k=1; k<=n; k++) {
					 c++;
					 System.out.println("Counter is "+c+" , for the loop number "+k+" inside subloop "+j+" inside main loop "+i); 
				 }
			 }
		 }
		System.out.println("\nEnding method 2 \n-----------------------------------------------------------------------------");
	 }
	
	 public static void method3(int n) { // O(logn)
			System.out.println("\nStarting method 3 \n"); 
			int c = 1;
			for(int i=1; i<=n; i = i*2) {
				System.out.println("Counter is "+c+" and Currently i is "+i);
				c++;
			}
			System.out.println("\nEnding method 3 \n-----------------------------------------------------------------------------");
	 }

	 public static void method4(int n) { // O(nlogn)
			System.out.println("\nStarting method 4 \n"); 
			int c = 1;
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j = j*2) {
					System.out.println("Counter is "+c+" and Currently i is "+i+" and j is "+j);
					c++;
				}
			}
			System.out.println("\nEnding method 4 \n-----------------------------------------------------------------------------");
	 }
	 
	 public static void method5(int n) { // O(loglogn)
			System.out.println("\nStarting method 5 \n"); 
			int c=1;
			for(int i=n; i>1; i = (int) Math.sqrt(i)) {
				System.out.println("Counter is "+c+" and Currently i is "+i);
				c++;
			}
			System.out.println("\nEnding method 5 \n-----------------------------------------------------------------------------");
	 }
	 	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Complexity c = new Complexity();
		int n = 10;
		c.method1(n);
		c.method2(n);
		c.method3(n);
		c.method4(n);
		c.method5(n);
	}

}
