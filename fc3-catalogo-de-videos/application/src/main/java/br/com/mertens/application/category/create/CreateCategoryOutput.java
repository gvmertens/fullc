package br.com.mertens.application.category.create;

import br.com.mertens.domain.category.Category;
import br.com.mertens.domain.category.CategoryID;

public record CreateCategoryOutput(
        CategoryID id
) {

    public static CreateCategoryOutput from(final Category aCategory) {
        return new CreateCategoryOutput(aCategory.getId());
    }

}
