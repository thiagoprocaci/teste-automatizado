package com.tbp.model;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class PessoaTest {

    @Test
    public void testGetNome() {
        Pessoa pessoa = new Pessoa();
        pessoa.nome = "Thiago";
        // verifico se o getNome funciona como esperado
        assertEquals("Thiago", pessoa.getNome());
        assertNotEquals("Joao", pessoa.getNome());
    }

    @Test
    public void testGetIdade() {
        Pessoa pessoa = new Pessoa();
        pessoa.idade = 10;
        assertEquals(10, pessoa.getIdade().intValue());
    }

    @Test
    public void testConstrutor() {
        // inicio as configuracoes do teste
        Integer id = 1;
        // coloco a data de nascimento ha 2 anos atras
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -2);
        Date dataNascimento = c.getTime();
        // configuro cidade e nome
        String cidade = "Rio de Janeiro";
        String nome = "Maria";

        Pessoa pessoa = new Pessoa(id, nome, dataNascimento, cidade);

        // verifico se o construtor executa como esperado
        assertEquals(1, pessoa.getId().intValue());
        assertEquals(2, pessoa.getIdade().intValue());
        assertEquals("Maria", pessoa.getNome());
        assertEquals("Rio de Janeiro", pessoa.getCidade());
        assertFalse(pessoa.isVacinado());
        assertEquals(dataNascimento, pessoa.getDataNascimento());
    }

    @Test
    public void testCompareToPessoaMesmaIdade() {
        // inicio as configuracoes do teste
        Integer id = 1;
        // coloco a data de nascimento ha 2 anos atras
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -2);
        Date dataNascimento = c.getTime();
        // configuro cidade e nome
        String cidade = "Rio de Janeiro";
        String nome = "Maria";

        Pessoa pessoa = new Pessoa(id, nome, dataNascimento, cidade);

        // retorna zero se a idade for igual
        assertEquals(0, pessoa.compareTo(pessoa));

    }

    @Test
    public void testCompareToPessoaMaisVelhaMaisNova() {

        Integer idPessoaMaisVelha = 1;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -10);
        Date dataNascimentoPessoaMaisVelha = c.getTime();
        String cidadePessoaMaisVelha = "Rio de Janeiro";
        String nomePessoaMaisVelha = "Maria";

        Pessoa pessoaMaisVelha = new Pessoa(idPessoaMaisVelha, nomePessoaMaisVelha, dataNascimentoPessoaMaisVelha, cidadePessoaMaisVelha);

        Integer idPessoaMaisNova = 2;
        c = Calendar.getInstance();
        c.add(Calendar.YEAR, -1);
        Date dataNascimentoPessoaMaisNova = c.getTime();
        String cidadePessoaMaisNova = "Sao Paulo";
        String nomePessoaMaisNova = "Jose";

        Pessoa pessoaMaisNova = new Pessoa(idPessoaMaisNova, nomePessoaMaisNova, dataNascimentoPessoaMaisNova, cidadePessoaMaisNova);

        // a pessoa mais velha sempre tem prioridade. Entao retorna -1
        // -1 vem antes de 1.
        assertEquals(-1, pessoaMaisVelha.compareTo(pessoaMaisNova));

        // a pessoa mais nova nao tem prioridade. Entao retorna 1
        // 1 vem depois de -1
        assertEquals(1, pessoaMaisNova.compareTo(pessoaMaisVelha));
    }

}
