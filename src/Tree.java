public class Tree<K extends Comparable,D> {

    private Node<K,D> root;

    public Tree() { root = null; }

    public Tree(K[] keys, D[] data) {
        root = buildTree(keys, data, 0, keys.length-1);
    }

    private Node<K,D> buildTree(K[] keys, D[] data, int lo, int hi) {
        if(lo > hi)
            return null;
        int m = (hi - lo) / 2 + lo;
        return new Node(keys[m], data[m],
                buildTree(keys, data, lo, m-1),
                buildTree(keys, data, m+1, hi));
    }

    //Standard Database Operators.

    private D find(K key, Node<K,D> x) {
        if(x == null)
            return null;
        int c = key.compareTo(x.key);
        if(c == 0)
            return x.data;
        else if(c < 0)
            return find(key, x.left);
        else // c > 0
            return find(key, x.right);
    }

    public D find(K key) {
        return find(key, root);
    }

    private Node<K,D> add(K key, D data, Node<K,D> root) {
        // returns the tree with the added record.
        if(root == null)
            return new Node<K,D>(key,data);
        int c = key.compareTo(root.key);
//        if(c == 0) {
//            System.err.println("Error: duplicate key: "+key);
//            System.exit(1);
//            return null;
//        }
        if(c <= 0) {
            root.left =  add(key, data, root.left);
            return root;
        }
        else { // c > 0
            root.right = add(key, data, root.right);
            return root;
        }
    }

    public void add(K key, D data) {
        root = add(key, data, root);
    }

    private void modify(K key, D data, Node<K,D> root) {
        if(root == null) {
            System.err.println("Error: key not found: "+key);
            System.exit(1);
        }
        int c = key.compareTo(root.key);
        if(c == 0)
            root.data = data;
        else if(c < 0)
            modify(key, data, root.left);
        else // c > 0
            modify(key, data, root.right);
    }

    public void modify(K key, D data) {
        modify(key, data, root);
    }

    private Node<K,D> findLeftmost(Node<K,D> root) {
        // Assumes root != null.
        return root.left == null
                ? root
                : findLeftmost(root.left);
    }

    private Node<K,D> removeLeftmost(Node<K,D> root) {
        if(root.left == null)
            return root.right;
        else {
            root.left = removeLeftmost(root.left);
            return root;
        }
    }

    private Node<K,D> delete(K key, Node<K,D> root) {
        if(root == null) {
            System.err.println("Error: key not found");
            System.exit(1);
            return null;
        }
        int c = key.compareTo(root.key);
        if(c == 0) {
            if(root.left == null)
                return root.right;
            else if(root.right == null)
                return root.left;
            else {
                Node<K,D> t = root;
                root = findLeftmost(root.right);
                root.right = removeLeftmost(t.right);
                root.left = t.left;
                return root;
            }
        }
        else if(c < 0) {
            root.left = delete(key, root.left);
            return root;
        }
        else { // c > 0
            root.right = delete(key, root.right);
            return root;
        }
    }

    public void delete(K key) {
        root = delete(key, root);
    }

//    private String toString(Node<K,D> root) {
//        if(root == null)
//            return "";
//        return toString(root.left)
//                + "(" + root.key + "," + root.data + ")"
//                + toString(root.right);
//    }
//    public String toString() {
//        return toString(root);
//    }

    private String toString(Node<K, D> root) {
        if(root == null)
            return "";
        return toString(root.left) + root.key + " " + root.data + toString(root.right);
    }

    public String toString() {
        return toString(root);
    }
}