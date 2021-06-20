package com.tbp.service;


import com.tbp.repository.PessoaRepository;
import org.junit.Test;

public class PessoaServiceTest {

    PessoaService pessoaService;
    PessoaRepository pessoaRepository;

    @Test
    public void before() {
        pessoaRepository = new PessoaRepository();
        pessoaRepository.deleteAll();
        pessoaService = new PessoaService();
        pessoaService.pessoaRepository = pessoaRepository;
    }



}
