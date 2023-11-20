package com.nentu.lab3.lab3.start.start;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@RequestScoped
@Named
public class AuthorBean {
    private String name = "Зайцев Артём Михайлович";
    private String group = "P3219";
    private int variant = 2933;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getVariant() {
        return variant;
    }

    public void setVariant(int variant) {
        this.variant = variant;
    }
}
