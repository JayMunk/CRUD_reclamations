import java.sql.*;

public class jdbc {
    private Connection connection;

    public static void main(String[] args) throws SQLException {
        jdbc myApp = new jdbc();
        myApp.ajouterReclamationAvecId(6, "25 Janvier 2020", "25 Janvier 2025", 250000);
        myApp.ajouterReclamationAvecId(7, "10 Septembre 2002", "30 Septembre 2002", 2500);
        System.out.println("Afficher reclamation:");
        myApp.afficherReclamation();
        myApp.ajouterNoteAvecId(9, "30 Septembre 2002", "Dossier fermer", 7);
        myApp.ajouterNoteAvecId(10, "25 Janvier 2021", "1 ans", 6);
        System.out.println("Afficher note:");
        myApp.afficherNote();
        myApp.ajouterNoteAvecId(11, "25 Janvier 2022", "2 ans", 6);
        myApp.ajouterNoteAvecId(12, "25 Janvier 2023", "3 ans", 6);
        System.out.println("Afficher note pour la reclamation #6:");
        myApp.afficherNoteParReclamation(6);
    }

    public jdbc() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp6", "test", "test"

            );
            System.out.println(connection);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    // reclamation
    public double rechercheMontant(int idReclamation) throws SQLException {
        double montant = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement
                .executeQuery("select * from reclamation" + " where idReclamation='" + idReclamation + "'");
        if (resultSet.next()) {
            montant = resultSet.getDouble("montant");
        }
        statement.close();
        return montant;
    }

    public String rechercheDateReclamation(int idReclamation) throws SQLException {
        String date = null;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement
                .executeQuery("select * from reclamation" + " where idReclamation='" + idReclamation + "'");
        if (resultSet.next()) {
            date = resultSet.getString("date");
        }
        statement.close();
        return date;
    }

    public String rechercheDatePaiment(int idReclamation) throws SQLException {
        String datePaiment = null;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement
                .executeQuery("select * from reclamation" + " where idReclamation='" + idReclamation + "'");
        if (resultSet.next()) {
            datePaiment = resultSet.getString("date_paiment");
        }
        statement.close();
        return datePaiment;
    }

    public void afficherReclamation() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet reusultSet = statement.executeQuery("select * from reclamation");
        while (reusultSet.next()) {
            System.out.println(reusultSet.getString("idReclamation") + ", " + reusultSet.getString("date") + ", "
                    + reusultSet.getString("date_paiment") + ", " + reusultSet.getString("montant"));
        }
    }

    public void ajouterReclamation(String date, String datePaiment, double montant) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "insert into reclamation (date, date_paiment, montant) " + "values ('" + date + "','" + datePaiment
                + "','" + montant + "')";
        statement.executeUpdate(sql);
        statement.close();
    }

    public void ajouterReclamationAvecId(int id, String date, String datePaiment, double montant) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "insert into reclamation (idReclamation, date, date_paiment, montant) " + "values ('" + id + "','"
                + date + "','" + datePaiment + "','" + montant + "')";
        statement.executeUpdate(sql);
        statement.close();
    }

    public void supprimerReclamationParId(int idReclamation) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "delete from reclamation where idReclamation='" + idReclamation + "'";
        statement.executeUpdate(sql);
        statement.close();
    }

    public void miseAJourDatePaiment(int idReclamation, String datePaiment) throws SQLException {
        String sql = "update reclamation set date_paiment = ? where idReclamation = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(2, Integer.toString(idReclamation));
        statement.setString(1, datePaiment);
        statement.executeUpdate();
        statement.close();
    }

    // note
    public void ajouterNote(String date, String texte, int idReclamation) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "insert into note (date, texte, idReclamation) " + "values ('" + date + "','" + texte + "','"
                + idReclamation + "')";
        statement.executeUpdate(sql);
        statement.close();
    }

    public void ajouterNoteAvecId(int id, String date, String texte, int idReclamation) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "insert into note (idNote, date, texte, idReclamation) " + "values ('" + id + "','" + date + "','"
                + texte + "','" + idReclamation + "')";
        statement.executeUpdate(sql);
        statement.close();
    }

    public void afficherNote() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet reusultSet = statement.executeQuery("select * from note");
        while (reusultSet.next()) {
            System.out.println(reusultSet.getString("idNote") + ", " + reusultSet.getString("date") + ", "
                    + reusultSet.getString("texte") + ", " + reusultSet.getString("idReclamation"));
        }
    }

    public void afficherNoteParReclamation(int idReclamation) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet reusultSet = statement
                .executeQuery("select * from note" + " where idReclamation='" + idReclamation + "'");
        while (reusultSet.next()) {
            System.out.println(reusultSet.getString("idNote") + ", " + reusultSet.getString("date") + ", "
                    + reusultSet.getString("texte") + ", " + reusultSet.getString("idReclamation"));
        }
    }

    public String rechercheTexte(int idNote) throws SQLException {
        String texte = null;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from note" + " where idNote='" + idNote + "'");
        if (resultSet.next()) {
            texte = resultSet.getString("texte");
        }
        statement.close();
        return texte;
    }

    public String rechercheDateNote(int idNote) throws SQLException {
        String date = null;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from note" + " where idNote='" + idNote + "'");
        if (resultSet.next()) {
            date = resultSet.getString("date");
        }
        statement.close();
        return date;
    }

    public void supprimerNoteParId(int idNote) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "delete from note where idNote='" + idNote + "'";
        statement.executeUpdate(sql);
        statement.close();
    }

    public void miseAJourTexte(int idNote, String texte) throws SQLException {
        String sql = "update note set texte = ? where idNote = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(2, Integer.toString(idNote));
        statement.setString(1, texte);
        statement.executeUpdate();
        statement.close();
    }

}
