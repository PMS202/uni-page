package com.github.ethancarter.unipage.web.component;

import com.github.ethancarter.unipage.domain.PageInformation;
import lombok.Builder;
import lombok.Data;

/**
 * 页面信息表示
 *
 * @author Ethan Carter
 * @date 2025/05/26
 */
@Builder
@Data
public class PageInfoRepresentation {

    private final int number;
    private final int size;
    private final boolean first;
    private final boolean last;
    private final boolean hasNext;
    private final boolean hasPrevious;
    private final int totalPages;
    private final long totalElements;

    public static PageInfoRepresentation of(PageInformation pageInformation) {
        return PageInfoRepresentation.builder()
                .number(pageInformation.getNumber())
                .size(pageInformation.getSize())
                .first(pageInformation.isFirst())
                .last(pageInformation.isLast())
                .hasNext(pageInformation.hasNext())
                .hasPrevious(pageInformation.hasPrevious())
                .totalPages(pageInformation.getTotalPages())
                .totalElements(pageInformation.getTotalElements())
                .build();

    }
}
