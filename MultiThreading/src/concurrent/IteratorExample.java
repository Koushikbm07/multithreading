package concurrent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IteratorExample {


    public static void main(String[] args) {

        ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>();

//        map.put(null,0);

        map.put("A",1);
        map.put("B",2);
        map.put("C",3);
        map.put("D",4);
        map.put("E",5);
        System.out.println(map.size());

//        map.forEach((s,i)->{
//            if(s.equals("B")){
//                map.remove(s);
//                map.put("F",1);
//            }
//        });



        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        while(it.hasNext()){
            if(it.next().getKey().equals("B")){
                map.remove("B");
            }
        }

        System.out.println(" ConcurrentHashMap ----");
        map.forEach((k,v)->{
            System.out.println("key:"+k+",value:"+v);
        });


        HashMap<String,Integer> hashmap=new HashMap<>();
        hashmap.put("A",1);
        hashmap.put("B",2);
        hashmap.put("C",3);
        hashmap.put("D",4);
        hashmap.put("E",5);


        Iterator<Map.Entry<String, Integer>> cit = hashmap.entrySet().iterator();

        while(cit.hasNext()){
            if(cit.next().getKey().equals("B")){
                cit.remove();
            }
        }


        System.out.println(" HashMap ----");

        hashmap.forEach((k,v)->{
            System.out.println("key:"+k+",value:"+v);
        });





    }
}
