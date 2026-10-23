import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {

    public void salvar(Pet pet) throws SQLException {
        String sql = "INSERT INTO pet (nome, tipo, sexo, endereco, idade, peso, raca) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pet.getNome());
            ps.setString(2, pet.getTipo().name());   // enum -> texto
            ps.setString(3, pet.getSexo().name());   // enum -> texto
            ps.setString(4, pet.getEndereco());
            ps.setInt(5, pet.getIdade());

            if (pet.getPeso() == 0.0) {              // peso não informado
                ps.setNull(6, Types.DECIMAL);
            } else {
                ps.setDouble(6, pet.getPeso());
            }

            ps.setString(7, pet.getRaca());
            ps.executeUpdate();
        }
    }

    public List<Pet> listar() throws SQLException {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT nome, tipo, sexo, endereco, idade, peso, raca FROM pet";

        try (Connection conn = ConexaoBD.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Pet p = new Pet(
                        Tipo.valueOf(rs.getString("tipo")),
                        Sexo.valueOf(rs.getString("sexo")),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("endereco"),
                        rs.getDouble("peso"),     // se for NULL no banco, volta 0.0
                        rs.getString("raca")
                );
                pets.add(p);
            }
        }
        return pets;
    }
}