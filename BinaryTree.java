
public class BinarySearchTree {

    class Node {
        int key;
        String value;
        Node left, right;

        public Node(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public Node min() {
            if (left == null) {
                return this;
            } else {
                return left.min();
            }
        }
    }

    Node root;

    public BinarySearchTree() {
        root = null;
    }

    // Find
    public String find(int key) {

        // First find the node
        Node node = find(root, key);

        // Then return the value
        return node == null ? null : node.value;
    }

    private Node find(Node node, int key) {
        if (node == null || node.key == key) {
            return node;
        } else if (key < node.key) {
            return find(node.left, key);
        } else if (key > node.key) {
            return find(node.right, key);
        }
        return null;

        // Note: Duplicate keys aren't allowed (so we don't need to check for that)
    }

    // Insert
    public void insert(int key, String value) {
        root = insertItem(root, key, value);
    }

    public Node insertItem(Node node, int key, String value) {

        // If null - set it here. We are done.
        if (node == null) {
            node = new Node(key, value);;
            return node;
        }

        // Else
        // Walk until you find a node
        // that is null and set it there
        if (key < node.key) {
            node.left = insertItem(node.left, key, value);
        } if (key > node.key) {
            node.right = insertItem(node.right, key, value);
        }

        // If we get here we have have hit the bottom our or tree with a duplicate.
        // Since duplicates are not allowed in BSTs, simply ignore the duplicate,
        // and return our fully constructed tree. We are done!
        return node;
    }

    // Delete: Three cases
    // 1. No child
    // 2. One child
    // 3. Two children


    // Case 1: No child - simply remove from tree by nulling it.
    //
    // Case 2: One child - copy the child to the node to be deleted and delete the child

    // Case 3: Two children - re-gig the tree to turn into a Case 1 or a Case 2

   
    // Step 1: Create a minvalue function
    public int findMinKey() {
        return findMin(root).key;
    }

    public Node findMin(Node node) {
        return node.min();
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    public Node delete(Node node, int key) {
        if (node == null) {
            return null;
        } else if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else { // Woohoo! Found you. This is the node we want to delete.

            // Case 1: No child
            if (node.left == null && node.right == null) {
                node = null;
            }

            // Case 2: One child
            else if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            }

            // Case 3: Two children
            else {
                // Find the minimum node on the right (could also max the left...)
                Node minRight = findMin(node.right);

                // Duplicate it by copying its values here
                node.key = minRight.key;
                node.value = minRight.value;

                // Now go ahead and delete the node we just duplicated (same key)
                node.right = delete(node.right, node.key);
            }
        }

        return node;
    }
