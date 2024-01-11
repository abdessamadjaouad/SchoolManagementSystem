package services;

import classes.Enseignant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnseignantService {
    public void ajouterEnseignant(Enseignant enseignant) {

        DB conn = new DB();
        Connection connection = conn.getConnection();
        String query = "INSERT INTO enseignant (id, email, grade, nom, prenom) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, enseignant.getId());
            statement.setString(2, enseignant.getEmail());
            statement.setString(3, enseignant.getGrade());
            statement.setString(4, enseignant.getNom());
            statement.setString(5, enseignant.getPrenom());
            statement.executeUpdate();
            System.out.println("New enseignant record successfully inserted into the table.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }
    public Enseignant getEnseignantById(long enseignantId) {
        Enseignant enseignant = null;

        DB conn = new DB();
        Connection connection = conn.getConnection();
        String query = "SELECT id, email, grade, nom, prenom FROM enseignant WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, enseignantId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                enseignant = new Enseignant();
                enseignant.setId(resultSet.getLong("id"));
                enseignant.setNom(resultSet.getString("nom"));
                enseignant.setPrenom(resultSet.getString("prenom"));
                enseignant.setEmail(resultSet.getString("email"));
                enseignant.setGrade(resultSet.getString("grade"));
            }
            System.out.println("Retrieval of Enseignant well done.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.disconnect();
        }
        return enseignant;
    }

    public List<Enseignant> getAllEnseignants() {
        List<Enseignant> enseignants = new ArrayList<>();
        DB conn = new DB();
        Connection connection = conn.getConnection();
        String query = "SELECT id, email, grade, nom, prenom FROM enseignant";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Enseignant enseignant = new Enseignant();
                enseignant.setId(resultSet.getLong("id"));
                enseignant.setNom(resultSet.getString("nom"));
                enseignant.setPrenom(resultSet.getString("prenom"));
                enseignant.setEmail(resultSet.getString("email"));
                enseignant.setGrade(resultSet.getString("grade"));
                enseignants.add(enseignant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return enseignants;
    }

    public void modifierEnseignant(Enseignant enseignant) {
        DB conn = new DB();
        Connection connection = conn.getConnection();
        String query = "UPDATE enseignant SET email = ?, grade = ?, nom = ?, prenom = ? WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, enseignant.getEmail());
            statement.setString(2, enseignant.getGrade());
            statement.setString(3, enseignant.getNom());
            statement.setString(4, enseignant.getPrenom());
            statement.setLong(5, enseignant.getId());
            statement.executeUpdate();
            System.out.println("Enseignant with id:"+enseignant.getId()+" is well modified");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    public void supprimerEnseignant(long enseignantId) {
        DB conn = new DB();
        Connection connection = conn.getConnection();
        String query = "DELETE FROM enseignant WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, enseignantId);
            statement.executeUpdate();
            System.out.println("Enseignant with id : "+enseignantId+" is well deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }
}
