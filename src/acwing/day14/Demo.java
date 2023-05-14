package acwing.day14;
import java.util.*;
/**
 * @program: danceOffer
 * @description: 123
 * @author: mobing_yin
 * @create: 2020-11-23 16:03
 **/

public class Demo {
    public static void main(String[] args) {
        List<Long> lists = new ArrayList<>();
        lists.add(1234l);
        lists.add(2123l);
        lists.add(2123l);
        lists.add(2123l);
        lists.add(34331l);
        lists.add(11111l);
        lists.add(542133l);
        lists.add(442133l);
        lists.add(11111l);
        lists.add(2222l);
        lists.add(33333l);
        for (Long temp : lists){
            System.out.println(temp);
        }
        Set<Long> sets = new LinkedHashSet<>(4);
        for (Long temp : lists){
            sets.add(temp);
            if(sets.size()>3){
                break;
            }
        }
        int i = 4;
        Map<Long, Integer> maps = new HashMap<>();
        for (Long temp : sets){
            maps.put(temp, i--);
        }
        Map<Long, Integer> sortMaps = new LinkedHashMap<>();
        for (Long id : sets){
            sortMaps.put(id, maps.get(id));
        }
    }
}
