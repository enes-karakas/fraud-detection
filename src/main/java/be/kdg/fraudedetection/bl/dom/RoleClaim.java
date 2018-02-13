package be.kdg.fraudedetection.bl.dom;

/**
 * @author Loïc Gijsemans
 * @version 1.0 13/02/18 13:01 *
 */
public enum RoleClaim {
    DRIVER("driver"), PASSENGER("passenger"), WITNESS("witness");

    private String role;

    RoleClaim(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return String.format("%s", role);
    }
}
