package org.example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column
    private String userName;

    @Column
    private String userMessage;

    // cascade = CascadeType.ALL -> Esto es para que se propague a sus hijos y no necesitas que los datos de los hijos se tengan que persistir
    // orphanRemoval = true -> esto indica que los hijos de un padre deben desaparecer cuando el padre sea eliminado
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Phone> phone;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_live_address",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private List<Address> addresses;

    public User() {
        phone = new ArrayList<>();
        addresses = new ArrayList<>();
    }

    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }

    public List<Address> getaddresses() {
        return addresses;
    }

    public void setaddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public void addPhone(Phone phone) {
        this.getPhone().add(phone);
        phone.setUser(this);
    }

    public void removePhone(Phone phone) {
        this.getPhone().remove(phone);
        phone.setUser(null);
    }

    public void addAddress(Address address) {
        this.getaddresses().add(address);
        address.getUser().add(this);
    }

    public void removeAddress(Address address) {
        this.getaddresses().remove(address);
        address.setUser(null);
    }
}
