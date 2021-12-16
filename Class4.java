public class Class4 {

    int value;
    String key;
    Class2 c2;

    // K
    public Class4() {
        this.value = 1;
        this.key = "key1";
        c2 = new Class2();
    }

    // L
    public Class4(int value1, String key1, int value2, String key2, int value3, String key3) throws NumberOutOfRange {
        this.value = value1;
        this.key = key1;
        c2 = new Class2(value2, key2, value3, key3);
    }

    // M
    public String toString() {
        return "Class4{key:" + key + ", value:" + value + ", [" + c2.toString() + "]}";
    }
}
