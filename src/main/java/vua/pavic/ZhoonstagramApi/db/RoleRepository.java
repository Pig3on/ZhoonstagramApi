package vua.pavic.ZhoonstagramApi.db;


import org.springframework.data.jpa.repository.JpaRepository;
import vua.pavic.ZhoonstagramApi.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findRoleByRoleName(String roleName);
}
