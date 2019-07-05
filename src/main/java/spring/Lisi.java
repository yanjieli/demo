package spring;

public class Lisi extends HumenWtihCar {

    public Lisi(Car car) {
        super(car);
    }

    @Override
    public void goHome() {
        car.start();
        car.stop();
    }

}
