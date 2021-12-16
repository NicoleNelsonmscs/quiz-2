public class Class3 {
    int key;
    String value;

    // H
    public Class3() {
        key = 0;
        value = "n/a";
    }

    // I
    public Class3(int var1, String var2) throws NumberOutOfRange {
        if (var1 < 3)
            throw new NumberOutOfRange();

        this.key = var1;
        this.value = var2;
    }

    // J
    public String toString() {
        return "Class3{key:" + key + ", value:" + value + "}";
    }
}
