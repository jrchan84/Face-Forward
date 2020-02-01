import java.io.File;


public class WebcamIO {

    int COUNT = 1;

    public WebcamIO() {

    }

    public File getImage() {
        File image = null;
        try {
            Runtime.getRuntime().exec("imagesnap src/"+COUNT+".png ");
            image = new File("0.png");
            COUNT++;
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Snap Error");
            throw new Error("You must allow the webcam to work!");
        }
        return image;
    }

    public int getCount(){
        return COUNT-1;
    }
}
