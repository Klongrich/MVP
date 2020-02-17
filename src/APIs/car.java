package APIs;

public class car {

    String name;
    String price;
    String miles;

    public car (String name, String price, String miles) {
        this.name = name;
        this.price = price;
        this.miles = miles;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getMiles () {
        return miles;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setPrice(String newPrice) {
        this.price = newPrice;
    }

    public void setMiles(String newMiles) {
        this.miles = newMiles;
    }
}
