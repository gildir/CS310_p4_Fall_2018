//
// Task 1. Set<T> class (10%)
// This is used in DisjointSets<T> to store actual data in the same sets
//

//You cannot import additonal items
import java.util.AbstractCollection;
import java.util.Iterator;
//You cannot import additonal items

//
//Hint: if you think really hard, you will realize this class Set<T> is in fact just a list
//      because DisjointSets<T> ensures that all values stored in Set<T> must be unique, 
//      but should it be dynamic array list or linked list??
//

public class Set<T> extends AbstractCollection<T> {
	//O(1)
	public Set() {
		
	}
	
	//O(1)
	public boolean add(T item) {
		return false; //TODO: remove and replace this line
	}
	
	//O(1)
	public boolean addAll(Set<T> other) {
		return false; //TODO: remove and replace this line
	}
	
	//O(1)
	public void clear() {
		//TODO: remove and replace this line
	}
	
	//O(1)
	public int size() {
		return -1;
	}
	
	//O(1) for next() and hasNext()
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			//O(1)
			public T next() {
				return null; //TODO: remove and replace this line
			}
			
			//O(1)
			public boolean hasNext() {
				return false; //TODO: remove and replace this line
			}
		};
	}
	
	//main method just for your testing
	//edit as much as you want
	public static void main(String[] args) {
		
	}
}
