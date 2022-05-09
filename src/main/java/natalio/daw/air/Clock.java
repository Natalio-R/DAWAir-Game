package natalio.daw.air;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Natalio Rabasco
 */
public class Clock implements Runnable {

    // Declaramos las variables necesarias
    private int frecuency; // Frecuencia
    private float delta; // Delta
    private long time; // Tiempo
    private Thread t;
    private ArrayList<IWarnClock> observers;
    private ClockState state;

    /**
     * Enumerado con el estado del reloj
     */
    public enum ClockState {
        STARTED,
        STOPED,
        PAUSED,
        CLOSED
    }

    /**
     * 
     * @param frecuency 
     */
    public Clock(int frecuency) {
        this.frecuency = frecuency;
        this.delta = (1.0f / (float) frecuency) * 1000;
        this.time = System.currentTimeMillis();
        this.observers = new ArrayList<>();
        this.state = ClockState.STOPED;
        this.t = null;
    }

    /**
     * 
     * @param element 
     */
    public void addIWarClock(IWarnClock element) {
        this.observers.add(element);
    }

    /**
     * 
     * @param index 
     */
    public void removeIWarClock(int index) {
        if (this.observers.get(index) != null) {
            this.observers.remove(index);
        }
    }

    private void update() {
        for (Iterator<IWarnClock> i = this.observers.iterator(); i.hasNext();) {
            i.next().TicTac();
        }
    }

    public void start() {
        if (this.t == null) {
            this.t = new Thread(this);
            this.t.start();
            this.state = ClockState.STARTED;
        }
    }

    public void stop() {
        this.state = ClockState.STOPED;
    }

    public void pause() {
        this.state = ClockState.PAUSED;
    }

    public void close() {
        this.state = ClockState.CLOSED;
    }

    public ClockState state() {
        return this.state;
    }

    @Override
    public void run() {
        long actual;
        while (this.state != ClockState.CLOSED) {
            actual = System.currentTimeMillis();
            if (actual - this.time > this.delta) {
                try {
                    this.update();
                    this.time = actual;
                    Thread.sleep((long) this.delta);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Get de delta
     * 
     * @return delta
     */
    public float getDelta() {
        return delta;
    }
}
