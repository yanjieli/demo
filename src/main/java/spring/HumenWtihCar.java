package spring;

public abstract class HumenWtihCar implements Humen {

    protected Car car;

    public HumenWtihCar(Car car) {
        super();
        this.car = car;
    }

    public abstract void goHome();
}
