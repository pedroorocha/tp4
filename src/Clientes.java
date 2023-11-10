
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Clientes {
    String nome;
    int telefone;
    String email;
    

    public Clientes(String nome, int telefone, String email){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public static void inserirClientes(String nome, int telefone, String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:src\\empresa x.db");

            if (connection != null) {
                System.out.println("Conexão com o banco de dados SQLite estabelecida.");
                
                String sql = "INSERT INTO Cliente (nome, telefone, email) VALUES (?, ?, ?)";
                
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, nome);
                preparedStatement.setInt(2, telefone);
                preparedStatement.setString(3,email);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Valores inseridos com sucesso.");
                } else {
                    System.out.println("Nenhum valor inserido.");
                }
            } else {
                System.out.println("Falha ao estabelecer a conexão com o banco de dados SQLite.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void deletaCliente(int chavePrimaria) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:src\\empresa x.db");

            if (connection != null) {
                System.out.println("Conexão com o banco de dados SQLite estabelecida.");
                
                String sql = "DELETE FROM Cliente WHERE CPF = ?";
                
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1, chavePrimaria);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Registro removido com sucesso.");
                } else {
                    System.out.println("Nenhum registro encontrado com a chave primária especificada.");
                }
            } else {
                System.out.println("Falha ao estabelecer a conexão com o banco de dados SQLite.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void atualizaCliente(int chavePrimaria, String nome, int telefone, String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:src\\empresa x.db");

            if (connection != null) {
                System.out.println("Conexão com o banco de dados SQLite estabelecida.");
                
                String sql = "UPDATE Cliente SET Nome = ?, Telefone = ?, email = ?  WHERE CPF = ?";
                
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, nome);
                preparedStatement.setInt(2, telefone);
                preparedStatement.setString(3, email);
                preparedStatement.setInt(4, chavePrimaria);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Registro atualizado com sucesso.");
                } else {
                    System.out.println("Nenhum registro encontrado com a chave primária especificada.");
                }
            } else {
                System.out.println("Falha ao estabelecer a conexão com o banco de dados SQLite.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
     public static void PesquisaCliente(int chave) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:src\\empresa x.db");

            if (connection != null) {
                System.out.println("Conexão com o banco de dados SQLite estabelecida.");
                
                String sql = "SELECT * FROM Cliente WHERE CPF = ?";
                
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1, chave);

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int CPF = resultSet.getInt("CPF");
                    String nome = resultSet.getString("Nome");
                    int telefone = resultSet.getInt("Telefone");
                    String email = resultSet.getString("email");

                    System.out.println("CPF: " + CPF);
                    System.out.println("Nome: " + nome);
                    System.out.println("Telefone: " + telefone);
                    System.out.println("email: " + email );
                }

                if (!resultSet.isBeforeFirst()) {
                    System.out.println("Nenhum resultado encontrado para o nome especificado.");
                }
            } else {
                System.out.println("Falha ao estabelecer a conexão com o banco de dados SQLite.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void inserirVarios(Clientes[] l){
        for(Clientes lista:l){
            inserirClientes(lista.nome, lista.telefone, lista.email);
        }
    }
    public static void pesquisaValor(String valor, String campo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Carregue o driver JDBC
            Class.forName("org.sqlite.JDBC");

            // Crie uma conexão com o banco de dados SQLite
            connection = DriverManager.getConnection("jdbc:sqlite:src\\empresa x.db");

            if (connection != null) {
                System.out.println("Conexão com o banco de dados SQLite estabelecida.");

                // Defina a consulta SQL utilizando a cláusula LIKE
                String sql = "SELECT * FROM Cliente WHERE "+campo+" LIKE ?";
                
                // Crie um PreparedStatement com a consulta SQL
                preparedStatement = connection.prepareStatement(sql);

                // Defina o parâmetro da consulta com o valor que contém a string desejada
                preparedStatement.setString(1, "%" + valor + "%");

                // Execute a consulta
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int CPF = resultSet.getInt("cpf");
                    String nomeR = resultSet.getString("Nome");
                    int telefone = resultSet.getInt("Telefone");
                    String email = resultSet.getString("email");

                    System.out.println("CPF: " +CPF );
                    System.out.println("Nome: " + nomeR);
                    System.out.println("Telefone " + telefone);
                    System.out.println("email: "+email);
                    System.out.println("------");}
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("Nenhum resultado encontrado para a string: " + valor);
                }
            } else {
                System.out.println("Falha ao estabelecer a conexão com o banco de dados SQLite.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    }


    
        

    


