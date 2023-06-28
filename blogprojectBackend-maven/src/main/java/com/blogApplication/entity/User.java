package com.blogApplication.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name="users_data")

public class User implements UserDetails{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(name="user_Name" ,nullable = false, length = 350)
	private String name;
	
	@Column(name="user_Email" ,nullable = false, length = 450)
	private String email;

	@Column(name="User_post")
	@OneToMany(fetch = FetchType.LAZY  ,mappedBy = "user" , cascade = {CascadeType.ALL}  )
	@JsonBackReference
	  private List<Post>post=new ArrayList<>();
	
	@Column(name="user_Password" ,nullable = false, length = 20)
	private String password;

	@Column(name="user_About" ,nullable = false, length = 500)	
	private String about;

	@OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonBackReference
	Set<Comment>comments=new HashSet<>();
	
	@ManyToMany
	@JoinTable(name="user_role",
	joinColumns=@JoinColumn(name="user" ,referencedColumnName =" userId"),
	inverseJoinColumns = @JoinColumn(name ="role", referencedColumnName="id")
	)
	Set<Role>role=new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	

	
}
