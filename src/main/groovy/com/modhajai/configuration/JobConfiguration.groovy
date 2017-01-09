package com.modhajai.configuration

import com.modhajai.mapper.PersonFiledSetMapper
import com.modhajai.model.Person
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.mapping.DefaultLineMapper
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.batch.core.Step
import org.springframework.batch.core.Job


/**
 * Created by jaimodha on 1/8/17.
 */
@Configuration
@EnableBatchProcessing
class JobConfiguration {

    @Autowired
    JobBuilderFactory jobBuilderFactory

    @Autowired
    StepBuilderFactory stepBuilderFactory

    @Bean
    public FlatFileItemReader<Person> personFlatFileItemReader() {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<>()
        reader.setLinesToSkip(1)
        reader.setResource(new ClassPathResource('/data/person.csv'))

        DefaultLineMapper<Person> personDefaultLineMapper = new DefaultLineMapper<>()
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer()
        tokenizer.setNames(['id', 'firstName', 'lastName'] as String[])

        personDefaultLineMapper.setLineTokenizer(tokenizer)
        personDefaultLineMapper.setFieldSetMapper(new PersonFiledSetMapper())
        personDefaultLineMapper.afterPropertiesSet()
        reader.setLineMapper(personDefaultLineMapper)
        return reader
    }

    @Bean
    public ItemReader<Person> personItemReader() {
        return items
    }

    @Bean
    public Step getData() {
        return stepBuilderFactory.get('readstep')
                    .<Person, Person> chunk(10)
                    .reader(personFlatFileItemReader())
                    .writer(personItemReader())
                    .build()
    }

    @Bean
    public Job readJob() {
        return jobBuilderFactory.get('readJob')
                        .start(getData())
                        .build()
    }

}

