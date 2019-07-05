package spring;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AudiCar implements Car {

    @Override
    public void start() {
        log.info("Audi starting!");

    }

    @Override
    public void stop() {
        log.info("Audi stopping!");

    }

    @Override
    public void turnLeft() {
        log.info("Audi turn left!");

    }

    @Override
    public void turnRight() {
        log.info("Audi turn right!");

    }

}
