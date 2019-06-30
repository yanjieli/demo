package algorithms.sort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BinaryTree {

    private Node root = null;

    private void insert(int key) {
        Node newNode = new Node(key);
        if (root == null) {
            root = newNode;
        } else {
            insertNode(root, newNode);
        }

    }

    private static void insertNode(Node node, Node newNode) {

        if (newNode.getKey() < node.getKey()) {
            if (node.getLeft() == null) {
                node.setLeft(newNode);
            } else {
                insertNode(node.getLeft(), newNode);
            }
        } else {
            if (node.getRight() == null) {
                node.setRight(newNode);
            } else {
                insertNode(node.getRight(), newNode);
            }
        }
    }

    private static void inOrderTraverse(Node root) {
        if (root != null) {
            inOrderTraverse(root.getLeft());
            log.info("{}", root.getKey());
            inOrderTraverse(root.getRight());
        }

    }

    private static void afterOrderTraverse(Node root) {
        if (root != null) {
            afterOrderTraverse(root.getLeft());
            afterOrderTraverse(root.getRight());
            log.info("{}", root.getKey());
        }

    }

    private static void beforeOrderTraverse(Node root) {
        if (root != null) {
            log.info("{}", root.getKey());
            beforeOrderTraverse(root.getLeft());
            beforeOrderTraverse(root.getRight());
        }

    }

    private static Node minNode(Node node) {
        if (node != null) {
            while (node.getLeft() != null && node != null) {
                node = node.getLeft();
            }
            return node;
        }
        return null;
    }

    private static Node maxNode(Node node) {
        if (node != null) {
            while (node.getRight() != null && node != null) {
                node = node.getRight();
            }
            return node;
        }
        return null;
    }

    private static Node searchNode(Node node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.getKey()) {
            return searchNode(node.getLeft(), key);
        } else if (key > node.getKey()) {
            return searchNode(node.getRight(), key);
        } else {
            return node;
        }
    }

    private static boolean isKeyInNode(Node node, int key) {
        if (node == null) {
            return false;
        }
        if (key < node.getKey()) {
            return isKeyInNode(node.getLeft(), key);
        } else if (key > node.getKey()) {
            return isKeyInNode(node.getRight(), key);
        } else {
            return true;
        }
    }

    private static Node removeNode(Node node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.getKey()) {
            node.setLeft(removeNode(node.getLeft(), key));
            return node;
        } else if (key > node.getKey()) {
            node.setRight(removeNode(node.getRight(), key));
            return node;
        } else {
            node = null;
            return node;
        }
    }

    public static void main(String[] args) {

        BinaryTree bt = new BinaryTree();
        int array[] = new int[] { 8, 3, 10, 1, 6, 14, 4, 7, 13 };
        for (int i = 0; i < array.length; i++) {
            bt.insert(array[i]);
        }
        log.info("Finished binary tree construct!");
        inOrderTraverse(bt.root);
        log.info("Finished in order tree!");
        afterOrderTraverse(bt.root);
        log.info("Finished after order tree!");
        beforeOrderTraverse(bt.root);
        log.info("Finished before order tree!");
        log.info("The minimal value in the binary tree is {}", minNode(bt.root).getKey());
        log.info("The maximal value in the binary tree is {}", maxNode(bt.root).getKey());
        log.info("The number {} was in Node? {} ", 4, isKeyInNode(bt.root, 4));
        log.info("The number {} was in Node? {} ", 5, isKeyInNode(bt.root, 5));

        Node newNode = removeNode(bt.root, 1);
        log.info("After remove 1,the Node was:");
        inOrderTraverse(newNode);
    }

}

class Node {
    private int key;
    private Node left;
    private Node right;

    public Node(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

}
