package myHashMap;

public class MyEntry<K, V> {
    private int hash;
    private K key;
    private V value;
    private MyEntry<K, V> next;
    
    
    public MyEntry() {
	
    }
    
    public MyEntry(int hash,K key, V value, MyEntry<K, V> next) {
	this.hash=hash;
	this.key=key;
	this.value=value;
	this.next=next;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public MyEntry<K, V> getNext() {
        return next;
    }

    public void setNext(MyEntry<K, V> next) {
        this.next = next;
    }
    
}
