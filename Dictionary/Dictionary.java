package Dictionary;
import java.util.Scanner;
/**
 *
 * @author Achilleas
 */

public class Dictionary {
    public static void main (String[] args){
        Trie ct;
        ct = new Trie();
        boolean ret;

        System.out.println("?: ");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String string[] = line.split(" ", 3); //split the line in maximum 3 substrings
            String operation = string[0]; //gives the operation
            switch (operation) {
                case "-i":
                    string[1].toLowerCase();
                    ret = ct.insert(string[1], ct.root);
                    if (ret) {
                        System.out.println("ADD " + string[1] + " OK");
                    } else {
                        System.out.println("ADD " + string[1] + " NOK");
                    }
                    break;
                case "-r":
                    string[1].toLowerCase();
                    ret = ct.remove(string[1], ct.root);
                    if (ret) {
                        System.out.println("RMV " + string[1] + " OK");
                    } else {
                        System.out.println("RMV " + string[1] + " NOK");
                    }
                    break;
                case "-f":
                    string[1].toLowerCase();
                    ret = ct.search(ct.root, string[1]);
                    if (ret) {
                        System.out.println("FND " + string[1] + " OK");
                    } else {
                        System.out.println("FND " + string[1] + " NOK");
                    }
                    break;
                case "-p":
                    System.out.print("PreOrder:");
                    ct.printPreorder(ct.root);
                    System.out.print("\n");
                    break;
                case "-d":
                    System.out.println("\n***** Dictionary *****");
                    ct.printDictionary(ct.root, "");
                    System.out.print("\n");
                    break;
                case "-w":
                    string[1].toLowerCase();
                    System.out.println("\nDistant words of " + string[1] + " (" + string[2] + "):");
                    int distance = Integer.parseInt(string[2]);
                    ct.distant(ct.root, "", string[1], distance);
                    System.out.print("\n");
                    break;
                case "-s":
                    string[1].toLowerCase();
                    System.out.println("\nWords with suffix " + string[1] + ":");
                    ct.suffix(ct.root, "", string[1]);
                    System.out.print("\n");
                    break;
                case "-q":
                    sc.close();
                    System.out.println("Bye bye!");
                    return ;
            }   
            System.out.println("?: ");
        }
    }
}