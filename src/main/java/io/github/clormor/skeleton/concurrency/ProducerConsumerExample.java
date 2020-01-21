package io.github.clormor.skeleton.concurrency;

import java.util.Random;

/**
 * See https://docs.oracle.com/javase/tutorial/essential/concurrency/guardmeth.html
 *
 * Demonstrates use of guarded blocks in a simple example where two threads interact with a shared message queue.
 *
 * The consumer of messages will wait() until the queue is non-empty. Similarly, the producer will wait until the
 * consumer has emptied the queue.
 *
 * The guarded blocks are in the Drop class' take() and put() methods
 */
public class ProducerConsumerExample {
    public static void main(String[] args) {
        Drop drop = new Drop();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
    }

    static class Drop {
        // Message sent from producer
        // to consumer.
        private String message;
        // True if consumer should wait
        // for producer to send message,
        // false if producer should wait for
        // consumer to retrieve message.
        private boolean empty = true;

        public synchronized String take() {
            // Wait until message is
            // available.
            while (empty) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }
            // Toggle status.
            empty = true;
            // Notify producer that
            // status has changed.
            notifyAll();
            return message;
        }

        public synchronized void put(String message) {
            // Wait until message has
            // been retrieved.
            while (!empty) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }
            // Toggle status.
            empty = false;
            // Store message.
            this.message = message;
            // Notify consumer that status
            // has changed.
            notifyAll();
        }
    }

    static class Consumer implements Runnable {
        private Drop drop;

        public Consumer(Drop drop) {
            this.drop = drop;
        }

        public void run() {
            Random random = new Random();
            for (String message = drop.take();
                 ! message.equals("DONE");
                 message = drop.take()) {
                System.out.format("MESSAGE RECEIVED: %s%n", message);
                try {
                    Thread.sleep(random.nextInt(5000));
                } catch (InterruptedException e) {}
            }
        }
    }

    static class Producer implements Runnable {
        private Drop drop;

        public Producer(Drop drop) {
            this.drop = drop;
        }

        public void run() {
            String importantInfo[] = {
                    "Mares eat oats",
                    "Does eat oats",
                    "Little lambs eat ivy",
                    "A kid will eat ivy too"
            };
            Random random = new Random();

            for (int i = 0;
                 i < importantInfo.length;
                 i++) {
                drop.put(importantInfo[i]);
                try {
                    Thread.sleep(random.nextInt(5000));
                } catch (InterruptedException e) {}
            }
            drop.put("DONE");
        }
    }
}
