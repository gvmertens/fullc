package br.com.mertens.application.category.retrieve.list;

import br.com.mertens.application.UseCase;
import br.com.mertens.domain.pagination.Pagination;
import br.com.mertens.domain.pagination.SearchQuery;

public abstract class ListCategoriesUseCase  extends UseCase<SearchQuery, Pagination<CategoryListOutput>> {
}
