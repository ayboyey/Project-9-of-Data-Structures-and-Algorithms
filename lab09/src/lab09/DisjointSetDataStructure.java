package lab09;

public interface DisjointSetDataStructure {
	 // in the constructor there has to be the number elements N
	// the elements are integer values from 0 to N-1
	        void makeSet(int item);
	        int findSet(int item);
	        boolean union(int itemA, int itemB);
	    }