package be.kdg.fraudedetection.bl.dom.roles;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@DiscriminatorValue("ROLE_STAFF")
public class Staff extends Role{
    @Override
    public RoleType getRoleType() {
        return RoleType.ROLE_STAFF;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_STAFF"));
        return authorities;
    }
}
