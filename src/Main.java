public class Main {
    public static void main(String[] args) {
        PasswordInputReader passwordChecker = new PasswordInputReader();
        DictionaryProcessor dictProcessorSChash1 = new DictionaryProcessor();
        DictionaryProcessor dictProcessorSChash2 = new DictionaryProcessor();
        DictionaryProcessor dictProcessorLPhash1 = new DictionaryProcessor();
        DictionaryProcessor dictProcessorLPhash2 = new DictionaryProcessor();
        //SeparateChainingHashST hash = new SeparateChainingHashST(5);
        passwordChecker.readPasswords("input.txt");

        // input all the passwords to be checked into the passwordChecker ArrayList<Strings>
        //passwordChecker.checksLengthofPasswords((passwordChecker.readPasswords("input.txt")));

        // set the dictionary of the processor object to be the ST of mitwords.txt
        dictProcessorSChash1.readDictionaryintoSCST("mitwords.txt",SeparateChainingHashST.HASH_MODE_OLD);
        dictProcessorSChash2.readDictionaryintoSCST("mitwords.txt",SeparateChainingHashST.HASH_MODE_CURRENT);
        dictProcessorLPhash1.readDictionaryintoLPST("mitwords.txt",LinearProbingHashST.HASH_MODE_OLD);
        dictProcessorLPhash2.readDictionaryintoLPST("mitwords.txt",LinearProbingHashST.HASH_MODE_CURRENT);

        // checks passwords using Seperate Chaining
        ConsoleColors.println("Seperate Chaining:", "green");
        for (String password : passwordChecker.getPasswords()){ // for each password from input.txt
            if (password.length() < 8) {
                ConsoleColors.println("password not strong; too short ","cyan");
            } else {
                String passwordMinusLastChar = password.substring(0,password.length()-1);
                char lastChar = password.charAt(password.length()-1);
                if (dictProcessorSChash1.searchSC(password)) {
                    System.out.print("password: " + password);
                    ConsoleColors.print("\npassword not strong; word found in dictionary\n", "cyan");
                } else if (dictProcessorSChash1.searchSC(passwordMinusLastChar)) {
                    if (Character.isDigit(lastChar)) {
                        System.out.print("password: " + password);
                        ConsoleColors.print("\npassword not strong; word found in dictionary with digit added\n", "cyan");
                    } else {
                        System.out.print("password: " + password);
                        ConsoleColors.print("\nstrong password!\n", "yellow");
                    }
                } else {
                    System.out.print("password: " + password);
                    ConsoleColors.print("\nstrong password!\n", "yellow");
                }
            }
            dictProcessorSChash1.searchSC(password);
            ConsoleColors.println("Amount of comparisons using old hash: " + dictProcessorSChash1.getCountSC(), "violet");
            dictProcessorSChash2.searchSC(password);
            ConsoleColors.println("Amount of comparisons using CURRENT hash: " + dictProcessorSChash2.getCountSC() + "\n", "INDIGO");
        }

        // checks passwords using Linear Probing
        ConsoleColors.println("\nLinear Probing:", "green");
        for (String password : passwordChecker.getPasswords()){ // for each password from input.txt
            if (password.length() < 8) {
                System.out.print("password: " + password);
                ConsoleColors.print("\npassword not strong; word found in dictionary\n", "cyan");
            } else {
                String passwordMinusLastChar = password.substring(0,password.length()-1);
                char lastChar = password.charAt(password.length()-1);
                if (dictProcessorLPhash1.searchLP(password)) {
                    System.out.print("password: " + password);
                    ConsoleColors.print("\npassword not strong; word found in dictionary\n", "cyan");
                } else if (dictProcessorLPhash1.searchLP(passwordMinusLastChar)) {
                    if (Character.isDigit(lastChar)) {
                        System.out.print("password: " + password);
                        ConsoleColors.print("\npassword not strong; word found in dictionary with digit added\n", "cyan");
                    } else {
                        System.out.print("password: " + password);
                        ConsoleColors.print("\nstrong password!\n", "yellow");
                    }
                } else {
                    System.out.print("password: " + password);
                    ConsoleColors.print("\nstrong password!\n", "yellow");
                }
            }
            dictProcessorLPhash1.searchLP(password);
            ConsoleColors.println("Amount of comparisons using old hash: " + dictProcessorLPhash1.getCountLP(), "violet");
            dictProcessorLPhash2.searchLP(password);
            ConsoleColors.println("Amount of comparisons using CURRENT hash: " + dictProcessorLPhash2.getCountLP() + "\n", "INDIGO");
        }
    }
}
