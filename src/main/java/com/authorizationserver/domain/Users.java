package com.authorizationserver.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cmsuser")
public class Users implements Serializable {

    private static final long serialVersionUID = -776092742028125483L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private Long id;

    @Type(type = "numeric_boolean")
    @Column(name = "accountLocked", nullable = false)
    private Boolean accountLocked;

    @Column(name = "create_date")
    private Date createDate;

    private String email;
    private Integer failedLoginAttempts;
    private String firstName;
    private String lastName;
    private String lgName;
    private String password;
    private String reasonForLockedAccount;

    @Column(name = "last_change_pass")
    private Date lastChangedPass;

    public Date getLastChangedPass() {
        return lastChangedPass;
    }

    public void setLastChangedPass(Date lastChangedPass) {
        this.lastChangedPass = lastChangedPass;
    }

    @ManyToOne
    @JoinColumn(name = "status")
    private CmsUserStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(Boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(Integer failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLgName() {
        return lgName;
    }

    public void setLgName(String lgName) {
        this.lgName = lgName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReasonForLockedAccount() {
        return reasonForLockedAccount;
    }

    public void setReasonForLockedAccount(String reasonForLockedAccount) {
        this.reasonForLockedAccount = reasonForLockedAccount;
    }

    public CmsUserStatus getStatus() {
        return status;
    }

    public void setStatus(CmsUserStatus status) {
        this.status = status;
    }

    @PrePersist
    public void PrePersist() {
        createDate = new Date();
    }
}