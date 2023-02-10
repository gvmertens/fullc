package br.com.mertens.domain.pagination;

import java.util.List;
import java.util.function.Function;

public record Pagination<T>(
        int currentPAge,
        int perPage,
        long total,
        List<T> items
) {

    public <R> Pagination<R> map(final Function<T, R> mapper) {
        final List<R> aNewList = (List<R>) this.items.stream()
                .map(mapper)
                .toList();

        return new Pagination<>(currentPAge(), perPage(), total(), aNewList);
    }

}
