package br.com.section3.repository;

import br.com.section3.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {
    private static String INSERT = "INSERT INTO cliente (nome) VALUES (?) ";
    private static String SELECT_ALL = "SELECT * FROM cliente";

    private static String UPDATE = "UPDATE cliente SET nome = ? WHERE ID = ?";
    private static  String DELETE = "DELETE FROM cliente WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente) {
         jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    public List<Cliente> obterTodos(){
        return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());
    }

    public Cliente alterar(Cliente cliente){
        jdbcTemplate.update(UPDATE, new Object[]{cliente.getNome(), cliente.getId()});
        return cliente;
    }

    public void deletar(Integer id){
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    public List<Cliente> buscarPorNome(String nome){
        return jdbcTemplate.query(
                SELECT_ALL.concat(" where nome like ? "),
                new Object[]{"%" + nome + "%"},
                obterClienteMapper());
    }

    private RowMapper<Cliente> obterClienteMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = rs.getInt("id");
                String nome = rs.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }
}

