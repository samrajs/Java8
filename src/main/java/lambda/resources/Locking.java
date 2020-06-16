package lambda.resources;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static lambda.resources.Locker.runLocked;

public class Locking {
    Lock lock = new ReentrantLock(); //or mock

    protected void setLock(final Lock mock) {
        lock = mock;
    }

    public void doOp1() {
        lock.lock();
        try {
            //...critical code...
        } finally {
            lock.unlock();
        }
    }
    //...

    public void doOp2() {
        runLocked(lock, () -> {
            System.out.println("Hallo world");
        });
    }

    public void doOp3() {
        runLocked(lock, () -> {/*...critical code ... */});
    }

    public void doOp4() {
        runLocked(lock, () -> {/*...critical code ... */});
    }

    public static void main(String[] args) {
        Locking locking = new Locking();
        locking.doOp2();
    }
}