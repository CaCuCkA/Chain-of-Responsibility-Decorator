package ua.edu.ucu.apps.atm;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@RequiredArgsConstructor
public class Handler {
    private Handler nextHandler;
    private final int quantity;

    public void process(int amount) {
        int count = amount / quantity,
                remainder = amount % quantity;

        if (nextHandler == null && remainder != 0) {
            throw new IllegalArgumentException("Cannot process this amount");
        }

        System.out.println("Dispensing " + count + " note of " + quantity);

        if (remainder != 0) {
            nextHandler.process(remainder);
        }
    }

}
