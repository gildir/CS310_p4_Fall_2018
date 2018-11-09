// Task 1. DisjointSets class (15%)

// Hint: you can use the DisjointSets from your textbook
// but it must be changed also union the the actual data together

import java.util.ArrayList;

// disjoint sets class, using union by size and path compression.
public class DisjointSets<T>
{
	//Data - You must use these variables for credit...
	//we will be "breaking in" to peak at them for testing
	//DO NOT change their names, types, etc.
	private int[] s; //the sets
	private ArrayList<Set<T>> sets; //the actual data for the sets

	//Constructor
	public DisjointSets(ArrayList<T> data) {
		//your code here
	}

	//Compute the union of two sets using rank union by size
	//returns the new root of the unioned set
	//if two sets are equal, root1 is the new root
	//Must be O(1) time (do not perform find())
	//throw IllegalArgumentException() if non-roots provided
	public int union(int root1, int root2) {
		return -1; //TODO: remove and replace this line
	}

	//Find and return the root
	//Must implement path compression
	public int find(int x) {
		return -1; //TODO: remove and replace this line
	}

	//Get all the data in the same set
	//Must be O(1) time
	public Set<T> get(int root) {
		return null; //TODO: remove and replace this line
	}
	
	//main method just for your testing
	//edit as much as you want
	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<>();
		for(int i = 0; i < 10; i++)
			arr.add(i);
		
		DisjointSets<Integer> ds = new DisjointSets<>(arr);
		System.out.println(ds.find(0)); //should be 0
		System.out.println(ds.find(1)); //should be 1
		System.out.println(ds.union(0, 1)); //should be 0
		System.out.println(ds.find(0)); //should be 0
		System.out.println(ds.find(1)); //should be 0
		System.out.println("-----");
		System.out.println(ds.find(0)); //should be 0
		System.out.println(ds.find(2)); //should be 2
		System.out.println(ds.union(0, 2)); //should be 0
		System.out.println(ds.find(0)); //should be 0
		System.out.println(ds.find(2)); //should be 0
		System.out.println("-----");
		//Note: AbstractCollection provides toString() method using the iterator
		//see: https://docs.oracle.com/javase/8/docs/api/java/util/AbstractCollection.html#toString--
		//so your iterator in Set needs to work for this to print out correctly
		System.out.println(ds.get(0)); //should be [0, 1, 2]
		System.out.println(ds.get(1)); //should be []
		System.out.println(ds.get(3)); //should be [3]
	}
}
