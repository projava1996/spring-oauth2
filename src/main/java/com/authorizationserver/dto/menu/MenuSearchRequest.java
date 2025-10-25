package com.authorizationserver.dto.menu;

import com.authorizationserver.dto.base.PaginationBase;
import lombok.Data;

@Data
public class MenuSearchRequest extends PaginationBase {
    private String name;
    private String url;
    private String app;
}
