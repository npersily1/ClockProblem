import java.awt.*;

public class Button {

    private int x, y, width, height;
    private Image i;

    public Button(int x, int y, int width, int height ) {

        int cornerX = x - width/2;
        int cornerY = y + height/2;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Returns true if the mouse is inside the area of the button
    public boolean isClicked(int mouseX, int mouseY) {
        return ((mouseX > x && mouseX < x + width) && (mouseY > y && mouseY < y + height));
    }
}