public class Main {

    public static Products_API PI = new Products_API();
    public static Items item;

    public static void main(String[] args)
    {
        item = PI.search("phone", "pcmcat209400050001");
        System.out.println(item.getName());
    }
}
