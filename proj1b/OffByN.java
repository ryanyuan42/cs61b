public class OffByN implements CharacterComparator {

    private int offset;

    public OffByN(int N) {
        offset = N;
    }

    public boolean equalChars(char x, char y) {
        return Math.abs(Character.toUpperCase(x) - Character.toUpperCase(y)) == offset;
    }

}
