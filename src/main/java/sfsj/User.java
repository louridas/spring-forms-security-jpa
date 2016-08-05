package sfsj;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @Size(min=2, max=30)
    private String username;
    
    @Size(min=2, max=30)
    private String name;
    
    @Size(min=2, max=30)
    private String surname;

    @Transient
    @Size(min=8, max=30)
    private String plaintextPassword;

    @Transient
    @Size(min=8, max=30)
    private String plaintextPasswordConf;
    
    private String password;

    @Size(min=8, max=30)
    private String email;
   
    @Size(min=8, max=200)
    private String address;

    @NotNull
    @Min(18)
    private Integer age;

    @ManyToMany
    @JoinTable(name="user_roles",
    	joinColumns=@JoinColumn(name="user_id", referencedColumnName="id"),
    	inverseJoinColumns=@JoinColumn(name="role_id", 
    		referencedColumnName="id"))
    private List<Role> roles;
    
    protected User() { }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPlaintextPassword() {
        return plaintextPassword;
    }
    
    public void setPlaintextPassword(String plaintextPassword) {
        this.plaintextPassword = plaintextPassword;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPlaintextPasswordConf() {
        return plaintextPasswordConf;
    }

    public void setPlaintextPasswordConf(String plaintextPasswordConf) {
        this.plaintextPasswordConf = plaintextPasswordConf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String toString() {
        return "User(username: " + this.username + ", Age: " + this.age + ")";
    }
}
