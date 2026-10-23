public class TesteConexao {
    public static void main(String[] args) {
        try (var conn = ConexaoBD.conectar()) {
            System.out.println("Conectou! Banco: " + conn.getCatalog());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}