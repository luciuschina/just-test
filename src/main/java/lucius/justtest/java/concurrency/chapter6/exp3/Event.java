package lucius.justtest.java.concurrency.chapter6.exp3;

/**
 * Created by Lucius on 9/7/18.
 */

public class Event implements Comparable<Event> {
    private int thread;
    private int priority;

    public Event(int thread, int priority) {
        this.thread = thread;
        this.priority = priority;
    }

    @Override
    public int compareTo(Event o) {
        if (this.priority > o.getPriority()) {
            return 1;
        } else if (this.priority < o.getPriority()) {
            return -1;
        } else {
            return 0;
        }
    }

    public int getThread() {
        return thread;
    }

    public int getPriority() {
        return priority;
    }

}
