package com.dat.android.horizontalcenterlockrecyclerviewexperiment.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Nguyen on 7/5/2016.
 */
public class DummyGenerator {
    public static List<CheeseListItem> getCheeseItems(int amount) {
        List<CheeseListItem> list = new ArrayList<>(amount);
        for (int i = 0; i < amount; i++) {
            CheeseListItem cheeseListItem = new CheeseListItem();
            cheeseListItem.setName("Cheese List #" + i);
            cheeseListItem.setCheese(DummyGenerator.getRandomSublist(Cheeses.sCheeseStrings, 10));
            list.add(cheeseListItem);
        }
        return list;
    }

    public static List<String> getRandomSublist(String[] array, int amount) {
        List<String> list = new ArrayList<>(amount);
        Random random = new Random();
        while (list.size() < amount) {
            list.add(array[random.nextInt(array.length)]);
        }
        return list;
    }
}
