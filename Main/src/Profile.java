import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Profile {

    private String firstName;
    private String lastName;
    private int age;
    private String emailAddress;
    private String passWord;

    private List<Item> recentItems;
    private List<Item> wishlist;
    private List<Item> purchases;
    private List<Item> recommendations;

    public Profile(String firstName, String lastName, String emailAddress, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.passWord = password;

        recentItems = new ArrayList<Item>();
        wishlist = new ArrayList<Item>();
    }

    public void setFirstName(String name) {
        this.firstName = firstName;
    }

    public void setLastName(String name) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.emailAddress = email;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void addrecentItem(Item item) {
        recentItems.add(item);
    }

    public void removerecentItem(Item item) {
        for (int i = 0; i < recentItems.size(); i++) {
            if (Objects.equals(recentItems.get(i), item)) {
                recentItems.remove(i);

            }
        }
    }

    public void addWishListItem(Item item) {
        wishlist.add(item);
    }

    public void removerecentItem(Item item) {
        for (int i = 0; i < wishlist.size(); i++) {
            if (Objects.equals(wishlist.get(i), item)) {
                wishlist.remove(i);
            }
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return emailAddress;
    }

    public List<Item> getRecentItems() {
        return recentItems;
    }

    public void setJill() {
        this.firstName = "Jill";
        this.lastName = "Bao";
        this.emailAddress = "jill.ba00@gmail.com";
        this.passWord = "123456789";
        this.age = 19;

        recentItems.add(item1);
        recentItems.add(item2);

        purchases.add(item3);
        purchases.add(item4);
    }

    public void setAlex() {
        this.firstName = "Alex";
        this.lastName = "Lin";
        this.emailAddress = "alxdanderlin@gmail.com";
        this.passWord = "987654321";
        this.age = 19;
        recentItems.add(item1);
        recentItems.add(item2);

        purchases.add(item3);
    }

    public void setAlan() {
        this.firstName = "Alan";
        this.lastName = "Milligan";
        this.emailAddress = "alanjmilligan@gmail.com";
        this.passWord = "12123434";
        this.age = 19;
        recentItems.add(item1);
        recentItems.add(item2);

    }


}
