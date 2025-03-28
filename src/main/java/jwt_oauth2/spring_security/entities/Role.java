package jwt_oauth2.spring_security.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_roles")
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "role_id")
    private Long roleId;

    private String name;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // enum class for const values for constants admin and basic
    public enum Values {
        ADMIN(1L),
        BASIC(2L); //


        long roleId;

        // constructor of enum class
        Values(long roleId) {
            this.roleId = roleId;
        }

        public long getRoleId() {
            return roleId;
        }
    }
}
