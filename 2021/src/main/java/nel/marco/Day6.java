package nel.marco;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day6 {
    public int part1(List<Integer> fishes, int days) {

        List<Integer> temp = new ArrayList<>(fishes);
        List<Integer> temp2 = new ArrayList<>();
        for (int i = 0; i < days; i++) {

            for (Integer fish : temp) {
                if (fish > 0) {
                    temp2.add(fish - 1);
                } else if (fish == 0) {
                    temp2.add(6);
                    temp2.add(8);
                }
            }
            temp = new ArrayList<>();
            temp.addAll(temp2);
            temp2 = new ArrayList<>();
        }

        return BigInteger.valueOf(temp.size()).intValue();
    }

    public BigInteger part2(List<Integer> fishes, int days) {

        Map<Integer, Long> totalFish = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            totalFish.put(i, 0L);
        }
        fishes.forEach(integer -> {
            totalFish.put(integer, totalFish.get(integer) + 1);
        });

        Map<Integer, Long> temp = new HashMap<>();
        for (int i = 0; i < days; i++) {
            temp.put(8,totalFish.getOrDefault(0,0L));
            temp.put(7,totalFish.getOrDefault(8,0L));
            temp.put(6,totalFish.getOrDefault(7,0L) + totalFish.getOrDefault(0,0L));
            temp.put(5,totalFish.getOrDefault(6,0L));
            temp.put(4,totalFish.getOrDefault(5,0L));
            temp.put(3,totalFish.getOrDefault(4,0L));
            temp.put(2,totalFish.getOrDefault(3,0L));
            temp.put(1,totalFish.getOrDefault(2,0L));
            temp.put(0,totalFish.getOrDefault(1,0L));

            totalFish.putAll(temp);
        }

        BigInteger total = BigInteger.ZERO;

        for (Long aLong : totalFish.values()) {
            total = total.add(BigInteger.valueOf(aLong));
        }

        return total;
    }
}
