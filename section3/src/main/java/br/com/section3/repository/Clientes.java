package br.com.section3.repository;

import br.com.section3.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public interface Clientes extends JpaRepository<Cliente, Integer> {

    @Query(value = "SELECT c FROM Cliente c WHERE c.nome LIKE :nome")
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    @Query("DELETE FROM Cliente c WHERE c.nome = :nome")
    @Modifying
    void deleteByNome(@Param("nome") String nome);

    boolean existsByNome(String nome);
}

