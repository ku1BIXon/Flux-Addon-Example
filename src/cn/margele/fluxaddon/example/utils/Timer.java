package cn.margele.fluxaddon.example.utils;

/*
*  This is a simple Timer Util.
* */
public class Timer {
    public long lastMs;

    public Timer() {
        this.lastMs = 0L;
    }

    public void reset() {
        this.lastMs = System.currentTimeMillis();
    }

    public boolean delay(long nextDelay) {
        return System.currentTimeMillis() - lastMs >= nextDelay;
    }
}
