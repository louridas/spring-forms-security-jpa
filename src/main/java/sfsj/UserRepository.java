package sfsj;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findBySurname(String surname);
    
    List<User> findByUsername(String username);
 
}