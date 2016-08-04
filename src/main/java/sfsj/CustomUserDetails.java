package sfsj;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

public class CustomUserDetails implements UserDetails {
    
    private static final long serialVersionUID = 1L;
    
    private User user;
    
    public CustomUserDetails(User user) {
        this.user = user;
    }
        
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = this.user.getRoles();
        List<String> userRolesStrings = roles.stream()
                .map(r -> r.getName())
                .collect(Collectors.toList());
                
        String strRoles =  StringUtils.collectionToCommaDelimitedString(
                userRolesStrings);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(strRoles);
    }

    public String getPassword() {
        return user.getPassword();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

}
