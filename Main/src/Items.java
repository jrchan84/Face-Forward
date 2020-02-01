import java.awt.image.BufferedImage;

public class Items {

    public String name;
    public String description;
    public BufferedImage img;
    public String price;

    public Items(){
        this.name = null;
        this.description = null;
        this.img = null;
    }

    public String getName() {
        return this.name;
    }

    public String getPrice() {
        return this.price;
    }

    public String getDescription() {
        return this.description;
    }

    public BufferedImage getImage() {
        return this.img;
    }
}
