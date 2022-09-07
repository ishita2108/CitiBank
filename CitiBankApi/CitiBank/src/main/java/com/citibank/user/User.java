package com.citibank.user;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@NotNull
	@Column(unique = true)
	private String username;
	@NotNull
	private String password;
	@NotNull
	private boolean loggedIn;
	
	public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.loggedIn = false;
}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(id, username, password,loggedIn
                            );
    }

}
