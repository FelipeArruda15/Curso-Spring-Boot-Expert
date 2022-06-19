package br.com.section4.repository;


import br.com.section4.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface Clientes extends JpaRepository<Cliente, Integer> {

    @Query(value = "SELECT c FROM Cliente c WHERE c.nome LIKE :nome")
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    @Query(value = "DELETE FROM Cliente c WHERE c.nome = :nome", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByNome(@Param("nome") String nome);

    boolean existsByNome(String nome);

    @Query(value = "SELECT c FROM Cliente c LEFT JOIN FETCH c.pedidos p WHERE c.id = :id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);
}


