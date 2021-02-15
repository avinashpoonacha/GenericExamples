package playground;

public class LinkedList {

    Node head;


    static class Node {
        int data;
        Node next;

        public Node(int d) {
            data = d;
            next = null;
        }
    }


    public static LinkedList insert (LinkedList list , int data) {
        Node newNode = new Node(data);
        newNode.next = null;

        if( list.head ==null){
            list.head = newNode;
        } else {
            Node last = list.head;
            while (last.next != null){
                last = last.next;
            }

            last.next = newNode;

        }

        return list;

    }

    public static void tostring(LinkedList list ){
        Node currentNode = list.head;

        while (currentNode != null){
            System.out.print(currentNode.data+ " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    public static LinkedList remove(LinkedList list , int key){

        Node currentNode = list.head , prev= null;

        // if head node holds key
        if(currentNode != null && currentNode.data == key){
            list.head = currentNode.next;
            System.out.println("key found");
            return list;
        }

        // if some other node holds key
        while (currentNode != null && currentNode.data != key){
            prev = currentNode;
            currentNode = currentNode.next;

        }

        if(currentNode != null){
            prev.next = currentNode.next;
            System.out.println("key found");
        }


        // if key is mot present
        if (currentNode == null) {

            System.out.println("oops key not found");
        }


        return list;
    }


    public static void main(String[] args) {

        LinkedList list = new LinkedList();

        list = insert(list,1);
        list = insert(list,23);
        list = insert(list,2);
        list = insert(list,3);

       tostring(list);

        remove(list,23);

        tostring(list);

    }

}




