package lucius.justtest.java.concurrency.chapter1;

/**
 * Created by root on 7/31/18.
 */
public class PrimeGenetor extends Thread {
    private boolean isPrime(long number) {
        if(number <= 2) {
            return true;
        }
        for (long i = 2; i < number ; i++) {
            if( number % 2 == 0) {
                return  false;
            }
        }
        return true;
    }

    @Override
    public void run() {
        long number = 1L;
        while (true){
            if (isPrime(number)) {
                System.out.printf("Number %d is Prime\n", number);
            }
            // call isInterrupted() to check if the thread has been interrupted
            if (isInterrupted()) {
                System.out.printf("The Prime Generator has been Interrupted");
                return;
            }
            number++;
        }
    }
}
