package org.yisus.spring.aop.example.models;

public class Employee {
    private Integer id;
    private String name;
    private Integer age;
    private Integer salary;
    private String area;


    public Employee(Integer id, String name, Integer age, Integer salary, String area) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.area = area;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", area='" + area + '\'' +
                '}';
    }
}
