package be.kdg.fraudedetection.presentation.DTO;


import javax.validation.constraints.NotNull;
import java.util.Objects;

public class PersonDTO {
    @NotNull
    private String name;

    @NotNull
    private String role;

    public PersonDTO() {
    }

    public PersonDTO(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(name, personDTO.name) &&
                Objects.equals(role, personDTO.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, role);
    }
}
