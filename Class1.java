public class Class1 {
    int value;
    String key;

    // A
    public Class1() {
        this.value = 1;
        this.key = "base";
    }

    // B
    public Class1(int value, String key) {
        this.value = value;
        this.key = key;
    }

    // C
    public String toString() {
        return "Class1{key:" + key + ", value:" + value +  "}";
    }
}
