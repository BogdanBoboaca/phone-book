package org.fasttrackit.transfer;

public class UpdateContactRequest {

    private String name;
    private String first_name;
    private String phone_number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "UpdateContactRequest{" +
                "name='" + name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                '}';
    }
}
