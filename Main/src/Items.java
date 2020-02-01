import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    public void setMacbook() {
        this.name = "MacBook";
        this.price = "$2,999.00";
        this.description= "Designed for those who defy limits and change the world, " +
                "the new MacBook Pro is by far the most powerful notebook we’ve ever made. " +
                "With an immersive 16-inch Retina display, superfast processors, next-generation graphics, " +
                "the largest battery capacity ever in a MacBook Pro, a new Magic Keyboard and massive storage, " +
                "it’s the ultimate pro notebook for the ultimate user.";
        try {
            img = ImageIO.read(new File("strawberry.jpg"));
        } catch (IOException e) {
            System.out.println("invalid img");
        }
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
