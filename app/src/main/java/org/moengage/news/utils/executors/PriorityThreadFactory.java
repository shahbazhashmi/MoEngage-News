package org.moengage.news.utils.executors;

import android.os.Process;

import java.util.concurrent.ThreadFactory;

/**
 * Created by Shahbaz Hashmi on 2020-03-20.
 */
public class PriorityThreadFactory implements ThreadFactory {

    private final int mThreadPriority;

    PriorityThreadFactory(int threadPriority) {
        mThreadPriority = threadPriority;
    }

    @Override
    public Thread newThread(final Runnable runnable) {
        Runnable wrapperRunnable = () -> {
            try {
                Process.setThreadPriority(mThreadPriority);
            } catch (Throwable t) {
                t.printStackTrace();
            }
            runnable.run();
        };
        return new Thread(wrapperRunnable);
    }

}
