package be.kdg.fraudedetection.bl.dom.roles;

import be.kdg.fraudedetection.bl.dom.User;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@DiscriminatorColumn(name = "RoleType", discriminatorType = DiscriminatorType.STRING)
public abstract class Role {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Integer roleId;

    @ManyToOne(targetEntity = User.class)
    private User user;

    public Role() {
    }

    public static <T extends Role> boolean hasRole(User user, Class<T> role) throws RuntimeException {
        try {
            loadRole(user, role);
            return true;
        } catch (RuntimeException use) {
            return false;
        }
    }

    public static <T extends Role> T loadRole(User user, Class<T> role) throws RuntimeException {
        List<Role> roles = user.getRoles();
        Optional<T> result = (Optional<T>) roles
                .stream()
                .filter(r -> role.isInstance(r))
                .findAny();

        if (!result.isPresent())
            throw new RuntimeException("Incorrect role for user");

        return result.get();
    }

/*    public static Role toRole(RoleType roleType) {
        switch (roleType) {
            case ROLE_ADMIN:
                return new Adminstrator();
            default:
                return new Client();
        }
    }*/

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId) &&
                Objects.equals(user, role.user);
    }

    public abstract RoleType getRoleType();

    public abstract Collection<? extends GrantedAuthority> getAuthorities();

    @Override
    public int hashCode() {

        return Objects.hash(roleId, user);
    }

    public enum RoleType {
        ROLE_CLIENT, ROLE_STAFF
    }
}