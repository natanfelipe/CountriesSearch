package com.br.natanfelipe.teste;

/**
 * Created by natanbrito on 25/04/2018.
 */

public class Country {
    String name;
    int id;

    public Country(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
