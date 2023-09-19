package pl.training.camel;

import java.util.Random;

public class ComputeSlip {

    public String compute(String body) {
        Random random = new Random();
        if (random.nextBoolean()) {
            return "direct:queueA;direct:queueC";
        } else {
            return "direct:queueB";
        }
    }

}
