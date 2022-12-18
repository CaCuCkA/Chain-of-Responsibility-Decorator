package ua.edu.ucu.apps.atm;

import org.jetbrains.annotations.NotNull;

public class ATM {
    public static void process(int amount, @NotNull Handler handler) {
        handler.process(amount);
    }
    public static void main(String[] args) {
        Handler handler6 = new Handler6();
        Handler handler36 = new Handler36();
        Handler handler72 = new Handler72();
        handler72.setNextHandler(handler36);
        handler36.setNextHandler(handler6);

        ATM.process(144, handler72);
        System.out.println("---");
        ATM.process(72, handler72);
        System.out.println("---");
        ATM.process(288, handler72);
    }
}