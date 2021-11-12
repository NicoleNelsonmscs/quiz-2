import java.util.ArrayList;

public class Four {
    private int i;
    private final ArrayList threes = new ArrayList<>();

    public Four() {
        this.i = 0;
    }

    public Four(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void addThree(Three three){
        this.threes.add(three);
    }

    public Three getFirstThree(){
        return (Three) this.threes.get(0);
    }

    public Three getNextThree(){
        return (Three) this.threes.get(i++);
    }

    public void test() {
        if (threes.size() > 0)
            String str = threes.get(0).getI();
        }
    }
