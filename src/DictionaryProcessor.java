import java.io.FileReader;
import java.util.Scanner;

public class DictionaryProcessor {
    private SeparateChainingHashST<Integer> DictSC;
    private LinearProbingHashST DictLP;

    public void readDictionaryintoSCST(String fileName, String hashMode) {
        try {
            SeparateChainingHashST DictST = new SeparateChainingHashST(hashMode);
            FileReader mitWords = new FileReader(fileName);
            Scanner scanner = new Scanner(mitWords);                                  // maybe make a file reader class?
            while(scanner.hasNextLine()) {
                String password = scanner.nextLine();
                DictST.put(password, password); // needs 2 arguments -- I just made them both password
            }
            this.DictSC = DictST;
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
    public boolean searchSC(String keyword){
        //DictSC.get(keyword); // returns a Value of an object...
        return DictSC.get(keyword) != null;
    }
    public void readDictionaryintoLPST(String fileName, String hashMode) {
        try {
            LinearProbingHashST DictLP = new LinearProbingHashST(hashMode);
            FileReader mitWords = new FileReader(fileName);
            Scanner scanner = new Scanner(mitWords);                                  // maybe make a file reader class?
            while(scanner.hasNextLine()) {
                String password = scanner.nextLine();
                DictLP.put(password, password); // needs 2 arguments -- I just made them both password
            }
            this.DictLP = DictLP;
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
    public boolean searchLP(String keyword){
        return DictLP.get(keyword) != null;
    }


}
