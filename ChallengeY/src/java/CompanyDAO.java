
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompanyDAO {

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CompanyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String checkSite(String name, String zip) throws Exception {

        try (Connection c = DriverManager.getConnection(
                "jdbc:postgresql://localhost/companies", "postgres", "admin")) {
            PreparedStatement ps = c.prepareStatement(
                    "select website from company where name = ? and zip = ?");
            ps.setString(1, name);
            ps.setString(2, zip);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("website");
            } else {
                throw new Exception("Não foi possível encontrar o site!");
            }
        }
    }

    public static void inserir(Company company) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost/companies", "postgres", "admin")) {
            String sql = "INSERT INTO company(id, name, zip, website) VALUES (?, ?, ?, ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, company.getId());
            stm.setString(2, company.getName());
            stm.setString(3, company.getZipcode());
            stm.setString(4, company.getWebsite());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível executar o acesso", e);
        }
    }

    public static void addSite(String name, String zip, String website) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost/companies", "postgres", "admin")) {
            String sql = "UPDATE company SET website = ? WHERE name = ? and zip = ?;";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, website);
            stm.setString(2, name);
            stm.setString(3, zip);
            stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível executar o acesso", e);
        }
    }
}
