package Practice.Trees;

/**
 * @author Sherafgan Kandov
 *         27.10.2015
 */
public class Main {
    public static void main(String[] args) {
        MySimpleAVLTree mySimpleAVLTree = new MySimpleAVLTree();
        mySimpleAVLTree.insert(1);
        mySimpleAVLTree.insert(2);
        mySimpleAVLTree.insert(3);
        mySimpleAVLTree.insert(4);
        System.out.println(mySimpleAVLTree.size());
        mySimpleAVLTree.delete(1);
    }
}
