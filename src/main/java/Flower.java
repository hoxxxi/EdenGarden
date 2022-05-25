public class Flower implements Comparable<Flower>{
    private final char type;
    private final int spaceRestriction;

    public Flower(char type, int spaceRestriction) {
        this.type = type;
        this.spaceRestriction = spaceRestriction;
    }

    public char getType() {
        return type;
    }

    public int getSpaceRestriction() {
        return spaceRestriction;
    }

    @Override
    public int compareTo(Flower flower) {
        return Integer.compare(this.spaceRestriction, flower.getSpaceRestriction());
    }
}
