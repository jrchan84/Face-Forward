public class Items {

    public String name;
    public String description;
    public String imgPath;
    public String price;

    public Items(){
        this.name = null;
        this.description = null;
        this.imgPath = null;
        this.price = null;
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

    public String getImagePath() {
        return this.imgPath;
    }
}
