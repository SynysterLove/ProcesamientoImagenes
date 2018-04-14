package domain.filter;

public abstract class Mask {

    private final Type type;
    private final int size;

    public Mask(Type type, int size) {
        this.type = type;
        this.size = size;
    }

    public Type getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public abstract double getValue(int x, int y);

    public enum Type {
        MEDIA
    }
}
