package ru.education.exceptions;

import org.springframework.util.Assert;
import ru.education.exceptions.BaseException;

/**
 * Исключение выбрасывается при попытки удаления сущности, на которую есть ссылки у других сущностей
 */
public class EntityHasDetailsException extends BaseException {

    public EntityHasDetailsException(String message) {
        super(message);
    }

    public EntityHasDetailsException(String type, Object id) {
        this(formatMessage(type, id));
    }

    private static String formatMessage(String type, Object id) {
        Assert.hasText(type, "Тип не может быть пустым");
        Assert.notNull(id, "Идентификатор объекта не может быть null");
        Assert.hasText(id.toString(), "Идентификатор объекта может быть пустым");
        return String.format("%s ссылается на удаляемую сущность с идентификатором %s", type, id);
    }
}
