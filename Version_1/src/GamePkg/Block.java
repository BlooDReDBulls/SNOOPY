package GamePkg;

public class Block {
    protected int x;
    protected int y;

    public Block(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
