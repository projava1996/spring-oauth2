package com.authorizationserver.dto.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CmsUserQuery extends BaseQuery {
    private String name;
    private String email;
    private String type;
    private Long merchantID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fromDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate toDate;
    private String mobile;
    private String status;
    private String merchantName;
}