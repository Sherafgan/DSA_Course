package Practice.Trees.MyBinarySearchTree;

/**
 * @author Sherafgan Kandov
 */
public interface ITree {

    public void insert(int key);

    public Node find(int key);

    public boolean delete(int key);

    public int size();
}
