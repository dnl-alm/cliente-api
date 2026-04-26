package br.com.cliente.service;

import br.com.cliente.dto.ClienteRequest;
import br.com.cliente.dto.ClienteResponse;
import br.com.cliente.entity.Cliente;
import br.com.cliente.exception.ClienteNotFoundException;
import br.com.cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public ClienteResponse criarCliente(ClienteRequest request) {
        Cliente cliente = new Cliente();
        cliente.setNome(request.nome());
        cliente.setIdade(request.idade());
        cliente.setCpf(request.cpf());

        Cliente clienteSalvo = clienteRepository.save(cliente);
        return convertToResponse(clienteSalvo);
    }

    @Transactional(readOnly = true)
    public List<ClienteResponse> listarTodosClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClienteResponse buscarClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado com ID: " + id));

        return convertToResponse(cliente);
    }

    @Transactional
    public ClienteResponse atualizarCliente(Long id, ClienteRequest request) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado com ID: " + id));

        cliente.setNome(request.nome());
        cliente.setIdade(request.idade());
        cliente.setCpf(request.cpf());

        Cliente clienteAtualizado = clienteRepository.save(cliente);
        return convertToResponse(clienteAtualizado);
    }

    @Transactional
    public void deletarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNotFoundException("Cliente não encontrado com ID: " + id);
        }

        clienteRepository.deleteById(id);
    }

    private ClienteResponse convertToResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getIdade(),
                cliente.getCpf()
        );
    }
}
