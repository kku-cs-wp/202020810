package kr.ac.kku.cs.wp.DaeKuen16.user.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole {

    @EmbeddedId
    private UserRoleId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "role", length = 45)
    private String roleName;

    // Constructors – default, fields
    public UserRole() {}

    public UserRole(User user, Role role, String roleName) {
        this.user = user;
        this.role = role;
        this.roleName = roleName;
        this.id = new UserRoleId(user.getId(), role.getId());
    }

	public UserRole(UserRoleId id, User user, Role role, String roleName) {
		super();
		this.id = id;
		this.user = user;
		this.role = role;
		this.roleName = roleName;
	}

	public UserRoleId getId() {
		return id;
	}

	public void setId(UserRoleId id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(role, roleName, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRole other = (UserRole) obj;
		return Objects.equals(role, other.role) && Objects.equals(roleName, other.roleName)
				&& Objects.equals(user, other.user);
	}
}
