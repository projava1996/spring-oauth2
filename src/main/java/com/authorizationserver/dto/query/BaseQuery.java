//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.authorizationserver.dto.query;

import java.io.Serializable;

public class BaseQuery implements Serializable {
    private int currentPage;
    private int pageSize = 10;

    public int getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
