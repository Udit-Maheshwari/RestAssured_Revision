package PojoClass;

public class UserAddress_Parent {

    private String name;
    private String username;
    private String email;
    private UserAddress_Nested address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserAddress_Nested getAddress() {
        return address;
    }

    public void setAddress(UserAddress_Nested address) {
        this.address = address;
    }
}
