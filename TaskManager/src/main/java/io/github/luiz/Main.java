package io.github.luiz;

import io.github.luiz.databaseConnection.DatabaseConenction;
import io.github.luiz.databaseConnection.TarefaDAO;
import io.github.luiz.model.Tarefa;
import io.github.luiz.model.Usuario;
import io.github.luiz.databaseConnection.UsuarioDAO;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioLogado = null;

        while(usuarioLogado == null) {
            System.out.println("===MENU TASK MANAGER===");
            System.out.println("1. Login");
            System.out.println("2. Cadastrar");
            System.out.println("3. Sair");

            try {
              System.out.print("Escolha uma opção a cima: ");
              int opcao = Integer.parseInt(teclado.nextLine());

              switch (opcao) {
                  case 1:
                      System.out.print("E-mail: ");
                      String email = teclado.nextLine();
                      System.out.print("Senha: ");
                      String senha = teclado.nextLine();
                      Usuario usuario = usuarioDAO.buscarPorEmail(email);

                      if (usuario != null && BCrypt.checkpw(senha, usuario.getSenha())) {
                          System.out.println("Login efetuado com sucesso!");
                          usuarioLogado = usuario;
                      } else {
                          System.out.println("E-mail ou senha inválido!");
                      }
                      break;

                  case 2:
                      System.out.print("Nome: ");
                      String nome = teclado.nextLine();
                      System.out.print("E-mail: ");
                      String emailcad = teclado.nextLine();

                      if (usuarioDAO.buscarPorEmail(emailcad) != null) {
                          System.out.println("E-mail já cadastrado!");
                          break;
                      }

                      System.out.print("Senha: ");
                      String senhacad = teclado.nextLine();

                      String senhaCriptografada = BCrypt.hashpw(senhacad, BCrypt.gensalt());
                      Usuario usuariocad = new Usuario(nome, emailcad, senhaCriptografada);

                      usuarioDAO.cadastrarUsuario(usuariocad);
                      break;

                  case 3:
                      System.exit(0);
                      break;

                  default:
                      System.out.println("Opção inválida");

              }
          } catch (Exception e) {
              System.out.println("Digite uma opção válida");
          }

            TarefaDAO tarefaDAO = new TarefaDAO();

            while(usuarioLogado != null) {
                System.out.println("\n=== Menu de Tarefas ===");
                System.out.println("1. Adicionar tarefa");
                System.out.println("2. Listar tarefas");
                System.out.println("3. Atualizar tarefa");
                System.out.println("4. Deletar tarefa");
                System.out.println("5. Marcar como concluída");
                System.out.println("6. Marcar como não concluída");
                System.out.println("7. Logout");
                System.out.println("0. Sair");

              try {
                  System.out.print("Escolha: ");
                  int opcao1 = Integer.parseInt(teclado.nextLine());

                  switch (opcao1) {
                      case 1:
                          System.out.print("Título: ");
                          String titulo = teclado.nextLine();
                          System.out.print("Descrição: ");
                          String descricao = teclado.nextLine();
                          Tarefa t = new Tarefa(titulo, descricao, usuarioLogado.getId());
                          tarefaDAO.adicionarTarefa(t);
                          break;

                      case 2:
                          List<Tarefa> tarefas = tarefaDAO.listarTarefas(usuarioLogado.getId());
                          System.out.println("\n=== Suas Tarefas ===");
                          for (Tarefa ta : tarefas) {
                              String status = ta.isConcluida() ? "[X]" : "[ ]";
                              System.out.println("ID: " + ta.getId() + " CONCLUÍDA: " + status + " " + ta.getTitulo() + " - " + ta.getDescricao());
                          }
                          break;

                      case 3:
                          System.out.print("Tarefa que deseja atualizar: ");
                          String taskUpdate = teclado.nextLine();
                          Tarefa tarefa = tarefaDAO.buscarTarefaPorTitulo(taskUpdate, usuarioLogado.getId());

                          if (tarefa != null) {

                              System.out.println("Tarefa encontrada: " + tarefa.getTitulo());
                              System.out.println("Descrição: " + tarefa.getDescricao());
                              System.out.println("ID: " + tarefa.getId());

                              System.out.println("Deseja alterar essa tarefa? 1.SIM  2.NÂO");
                              int simounao = Integer.parseInt(teclado.nextLine());

                              if (simounao == 1) {
                                  System.out.print("Novo título: ");
                                  String novoTitulo = teclado.nextLine();
                                  System.out.print("Nova decrição: ");
                                  String novaDescricao = teclado.nextLine();

                                  tarefaDAO.atualizarTarefa(tarefa.getId(), novoTitulo, novaDescricao, usuarioLogado.getId());
                                  System.out.println("Tarefa atualizada!");
                              } else if (simounao == 2) {
                                  break;
                              }

                          }
                          break;

                      case 4:
                          System.out.print("Digite o ID da tarefa que deseja deletar: ");
                          int idDeletar = Integer.parseInt(teclado.nextLine());
                          Tarefa resultadoBusca = tarefaDAO.buscarTarefaPorId(idDeletar);

                          if(resultadoBusca != null){
                              boolean sucesso = tarefaDAO.deletarTarefa(idDeletar, usuarioLogado.getId());

                              if(sucesso){
                                  System.out.println("Tarefa: " + resultadoBusca.getTitulo() + " deletada com sucesso!");
                              } else {
                                  System.out.println("Não foi possível deletar");
                              }
                          } else{
                              System.out.println("Não foi possível deletar");
                          }
                          break;


                      case 5:
                          System.out.print("Digite o ID da tarefa que deseja marcar como concluída: ");
                          int inCon = Integer.parseInt(teclado.nextLine());
                          Tarefa busca = tarefaDAO.buscarTarefaPorId(inCon);

                          if(busca != null){
                              boolean sucesso = tarefaDAO.marcarComoConcluida(inCon, usuarioLogado.getId());

                              if(sucesso){
                                  System.out.println("Tarefa: " + busca.getTitulo() + " concluída");
                              } else {
                                  System.out.println("Não foi possível concluir a tarefa");
                              }

                          } else {
                              System.out.println("Não existe uma tarefa com o id informado");
                          }

                          break;

                      case 6:
                          System.out.print("Digite o ID da tarefa que deseja marcar como NÃO concluída: ");
                          int naoCon = Integer.parseInt(teclado.nextLine());
                          Tarefa buscaporID = tarefaDAO.buscarTarefaPorId(naoCon);

                          if(buscaporID != null){
                              boolean sucesso = tarefaDAO.desmarcarComoConcluida(naoCon, usuarioLogado.getId());

                              if(sucesso){
                                  System.out.println("Tarefa '" + buscaporID.getTitulo() + "'" + " marcada como NÃO concluída");
                              } else {
                                  System.out.println("Não foi possível realizar essa ação");
                              }
                          } else {
                              System.out.println("Tarefa não encontrada!");
                          };
                          break;


                      case 7:
                          usuarioLogado = null;
                          break;

                      case 0:
                          System.out.println("Saindo");
                          return;


                      default:
                          System.out.println("Opção inválida!");

                  }
              } catch (Exception e) {
                  System.out.println("Digite um opção válida");
              }
            }

        }



    }
}