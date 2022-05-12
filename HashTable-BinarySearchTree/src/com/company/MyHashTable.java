package com.company;
import java.util.ArrayList;
import java.util.Objects;

public class MyHashTable <K,V> {

    private ArrayList <HashNode<K,V>> Array; // array with K-key, V - value
    private int numArray; // capacity of array
    private int size; // number of element

    public MyHashTable(){
        Array = new ArrayList<>();
        numArray = 10;
        size = 0;

        for (int i = 0; i < numArray; i++){
            Array.add(null); // to fill Array with null elements
        }
    }


    public int size(){ // to get size
        return size;
    }


    public boolean empty(){ // to check if empty return TRUE if not return FALSE
        return size() == 0;
    }
    private int getArrayIndex(K key){
        int hashCode = hashCode(key);
        int index = hashCode % numArray;

        // to check if index is negative, if yes than multiple to negative 1
        index = (index < 0) ? index * (-1) : index;
        return index;
    }

    public final int hashCode(K key){
        return Objects.hashCode(key);
    }


    public V vRemove (K key){ // to remove element
        int ArrayIndex = getArrayIndex(key);
        int hashCode = hashCode(key);

        HashNode <K,V> head = Array.get(ArrayIndex);

        // to connect previous node with current
        HashNode <K,V> prev = null;
        while (head != null){
            if (head.key.equals(key) && hashCode == head.hC){
                break;
            }
            prev = head;
            head = head.next;
        }

        if (head == null){
            return null;
        }
        size--;

        // if element in the middle
        if (prev != null) {
            prev.next = head.next;
        }

        // if element in the beginning
        else {
            Array.set(ArrayIndex, head.next);
        }

        return head.value;
    }



    // to get element's value bu using key
    public V get(K key) {
        int bucketIndex = getArrayIndex(key);
        int hashCode = hashCode(key);

        HashNode <K,V> head = Array.get(bucketIndex); // starting from the head


        // to check key with hashcode, to avoid the save keys and elements
        while (head != null) {

            if (head.key.equals(key) && head.hC == hashCode)
            {
                return head.value;
            }

            head = head.next;
        }

        return null;
    }


    // to add elements
    public void add(K key, V value) {

        int ArrayIndex = getArrayIndex(key);
        int hashCode = hashCode(key);

        HashNode <K,V> head = Array.get(ArrayIndex);

        while(head != null) {
            if (head.key.equals(key) && head.hC == hashCode) {

                head.value = value;
                return;

            }
            head = head.next;
        }

        size++;

        head = Array.get(ArrayIndex);
        HashNode <K,V> newNode = new HashNode <K,V> (key, value, hashCode);
        newNode.next = head;
        Array.set(ArrayIndex, newNode);

        // increasing size of array
        if ((1.0 * size) / numArray >= 0.7){
            ArrayList <HashNode<K,V>> temp = Array;
            Array = new ArrayList<>();
            numArray = 2*numArray;

            size = 0;
            for (int i=0; i<numArray; i++) {

               Array.add(null);
            }

            for (HashNode<K,V> headNode:temp) {

                while(headNode != null) {

                    add(headNode.key, headNode.value);
                    headNode = headNode.next;

                }
            }
        }
    }
}