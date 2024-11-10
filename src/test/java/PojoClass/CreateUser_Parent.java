package PojoClass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUser_Parent {

    CreateUser_Nested description;

    public CreateUser_Nested getDescription() {
        return description;
    }

    public void setDescription(CreateUser_Nested description) {
        this.description = description;
    }
}
