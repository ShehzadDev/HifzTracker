package com.example.hifztracker;

public class Student {
    private int id;
    private String name;
    private int age;
    private String className;
    private String tasks;

    public Student(int id, String name, int age, String className) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.className = className;
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getClassName() {
        return className;
    }

    public String getTasks() {
        return tasks;
    }
}

