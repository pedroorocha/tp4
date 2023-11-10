import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Produto {
    String nome;
    double preço;
    public Produto(String nome, double preço){
        this.nome =nome;
        this.preço = preço;

    }
    public static void inserirProduto(String nome, double Preço){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:src\\empresa x.db");

            if (connection != null) {
                System.out.println("Conexão com o banco de dados SQLite estabelecida.");
                
                String sql = "INSERT INTO Produto (Nome_produto, Preço) VALUES (?, ?)";
                
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, nome);
                preparedStatement.setDouble(2, Preço);

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

    public static void deletaProduto(int chavePrimaria) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:src\\empresa x.db");

            if (connection != null) {
                System.out.println("Conexão com o banco de dados SQLite estabelecida.");
                
                String sql = "DELETE FROM Produto WHERE ID_produto = ?";
                
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

    public static void atualizaProduto(int chavePrimaria, String nome, double preço) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:src\\empresa x.db");

            if (connection != null) {
                System.out.println("Conexão com o banco de dados SQLite estabelecida.");
                
                String sql = "UPDATE Produto SET Nome_produto = ?, Preço = ?  WHERE ID_produto = ?";
                
                preparedStatement = connection.prepareStatement(sql);
                
                preparedStatement.setString(1, nome);
                preparedStatement.setDouble(2, preço);
                preparedStatement.setInt(3, chavePrimaria);

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

    public static void PesquisaProduto(int chave) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:src\\empresa x.db");

            if (connection != null) {
                System.out.println("Conexão com o banco de dados SQLite estabelecida.");
                
                String sql = "SELECT * FROM Produto WHERE ID_produto = ?";
                
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1, chave);

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int CPF = resultSet.getInt("ID_produto");
                    String nome = resultSet.getString("Nome_produto");
                    double preço = resultSet.getDouble("Preço");
                    

                    System.out.println("CPF: " + CPF);
                    System.out.println("Nome: " + nome);
                    System.out.println("Telefone: " + preço);
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
    public static void inserirVarios(Produto[] l){
        for(Produto lista:l){
            inserirProduto(lista.nome, lista.preço);
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
                String sql = "SELECT * FROM Produto WHERE "+campo+" LIKE ?";
                
                // Crie um PreparedStatement com a consulta SQL
                preparedStatement = connection.prepareStatement(sql);

                // Defina o parâmetro da consulta com o valor que contém a string desejada
                preparedStatement.setString(1, "%" + valor + "%");

                // Execute a consulta
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("ID_produto");
                    String nomeR = resultSet.getString("Nome_produto");
                    double preço = resultSet.getDouble("Preço");
                    

                    System.out.println("CPF: " +id );
                    System.out.println("Nome: " + nomeR);
                    System.out.println("Telefone " + preço);
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

