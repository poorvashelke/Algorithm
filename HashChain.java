
/*************************************************************************
 *
 *  Problem: Testing running times of insertion and find for Hash Table
 *  Description: This Problem measures running time for Insertion and Search for Hash Table
 *				
 *  Input: number of entries
 *  Output: running times to to insert and find key value in Hash Table
 *
 *  Visible data fields:
 * none.
 *
 * Visible methods:
 * public void displayTable()      --> Print Hash table 
 * public int hashFunc(int key)    --> Hash function for deciding number of keys 
 * public void insert(Link theLink) --> insert into hash table   
 * public void delete(int key)      --> Delete from hash table 
 *
 *   Remarks
 *   -------
 *  Chart of running times observed in nanoseconds:
 * 
 *  Size     |    AVL Tree Insertion   |    AVL Tree Find
 *  ------------------------------------------------------------
 *  1000      |        4040054          |    1062294	 
 *  ------------------------------------------------------------
 *  10000     |        10689230         |    1105488		
 *  ------------------------------------------------------------
 *  100000    |        47968747         |    2334995	
 *  ------------------------------------------------------------
 *  1000000   |        273546348        |    2170775		
 *  ------------------------------------------------------------
 *  10000000  |        8211304197     	|    2216107	
 *  ------------------------------------------------------------
 *
 *
 *   Size     |    Hash Table Insertion |    Hash Table Find
 *  ------------------------------------------------------------
 *  1000      |        823235           |    231789	 
 *  ------------------------------------------------------------
 *  10000     |        2278544          |    461012		
 *  ------------------------------------------------------------
 *  100000    |        9525154          |    526870	
 *  ------------------------------------------------------------
 *  1000000   |        86503431         |    504632		
 *  ------------------------------------------------------------
 *  10000000  |        4003395151     	|    420812	
 *  ------------------------------------------------------------
 *

 * Observation:
 * AVL tree:
 * Insertion :
 * We are inserting values in consecutive order. AVL tree's balances itself to maintain the right height and balance of tree. 
 * This balancing causes O(log N) the time to insert single item. That means for N items time required is O( N log N ).
 * we can see in this table that, when 1000 items are inserted time taken is 4040054, For 10000 items time required is 10689230,
 * As we increases the items by N*10, time increases with N plus some constant value for balancing tree that is : Log N.
 * So for 10000000 items time taken is 8211304197. That means it is increasing with O(N log N).
 *
 * Find :
 * When it comes to search, Tree is balanced and so searching do not take linear time, but some constant time to go down to the branches. 
 * As tree grows exponentially, searching time is O(Log N), ie comparatively little. Time taken to search item which is not present is also
 * O(Log N), as when item is not present, it goes down till the leaf nodes.
 * From the above table, when the size is 1000, Time taken is 1062294, 100000 number of items, time taken is 1105488, For the larger size such as 10000000  
 * time taken is 2216107. For 1000 and for 10000000 node size, Time doesn't vary much but varies in constant speed , that proves it is O(Log N). 
 * 
 * Hash Table :
 * Insertion :
 * We are inserting values in consecutive order. Hash table with separate chaining, creates separate chain for each key when value for single key is 
 * increases. Typical single insertion for hash table takes O(1) time as it directly matches the key value pair. Here the load factor is .75.
 * So when th input size increases it does not take exact O(1) but increases in much lesser amount. For 1000 size time is 823235, For 10000 size the time is * 2278544 but for 10000000 size, time required is 4003395151. That means it gradually increases as increase in N. SO for N item it is O(N). 
 * 
 * Find :
 * When it comes to search, For each first index key element in hash table, time required is O(1) but if there are M elements in single key, then to find 
 * other values time requires is O(M). Load factor .75 , so the value of M does not vary drastically.  
 * From the above table, when the size is 1000, Time taken is 231789, 100000 number of items, time taken is 461012, For the larger size such as 10000000  
 * time taken is 420812. As the normal search time is O(1) to max O(M) we can see there is little difference in searching times despite the number of items  * in that hash table.
 *
 * Comparisons with measurements in AVL and HASH table; 
 * If we compare the insertion time for AVL for size 1000, time is 4040054 and for hash table time is 823235,
 * For some greater input 10000, AVL time is 10689230 and hash table's time is 2278544.
 * We can say that hash table takes comparatively lesser time for insertion than AVL tree as AVL tree requires balacning for each input so for N input it
 * is O(N Log N) where as Hash table requires simply O(N) time and plus some more but less than (log N) for inserting N times.
 * For Finding the item when size is 1000, AVL tree takes 1062294 whereas hash table takes 231789, 
 * For larger input such as 10000000,  AVL tree takes 2216107 whereas hash table takes 420812.
 * In this case also, for the item which is not present, AVL tree goes to the leaf node, as AVL is balanced it takes O(Log N) time.
 * For the Hash table time varies from O(1)to O(M) for M elements for each key. 
 * But because of load factor, searching time is lower.
 * NOTE: 
 * We can not directly compare the timings of both AVL and Hash as implementation techniques of both algorithms is different and also time varies based on  
 * the other processes running at the time of execution.
 *************************************************************************/

import java.io.IOException;

class Link {
  private int data;
  public Link next;

  public Link(int d) {
    data = d;
  }

  public int getKey() {
    return data;
  }

  public void displayLink() {
    System.out.print(data + " L");
  }
}

class SortedList {
  private Link first;
  public SortedList() {
    first = null;
  }

  public void insert(Link theLink){
    int key = theLink.getKey();
    Link previous = null; // start at first
    Link current = first;
    // until end of list,
        //or current bigger than key,
    while (current != null && key > current.getKey()) { 
      previous = current;
      current = current.next; // go to next item
    }
    if (previous == null) // if beginning of list,
      first = theLink; 
    else
      // not at beginning,
      previous.next = theLink; 
    theLink.next = current; 
  }

  public void delete(int key){ 
    Link previous = null; 
    Link current = first;

    while (current != null && key != current.getKey()) { 
      previous = current;
      current = current.next; 
    }
    // disconnect link
    if (previous == null) //   if beginning of list delete first link
      first = first.next;       
    else
      //   not at beginning
      previous.next = current.next; //delete current link
  }

  public Link find(int key) {
    Link current = first; 
    while (current != null && current.getKey() <= key) { // or key too small,
      if (current.getKey() == key) // found, return link
        return current;  
      current = current.next; // go to next item
    }
    return null; // cannot find it
  }

  public void displayList() {
    System.out.print("List: ");
    Link current = first;
    while (current != null){
      current.displayLink(); 
      current = current.next;
    }
    System.out.println("");
  }
}

public class HashChain {
  private SortedList[] hashArray; 

  private int arraySize;

  public HashChain(int size) {
    arraySize = size;
    hashArray = new SortedList[arraySize];
    for (int i = 0; i < arraySize; i++)
      hashArray[i] = new SortedList(); 
  }

  public void displayTable() {
    for (int j = 0; j < arraySize; j++) {
      System.out.print(j + ". "); 
      hashArray[j].displayList(); 
    }
  }

  public int hashFunc(int key) {
    return key % arraySize;
  }

  public void insert(Link theLink) {
    int key = theLink.getKey();
    int hashVal = hashFunc(key); 
    hashArray[hashVal].insert(theLink); 
  }

  public void delete(int key) {
    int hashVal = hashFunc(key); // hash the key
    hashArray[hashVal].delete(key); 
  }

  public Link find(int key) {
    int hashVal = hashFunc(key); // hash the key
    Link theLink = hashArray[hashVal].find(key); // get link
    return theLink;
  }

  public static void main(String[] args) throws IOException {
    int aKey;
    Link dataItem;
    int size, initSize, keysPerCell = 100;
    size = 7500000;
    initSize = 10000000;
    HashChain hashTable = new HashChain(size);

	System.out.println("\nHashTable : ");
	long startTime = System.nanoTime();
    for (int i = 0; i < initSize; i++)
	{
      aKey = i;
      dataItem = new Link(aKey);
      hashTable.insert(dataItem);
    }

	System.out.println("\nTime Taken by Insert " + (System.nanoTime() - startTime) + " nanoseconds.");
	
	startTime = System.nanoTime();
    aKey = initSize;
    dataItem = hashTable.find(aKey);
    if (dataItem != null)
      System.out.println("Found " + aKey);
    else
      System.out.println("Could not find " + aKey);
	System.out.println("\nTime Taken by Find " + (System.nanoTime() - startTime) + " nanoseconds.");
  }

}
