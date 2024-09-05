public class DeadlockExample {
    final static Object lock1 = new Object();
    final static Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (lock1) {
                    System.out.println("Thread 1 locked -> Resource R1");

                    synchronized (lock2) {
                        System.out.println("Thread 2 locked -> Resource R2");
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock2) {
                    System.out.println("Thread 2 locked -> Resource R1");

                    synchronized (lock1) {
                        System.out.println("Thread 1 locked -> Resource R2");
                    }
                }
            }
        });


        thread1.start();
        thread2.start();
    }
}
