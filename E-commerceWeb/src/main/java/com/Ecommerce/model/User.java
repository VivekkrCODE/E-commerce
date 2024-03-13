package com.Ecommerce.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

@NotEmpty
@Column(nullable = false)
private String firstName;

private String lastname;

@Column(nullable = false,unique = true)
@NotEmpty
@Email(message = "{errors.invalid_email}")
private String email;

@Column(nullable = false)

private String password;

@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
@JoinTable(name = "user_role",
joinColumns = {@JoinColumn(name = "USER_ID",referencedColumnName = "ID")},
inverseJoinColumns = {@JoinColumn(referencedColumnName = "ID",name = "ROLE_ID")} 
)
private List<Role> roles;

public User(User user) {
	super();
	this.id = user.getId();
	this.firstName =  user.getFirstName();
	this.lastname = user.getLastname();
	this.email = user.getEmail();
	this.password = user.getPassword();
	this.roles = user.getRoles();
}
public User() {
	super();
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastname() {
	return lastname;
}

public void setLastname(String lastname) {
	this.lastname = lastname;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public List<Role> getRoles() {
	return roles;
}

public void setRoles(List<Role> roles) {
	this.roles = roles;
}


}
