public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == 1;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
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
