package org.moengage.news.utils.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Shahbaz Hashmi on 2020-03-20.
 */
public class ParallelExecutor extends Thread {
    private ConcurrentLinkedQueue<Worker> workers;
    private Callback callback;
    private CountDownLatch latch;

    public ParallelExecutor(List<Runnable> tasks, Callback callback) {
        super();
        this.callback = callback;
        workers = new ConcurrentLinkedQueue<>();
        latch = new CountDownLatch(tasks.size());

        for (Runnable task : tasks) {
            workers.add(new Worker(task, latch));
        }
    }

    public void execute() {
        start();
    }

    @Override
    public void run() {
        while (true) {
            Worker worker = workers.poll();
            if (worker == null) {
                break;
            }
            worker.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (callback != null) {
            callback.onComplete();
        }
    }

    public static class Builder {
        private List<Runnable> tasks = new ArrayList<>();
        private Callback callback;

        public Builder add(Runnable task) {
            tasks.add(task);
            return this;
        }

        public Builder callback(Callback callback) {
            this.callback = callback;
            return this;
        }

        public ParallelExecutor build() {
            return new ParallelExecutor(tasks, callback);
        }
    }

    public interface Callback {
        void onComplete();
    }


    public class Worker implements Runnable {

        private AtomicBoolean started;
        private Runnable task;
        private Thread thread;
        private CountDownLatch latch;

        public Worker(Runnable task, CountDownLatch latch) {
            this.latch = latch;
            this.task = task;
            started = new AtomicBoolean(false);
            thread = new Thread(this);
        }

        public void start() {
            if (!started.getAndSet(true)) {
                thread.start();
            }
        }

        @Override
        public void run() {
            task.run();
            latch.countDown();
        }
    }
}