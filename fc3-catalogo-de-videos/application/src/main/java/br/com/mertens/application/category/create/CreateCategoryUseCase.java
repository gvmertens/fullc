package br.com.mertens.application.category.create;

import br.com.mertens.application.UseCase;
import br.com.mertens.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {


}
