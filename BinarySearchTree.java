
/*************************************************************************
 *
 *  Problem: Testing running times of Skewed Tree and Balanced Tree
 *  Description: This Problem measures running time of searches for skewed tree and Balanced tree.
 *				
 *  Input: Number of Nodes
 *  Output: running times to find node in tree
 *
 *  Visible data fields:
 * none.
 *
 * Visible methods:
 * void insert( x )       --> Insert x
 * void remove( x )       --> Remove x
 * boolean contains( x )  --> Return true if x is present
 * Comparable findMin( )  --> Return smallest item
 * Comparable findMax( )  --> Return largest item
 * boolean isEmpty( )     --> Return true if empty; else false
 * void makeEmpty( )      --> Remove all items
 * void printTree( )      --> Print tree in sorted order *
 *
 *   Remarks
 *   -------
 *
 *   Chart of running times observed in nanoseconds:
 *
 *   Size     |    Skewed Tree		    |    Balanced Tree	
 *  ------------------------------------------------------------
 *  1000      |        1019101          |    339558	 
 *  ------------------------------------------------------------
 *  3000      |        2417531          |   1062721		
 *  ------------------------------------------------------------
 *  5000      |        2481252          |   1025943	
 *  ------------------------------------------------------------
 *  8000      |        3099641          |    896364		
 *  ------------------------------------------------------------
 *  10000     |        3027794     	    |    920740	
 *  ------------------------------------------------------------
 *  100000	  |			-				|	1358231
 *  ------------------------------------------------------------
 *  1000000	  |			-				|	1490803
 *  ------------------------------------------------------------
 *
 * Observation:
 * Skewed BST:
 * As we are giving input in increasing order all the sub nodes of the tree will be at one side right. 
 * Time taken by to perform search operation is linear ie. O(N). 
 * In this case we have considered elements starting from 1 to N, and according to N, running time exceeds.
 * For example, for 1000 numbers it takes 1019101 nanoseconds time,
 * for 3000 numbers it almost takes triple time ie. 2417531 and so on...
 * for 10000 numbers, as N increased to N*10, so does the time of search, means it increases in order of N.
 * Balanced BST:
 * As input is chosen randomly a balanced tree is form so time taken by to perform tree operations on it will be half as it will traverse to either left or   * right and root.
 * So as working of BST, It takes O(log(n)) times to search element.
 * In this case we have created random numbers within range.
 * we increased value of N from 1000 to 1000000,
 * But time required for 1000 to 100000 to 1000000 doesn't vary much.
 * 1000 numbers its 339558 and 1000000 numbers its 1490803 which is almost constant with slight change of nanoseconds
 * time measurements proves that Balance tree takes O(log(n)) time which is considerably low than O(N) that skewed tree takes.  
 *************************************************************************/

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
import java.util.Random;
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<AnyType>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;


        // Test program
    public static void main( String [ ] args )
    {
        BinarySearchTree<Integer> t = new BinarySearchTree<Integer>( );
        final int NUMS = 1000000;
        final int GAP  = 1;
		int rand;

        System.out.println( "Checking... (no more output means success)" );

		//Skewed Tree
        for( int i = 1; i <= NUMS; i++ )	//inserting elements from 1 to n to create skewed tree
            t.insert( i );
	
		long startTime = System.nanoTime();
	
		System.out.println("\nSkewed Tree: ");
		
		if( !t.contains( NUMS+1 ) )
			 System.out.println( "Element Not Found!" );
		
		System.out.println("\nThe time taken by it is " + (System.nanoTime() - startTime) + " nanoseconds.");
		
		for( int i = 1; i <= NUMS; i++ )
        	    t.remove( i );
		
		//Balanced Tree
		Random rand1 = new Random();	// generating random numbers within range to create balance tree
		for( int i = 0; i < NUMS ; i++)	
		{
			rand = rand1.nextInt(Integer.MAX_VALUE) % NUMS + 1;
            t.insert(rand);
		}

		startTime = System.nanoTime();
	
		System.out.println("\nBalanced Tree: ");
		
		
		if( !t.contains( NUMS+1 ) )
			 System.out.println( "Element Not Found!" );
		
		System.out.println("\nThe time taken by it is " + (System.nanoTime() - startTime) + " nanoseconds.");


    }
}
