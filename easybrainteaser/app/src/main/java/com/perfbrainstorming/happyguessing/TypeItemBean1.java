package com.perfbrainstorming.happyguessing;

public class TypeItemBean1 {
    int id;
    String name;
    int draw;

    public TypeItemBean1(int id, String name, int draw) {
        this.id = id;
        this.name = name;
        this.draw = draw;
    }

    @Override
    public String toString() {
        return "ItemBean1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", draw=" + draw +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }
}
