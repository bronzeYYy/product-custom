import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.start();
    }
}
class Message{}
class Product implements Runnable {
    private ArrayBlockingQueue<Message> arrayBlockingQueue;
    Product(ArrayBlockingQueue arrayBlockingQueue) {
        this.arrayBlockingQueue = arrayBlockingQueue;
    }
    @Override
    public void run() {
        try {
            while (true) {
                arrayBlockingQueue.put(new Message());
                System.out.println("生产了一个商品");
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Custom implements Runnable {
    private ArrayBlockingQueue<Message> arrayBlockingQueue;
    Custom(ArrayBlockingQueue arrayBlockingQueue) {
        this.arrayBlockingQueue = arrayBlockingQueue;
    }
    @Override
    public void run() {
        try {
            while (true) {
                arrayBlockingQueue.take();
                System.out.println("消费了一个商品");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Solution {
    private ArrayBlockingQueue<Message> arrayBlockingQueue;
    private Product product;
    private Custom custom;
    Solution() {
        arrayBlockingQueue = new ArrayBlockingQueue<>(2);
        this.custom = new Custom(arrayBlockingQueue);
        this.product = new Product(arrayBlockingQueue);
    }

    public void start() {
        new Thread(product).start();
        new Thread(custom).start();
    }
}
