package com.tbp.repository;

import com.tbp.model.Pessoa;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class PessoaRepositoryTest {

    PessoaRepository pessoaRepository;

    // este metodo eh executado sempre antes de cada teste
    @Before
    public void before() {
        pessoaRepository = new PessoaRepository();
        // sempre limpo a lista de pessoas
        PessoaRepository.pessoaList = new ArrayList<>();
    }

    @Test
    public void testSalvar() {

        // variaveis de configuracao
        String nome = "Maria";
        Date dataNascimento = new Date();
        String cidade = "Rio de Janeiro";

        // chamo o metodo que quero testar
        Pessoa pessoa = pessoaRepository.salvar(nome, dataNascimento, cidade);

        // inicio dos testes - asserts
        assertNotNull(pessoa);
        assertTrue(pessoa != null);
        assertFalse(pessoa == null);
        // verificamos se o retorno confere com os parametros
        assertEquals(nome, pessoa.getNome());
        assertEquals(dataNascimento, pessoa.getDataNascimento());
        assertEquals(cidade, pessoa.getCidade());

        // testar se a lista estatica foi devidamente preenchida.
        List<Pessoa> pessoaList = PessoaRepository.pessoaList;
        assertNotNull(pessoaList);
        assertEquals(1, pessoaList.size());

        // testar se a pessoa dentro da lista esta configurado corretamente
        Pessoa pessoaDaLista = pessoaList.get(0);
        assertNotNull(pessoaDaLista);
        assertNotNull(pessoaDaLista.getId());
        assertNotNull(pessoaDaLista.getNome());
        assertNotNull(pessoaDaLista.getDataNascimento());

        assertEquals(pessoa.getNome(), pessoaDaLista.getNome());
        assertEquals(pessoa.getId(), pessoaDaLista.getId());
        assertEquals(pessoa.getDataNascimento(), pessoaDaLista.getDataNascimento());
        assertEquals(pessoa.getCidade(), pessoaDaLista.getCidade());

        // testo se o id foi gerado corretamente
        assertEquals(pessoaList.size() - 1, pessoa.getId().intValue());
    }

    @Test
    public void testListar() {
        // configurando meu teste
        Date dataNascimentoMaria = new Date();
        Date dataNascimentoJose = new Date();

        pessoaRepository.salvar("Maria", dataNascimentoMaria, "Rio de Janeiro");
        pessoaRepository.salvar("Jose", dataNascimentoJose, "Belo Horizonte");

        // inicio o meu teste
        List<Pessoa> pessoaList = pessoaRepository.listar();
        assertNotNull(pessoaList);
        assertEquals(2, pessoaList.size());

        assertEquals("Maria", pessoaList.get(0).getNome());
        assertEquals("Jose", pessoaList.get(1).getNome());

        assertEquals("Rio de Janeiro", pessoaList.get(0).getCidade());
        assertEquals("Belo Horizonte", pessoaList.get(1).getCidade());

        assertEquals(dataNascimentoMaria, pessoaList.get(0).getDataNascimento());
        assertEquals(dataNascimentoJose, pessoaList.get(0).getDataNascimento());

    }

    @Test
    public void testObterPorId() {

        // configuracao do meu teste
        pessoaRepository.salvar("Jose", new Date(), "Belo Horizonte");

        String nome = "Maria";
        Date dataNascimento = new Date();
        String cidade = "Rio de Janeiro";

        Pessoa pessoa = pessoaRepository.salvar(nome, dataNascimento, cidade);

        // inicia o teste de fato
        Pessoa pessoaObtida = pessoaRepository.obterPorId(pessoa.getId());
        assertNotNull(pessoaObtida);
        assertEquals(pessoa.getId(), pessoaObtida.getId());
        assertEquals(pessoa.getNome(), pessoaObtida.getNome());
        assertEquals(pessoa.getIdade(), pessoaObtida.getIdade());
        assertEquals(pessoa.getDataNascimento(), pessoaObtida.getDataNascimento());
        assertEquals(pessoa.getCidade(), pessoaObtida.getCidade());
        assertEquals(pessoa.isVacinado(), pessoaObtida.isVacinado());
        assertEquals(2, pessoaRepository.listar().size());


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
        // cpnfigurando meu teste
        pessoaRepository.salvar("Maria", new Date(), "Rio de Janeiro");
        pessoaRepository.salvar("Jose", new Date(), "Belo Horizonte");

        List<Pessoa> pessoaList = pessoaRepository.listar();
        assertNotNull(pessoaList);
        assertEquals(2, pessoaList.size());

        // inicia meu teste - deleteAll
        pessoaRepository.deleteAll();

        pessoaList = pessoaRepository.listar();

        assertNotNull(pessoaList);
        assertEquals(0, pessoaList.size());
        assertTrue(pessoaList.isEmpty());

    }



}
