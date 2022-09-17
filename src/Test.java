import lists.MyArrayList;
import lists.MyDoubleLinkedList;
import lists.MySingleLinkedList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        myArrayList();

//        mySingleLinkedList();

        myDoubleLinkedList();
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

    private static void myDoubleLinkedList() {
        var myList = new MyDoubleLinkedList<String>();

        myList.add("1");
        myList.add("2");
        myList.add("3");
        System.out.println("Size: " + myList.size());
        System.out.println("myList: " + myList);
        System.out.println("first: " + myList.getFirst());
        System.out.println("last: " + myList.getLast());

        List<Integer> list = new ArrayList<>();
        list.add(7);
        list.add(8);
        list.add(9);
        var myList2 = new MyDoubleLinkedList<Integer>(list);
        System.out.println("Initialise from collection: " + myList2);
        myList2.addAll(0, list);
        System.out.println("Adding collection to index 0: " + myList2);
        myList2.addFirst(10);
        System.out.println("addFirst 10: " + myList2);
        System.out.println("removeFirst: " + myList2.removeFirst() + " " + myList2);
        System.out.println("removeLast: " + myList2.removeLast() + " " + myList2);
        System.out.println("contains 9: " + myList2.contain(9));
        System.out.println("indexOf 9: " + myList2.indexOf(9));
        myList2.remove(9);
        System.out.println("remove 9: " + myList2);
        System.out.println("get 3: " + myList2.get(3));
        myList2.clear();
        System.out.println("clear: " + myList2);

        System.out.println("myList: " + myList);
        myList.set(1, "5");
        System.out.println("set index 1 element 5: " + myList);
        myList.add(2, "6");
        System.out.println("add index 2 element 6: " + myList);
    }
}
