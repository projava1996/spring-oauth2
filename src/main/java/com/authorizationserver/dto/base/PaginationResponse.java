package com.authorizationserver.dto.base;

import lombok.*;

import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponse<T> {
    private long itemsPerPage;
    private long count;
    private long currentPage;
    private long totalPages;
    private List<T> data;
}
