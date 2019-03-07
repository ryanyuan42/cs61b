public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> list = new ArrayDeque2<Character>();
        for (int i = 0; i < word.length(); i++) {
            list.addLast(word.charAt(i));
        }
        return list;
    }

    public boolean isPalindrome(String word) {
        int wordLength = word.length();
        if (wordLength == 0 | wordLength == 1) {
            return true;
        }

        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindrome(wordDeque);

    }

    public boolean isPalindrome(Deque<Character> wordDeque) {
        if (wordDeque.size() == 1 | wordDeque.size() == 0) {
            return true;
        }
        char start = wordDeque.removeFirst();
        char end = wordDeque.removeLast();
        return (start == end) & (isPalindrome(wordDeque));
    }

}
