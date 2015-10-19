package Practice.Trees.MyBinarySearchTree;

/**
 * @author Sherafgan Kandov
 */
public interface ITree {

    public void add(int key);

    public int find(int key);

    public void delete(int key);

    public int size();
}
