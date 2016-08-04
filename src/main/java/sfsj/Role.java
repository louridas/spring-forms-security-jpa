package sfsj;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    private String name;
    
    protected Role() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
