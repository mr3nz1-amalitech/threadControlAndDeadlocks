public class Main {
    public static void main(String[] args) {
        Thread taskThread = new Thread(new Task());
        taskThread.start();
        System.out.println("Long running task");

//        interrupting task
            taskThread.interrupt();
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
//                throw new RuntimeException(e);
            }

            System.out.println("Continuing execution");
        }
    }
}