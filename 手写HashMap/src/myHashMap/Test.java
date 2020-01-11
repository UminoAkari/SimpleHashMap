package myHashMap;

public class Test {

    public static void main(String[] args) {
	MyHashMap<String, String> map = new MyHashMap<String, String>();
	
	for(int i=0;i<20;i++) {
	    map.put(i+"", "ÎÒÊÇÄãµù"+i);
	    //System.out.println("put "+i+" = "+ (i+1));
	}
	
	for(int i=0;i<20;i++) {
	    System.out.println("get "+i+" = "+map.get(i+""));
	}
	
    }

}
