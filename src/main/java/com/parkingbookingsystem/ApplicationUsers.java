package com.parkingbookingsystem;

import com.parkingbookingsystem.role.Role;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.type.PostgresUUIDType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@TypeDef(
		name = "pg-uuid",
		defaultForType = UUID.class,
		typeClass = PostgresUUIDType.class
)
public class ApplicationUsers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type = "pg-uuid")
	@Column(name = "id", columnDefinition = "uuid")
	private UUID id;

	@Column(name = "name", updatable = true, nullable = true, length = 500, unique = false)
	private String name = null;

	@Column(updatable = true, unique = true)
	private String email = null;

	@Column(name = "phoneNo", updatable = true, nullable = true, length = 60, unique = false)
	private String phoneNo = null;

	@Column(name = "password", updatable = true, nullable = true, length = 300, unique = false)
	private String password = null;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public ApplicationUsers() {
	}

	public ApplicationUsers(UUID id, String name, String email, String phoneNo, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
		this.password = password;

	}

	public void addRole(Role role) {
		this.roles.add(role);

	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
