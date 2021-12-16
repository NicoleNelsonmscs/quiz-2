public class Class2 extends Class3 {
    int value;
    String key;

    // D
    public Class2() {
        super();
        this.value = 1;
        this.key = "base";
    }

    // E
    public Class2(int var1, String var2) throws NumberOutOfRange {
        super();
        if (var1 <= 0)
            throw new NumberOutOfRange();
        this.value = var1;
        this.key = var2;
    }

    // F
    public Class2(int var1, String var2, int var3, String var4) throws NumberOutOfRange {
        super(var3, var4);
        this.value = var1;
        this.key = var2;
    }

    // G
    public String toString(){
        return "Class2{key:" + key + ", value:" + value + ", [" + super.toString() + "]}";
    }
}
