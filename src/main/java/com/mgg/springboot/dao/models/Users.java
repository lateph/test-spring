package com.mgg.springboot.dao.models;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import javax.persistence.Lob;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Users extends BaseEntity {

			@Schema(description = "user email for loggin", example = "", required = true)
			@NotBlank
		private String email;
			@Schema(description = "user password for login", example = "", required = true)
			@JsonProperty(access = Access.WRITE_ONLY)
			@NotBlank
		private String password;
			@Schema(description = "Name of the Users.", example = "", required = false)
		private String name;
}