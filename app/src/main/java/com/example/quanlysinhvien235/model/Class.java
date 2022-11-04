package com.example.quanlysinhvien235.model;

public class Class {
    String idClass235,nameClass235;

    public Class() {
    }

    public Class(String idCategory, String nameCategory) {
        this.idClass235 = idCategory;
        this.nameClass235 = nameCategory;
    }

    public String getIdClass() {
        return idClass235;
    }

    public void setIdClass(String idCategory) {
        this.idClass235 = idCategory;
    }

    public String getNameClass() {
        return nameClass235;
    }

    public void setNameClass(String nameCategory) {
        this.nameClass235 = nameCategory;
    }

    @Override
    public String toString() {
        return " "+ idClass235 +"\n"+"    "+nameClass235;
    }
}

