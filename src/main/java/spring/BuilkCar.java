package spring;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuilkCar implements Car {

    @Override
    public void start() {
        log.info("Buick start!");
    }

    @Override
    public void stop() {
        log.info("Buick stop!");
    }

    @Override
    public void turnLeft() {
        log.info("Buick turn left!");
    }

    @Override
    public void turnRight() {
        log.info("Buick right!");
    }

}
