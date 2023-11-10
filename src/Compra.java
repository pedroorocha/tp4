import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Compra {
    int ID_cliente;
    int ID_produto;
    int quantidade;
    public Compra(int ID_cliente, int ID_produto, int quantidade ){
        this.ID_cliente = ID_cliente;
        this.ID_produto = ID_produto;
        this.quantidade = quantidade;

    }
        public static void inserirCompra(int ID_cliente, int ID_produto, int quantidade){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:src\\empresa x.db");

            if (connection != null) {
                System.out.println("Conexão com o banco de dados SQLite estabelecida.");
                
                String sql = "INSERT INTO Compra (Quantidade, ID_produto, CPF_cliente) VALUES (?, ?, ?)";
                
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, quantidade);
                preparedStatement.setInt(2, ID_produto);
                preparedStatement.setInt(3, ID_cliente);
                

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

    public static void deletaCompra(int chavePrimaria) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:src\\empresa x.db");

            if (connection != null) {
                System.out.println("Conexão com o banco de dados SQLite estabelecida.");
                
                String sql = "DELETE FROM Compra WHERE ID_compra = ?";
                
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

    public static void atualizaCompra(int chavePrimaria,int ID_cliente, int ID_produto, int quantidade) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:src\\empresa x.db");

            if (connection != null) {
                System.out.println("Conexão com o banco de dados SQLite estabelecida.");
                
                String sql = "UPDATE Compra SET Quantidade = ?, CPF_cliente = ?, ID_produto = ?  WHERE ID_compra = ?";
                
                preparedStatement = connection.prepareStatement(sql);
                
                preparedStatement.setInt(1, quantidade);
                preparedStatement.setInt(2, ID_cliente);
                preparedStatement.setInt(3, ID_produto);
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

    public static void PesquisaCompra(int chave) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:src\\empresa x.db");

            if (connection != null) {
                System.out.println("Conexão com o banco de dados SQLite estabelecida.");
                
                String sql = "SELECT * FROM Compra WHERE ID_Compra = ?";
                
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1, chave);

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int ID_cliente = resultSet.getInt("CPF_cliente");
                    int ID_produto = resultSet.getInt("ID_produto");
                    int quantidade = resultSet.getInt("Quantidade");
                    

                    System.out.println("ID_cliente " + ID_cliente);
                    System.out.println("ID_produto " + ID_produto);
                    System.out.println("quantidade: "+quantidade);
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
    public static void inserirVarios(Compra[] l){
        for(Compra lista:l){
           inserirCompra(lista.ID_cliente, lista.ID_produto, lista.quantidade);
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
                String sql = "SELECT * FROM Compra WHERE "+campo+" LIKE ?";
                
                // Crie um PreparedStatement com a consulta SQL
                preparedStatement = connection.prepareStatement(sql);

                // Defina o parâmetro da consulta com o valor que contém a string desejada
                preparedStatement.setString(1, "%" + valor + "%");

                // Execute a consulta
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("ID_Compra");
                    int produto = resultSet.getInt("ID_produto");
                    int Cliente = resultSet.getInt("CPF_cliente");
                    int quantidade = resultSet.getInt("Quantidade");
                    

                    System.out.println("ID_compra: " +id );
                    System.out.println("produto: " + produto);
                    System.out.println("Cliente " +Cliente);
                    System.out.println("quantidade: "+ quantidade);
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
