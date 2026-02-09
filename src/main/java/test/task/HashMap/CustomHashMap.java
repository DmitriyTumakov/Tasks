package test.task.HashMap;

import java.util.*;

public class CustomHashMap<K, V> implements Map<K, V> {
    private final int capacity;
    private Node[] map;
    private int threshold;
    private int size;

    private static class Node<K, V> implements Entry<K, V> {
        private final int hash;
        private final K key;
        private V value;
        private Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public int getHash() {
            return hash;
        }

        public Node<K, V> getNext() {
            return next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }
    }

    public CustomHashMap() {
        map = new Node[16];
        capacity = 16;
    };

    public CustomHashMap(int capacity) {
        map = new Node[capacity];
        this.capacity = capacity;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int index = calculateIndex(key);
        Node<K, V> node = map[index];
        if (node.getKey().equals(key)) {
            return true;
        } else if (!node.getKey().equals(key) && node.getNext() != null) {
            while (node.getNext() != null) {
                if (node.getKey().equals(key)) {
                    return true;
                }
                node = node.getNext();
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < capacity; i++) {
            if (map[i] != null) {
                Node<K, V> node = findValue(map[i], value);
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int index = calculateIndex(key);
        if (map[index] != null) {
            Node<K, V> node = findValueByKey(map[index], key);
            return node.getValue();
        }
        return null;
    }

    @Override
    public V put(Object key, Object value) {
        int index = calculateIndex(key);
        if (map[index] == null) {
            map[index] = new Node(key.hashCode(), key, value);
            size++;
        } else {
            V oldValue = (V) map[index].getValue();
            Node newNode = new Node(key.hashCode(), key, value, map[index]);
            map[index] = newNode;
            size++;
            return oldValue;
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        int index = calculateIndex(key);
        Node<K, V> node = findValueByKey(map[index], key);
        if (node.getNext() == null && node.getKey().equals(key)) {
            map[index] = null;
            return node.getValue();
        } else {
            Node<K, V> prevNode = null;
            while (node.getNext() != null) {
                if (node.getKey().equals(key)) {
                    if (prevNode != null) {
                        prevNode.setNext(node.getNext());
                    } else {
                        map[index] = node;
                    }
                    return node.getValue();
                }
                prevNode = node;
                node = node.getNext();
            }
        }
        return null;
    }

    @Override
    public void putAll(Map m) {
    }

    @Override
    public void clear() {
        map = new Node[capacity];
    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    private Node<K, V> findValueByKey(Node<K, V> node, Object key) {
        boolean isCorrect = node.getKey().equals(key);
        if (node.getNext() != null) {
            return findValueByKey(node.getNext(), key);
        }
        if (isCorrect) {
            return node;
        }
        return null;
    }

    private Node<K, V> findValue(Node<K, V> node, Object value) {
        boolean isCorrect = node.getValue().equals(value);
        if (node.getNext() != null) {
            return findValue(node.getNext(), value);
        }
        if (isCorrect) {
            return node;
        }
        return null;
    }

    private int calculateIndex(Object key) {
        return key.hashCode() & (capacity - 1);
    }
}