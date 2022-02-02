package model.entity.enums;

public enum Role {
    GUEST(0),
    USER(1),
    ADMIN(2);

    private int role;

    Role(int role){this.role = role;}

    public int getRole() {
        return role;
    }


}
