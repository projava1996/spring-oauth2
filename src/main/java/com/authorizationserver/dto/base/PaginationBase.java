package com.authorizationserver.dto.base;

import lombok.Data;

@Data
public class PaginationBase {
    private Integer page = 0;
    private Integer pageSize = 10;
}
