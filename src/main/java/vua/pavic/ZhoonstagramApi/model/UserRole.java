package vua.pavic.ZhoonstagramApi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UserRole {
    @Id
    public long id;
    public UserRoleEnum roleName;
}
