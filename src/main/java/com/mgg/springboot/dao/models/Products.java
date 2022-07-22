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
@Table(name = "products")
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Products extends BaseEntity {

			@Schema(description = "Name of the Products.", example = "", required = false)
		private String name;
			@Schema(description = "Price of the Products.", example = "", required = false)
		private Integer price;
			@Schema(description = "Description of the Products.", example = "", required = false)
			@Lob
		private String description;
			@Schema(description = "Tags of the Products.", example = "", required = false)
		private String tags;
			@Schema(description = "Categories Id of the Products.", example = "", required = false)
		private Integer categoriesId;
			@Schema(description = "Image of the Products.", example = "", required = false)
		private String image;
}