package model.entity.enums;

public enum Role {
    GUEST(1),
    USER(2),
    ADMIN(3);

    private int role;

    Role(int role){this.role = role;}

    public int getRole() {
        return role;
    }


}
