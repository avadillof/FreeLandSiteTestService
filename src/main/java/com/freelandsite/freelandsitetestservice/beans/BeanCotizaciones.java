package com.freelandsite.freelandsitetestservice.beans;

import java.io.Serializable;

public class BeanCotizaciones implements Serializable{


    private int id;
    private String banco;
    private String percent;

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }
    public int getId() {
        return id;
    }


}
