public class HashFunctions {

    //earlier
    public static int hashCodeOld(String key) {
        int hash = 0;
        int skip = Math.max(1, key.length() / 8);
        for (int i = 0; i < key.length(); i += skip)
            hash = (hash * 37) + key.charAt(i);
        return hash;
    }

    //current
    public static int hashCodeCurr(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++)
            hash = (hash * 31) + key.charAt(i);
        return hash;
    }
}
