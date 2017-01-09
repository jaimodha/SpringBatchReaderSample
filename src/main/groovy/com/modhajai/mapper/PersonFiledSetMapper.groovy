package com.modhajai.mapper

import com.modhajai.model.Person
import org.springframework.batch.item.file.mapping.FieldSetMapper
import org.springframework.batch.item.file.transform.FieldSet
import org.springframework.validation.BindException

/**
 * Created by jaimodha on 1/8/17.
 */
class PersonFiledSetMapper implements FieldSetMapper<Person> {
    @Override
    Person mapFieldSet(FieldSet fieldSet) throws BindException {
        return new Person(fieldSet.readLong('id'),
                            fieldSet.readString('firstName'),
                            fieldSet.readString('lastName'))
    }
}
