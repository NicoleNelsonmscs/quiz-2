import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Two {
    private int i, j;

    public Two(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public boolean equals(Two t) {
        throw new NotImplementedException();
    }

    public boolean greaterThan(Two b) {
        throw new NotImplementedException();
    }

    public Two copy() {
        throw new NotImplementedException();
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public Two add(Two t) {
        throw new NotImplementedException();
    }

    public Two subtract(Two t) {
        throw new NotImplementedException();
    }

    @Override
    public String toString() {
        return "Two{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }

    public void test() {

    }
}