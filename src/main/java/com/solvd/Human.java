package com.solvd;

public abstract class Human
{
    public String name;
    public int age;
    public int weight;

    public Human()
    {
    }

    public String toString() {
        return ("Human: " + this.name + " " + this.age + " " + this.weight);
    }
}