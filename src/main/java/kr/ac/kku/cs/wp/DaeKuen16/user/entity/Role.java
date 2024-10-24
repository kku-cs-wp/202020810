package kr.ac.kku.cs.wp.DaeKuen16.user.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "id", length = 200, nullable = false)
    private String id;

    @Column(name = "role", length = 200)
    private String role;

    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToMany(mappedBy = "role")
    private Set<UserRole> userRoles;

    // Constructors - default, fields
    public Role() {}

    public Role(String id, String role, Date createdAt, Date updatedAt) {
        this.id = id;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

	public Role(String id, String role, Date createdAt, Date updatedAt, Set<UserRole> userRoles) {
		super();
		this.id = id;
		this.role = role;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.userRoles = userRoles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, id, role, updatedAt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(id, other.id)
				&& Objects.equals(role, other.role) && Objects.equals(updatedAt, other.updatedAt);
	}
    
}
