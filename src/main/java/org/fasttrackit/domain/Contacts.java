package org.fasttrackit.domain;

public class Contacts {

    private long contact_id;
    private String name;
    private String first_name;
    private String phone_number;

    public long getUser_id() {
        return contact_id;
    }

    public void setUser_id(long contact_id) {
        this.contact_id = contact_id;
    }

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
        return "Users{" +
                "user_id=" + contact_id +
                ", name='" + name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                '}';
    }
}
