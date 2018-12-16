package com.avanade.santander.fundos.domain.model;

public enum Risk {
    baixo(1),
    moderado(2),
    medio(3),
    alto(4),
    grave(5);

    private final int risk;

    Risk(int risk){
        this.risk = risk;
    }

    public int getRisk() {
        return risk;
    }
}
