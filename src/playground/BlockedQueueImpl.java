package playground;




class Node {
    public int data;
    public Node nextNode;

    public Node(int data ) {
        this.data = data;
        this.nextNode =null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }
}




public class BlockedQueueImpl  {
    private Node front;
    private Node rear;
    volatile static int capacity;

    public static boolean isEmpty(){
        return capacity==0;
    }

    public synchronized int take() throws InterruptedException {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            wait();
        }
        int data;
        data = front.getData();
        front = front.getNextNode();

        /*
        data = rear.getData();
        rear=rear.prevNode();
         */
        capacity--;
        return data;
    }

    public synchronized void put(int data) {

        if(capacity > 10){
            System.out.println("Queue is full");
        }
        Node newNode = new Node(data);
        if (isEmpty()){
            front=newNode;
        } else {
            rear.setNextNode(newNode);
        }
        rear=newNode;
        capacity++;

    }

    public void tostring(BlockedQueueImpl blockedQueueImpl) {
        Node indexNode = blockedQueueImpl.front;
        while(indexNode!=null){
            System.out.println(indexNode.getData()+ " ");
            indexNode=indexNode.getNextNode();
        }
        System.out.println();
    }



    public static void main(String[] args) throws InterruptedException {
        BlockedQueueImpl blockedQueueImpl = new BlockedQueueImpl();

        blockedQueueImpl.put(12);
        blockedQueueImpl.put(124);
        blockedQueueImpl.put(1232);

        System.out.println(blockedQueueImpl.capacity);
        blockedQueueImpl.tostring(blockedQueueImpl);

        blockedQueueImpl.take();
        System.out.println(blockedQueueImpl.capacity);
        blockedQueueImpl.take();
        System.out.println(blockedQueueImpl.capacity);
        blockedQueueImpl.take();
        System.out.println(blockedQueueImpl.capacity);
        blockedQueueImpl.take();
    }
}