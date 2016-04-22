/**
 * Created by renaganti on 4/22/16.
 */
import java.util.*;

class SearchTrie {
    public static void main(String args[] ) throws Exception {
        Scanner reader = new Scanner(System.in);
        int n = Integer.parseInt(reader.next());
        int q = Integer.parseInt(reader.next());
        int i;
        Trie trie = new Trie();
        for(i = 0; i < n; i++) {
            String keyword = reader.next();
            int rank = Integer.parseInt(reader.next());
            trie.insert(keyword,rank);
        }

        for(i = 0; i < q; i++) {
            String query = reader.next();
            System.out.println(trie.findBest(query));
        }
    }
    static class TrieNode {
        public char letter;
        public Map<Character, TrieNode> children;
        public int score;
        public TrieNode() {
            letter = '#';
            children = new HashMap<Character,TrieNode>();
            score = -1;
        }
    }
    static class Trie {
        private TrieNode root;
        public Trie() {
            root = new TrieNode();
        }
        public void insert(String strInput, int rank) {
            TrieNode temp = root;
            int i;
            for(i = 0; i < strInput.length(); i++) {
                TrieNode child = temp.children.get(strInput.charAt(i));
                if(child == null) {
                    TrieNode newNode = new TrieNode();
                    newNode.letter = strInput.charAt(i);
                    newNode.score = rank;
                    temp.children.put(strInput.charAt(i), newNode);
                    temp = newNode;
                }
                else {
                    temp = child;
                    child.score = Math.max(child.score, rank);
                }
            }
        }
        public int findBest(String input) {
            TrieNode temp = root;
            int i;
            int maxScore = -1;
            for(i = 0; i < input.length(); i++) {
                TrieNode child = temp.children.get(input.charAt(i));
                if(child != null) {
                    temp = child;
                    maxScore = temp.score;
                }
                else
                    break;
            }

            if(i < input.length())
                return -1;

            while(temp != null &&!temp.children.isEmpty()) {
                TrieNode maxNode = null;
                for(Map.Entry<Character,TrieNode> entry: temp.children.entrySet()) {
                    if( entry.getValue().score > maxScore ) {
                        maxNode = entry.getValue();
                        maxScore = entry.getValue().score;
                    }
                }
                temp = maxNode;
            }
            return maxScore;
        }
    }
}
