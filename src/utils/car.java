package utils;

public class car {

    String name;
    String price;
    String miles;
    String zip_code;

    public car() {};

    public car (String name, String price, String miles, String zip_code) {
        this.name = name;
        this.price = price;
        this.miles = miles;
        this.zip_code = zip_code;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getMiles () { return miles; }

    public String getZip_code() { return zip_code; }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setPrice(String newPrice) { this.price = newPrice;}

    public void setMiles(String newMiles) {
        this.miles = newMiles;
    }
}
