package Dictionary;
/**
 *
 * @author Achilleas
 */

public class TrieNode {
    TrieNode[] children;
    String word;
    boolean isWord; //determines if a leaf is a word

    public TrieNode (){
        children = new TrieNode[26];
        word = "";
        isWord = false;
    }
}
