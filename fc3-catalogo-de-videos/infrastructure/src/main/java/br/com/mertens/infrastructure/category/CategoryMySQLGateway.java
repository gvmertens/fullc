package br.com.mertens.infrastructure.category;

import br.com.mertens.domain.category.Category;
import br.com.mertens.domain.category.CategoryGateway;
import br.com.mertens.domain.category.CategoryID;
import br.com.mertens.domain.pagination.Pagination;
import br.com.mertens.domain.pagination.SearchQuery;
import br.com.mertens.infrastructure.category.persistence.CategoryJpaEntity;
import br.com.mertens.infrastructure.category.persistence.CategoryRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static br.com.mertens.infrastructure.utils.SpecificationUtils.like;

public class CategoryMySQLGateway implements CategoryGateway {

    private final CategoryRepository repository;

    public CategoryMySQLGateway(final CategoryRepository repository) {
        this.repository = repository;
    }

    private Category save(final Category aCategory) {
        return this.repository.save(CategoryJpaEntity.from(aCategory)).toAggregate();
    }

    private Specification<CategoryJpaEntity> assembleSpecification(final String str) {
        final Specification<CategoryJpaEntity> nameLike = like("name", str);
        final Specification<CategoryJpaEntity> descriptionLike = like("description", str);
        return nameLike.or(descriptionLike);
    }

    @Override
    public Category create(Category aCategory) {
        return save(aCategory);
    }

    @Override
    public void deleteById(CategoryID anId) {
        final String anIdValue = anId.getValue();
        if (this.repository.existsById(anIdValue)) {
            this.repository.deleteById(anIdValue);
        }
    }

    @Override
    public Optional<Category> findById(CategoryID anId) {
        return this.repository.findById(anId.getValue()).map(CategoryJpaEntity::toAggregate);
    }

    @Override
    public Category update(Category aCategory) {
        return save(aCategory);
    }

    @Override
    public Pagination<Category> findAll(SearchQuery aQuery) {
        final var page = PageRequest.of(aQuery.page(), aQuery.perPage(), Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort()));

        // Busca dinamica pelo criterio terms (name ou description)
        final var specifications = Optional.ofNullable(aQuery.terms()).filter(str -> !str.isBlank()).map(this::assembleSpecification).orElse(null);

        final var pageResult = this.repository.findAll(Specification.where(specifications), page);

        return new Pagination<>(pageResult.getNumber(), pageResult.getSize(), pageResult.getTotalElements(), pageResult.map(CategoryJpaEntity::toAggregate).toList());
    }

    @Override
    public List<CategoryID> existsByIds(Iterable<CategoryID> categoryIDs) {
        final var ids = StreamSupport.stream(categoryIDs.spliterator(), false).map(CategoryID::getValue).toList();
        return this.repository.existsByIds(ids).stream().map(CategoryID::from).toList();
    }
}
