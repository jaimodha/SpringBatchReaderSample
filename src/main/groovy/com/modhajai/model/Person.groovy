package com.modhajai.model

/**
 * Created by jaimodha on 1/8/17.
 */
class Person {

    Long id
    String firstName
    String lastName

    Person(Long id, String firstName, String lastName){
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
    }
}
