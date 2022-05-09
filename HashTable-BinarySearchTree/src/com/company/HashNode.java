package com.company;

public class HashNode <K,V> {
    K key;
    V value;
    final int hC;
    HashNode<K,V> next;

    public HashNode(K key, V value, int hC){
        this.key = key;
        this.value = value;
        this.hC = hC;
    }
}