public class Class5 {
    int value;
    String key;
    Class1 c1;
    Class3 c3;

    // O
    public Class5(int var1, String var2, int var3, String var4) throws NumberOutOfRange {
        c3 = new Class3(var3, var4);
        c1 = new Class1();
        this.value = var1;
        this.key = var2;
    }

    // P
    public Class5(int var1, String var2, int var3, String var4, int var5, String var6) throws NumberOutOfRange {
        c3 = new Class3(var3, var4);
        c1 = new Class1(var5, var6);
        this.value = var1;
        this.key = var2;
    }

    // Q
    public String toString() {
        return "Class5{key:"  + key + ", value:" + value + ", [" + c1.toString() + ", " + c3.toString() + "]}";
    }
}
