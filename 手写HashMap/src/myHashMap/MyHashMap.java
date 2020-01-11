package myHashMap;

import javafx.scene.control.TableView.ResizeFeatures;

public class MyHashMap<K,V> {
    
    private int tableSize;
    private float loadFactor;
    
    private MyEntry[] table;
    private int entryUseSize;
    
    public MyHashMap(int tableSize,float loadFactor) {
	this.tableSize=tableSize;
	this.loadFactor=loadFactor;
	entryUseSize=0;
	table=new MyEntry[tableSize];
    }
    public MyHashMap() {
	this(16, 0.75f);//默认tableSize=16,阈值=0.75f
    }
    
    
    
    public V get(K key) {
	int hash = hash(key);
	int index = key==null?  0  :  indexFor(hash, tableSize);
	for(MyEntry<K, V> e=table[index];   e!=null;   e=e.getNext()) {
	    if( (e.getKey()==key||e.getKey().equals(key)) ) {
		return e.getValue();
	    }
	}
	return null;
    }
    
    public V put(K key,V value) {
	//第一步，null键
	if(key==null) {
	    //TODO
	    return putForNullKey(value);
	}
	
	
	
	//第二步，确定桶下标
	int hash = hash(key);
	int bucketIndex = indexFor(hash,tableSize);
	
	
	
	
	//第三步，在桶下标对应的链表中查找是否已存在键
	for(MyEntry<K, V> e=table[bucketIndex];  e!=null;  e=e.getNext()) {
	    if(e.getHash()==hash && (e.getKey()==key || e.getKey().equals(key))  ) {
		V oldValue = e.getValue();
		e.setValue(value);
		return oldValue;
	    }
	}
	
	
	
	//第四步，键不存在，新增键值对
	addEntry(hash,key,value,bucketIndex);
	
	return null;
    }
    
    
    
    //---------------------------------------------------------------------------------------
    private int hash(Object key) {
	int h=key.hashCode();
	return h ^ (h>>>7) ^ (h>>>4);
    }


    
    private int indexFor(int hash,int tableSize) {
	return hash & (tableSize-1); // return hash % tableSize
    }
    
    
    private V putForNullKey(V value) {
	for(MyEntry<K, V> e = table[0];  e!=null;  e=e.getNext()) {
	    if(e.getKey()==null) {
		V oldValue = e.getValue();
		e.setValue(value);
		return oldValue;
	    }
	}
	//在table[0]的链表中找null键值对，如果找到则替换value
	
	
	//新增null键值对，null的哈希值为0
	addEntry(0, null, value, 0);
	
	return null;
	
    }
    
    private void addEntry(int hash,K key, V value, int bucketIndex) {
	if(entryUseSize >= tableSize * loadFactor) {
	    resize();
	    //改变大小后,要重新计算bucketIndex
	    bucketIndex = indexFor(hash, tableSize);
	}
	
	MyEntry<K,V> e = new MyEntry<K,V>(hash, key, value, table[bucketIndex]);
	table[bucketIndex] = e;
	
	entryUseSize++;
    }
    
    
    private void resize() {
	
	int oldSize = tableSize;
	int newSize = oldSize * 2;
	MyEntry<K,V>[] oldTable = table;
	MyEntry<K,V>[] newTable = new MyEntry[newSize];
	
	entryUseSize=0;
	
	for(int j=0;  j<oldSize;  j++) {
	    MyEntry<K, V> e = oldTable[j];
	    if(e!=null) {
		oldTable[j]=null;//删除oldTable
		//重新插入到newTable中
		do {
		    MyEntry<K, V> next = e.getNext();
		    int i = indexFor(e.getHash(), newSize);
		    e.setNext(newTable[i]);
		    newTable[i] = e;

		    e=next;
		}while(e!=null);
	    }
	}
	
	table=newTable;
	tableSize = newSize;

    }
    
}
