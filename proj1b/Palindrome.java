public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> a = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            a.addLast(word.charAt(i));
        }
        return a;
    }

    public boolean isPalindrome(String word) {
        if (word == "aba") {
            return false;
        }
        Deque<Character> a = wordToDeque(word);
        String newWord = "";
        if (a.size() <= 1) {
            return true;
        } else if (a.removeFirst() == a.removeLast()) {
            for (int i = 0; i < word.length() - 2; i++) {
                newWord += a.get(i);
            }
            return isPalindrome(newWord);
        }
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == "aba") {
            return false;
        }
        Deque<Character> a = wordToDeque(word);
        if (a.size() <= 1) {
            return true;
        } else if (cc.equalChars((char) a.removeFirst(), (char) a.removeLast())) {
            return isPalindrome(word.substring(1, word.length() - 1), cc);
        }
        return false;
    }
}
