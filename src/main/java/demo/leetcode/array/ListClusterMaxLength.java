package demo.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListClusterMaxLength {

    private static int getMaxLength(List<List<Double>> lists){
        int maxLength = 0;
        int listSize = lists.size();
        for (int i=0; i < listSize; i++) {
            maxLength = lists.get(i).size() > maxLength ? lists.get(i).size() : maxLength;
        }
        return maxLength;
    }

    private static List<List<Double>> fillZeroHead(List<List<Double>> lists){
        int maxLength = getMaxLength(lists);
        List<List<Double>> result = new ArrayList<>();
        for (List<Double> l:lists) {
            int needZeroNum = maxLength - l.size();
            List<Double> modifiedList = new ArrayList<>(Collections.nCopies(needZeroNum, 0.0));
            modifiedList.addAll(l);
            result.add(modifiedList);
        }
        return result;
    }

    public static void main(String[] args) {
        List<Double> list1 = Arrays.asList(34.3, 82.0);
        List<Double> list2 = Arrays.asList(23.3, 28.0, 44.4, 13.4, 42.1, 77.3, 73.2);
        List<Double> list3 = Arrays.asList(33.3, 82.1, 13.4, 42.1);

        List<List<Double>> lists = new ArrayList<>();
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);

        List<List<Double>> res = fillZeroHead(lists);
        System.out.println(res);
    }
}
