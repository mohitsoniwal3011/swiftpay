package com.swiftpay.swifpay_backend.datamodel;

import java.time.LocalDateTime;
import java.util.SortedMap;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseResponse  {

    @Column(name = "create_date_time")
    private LocalDateTime createdDateTime;

    @Column(name = "modified_date_time")
    private LocalDateTime modifiedDateTime;

    @PrePersist
    protected void onCreate() {
        this.createdDateTime = LocalDateTime.now();
        this.modifiedDateTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifiedDateTime = LocalDateTime.now();
    }
    
}
