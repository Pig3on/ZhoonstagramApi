package vua.pavic.ZhoonstagramApi.model;

public enum UserRoleEnum {
    ADMIN("ROLE_ADMIN"),
    FREE("ROLE_FREE"),
    GOLD("ROLE_GOLD"),
    PLATINUM("ROLE_PLATINUM");

    public final String roleValue;

    UserRoleEnum(String roleValue) {
        this.roleValue = roleValue;
    }
}
