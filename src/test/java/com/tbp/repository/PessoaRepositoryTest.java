package com.tbp.repository;

import com.tbp.model.Pessoa;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class PessoaRepositoryTest {

    PessoaRepository pessoaRepository;

    @Before
    public void before() {
        // este metodo eh executado antes de cada teste
        pessoaRepository = new PessoaRepository();
        // sempre limpo a lista de pessoas
        PessoaRepository.pessoaList = new ArrayList<>();
    }

    @Test
    public void testSalvar() {
        String nome = "Maria";
        Date dataNascimento = new Date();
        String cidade = "Rio de Janeiro";

        Pessoa pessoa = pessoaRepository.salvar(nome, dataNascimento, cidade);
        // testando o retorno do metodo
        assertNotNull(pessoa.getId());
        assertEquals(nome, pessoa.getNome());
        assertEquals(dataNascimento, pessoa.getDataNascimento());
        assertEquals(cidade, pessoa.getCidade());

        // verificando se a lista foi preenchida corretamente
        List<Pessoa> pessoaList = PessoaRepository.pessoaList;
        assertEquals(1, pessoaList.size());
        Pessoa pessoaDaLista = pessoaList.get(0);
        assertNotNull(pessoaDaLista.getId());
        assertEquals(nome, pessoaDaLista.getNome());
        assertEquals(dataNascimento, pessoaDaLista.getDataNascimento());
        assertEquals(cidade, pessoaDaLista.getCidade());
        assertEquals(pessoa.getId(), pessoaDaLista.getId());

        // testa se a geracao do id esta correta
        assertEquals(pessoaList.size() - 1, pessoa.getId().intValue());
    }

    @Test
    public void testListar() {
        pessoaRepository.salvar("Maria", new Date(), "Rio de Janeiro");
        pessoaRepository.salvar("Jose", new Date(), "Belo Horizonte");

        List<Pessoa> pessoas = pessoaRepository.listar();
        assertNotNull(pessoas);
        assertEquals(2, pessoas.size());

        assertEquals("Maria", pessoas.get(0).getNome());
        assertEquals("Jose", pessoas.get(1).getNome());

        assertEquals("Rio de Janeiro", pessoas.get(0).getCidade());
        assertEquals("Belo Horizonte", pessoas.get(1).getCidade());

    }

    @Test
    public void testObterPorId() {
        String nome = "Maria";
        Date dataNascimento = new Date();
        String cidade = "Rio de Janeiro";

        Pessoa pessoa = pessoaRepository.salvar(nome, dataNascimento, cidade);
        Pessoa pessoaObtida  = pessoaRepository.obterPorId(pessoa.getId());

        assertNotNull(pessoaObtida);
        assertEquals(pessoa.getId(), pessoaObtida.getId());
        assertEquals(pessoa.getIdade(), pessoaObtida.getIdade());
        assertEquals(pessoa.getDataNascimento(), pessoaObtida.getDataNascimento());
        assertEquals(pessoa.getCidade(), pessoaObtida.getCidade());
        assertEquals(pessoa.isVacinado(), pessoaObtida.isVacinado());
    }

    @Test
    public void testObterPorIdRetornoNull() {
        Pessoa pessoa = pessoaRepository.obterPorId(-100);
        assertNull(pessoa);

        pessoa = pessoaRepository.obterPorId(10);
        assertNull(pessoa);
    }

    @Test
    public void testDeleteAll() {
        pessoaRepository.salvar("Maria", new Date(), "Rio de Janeiro");
        pessoaRepository.salvar("Jose", new Date(), "Belo Horizonte");

        List<Pessoa> pessoas = pessoaRepository.listar();
        assertNotNull(pessoas);
        assertEquals(2, pessoas.size());

        pessoaRepository.deleteAll();

        pessoas = pessoaRepository.listar();
        assertNotNull(pessoas);
        assertEquals(0, pessoas.size());


    }


}
