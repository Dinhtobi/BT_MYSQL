package com.springSecurity.Entities;


import java.io.Serializable;
import java.util.Set;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name ="User")
public class User implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ;
	
	@Column(nullable = false, columnDefinition = "varchar(20)")
	private String username;
	@Column(nullable = false , columnDefinition = "text")
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.DETACH)
	@JoinTable(name = "user_role" , joinColumns = @JoinColumn(name = "user_id" , referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "role_id" , referencedColumnName =  "id"))
	private Set<Role> roles ;
	
	@OneToOne(mappedBy = "user")
	private RefreshToken token;
}
