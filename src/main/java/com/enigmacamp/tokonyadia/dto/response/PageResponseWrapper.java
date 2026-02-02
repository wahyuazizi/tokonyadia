package com.enigmacamp.tokonyadia.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PageResponseWrapper<T> {

    private List<T> data;
    private Long totalElement;
    private Integer totalPages;
    private Integer page;
    private Integer size;



    public PageResponseWrapper(Page<T> page){
        this.data = page.getContent();
        this.totalElement = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.page = page.getNumber()+1;
        this.size = page.getSize();
    }
}
