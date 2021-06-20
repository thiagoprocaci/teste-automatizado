package com.tbp.model;

import java.util.Calendar;
import java.util.Date;

public class Pessoa implements Comparable<Pessoa> {

    Integer id;
    String nome;
    Date dataNascimento;
    String cidade;
    Integer idade;
    boolean vacinado;

    Pessoa() {

    }

    public Pessoa(Integer id, String nome, Date dataNascimento, String cidade) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cidade = cidade;
        this.id = id;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataNascimento);
        int anoNascimento = calendar.get(Calendar.YEAR);

        Calendar hoje = Calendar.getInstance();
        int anoHoje = hoje.get(Calendar.YEAR);
        this.idade = anoHoje - anoNascimento;
        this.vacinado = false;
    }

    public void setVacinado(boolean vacinado) {
        this.vacinado = vacinado;
    }

    public Integer getId() {
        return id;
    }

    public boolean isVacinado() {
        return vacinado;
    }

    @Override
    public int compareTo(Pessoa o) {
        int i = this.idade.compareTo(o.idade);
        return i * (-1);
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getCidade() {
        return cidade;
    }

    public Integer getIdade() {
        return idade;
    }
}
