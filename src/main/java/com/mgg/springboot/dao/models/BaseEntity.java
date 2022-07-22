package com.mgg.springboot.dao.models;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEntity {
    @Schema(description = "Unique identifier of the employee.", example = "1", required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    @CreationTimestamp
	@Schema(description = "Timestamp of creation.", example = "62482211", required = false)
	private Date createdAt;

	@UpdateTimestamp
	@Schema(description = "Timestamp of update.", example = "62482211", required = false)
	private Date updatedAt;

	@PrePersist
    protected void prePersist() {
        if (this.createdAt == null) createdAt = new Date();
        if (this.updatedAt == null) updatedAt = new Date();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = new Date();
    }

    @PreRemove
    protected void preRemove() {
        this.updatedAt = new Date();
    }
}
