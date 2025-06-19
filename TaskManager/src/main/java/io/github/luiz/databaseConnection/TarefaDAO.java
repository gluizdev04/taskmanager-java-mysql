package io.github.luiz.databaseConnection;

import io.github.luiz.model.Tarefa;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
public class TarefaDAO {

    public void adicionarTarefa(Tarefa tarefa){
        String sql = "INSERT INTO tarefas (titulo, descricao, usuario_id) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConenction.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setInt(3, tarefa.getUsuarioId());
            stmt.executeUpdate();

            System.out.println("Tarefa adiconada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar tarefa: " + e.getMessage());
        }
    }

    public List<Tarefa> listarTarefas(int usuarioID){
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefas WHERE usuario_id = ?";

        try(Connection conn = DatabaseConenction.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, usuarioID);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Tarefa t = new Tarefa(rs.getString("titulo"), rs.getString("descricao"), usuarioID);
                t.setId(rs.getInt("id"));
                t.setConcluida(rs.getBoolean("concluida"));
                tarefas.add(t);
            }

        } catch (SQLException e){
            System.out.println("Erro ao listar tarefas" + e.getMessage());
        }
        return tarefas;
    }

    public boolean atualizarTarefa(int id, String novoTitulo, String novaDescricao, int id_usuario){
        String sql = "UPDATE tarefas SET titulo = ?, descricao = ? WHERE id = ? AND usuario_id = ?";

        try(Connection conn = DatabaseConenction.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, novoTitulo);
            stmt.setString(2, novaDescricao);
            stmt.setInt(3, id);
            stmt.setInt(4, id_usuario);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e){
            System.out.println("Erro ao atualizar tarefa: " + e.getMessage());
            return false;
        }
    }

    public boolean deletarTarefa(int id, int id_usuario){
        String sql = "DELETE FROM tarefas WHERE id = ? AND usuario_id = ?";

        try(Connection conn = DatabaseConenction.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            stmt.setInt(2, id_usuario);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e){
            System.out.println("Erro ao deletar tarefa: " + e.getMessage());
            return false;
        }
    }

    public Tarefa buscarTarefaPorTitulo(String titulo, int usuario_id) {
        String sql = "SELECT * FROM tarefas WHERE titulo COLLATE utf8mb4_0900_ai_ci = ? AND usuario_id = ?";

        try (Connection conn = DatabaseConenction.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            stmt.setInt(2, usuario_id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Tarefa tarefa = new Tarefa(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getBoolean("concluida"),
                        rs.getInt("usuario_id")
                );
                return tarefa;
            } else {
                System.out.println("Tarefa não encontrada.");
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar tarefa: " + e.getMessage());
            return null;
        }
    }

    public Tarefa buscarTarefaPorId(int id){
        String sql = "SELECT * FROM tarefas WHERE id = ?";

        try(Connection conn = DatabaseConenction.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Tarefa tarefa = new Tarefa(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getBoolean("concluida"),
                        rs.getInt("usuario_id")
                );
                return tarefa;
            }
        } catch (SQLException e ) {
            System.out.println("Erro ao buscar tarefa por ID: " + e.getMessage());
        }
        return null;
    }

    public boolean marcarComoConcluida(int id, int usuario_id){
        String sql = "UPDATE tarefas SET concluida = TRUE WHERE id = ? AND usuario_id = ?";

        try(Connection conn = DatabaseConenction.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            stmt.setInt(2, usuario_id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e){
            System.out.println("Erro ao marcar como lida: " + e.getMessage());
        }
        return false;
    }

    public boolean desmarcarComoConcluida(int id, int usuario_id) {
        String sql = "UPDATE tarefas SET concluida = false WHERE id = ? AND usuario_id = ?";

        try(Connection conn = DatabaseConenction.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            stmt.setInt(2, usuario_id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao desmarcar tarefa como concluída: " + e.getMessage());
        }
        return false;
    }
}
