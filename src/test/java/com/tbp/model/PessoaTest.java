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
        Integer id = 1;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -2);
        Date dataNascimento = c.getTime();
        String cidade = "Rio";
        String nome = "Maria";

        Pessoa pessoa = new Pessoa(id, nome, dataNascimento, cidade);

        assertEquals(1, pessoa.getId().intValue());
        assertEquals(2, pessoa.getIdade().intValue());
        assertEquals("Maria", pessoa.getNome());
        assertEquals("Rio", pessoa.getCidade());
        assertFalse(pessoa.isVacinado());
        assertEquals(dataNascimento, pessoa.getDataNascimento());

    }

    @Test
    public void testConstructorDataNascimentoNull() {
        Integer id = 1;
        String cidade = "Rio";
        String nome = "Maria";
        try {
            new Pessoa(id, nome, null, cidade);
            fail("Nao deveria aceitar a data de nascimento nula");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals("A data de nascimento nao pode ser nula", e.getMessage());
        }
    }


    @Test
    public void testCompareToPessoaMesmaIdade() {
        Integer id = 1;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -2);
        Date dataNascimento = c.getTime();
        String cidade = "Rio";
        String nome = "Maria";

        Pessoa p1 = new Pessoa(id, nome, dataNascimento, cidade);
        Pessoa p2 = new Pessoa(2, "nome", dataNascimento, "cidade");
        assertEquals(0, p1.compareTo(p1));
        assertEquals(0, p1.compareTo(p2));
    }

    @Test
    public void testCompareToPessoaidadeDiferente() {
        Integer idPessoaMaisVelha = 1;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -10);
        Date dataNascimentoPessoaMaisVelha = c.getTime();
        String cidadePessoaMaisVelha = "Rio";
        String nomePessoaMaisVelha = "Maria";

        Pessoa pessoaMaisVelha = new Pessoa(idPessoaMaisVelha, nomePessoaMaisVelha, dataNascimentoPessoaMaisVelha, cidadePessoaMaisVelha);

        Integer idPessoaMaisNova = 2;
        Calendar c2 = Calendar.getInstance();
        c2.add(Calendar.YEAR, -2);
        Date dataNascimentoPessoaMaisNova = c2.getTime();
        String cidadePessoaMaisNova = "SP";
        String nomePessoaMaisNova = "Jose";

        Pessoa pessoaMaisNova = new Pessoa(idPessoaMaisNova, nomePessoaMaisNova, dataNascimentoPessoaMaisNova, cidadePessoaMaisNova);

        assertEquals(-1, pessoaMaisVelha.compareTo(pessoaMaisNova));

        assertEquals(1, pessoaMaisNova.compareTo(pessoaMaisVelha));



    }







}
