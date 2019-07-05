package spring;

public class Zhangsan extends HumenWtihCar {

    public Zhangsan(Car car) {
        super(car);
    }

    @Override
    public void goHome() {
        car.start();
        car.turnLeft();
        car.turnRight();
        car.stop();
    }
}
