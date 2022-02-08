package model.entity.enums;

/**
 * ENUM for all DB user roles
 *
 */
public enum Role {
    GUEST(0),
    USER(1),
    ADMIN(2),
    MANAGER(3);

    private int role;

    Role(int role){this.role = role;}

    public int getRole() {
        return role;
    }


}
