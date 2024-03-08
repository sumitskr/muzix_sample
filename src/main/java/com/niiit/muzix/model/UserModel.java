package com.niiit.muzix.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "username")
@Document("UserModel")

public class UserModel {
	@Id
	public String username;
	public String password;
	public String firstname;
	public String lastname;
	public String email;
	public String secretanswer;
}
