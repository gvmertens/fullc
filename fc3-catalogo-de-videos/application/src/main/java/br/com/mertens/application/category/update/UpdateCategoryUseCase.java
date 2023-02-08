package br.com.mertens.application.category.update;

import br.com.mertens.application.UseCase;
import br.com.mertens.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}
