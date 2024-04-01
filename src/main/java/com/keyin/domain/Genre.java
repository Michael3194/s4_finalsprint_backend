package com.keyin.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Genre {

    @Id
    @SequenceGenerator(name = "genre_sequence", sequenceName = "genre_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "genre_sequence")
    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
