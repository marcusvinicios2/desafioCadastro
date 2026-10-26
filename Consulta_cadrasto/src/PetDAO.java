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
            preencher(ps, pet);
            ps.executeUpdate();
        }
    }

    public List<Pet> listar() throws SQLException {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT id, nome, tipo, sexo, endereco, idade, peso, raca FROM pet ORDER BY id";

        try (Connection conn = ConexaoBD.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                String endereco = rs.getString("endereco");
                String raca = rs.getString("raca");

                Pet p = new Pet(
                        Tipo.valueOf(rs.getString("tipo")),
                        Sexo.valueOf(rs.getString("sexo")),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        endereco == null ? "" : endereco,
                        rs.getDouble("peso"),
                        raca == null ? "" : raca
                );
                p.setId(rs.getLong("id"));
                pets.add(p);
            }
        }
        return pets;
    }

    public void atualizar(Pet pet) throws SQLException {
        String sql = "UPDATE pet SET nome = ?, tipo = ?, sexo = ?, endereco = ?, "
                + "idade = ?, peso = ?, raca = ? WHERE id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            preencher(ps, pet);
            ps.setLong(8, pet.getId());
            ps.executeUpdate();
        }
    }

    public void deletar(long id) throws SQLException {
        String sql = "DELETE FROM pet WHERE id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    // Preenche os 7 primeiros parâmetros (usado por salvar e atualizar)
    private void preencher(PreparedStatement ps, Pet pet) throws SQLException {
        ps.setString(1, pet.getNome());
        ps.setString(2, pet.getTipo().name());
        ps.setString(3, pet.getSexo().name());
        ps.setString(4, pet.getEndereco());
        ps.setInt(5, pet.getIdade());

        if (pet.getPeso() == 0.0) {
            ps.setNull(6, Types.DECIMAL);
        } else {
            ps.setDouble(6, pet.getPeso());
        }

        ps.setString(7, pet.getRaca());
    }
}