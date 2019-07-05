package anno;

@Description(test = "father", test2 = "father2")
public abstract class Father {
    public abstract void speak();

    public String eat(String food) {
        return food;
    }
}
