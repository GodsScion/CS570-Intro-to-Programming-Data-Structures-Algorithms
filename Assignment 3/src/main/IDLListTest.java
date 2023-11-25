package main;

public class IDLListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			IDLList<Integer> l = new IDLList<Integer>();
			
			l.add(1);
			System.out.println(l.toString()); //1 
			
			l.add(2);
			System.out.println(l.toString()); //2 1 
			
			l.add(0,3);
			System.out.println(l.toString()); //3 2 1 
			
			l.add(3,4);
			System.out.println(l.toString()); //3 2 1 4 
			
			l.add(2,5);
			System.out.println(l.toString()); //3 2 5 1 4 
			
			l.add(9,6); //Index must be greater than or equal to 0 and less than or equal to size of ArrayList = 5
			
			l.append(7);
			System.out.println(l.toString()); //3 2 5 1 4 7 
			
			l.get(6); //Index must be greater than or equal to 0 and less than size of ArrayList = 6
			
			System.out.println(l.get(2)); //5
			
			System.out.println(l.size()); //6
			
			System.out.println(l.getHead()); //3
			
			System.out.println(l.toString()); //3 2 5 1 4 7 
			
			System.out.println(l.remove()); //3
			System.out.println(l.toString()); //2 5 1 4 7 
			
			System.out.println(l.getLast()); //7
			
			System.out.println(l.removeLast()); //7
			System.out.println(l.toString()); //2 5 1 4 
			
			System.out.println(l.removeAt(0)); //2
			System.out.println(l.toString()); //5 1 4 
			
			System.out.println(l.removeAt(1)); //1
			System.out.println(l.toString()); //5 4 
			
			System.out.println(l.removeAt(1)); //4
			System.out.println(l.toString()); //5 
			
			l.append(6);
			l.append(4);
			l.append(3);
			l.append(6);
			l.append(5);
			System.out.println(l.toString()); //5 6 4 3 6 5 
			
			l.removeAt(6);//Index must be greater than or equal to 0 and less than size of ArrayList = 6
			System.out.println(l.toString()); //5 6 4 3 6 5 

			System.out.println(l.remove(100)); //false
			System.out.println(l.remove(6)); //true
			System.out.println(l.toString()); //5 4 3 6 5 
			
			
		
		} 
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
	}

}
