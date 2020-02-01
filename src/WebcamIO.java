import java.io.File;


public class WebcamIO {

    int COUNT = 0;

    public WebcamIO() {

    }


    public static void main(String[] args) {
        WebcamIO test = new WebcamIO();
        test.getImage();
        test.getImage();
        test.getImage();
        test.getImage();
    }


    public File getImage() {
        File image = null;
        try {
            Runtime.getRuntime().exec("imagesnap "+COUNT+".png ");
            image = new File(COUNT+".png ");
            COUNT++;
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Snap Error");
            throw new Error("You must allow the webcam to work!");
        }
        return image;
    }
}
