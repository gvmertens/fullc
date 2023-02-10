package br.com.mertens.application.category.retrieve.list;

import br.com.mertens.domain.category.Category;
import br.com.mertens.domain.category.CategoryID;

import java.time.Instant;

public record CategoryListOutput(CategoryID id, String name, String description, boolean isActive, Instant createdAt,
                                 Instant deletedAt) {

    public static CategoryListOutput from(final Category aCategory) {
        return new CategoryListOutput(
                aCategory.getId(),
                aCategory.getName(),
                aCategory.getDescription(),
                aCategory.isActive(),
                aCategory.getCreatedAt(),
                aCategory.getDeletedAt()
        );
    }

}
