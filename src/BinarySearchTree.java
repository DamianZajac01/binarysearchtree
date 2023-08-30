public class BinarySearchTree<T extends Comparable<T>> {

    class Node {
        T data;
        Node left;
        Node right;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node root;

    public void insert(T data) {
        root = insert(data, root);
    }

    Node insert(T data, Node node) {
        if (node == null) {
            node = new Node(data);
        }

        else if (data.compareTo(node.data) <= 0) {
            node.left = insert(data, node.left);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insert(data, node.right);
        }

        return node;
    }

    public Node search(T data) {
        return search(data, root);
    }

    private Node search(T data, Node node) {
        if (node == null) {
            return null;
        }

        if (data == node.data) {
            return node;
        } else if (data.compareTo(node.data) < 0) {
            return search(data, node.left);
        } else {
            return search(data, node.right);
        }
    }

    public void delete(T data) {
        root = delete(data, root);
    }

    Node delete(T data, Node node) {
        if (node == null) {
            return null;
        }

        if (data.compareTo(node.data) < 0) {
            node.left = delete(data, node.left);
        } else if (data.compareTo(node.data) > 0) {
            node.right = delete(data, node.right);
        }

        else if (node.left == null && node.right == null) {
            node = null;
        }

        else if (node.left == null) {
            node = node.right;
        } else if (node.right == null) {
            node = node.left;
        } else {
            deleteNodeWithTwoChildren(node);
        }

        return node;
    }

    private void deleteNodeWithTwoChildren(Node node) {
        Node inOrderSuccessor = findMin(node.right);
        node.data = inOrderSuccessor.data;
        node.right = delete(inOrderSuccessor.data, node.right);
    }

    public Node findMin(Node root) {
        if (root == null) {
            return null;
        }

        Node current = root;
        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    public Node findMax(Node root) {
        if (root == null) {
            return null;
        }

        Node current = root;
        while (current.right != null) {
            current = current.right;
        }

        return current;
    }

    public void preOrder(Node root) {
        if (root == null) {
            return;
        }

        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }

    public void inOrder(Node root) {
        if (root == null) {
            return;
        }

        System.out.println(root.data);
        inOrder(root.right);
        inOrder(root.left);
    }

    public int height(Node root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        if (leftHeight > rightHeight) {
            return leftHeight + 1;
        } else {
            return  rightHeight + 1;
        }
    }

    public void clear(Node root) {
        if (root == null) {
            return;
        }

        inOrder(root.left);
        inOrder(root.right);
        delete(root.data, root);
    }

    public Node getRoot() {
        return root;
    }
}
