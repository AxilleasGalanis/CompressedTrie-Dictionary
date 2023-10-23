# Compressed Trie Dictionary
This is a Java implementation of a dictionary based on a compressed trie (radix tree) structure designed for the English language(a-z).

# Features
● **-i word:** Inserts *word* into Trie.

● **-r word:** Deletes *word* from the Trie.

● **-f word:** Searches the *word* in the Trie.

● **-p:** Prints the pre-order traversal of Trie.

● **-d:** Prints all the words stored in the dictionary in alphabetical order.

● **-w word X:** Searches the Trie for all words of the same length as *word* that are exactly X characters away from the given *word*. For example, the words boy and toy are one character apart.
the words small and smell are one character apart.
the words small and smile are two characters apart.
the words newspaper and newsgroup are five characters apart.

● -s suffix: Searches the Trie for words containing the given suffix.

● -q: Prints the string “Bye bye!” followed by a newline character and terminates the program.

# Compilation and Execution.
Compile the files using the command:
```
javac Dictionary/Dictionary.java
```
Execute the program using the command:
```
java Dictionary/Dictionary
```
