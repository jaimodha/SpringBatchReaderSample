package com.modhajai.writer

import com.modhajai.model.Person
import org.springframework.batch.item.ItemWriter
import org.springframework.context.annotation.Configuration

/**
 * Created by jaimodha on 1/8/17.
 */
@Configuration
class PersonItemWriter implements ItemWriter<Person> {
    @Override
    void write(List<? extends Person> items) throws Exception {
        println items
    }
}
