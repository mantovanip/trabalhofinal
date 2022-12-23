package com.example.trabalhofinal.backend.controller;

import com.example.trabalhofinal.backend.dto.PedidoRequest;
import com.example.trabalhofinal.backend.dto.PedidoResponse;
import com.example.trabalhofinal.backend.modelo.Pedido;
import com.example.trabalhofinal.backend.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/pedido"})
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<Void> criarPedido(@RequestBody PedidoRequest pedido){
        Pedido pedidoModel = new Pedido();

        pedidoModel.setNomeCliente(pedido.getNomeCliente());
        pedidoModel.setRua(pedido.getRua());
        pedidoModel.setBairro(pedido.getBairro());
        pedidoModel.setCidade(pedido.getCidade());
        pedidoModel.setEstado(pedido.getEstado());
        pedidoModel.setValorPedido(pedido.getValorPedido());

        pedidoRepository.save(pedidoModel);

        return ResponseEntity.ok().body(null);

    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<PedidoResponse>> retornarPedidos(){
        List<Pedido> pedidoList = new ArrayList<>();
        pedidoList = pedidoRepository.findAll();
        List<PedidoResponse> pedidoResponseList = new ArrayList<>();
        for(Pedido pedido : pedidoList){
            pedidoResponseList.add(
                    new PedidoResponse(
                            pedido.getId(),
                            pedido.getNomeCliente(),
                            pedido.getRua(),
                            pedido.getBairro(),
                            pedido.getCidade(),
                            pedido.getEstado(),
                            pedido.getValorPedido()));
        }
        return ResponseEntity.ok().body(pedidoResponseList);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> removerPedido(@PathVariable Long id){
        pedidoRepository.deleteById(id);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<Void> atualizarPedido(@RequestBody PedidoRequest pedidoRequest, @PathVariable Long id){
        Optional<Pedido> pedido;
        pedido = pedidoRepository.findById(id)
                .map(record -> {
                record.setNomeCliente(pedidoRequest.getNomeCliente());
                record.setRua(pedidoRequest.getRua());
                record.setBairro(pedidoRequest.getBairro());
                record.setCidade(pedidoRequest.getCidade());
                record.setEstado(pedidoRequest.getEstado());
                record.setValorPedido(pedidoRequest.getValorPedido());
                return pedidoRepository.save(record);
            });
        return ResponseEntity.ok().body(null);
    }
}
