import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/*
 *
 *         45
 *        /  \
 *      10    90
 *     / \     \
 *    7  12    50
 */

class BinaryTree {
    // DECLARING THE NODE CLASS
    class Node {
        int key;
        Node left, right;

        public Node(int data) {
            key = data;
            left = right = null;
        }
    }

    Node root;

    BinaryTree() {
        root = null;
    }



    void deleteKey(int key) {
        root = delete_Recursive(root, key);
    }

    //Function to delete the node in binary tree
    Node delete_Recursive(Node root, int key) {
        if (root == null) return root;

        if (key < root.key)
            root.left = delete_Recursive(root.left, key);
        else if (key > root.key)
            root.right = delete_Recursive(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.key = minValue(root.right);
            root.right = delete_Recursive(root.right, root.key);
        }
        return root;
    }

    int minValue(Node root) {
        int minval = root.key;
        while (root.left != null) {
            minval = root.left.key;
            root = root.left;
        }
        return minval;
    }

    void insert(int key) {
        root = insert_Recursive(root, key);
    }

    //Function to insert the node in binary tree
    Node insert_Recursive(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.key)
            root.left = insert_Recursive(root.left, key);
        else if (key > root.key)
            root.right = insert_Recursive(root.right, key);

        return root;
    }

    //IN-ORDER TRAVERSAL
    void inorder() {
        inorder_Recursive(root);
        System.out.println();
    }

    //Function to do in order traversal
    void inorder_Recursive(Node root) {
        if (root != null) {
            inorder_Recursive(root.left);
            System.out.print(root.key + " ");
            inorder_Recursive(root.right);
        }
    }
    //PRE-ORDER TRAVERSAL
    void preorder() {
        preorder_Recursive(root);
        System.out.println();
    }
    //Function to do pre order traversal
    void preorder_Recursive(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preorder_Recursive(root.left);
            preorder_Recursive(root.right);
        }
    }
    //POST-ORDER TRAVERSAL
    void postorder() {
        postorder_Recursive(root);
        System.out.println();
    }
    //Function to do post order traversal
    void postorder_Recursive(Node root) {
        if (root != null) {
            postorder_Recursive(root.left);
            postorder_Recursive(root.right);
            System.out.print(root.key + " ");
        }
    }

    boolean search(int key) {
        root = search_Recursive(root, key);
        return root != null;
    }

    //Function to search a node in binary tree
    Node search_Recursive(Node root, int key) {
        if (root == null || root.key == key)
            return root;

        if (root.key > key)
            return search_Recursive(root.left, key);

        return search_Recursive(root.right, key);
    }

    //DFS USING STACK
    void dfs() {
        System.out.println("DFS traversal:");
        dfs_Stack(root);
        System.out.println();
    }

    //DFS inplementation using stack
    void dfs_Stack(Node root) {
        if (root == null) return;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.print(current.key + " ");

            if (current.right != null) stack.push(current.right);
            if (current.left != null) stack.push(current.left);
        }
    }

    //BFS USING QUEUE
    void bfs() {
        System.out.println("BFS traversal:");
        bfs_Queue(root);
        System.out.println();
    }
    void bfs_Queue(Node root) {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        //BFS inplementation using Queue
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.key + " ");

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }
}

class Main {
    public static void main(String[] args) {
        BinaryTree bst = new BinaryTree();

        bst.insert(45);
        bst.insert(10);
        bst.insert(7);
        bst.insert(12);
        bst.insert(90);
        bst.insert(50);

        System.out.println("In-order traversal(Left-root-right):");
        bst.inorder();
        System.out.println("Pre-order traversal(root-Left-right):");
        bst.preorder();
        System.out.println("Post-order traversal(Left-right-root):");
        bst.postorder();

        bst.dfs();
        bst.bfs();

        System.out.println("Deleting 12:");
        bst.deleteKey(12);
        bst.inorder();

        System.out.println("Deleting 90:");
        bst.deleteKey(90);
        bst.inorder();

        System.out.println("Deleting 45:");
        bst.deleteKey(45);
        bst.inorder();

        System.out.println("Key 50 found in BST: " + bst.search(50));
        System.out.println("Key 12 found in BST: " + bst.search(12));
    }
}
