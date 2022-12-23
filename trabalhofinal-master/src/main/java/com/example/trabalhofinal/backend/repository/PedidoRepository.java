package com.example.trabalhofinal.backend.repository;

import com.example.trabalhofinal.backend.modelo.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
