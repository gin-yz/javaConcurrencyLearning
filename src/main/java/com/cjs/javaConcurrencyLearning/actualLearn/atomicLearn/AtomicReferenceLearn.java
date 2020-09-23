package com.cjs.javaConcurrencyLearning.actualLearn.atomicLearn;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceLearn {
    public static void main(String[] args) {
        AtomicReference<Person> atomicPerson = new AtomicReference<>(new Person("cjs",15));

    }
}

class Person{
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}