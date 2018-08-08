package lucius.justtest.java.concurrency.chapter2.synchronize.exp2;

/**
 * Created by Lucius on 8/8/18.
 * 使用非依赖属性实现同步
 * 当使用synchronized关键字来保护代码块时，必须把对象引用作为传入参数。通常情况下使用this关键字来引用执行方法所属的对象，
 * 也可以使用其他的对象对其进行引用。一般来说，这些对象就是为了这个目的而创建的。例如，在类中有两个非依赖属性。它们被多个
 * 线程共享，你必须同步每一个变量的访问，但是同一时刻只允许一个线程访问一个属性变量，其他某个线程访问另一个属性变量。
 * <p>
 * 以下范例实现电影院售票场景的编程。这个范例模拟了有两个屏幕和两个售票处的电影院。一个售票处卖出的一张票，只能用于其中一个电影院，
 * 不能同时用于两个电影院，因此每个电影院的剩余票数是独立的属性。
 */
public class Cinema {
    private long vacanciesCinema1;
    private long vacanciesCinema2;
    private final Object controlCinema1, controlCinema2;

    public Cinema() {
        this.vacanciesCinema1 = 200000;
        this.vacanciesCinema2 = 200000;
        this.controlCinema1 = new Object();
        this.controlCinema2 = new Object();
    }

    //卖票
    public boolean sellTicket1(int number) {
        synchronized (controlCinema1) {
            if (number < vacanciesCinema1) {
                vacanciesCinema1 -= number;
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean sellTicket2(int number) {
        synchronized (controlCinema2) {
            if (number < vacanciesCinema2) {
                vacanciesCinema2 -= number;
                return true;
            } else {
                return false;
            }
        }
    }

    //退票
    public boolean returnTicket1(int number) {
        synchronized (controlCinema1) {
            vacanciesCinema1 += number;
            return true;
        }
    }

    public boolean returnTicket2(int number) {
        synchronized (controlCinema2) {
            vacanciesCinema2 += number;
            return true;
        }
    }

    //返回剩余电影票数
    public long getVacanciesCinema1() {
        return vacanciesCinema1;
    }

    public long getVacanciesCinema2() {
        return vacanciesCinema2;
    }

}
