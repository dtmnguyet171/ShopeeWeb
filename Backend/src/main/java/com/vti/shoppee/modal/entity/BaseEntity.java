package com.vti.shoppee.modal.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {
    @Column(name = "create_date")
    protected Date createDate;

    @Column(name = "create_by")
    protected String createBy;

    @Column(name = "update_date")
    protected Date updateDate;

    @Column(name = "update_by")
    protected String updateBy;

    /**
     * Hàm này được gọi khi entity được thêm mới
     */
    @PrePersist
    public void onPrePersist(){
        this.createDate = new Date();
        this.createBy = "Mr.Uoc Create";
    }

    /**
     * Hàm này được gọi khi entity được update
     */
    @PreUpdate
    public void onPreUpdate(){
        this.updateDate = new Date();
        this.updateBy = "Mr.Uoc Create";
    }

}
