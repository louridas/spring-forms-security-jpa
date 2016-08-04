package sfsj;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    
    private final UserRepository userRepository;
    
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // This is required so that the many-to-many collection is populated, because
    // we must ensure that the session is still available.
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        
        List<User> users = userRepository.findByUsername(username);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("No user found " +
                    "with username: " + username);
        } 
        User user = users.get(0);
        
        // Having made sure that the session is still available, we populate
        // the roles list.
        user.getRoles().size();
        return new CustomUserDetails(user);
    }

}
