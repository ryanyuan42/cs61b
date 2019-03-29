package byog.Core;
import java.io.Serializable;


public class Location implements Serializable {
    private static final long serialVersionUID = 7526471155622776147L;
    private int xPos = 0;
    private int yPos = 0;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setxPos(int x) {
        xPos = x;
    }

    public void setyPos(int y) {
        yPos = y;
    }
}
