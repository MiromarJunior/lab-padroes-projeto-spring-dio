package br.com.padroesdeprojeto.labpadroesprojetospring.service.implementservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.padroesdeprojeto.labpadroesprojetospring.model.Cliente;
import br.com.padroesdeprojeto.labpadroesprojetospring.model.Endereco;
import br.com.padroesdeprojeto.labpadroesprojetospring.repository.ClienteRepository;
import br.com.padroesdeprojeto.labpadroesprojetospring.repository.EnderecoRepository;
import br.com.padroesdeprojeto.labpadroesprojetospring.service.ClienteService;
import br.com.padroesdeprojeto.labpadroesprojetospring.service.EnderecoService;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;
    
    @Autowired
    EnderecoService enderecoService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();

    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);

    }

   

    @Override
    public void atualizar(Long id, Cliente cliente) {
     Optional<Cliente> clienteSel =   clienteRepository.findById(id);
        if(clienteSel.isPresent()){
            salvarClienteComCep(cliente);
        }
    }
    private void salvarClienteComCep(Cliente cliente) {
       
        String cep = cliente.getEndereco().getCep();
    
        Endereco endereco =  enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = enderecoService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);

    }

}
