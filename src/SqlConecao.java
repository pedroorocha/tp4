
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
public class SqlConecao {

    private static Connection conexão;
    public static Connection conectar() throws SQLException {
        if (conexão == null || conexão.isClosed()) {
            try {
                Class.forName("org.sqlite.JDBC");
                conexão = DriverManager.getConnection("jdbc:sqlite:src\\empresa x.db");
                System.out.println("Conexão com o banco de dados SQLite estabelecida.");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
            return conexão;
        }
    
        public static void closeConnection() {
            try {
                if (conexão != null && !conexão.isClosed()) {
                    conexão.close();
                    System.out.println("Conexão com o banco de dados SQLite fechada.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    public static void main(String[] args) {
        //Clientes.inserirClientes("pedro", 40028922, "email@gmail");
        //Clientes.deletaCliente(3);
        //Clientes.atualizaCliente(4,"pedro h", 99999999, "email@hotmail");
        //Clientes.PesquisaCliente(4);

        Clientes[] listaC = new Clientes[]{
            new Clientes("serjão", 54548, "serjão"),
            new Clientes("ORIVALDO", 8461, "orivas@gmail"),
            new Clientes("eronildp", 5651351, "dp@gmail"),
            new Clientes("gerald", 666666666, "666@gmail"),
            new Clientes("lacelot", 76415, "l@gmail")
        };
        //Clientes.inserirVarios(listaC);
        //Clientes.pesquisaValor("gerald", "Nome");
       //Produto.inserirProduto("arroz", 1.50);
       //Produto.deletaProduto(1);
       //Produto.atualizaProduto(2, "arroz p", 2.50);
       //Produto.PesquisaProduto(2);
       Produto[] listaP = new Produto[]{
            new Produto("alface", 1.90),
            new Produto("cenoura",5.70),
            new Produto("batata",  4.10),
            new Produto("macarrão", 6.0),
            new Produto("feijão",2.81)
        };
        //Produto.inserirVarios(listaP);
        //Produto.pesquisaValor("arroz", "Nome_produto");
        //Compra.inserirCompra(4, 2, 4);
        //Compra.deletaCompra(1);
        //Compra.atualizaCompra(2, 4, 2, 6);
        //Compra.PesquisaCompra(2);
        Compra[] listaCO = new Compra[]{
            new Compra(5, 7,2),
            new Compra(4, 6, 8),
            new Compra(7, 4, 10),
            new Compra(8, 5, 11),
            new Compra(6, 7,1 )
        };
        //Compra.inserirVarios(listaCO);
       // Compra.pesquisaValor("2", "ID_compra");
    }

}
