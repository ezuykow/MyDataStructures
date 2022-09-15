import lists.MyArrayList;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(12);
        MyArrayList<String> myArrayList = new MyArrayList<>(12);

        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
            myArrayList.add(String.valueOf(i));
        }

        System.out.println(list);
        System.out.println(myArrayList);
    }
}
