package br.com.mertens.domain.category;

import br.com.mertens.domain.AggregateRoot;
import br.com.mertens.domain.validation.ValidationHandler;

import java.awt.dnd.DragGestureRecognizer;
import java.time.Instant;
import java.util.UUID;

public class Category extends AggregateRoot<CategoryID> {

    private CategoryID id;
    private String name;
    private String description;
    private boolean active;

    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Category(final CategoryID id,
                     final String name,
                     final String description,
                     final boolean active,
                     final Instant createdAt,
                     final Instant updatedAt,
                     final Instant deletedAt) {
        super(id);
        this.name = name;
        this.description = description;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static Category newCategory(final String aName, final String aDescription, final boolean isActive) {
        final var id = CategoryID.unique();
        final var now = Instant.now();
        return new Category(id, aName, aDescription, isActive, now, now, null);
    }

    public CategoryID getId() {
        return id;
    }

    @Override
    public void validate(ValidationHandler handler) {
        new CategoryValidator(this, handler).validate();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }
}
