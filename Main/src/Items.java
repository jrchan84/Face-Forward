public class Items {

    private String name;
    private String description;
    private String imgPath;
    private String price;
    private Products_API api = new Products_API();

    public Items(String keyword, String category){
        api.search(keyword, category);
        this.setDescription(api.getDescription());
        this.setImgPath(api.getImagePath());
        this.setPrice(api.getPrice());
        this.setName(api.getName());
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

    public String getImgPath() {
        return this.imgPath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
