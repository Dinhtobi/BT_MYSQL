package Practice.Collections;

import java.util.Random;

public class SinglyLinkedList {

	
	static class Node{
		int data;
		
		Node next ;
		
		Node(int d){
			this.data =d ;
			this.next = null ;
		}
	}
	
	public void display() {
		Node n = head ;
		while(n!=null) {
			System.out.println("Data: " + n.data );
			n = n.next;
		}
	}
	public Node head = null; 
	public Node tail  =null ;
	public void addNode(int data) {
		Node newNode = new Node(data);
		if(head == null ) {
			head = newNode;
			tail = newNode;
		}else {
			tail.next = newNode;	
			tail = newNode;
			
		}
	}
	
	public int countNode() {
		Node n = head ;
		int count = 0 ;
		while(n != null) {
			count += 1;
			n = n.next;
		}
		return count;
	}
	
	public void deleteFromStart() {
		if(head == null ) {
			System.out.println("Danh sách trống");
		}else if(head.next == null) {
			head = null;
			tail = null;
		}else {
			head = head.next;
		}
	}
	
	public int maxNode() {
		Node n = head; 
		int max = n.data;
		while(n !=null) {
			if(max < n.data) {
				max = n.data;
			}
			n = n.next;
		}
		return max; 
	}
	public static void main(String[] args) {
		SinglyLinkedList list = new SinglyLinkedList();
		list.display();
		list.addNode(500);
		list.addNode(400);

		list.addNode(450);
		Random ran = new Random();
		for(int i = 0 ; i< 10 ; i++) {
			int ranint= ran.nextInt(1000);
			list.addNode(ranint);
		}
		System.out.println("Số Node trong danh sách :" + list.countNode());
		
		list.display();
		System.out.println("Xoá node Node trong danh sách ");
		list.deleteFromStart();
		System.out.println("Gía trị lớn nhất trong danh sách: " + list.maxNode());
		
		
	}
}
