package kr.ac.kku.cs.wp.DaeKuen16.user.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class UserRoleId implements Serializable {

    @Column(name = "user_id", length = 200)
    private String userId;

    @Column(name = "role_id", length = 200)
    private String roleId;

    // Constructors – default, fields
    public UserRoleId() {}

    public UserRoleId(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(roleId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRoleId other = (UserRoleId) obj;
		return Objects.equals(roleId, other.roleId) && Objects.equals(userId, other.userId);
	}

}