package ru.hh.homework.at_least_some_backend.dto.insert;

import ru.hh.homework.at_least_some_backend.entity.HHEntity;

// Зачем? Так я говорю в createEntity у сервисов вставлять только DTO предназначенные для insert'а
// У QueryDto, например, может вообще оказаться только айдишка
// Плюс так Java знает для какого Entity такая Dto подойдет (все см. сервисы)
public abstract class InsertDto<TEntity extends HHEntity> { }
