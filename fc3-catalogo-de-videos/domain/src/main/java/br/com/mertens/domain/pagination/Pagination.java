package br.com.mertens.domain.pagination;

import java.util.List;

public record Pagination<T>(
        int currentPAge,
        int perPage,
        long total,
        List<T> items
) {


}
