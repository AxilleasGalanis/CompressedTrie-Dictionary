package Dictionary;
/**
 *
 * @author Achilleas
 */

public class Trie {
    public TrieNode root;

    public Trie (){
        root = new TrieNode();
    }
    
    public boolean insert (String string, TrieNode curr){
        if(string == null || string.length() == 0) {
            return false;
        }

        TrieNode node = curr.children[string.charAt(0) - 97]; //find first node that corresponds a word
        if (node == null) { //node doesnt exist, make new one
            node = new TrieNode();
            node.isWord = true;
            node.word = string;
        } else if (node.word.equals(string)) { //if it exists mark it as word
            if (node.isWord) {
                return false;
            } else {
                node.isWord = true;
            }
        } else if (string.startsWith(node.word)) { //node is the start of the word so put the rest recurssively
            return insert(string.substring(node.word.length()), node);
        } else if (node.word.startsWith(string)) { //node is longer than the word, split node
            TrieNode newChild = new TrieNode();
            newChild.word = node.word.substring(string.length());
            newChild.isWord = node.isWord;
            newChild.children = node.children;
            node.isWord = true;
            node.word = string;
            node.children = new TrieNode[26];
            node.children[newChild.word.charAt(0) - 97] = newChild;
        } else { //node starts the same, they have different endings
            TrieNode newChild = new TrieNode();
            //figure out the index of the first char they differ
            int i = 0;
            while (node.word.charAt(i) == string.charAt(i)) i++;
            newChild.word = node.word.substring(i);
            newChild.isWord = node.isWord;
            newChild.children = node.children;
            node.isWord = false;
            node.word = node.word.substring(0, i);
            node.children = new TrieNode[26];
            node.children[newChild.word.charAt(0) - 97] = newChild;
            return insert(string.substring(i), node);
        }
        curr.children[string.charAt(0) - 97] = node; //make the manipulated node part of the tree
        return true;
    }

    public int numberOfChildren (TrieNode curr){ //find number of children
        int counter = 0;
        for (int i = 0; i < 26; i++) { 
            if (curr.children[i] != null)
                counter++;
        }
        return counter;
    }

    public TrieNode findChild (TrieNode curr) { //find child node
        int i;
        for (i = 0; i < 26; i++) { 
            if (curr.children[i] != null)
                break;
        }
        return curr.children[i];
    }

    public boolean remove (String word, TrieNode curr) {
        if(word == null || word.length() == 0) {
            return false;
        }
        
        TrieNode node = curr.children[word.charAt(0) - 97]; //find first node that corresponds word

        if (node == null) {
            return false;
        } else if (node.word.equals(word)) {
            int childrenCount = numberOfChildren(node);
            if (childrenCount == 0) { //if it doesn't have children, remove it
                node = null;
            } else if (childrenCount == 1) { //if parent node has 1 child, unite them 
                TrieNode newChild = findChild(node);
                node.word += newChild.word;
                node.children = newChild.children;
                node.isWord = newChild.isWord;
            } else { //if it has more than 1 children, mark it as not a word
                node.isWord = false;
            }
        } else if (word.startsWith(node.word)) { //if the node is only the first part of the string, remove the rest 
            boolean ret = remove(word.substring(node.word.length()), node);
            int newChildrenCount = numberOfChildren(node);
            if (newChildrenCount == 1 && !node.isWord) { //if parent node has 1 child and is not a word, unite them 
                TrieNode new_Child = findChild(node);
                node.word += new_Child.word;
                node.children = new_Child.children;
                node.isWord = new_Child.isWord;
            }
            return ret;
        } else { 
            return false;
        }
        curr.children[word.charAt(0) - 97] = node; //make the manipulated node part of the tree
        return true;
    }

    public boolean search (TrieNode curr, String word) {
        if (word == null || word.length() == 0) return false; 
        TrieNode node = curr.children[word.charAt(0) - 97];
        
        if (node == null) return false;
        if (node.word.equals(word)) return node.isWord; //if string is found, return whether it's actually marked as a word
        if (word.startsWith(node.word)) return search(node, word.substring(node.word.length())); //if part of the word is found, search for the rest
        return false; 
    }

    public void printPreorder (TrieNode curr) {
        System.out.print(curr.word);
        if(curr.isWord) {
            System.out.print("# ");
        } else {
            System.out.print(" ");
        }
        for (TrieNode node : curr.children) { 
            if (node != null)
                printPreorder(node);
        }
    }

    public void printDictionary (TrieNode curr, String word) {
        if (curr.isWord) //if it is a word, print it
            System.out.println(word);
        for (TrieNode node : curr.children) { 
            if (node != null)
                printDictionary(node, word + node.word);
        }
    }

    public void distant (TrieNode curr, String word, String basicWord, int distance){
        if (curr.isWord && basicWord.length() == word.length()){ //if it is a word and has same length with the word we need
            int counter = 0;
            for(int i = 0; i < word.length(); i++){
                if (basicWord.charAt(i) != word.charAt(i)) counter++; //how much they are the same
            }
            if(counter == distance){
                System.out.println(word);
            }
        }
        for (TrieNode node : curr.children) { 
            if (node != null)
                distant(node, word + node.word, basicWord, distance);
        }
    }

    public void suffix(TrieNode curr, String word, String suffix){
        if (curr.isWord && word.endsWith(suffix)) //if the word has the suffx we want, print the word
            System.out.println(word);
        for (TrieNode node : curr.children) { 
            if (node != null)
                suffix(node, word + node.word, suffix);
        }
    }
}