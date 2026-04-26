package br.com.cliente.controller;

import br.com.cliente.dto.ClienteRequest;
import br.com.cliente.dto.ClienteResponse;
import br.com.cliente.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponse> criarCliente(
            @Valid @RequestBody ClienteRequest request) {

        ClienteResponse response = clienteService.criarCliente(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listarTodosClientes() {

        List<ClienteResponse> clientes = clienteService.listarTodosClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarClientePorId(
            @PathVariable Long id) {

        ClienteResponse response = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizarCliente(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequest request) {

        ClienteResponse response = clienteService.atualizarCliente(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {

        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
