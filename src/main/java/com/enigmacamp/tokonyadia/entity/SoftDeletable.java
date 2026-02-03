package com.enigmacamp.tokonyadia.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class SoftDeletable {
    @SoftDelete
    private Boolean deleted = Boolean.FALSE;

    private LocalDateTime deletedAt;
}
