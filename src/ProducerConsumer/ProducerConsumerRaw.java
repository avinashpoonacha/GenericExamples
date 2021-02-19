package ProducerConsumer;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerRaw {

    public static int createItem() {
        return new Random().nextInt();
    }

    public static void main(String[] args){

        //handles blocking
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

        final Runnable producer = () -> {
            while(true){
                try {
                    queue.put(createItem());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        final Runnable consumer = () ->{
            while(true){
                try {
                    int i = queue.take();
                    System.out.println(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };




        new Thread(producer).start();
        new Thread(producer).start();

        new Thread(consumer).start();
        new Thread(consumer).start();


    }

}

