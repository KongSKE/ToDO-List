package com.example.springboot;

import java.util.Objects;

public class ToDO {
    private String id;
    private String name;
    public ToDO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDO toDO = (ToDO) o;
        return Objects.equals(id, toDO.getId()) &&
                Objects.equals(name, toDO.getName());
    }
}
