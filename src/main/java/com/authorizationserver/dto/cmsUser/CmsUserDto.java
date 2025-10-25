package com.authorizationserver.dto.cmsUser;

import lombok.Data;

@Data
public class CmsUserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String country;
    private String state;
    private String city;
    private String address;
    private String accType;
    private String status;
    private String dob;
    private String merchantID;
    private String language;
    private String merchantName;
}