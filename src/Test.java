import lists.MyArrayList;
import lists.MySingleLinkedList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        myArrayList();

//        mySingleLinkedList();
    }

    private static void myArrayList() {
        MyArrayList<String> myArrayList = new MyArrayList<>(12);

        for (int i = 0; i < 10; i++) {
            myArrayList.add(String.valueOf(i));
        }

        System.out.println(myArrayList);
    }

    private static void mySingleLinkedList() {
        var myList = new MySingleLinkedList<String>();

        myList.add("1");
        myList.add("2");

        System.out.println("Size: " + myList.size());
        System.out.println("myList: " + myList);
        System.out.println("myList.contain(\"2\"): " + myList.contain("2"));
        System.out.println("myList.getFirst(): " + myList.getFirst());

        myList.removeFirst();
        System.out.println(myList);
        myList.addFirst("1");
        System.out.println(myList);
        myList.remove("2");
        System.out.println(myList);

        List<String> l = new LinkedList<>();
        l.add("8");
        l.add("10");
        myList.addAll(0, l);
        System.out.println("l: " + l);
        System.out.println("AddAll: " + myList);

        myList.clear();
        System.out.println(myList);

        myList.add("1");
        myList.add("2");
        myList.add("3");
        myList.set(1, "4");
        System.out.println(myList);

        myList.add(3, "2");
        System.out.println(myList);

        myList.remove(3);
        System.out.println(myList);
    }
}
