public class One {

    private int i;
    private String s;
    private Two two;
    private Four four;

    public One(int i, String str) {
        this.i = i;
        this.s = str;
    }

    public void setS(String s) {
        this.s = s;
    }

    public int getI() {
        return i;
    }

    public Two getTwo() {
        return two;
    }

    public void setTwo(Two t) {
        this.two = t;
    }
    public void setFour(Four f) {
        this.four = f;
    }

    public Four getFour() {
        return four;
    }

    public void test() {


    }
}
