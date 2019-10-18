package sn.htg.demosecurity.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Utilisateurs {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	private String roles = "";

	private int active;

	private String permissions = "";

	public Utilisateurs() {

	}

	public Utilisateurs(String username, String password, String roles, String permissions) {
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.permissions = permissions;
		this.active = 1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public List<String> getRoleList() {

		if (this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<String>();
	}

	public List<String> getPermissionsList() {

		if (this.permissions.length() > 0) {
			return Arrays.asList(this.permissions.split(","));
		}
		return new ArrayList<String>();
	}
}
