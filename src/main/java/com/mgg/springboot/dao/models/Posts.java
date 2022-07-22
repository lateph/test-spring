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
@Table(name = "posts")
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Posts extends BaseEntity {

			@Schema(description = "Title of the Posts.", example = "", required = false)
		private String title;
			@Schema(description = "User Id of the Posts.", example = "", required = false)
		private Integer userId;
}