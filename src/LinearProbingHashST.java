public class LinearProbingHashST<Value> {
    private int M = 20000; // size of dictionary
    private Value[] vals = (Value[]) new Object[M];
    private String[] keys = new String[M];
    private String hashMode;
    public static final String HASH_MODE_OLD = "old";
    public static final String HASH_MODE_CURRENT = "current";
    private int comparisonSearchAmount = 0;

    public LinearProbingHashST(String hashMode) {
        this.hashMode = hashMode;
    }
    private int hash(String key) {
        if (hashMode.equals(HASH_MODE_OLD)) {
            return (HashFunctions.hashCodeOld(key) & 0x7fffffff) % M;
        } else if (hashMode.equals(HASH_MODE_CURRENT)) {
            return (HashFunctions.hashCodeCurr(key) & 0x7fffffff) % M;
        } else {
            return 0;
        }
//        return (hashCode(key) & 0x7fffffff) % M;
    }

    public void put(String key, Value val) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (keys[i].equals(key)) break;
        }
        keys[i] = key;
        vals[i] = val;
    }

    public Value get(String key) {
        for (int i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (key.equals(keys[i])){
                return vals[i];
            }
        }
        return null;
    }

    public int getComparisonSearchAmount() {
        return comparisonSearchAmount;
    }
}
