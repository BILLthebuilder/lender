package com.lending.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@SQLDelete(sql = "UPDATE tbloans SET status=false WHERE id=?")
@Table(name = "tbloans")
public class Loan implements Serializable {
    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    @Column(nullable = false,precision=10, scale=2)
    private BigDecimal amount;

    @Column(name ="msisdn", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "email", unique = true, nullable = false, columnDefinition = "VARCHAR(50)")
    private String email;

    private String idNumber;
    private String repaymentStatus;

    private String loanStatus;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date loanCreationDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date loanTopupDate;

//    @Column(name = "date_created", updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
//    @Temporal(value = TemporalType.TIMESTAMP)
//    private Date dateCreated;

    @CreationTimestamp
    private Date dateCreated;

//    @PrePersist
//    void dateCreatedAt() {
//        this.dateCreated = new Date();
//    }

//    @Column(name = "date_updated", columnDefinition = "DATETIME ON UPDATE CURRENT_TIMESTAMP")
//    @Temporal(value = TemporalType.TIMESTAMP)
//    private Date dateUpdated;

    @UpdateTimestamp
    private Date dateUpdated;

//    @PreUpdate
//    void dateUpdatedAt() {
//        this.dateUpdated = new Date();
//    }

    @Column(name = "status", columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean status;

    /**
     * Ensures status value is also updated in the
     * current session during deletion
     */
    @PreRemove
    public void deleteLoan () {
        this.status = false;
    }

}

