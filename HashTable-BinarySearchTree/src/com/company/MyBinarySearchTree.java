package com.company;

public class MyBinarySearchTree {
    Node root;

    MyBinarySearchTree() {
        root = null;
        }

    // inserting of new value
    MyBinarySearchTree(int value){
        root = new Node(value);
    }

    Node insert(Node root, int key){
        if (root == null)
        {
            root = new Node(key);
            return root;
        }

        if (key < root.key)
        {
            root.left = insert(root.left, key); // if key < root, then key goes to the left side of node
        }

        else if (key > root.key) {
            root.right = insert(root.right, key); // if key > root, then key goes to the right side of node
        }

        return root;
    }

    // to print current left and right items
    void printOrder(Node root){
        if (root != null){
            printOrder(root.left);
            System.out.println(root.key);
            printOrder(root.right);
        }
    }

    // to search items
    public Node search(Node root, int key){ if (root == null || root.key == key) {
            return root;
        }

        if (root.key < key){ return search(root.right, key);}

        return search(root.left, key);

    }
}
