package com.dw.dwproject.api.pojo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@AllArgsConstructor
@Getter
@Setter
public class UserPojo {
	
	@JsonProperty
	public Integer id;
	
	@JsonProperty
	public String nom;
	
	@JsonProperty
	public LocalDate dateCreation;
}
