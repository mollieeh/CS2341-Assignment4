public class SeparateChainingHashST<Value> {
    private int M = 20000;
    private Node[] st = new Node[M];
    private String hashMode;
    public static final String HASH_MODE_OLD = "old";
    public static final String HASH_MODE_CURRENT = "current";
    private int comparisonSearchAmount = 0;

    public SeparateChainingHashST(String hashMode) {
        this.hashMode = hashMode;
    }

    private static class Node // can you just create an object without it being the name of the class?
    {
        private String key;
        private Object val;
        private Node next;
        public Node(String key, Object val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
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


    public Value get(String key) {
        int i = hash(key);
        comparisonSearchAmount = 0;
        for (Node x = st[i]; x!= null; x = x.next){
            comparisonSearchAmount++;
            if (key.equals(x.key))
            {
                return (Value) x.val;
            }
        }
        return null;
    }

    public void put(String key, Value val) {
        int i = hash(key);
        for (Node x = st[i]; x!= null; x = x.next) {
            if (key.equals(x.key)) { x.val = val; return; }
        }
        st[i] = new Node(key, val, st[i]);
    }

    public int getComparisonSearchAmount() {
        return comparisonSearchAmount;
    }
}
