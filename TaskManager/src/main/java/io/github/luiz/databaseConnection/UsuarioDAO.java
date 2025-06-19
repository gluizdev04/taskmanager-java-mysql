package io.github.luiz.databaseConnection;

import io.github.luiz.model.Usuario;

import java.sql.*;

public class UsuarioDAO {
    public void cadastrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConenction.getConnection();
             // Aqui dizemos que queremos retornar as chaves geradas (o ID)
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.executeUpdate();

            // Pega o ID gerado automaticamente pelo banco
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);         // Primeiro campo da resposta é o ID
                usuario.setId(idGerado);             // Atualiza o objeto Usuario com o ID correto
            }

            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";

        try(Connection conn = DatabaseConenction.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            var rs = stmt.executeQuery();

            if(rs.next()) {
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
                Usuario usuario = new Usuario(nome, email, senha);
                usuario.setId(rs.getInt("id"));
                return usuario;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return null;
    }

}



