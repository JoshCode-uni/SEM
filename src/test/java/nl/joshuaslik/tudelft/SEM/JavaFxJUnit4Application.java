package nl.joshuaslik.tudelft.SEM;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Coppied from: http://awhite.blogspot.nl/2013/04/javafx-junit-testing.html 
 * This is the application which starts JavaFx. It is controlled through the startJavaFx() method.
 */
public class JavaFxJUnit4Application extends Launcher {

    /**
     * The lock that guarantees that only one JavaFX thread will be started.
     */
    private static final ReentrantLock LOCK = new ReentrantLock();

    /**
     * Started flag.
     */
    private static AtomicBoolean started = new AtomicBoolean();

    /**
     * Start JavaFx.
     */
    public static void startJavaFx() {
        setHideViewForTesting(true);
        try {
            // Lock or wait. This gives another call to this method time to finish
            // and release the lock before another one has a go
            LOCK.lock();

            if (!started.get()) {
                // start the JavaFX application
                final ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        JavaFxJUnit4Application.launch();
                    }
                });

                while (!started.get()) {
                    Thread.yield();
                }
                Launcher.waitTillInitialized();
            }
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * Launch.
     */
    protected static void launch() {
        Application.launch();
    }

    /**
     * An empty start method.
     *
     * @param stage The stage
     */
    @Override
    public final void start(final Stage stage) {
        started.set(Boolean.TRUE);
        super.start(stage);
    }
}
