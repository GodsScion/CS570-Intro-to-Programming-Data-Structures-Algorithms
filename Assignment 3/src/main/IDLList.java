package main;

import java.util.ArrayList;



class Node<E> {								//Creating Node class as per given specifications
	public E data;
	public Node<E> next;
	public Node<E> prev;
	
	Node(E elem){
		data = elem;
		next = null;
		prev = null;
	}
	
	Node(E elem, Node<E> prev, Node<E> next){
		data = elem;
		this.prev = prev;
		this.next = next;
	}
}

public class IDLList<E> { 					//Creating IDLList class as per given specifications
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	public IDLList() {
		head = null;
		tail = null;
		size = 0;
		indices = new ArrayList<Node<E>>();
	}
	
	public boolean add(int index, E elem){ 
		if(index >= 0 && index <= size) {	//Index validation
			if(index == 0) {
				return add(elem);			//If adding at first
			}
			if(index == size) {				//If adding at last
				return append(elem);
			}
			Node<E> tempNode = new Node<E>(elem,indices.get(index-1),indices.get(index));
			indices.get(index-1).next = tempNode;
			indices.get(index).prev = tempNode;
			indices.add(index,tempNode);
			size();
			return true;
		}
		System.out.println("Index must be greater than or equal to 0 and less than or equal to size of ArrayList = "+size);
		return false;						//If index is invalid
	}
	
	public boolean add(E elem){ 
		if(head == null) {					//Checking if empty or not
			head = new Node<E>(elem);
			tail = head;
			indices.add(head);
		}
		else {								//If not empty
			Node<E> tempNode = new Node<E>(elem,null,head);
			head.prev = tempNode;
			head = tempNode;
			indices.add(0, head);
		}
		size();
		return true;
	}	

	public boolean append(E elem){ 
		if(head == null) {					//Checking if empty or not
			head = new Node<E>(elem);
			tail = head;
			indices.add(head);
		}
		else {								//If not empty
			Node<E> tempNode = new Node<E>(elem,tail,null);
			tail.next = tempNode;
			tail = tempNode;
			indices.add(tail);
		}
		size();
		return true;
	}	
	
	public E get(int index){ 
		if(!(index >= 0 && index < size)) {	//index validation
			System.out.println("Index must be greater than or equal to 0 and less than size of ArrayList = "+size);
			return null;
		}
		return indices.get(index).data;
	}	
	
	public E getHead(){ 					//Returns the head if exists or null
		return head.data;
	}
	
	public E getLast(){ 					//Returns the tail if exists or null
		return tail.data;
	}
	
	public int size(){ 						//Updates the size and returns
		size = indices.size();
		return size;
	}
	
	public E remove(){  
		if(size == 1 || head == tail) { 	//Case where only 1 element is there
			E output = head.data;
			head = null;
			tail = null;
			indices.remove(0);
			size = indices.size();
			return output;
		}
		if(size == 0 || head == null) { 	//Case where DLL is empty
			return null;
		}
		
			E output = head.data; 			//General case
			indices.remove(0);
			head = indices.get(0);
			head.prev = null;
			indices.set(0, head);
			size = indices.size();
		return output;
	}
	
	public E removeLast(){ 
		if(size == 1 || head == tail) { 	//Case where only 1 element is there
			E output = head.data;
			head = null;
			tail = null;
			indices.remove(0);
			size = indices.size();
			return output;
		}
		if(size == 0 || head == null) { 	//Case where DLL is empty
			return null;
		}
		
			E output = tail.data; 			//General case
			indices.remove(size()-1); 		//Since index starts with 0
			tail = indices.get(size()-1);
			tail.next = null;
			indices.set(size()-1, tail);
			// size = indices.size(); 		//won't be needed anymore
		return output;
	}
	
	public E removeAt(int index){ 
		if(!(index >= 0 && index < size)) {	//Validation for index
			System.out.println("Index must be greater than or equal to 0 and less than size of ArrayList = "+size);
			return null;
		}
		
		if(index == 0) {					//Removing first element
			return remove();
		}
		
		if(index == (size-1)) {				//Removing last element
			return removeLast();
		}
											//General Case
			E output = indices.get(index).data;
			indices.remove(index);
			indices.get(index - 1).next = indices.get(index);
			indices.get(index).prev = indices.get(index-1);
			size = indices.size();
		return output;
	}
	
	public boolean remove(E elem){ 
			for(int i=0; i<size(); i++) {	//Removes the first occurrence and stops
				if(indices.get(i).data == elem) {
					removeAt(i);
					return true;
				}
			}
		return false;
	}
	
	public String toString(){ 				//Gives output with spaces in between each element
		String output = "";
		for(int i=0; i<size(); i++) {
			output = output + indices.get(i).data.toString() + " ";
		}
		return output;
	}
	
}
