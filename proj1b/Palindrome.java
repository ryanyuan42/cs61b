public class Palindrome {
    public static Deque<Character> wordToDeque(String word) {
        Deque<Character> list = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            list.addLast(word.charAt(i));
        }
        return list;
    }

    public static boolean isPalindrome(String word) {
        int wordLength = word.length();
        if (wordLength == 0 | wordLength == 1) {
            return true;
        }

        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindrome(wordDeque);

    }

    private static boolean isPalindrome(Deque<Character> wordDeque) {
        if (wordDeque.size() == 1 | wordDeque.size() == 0) {
            return true;
        }
        char start = wordDeque.removeFirst();
        char end = wordDeque.removeLast();
        return (start == end) & (isPalindrome(wordDeque));
    }

    public static boolean isPalindrome(String word, CharacterComparator cc) {
        char start;
        char end;
        int wordLength = word.length();
        for (int i = 0; i < wordLength / 2; i++) {
            start = word.charAt(i);
            end = word.charAt(wordLength - 1 - i);
            if (!cc.equalChars(start, end)) {
                return false;
            }
        }
        return true;
    }
}
