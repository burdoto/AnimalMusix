package main;

public class Util {
    public static void sleep(Long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
