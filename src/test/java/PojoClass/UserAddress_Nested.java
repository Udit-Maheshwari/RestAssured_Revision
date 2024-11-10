package PojoClass;

public class UserAddress_Nested {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private UserAddress_Geo_Nested geo;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public UserAddress_Geo_Nested getGeo() {
        return geo;
    }

    public void setGeo(UserAddress_Geo_Nested geo) {
        this.geo = geo;
    }
}
