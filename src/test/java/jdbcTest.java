import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class jdbcTest {
    final int ID_RECLAMATION = 999;
    final String DATE_RECLAMATION = "1 Decembre 2020";
    final String DATE_PAIMENT = "1 Janvier 2021";
    final int ID_NOTE = 25;
    final double MONTANT = 15350;
    final String DATE_NOTE = "5 Decembre 2020";
    final String TEXTE = "1er contact avec client";

    @BeforeEach
    void setUp() {
        jdbc myApp = new jdbc();
        try {
            myApp.ajouterReclamationAvecId(ID_RECLAMATION, DATE_RECLAMATION, DATE_PAIMENT, MONTANT);
            myApp.ajouterNoteAvecId(ID_NOTE, DATE_NOTE, TEXTE, ID_RECLAMATION);

        } catch (SQLException sqle) {
            fail(sqle);
        }
    }

    @AfterEach
    void tearDown() {
        jdbc myApp = new jdbc();
        try {
            myApp.supprimerReclamationParId(ID_RECLAMATION);
            myApp.supprimerNoteParId(ID_NOTE);

        } catch (SQLException sqle) {
            fail(sqle);
        }
    }

    // Reclamation
    @org.junit.jupiter.api.Test
    void ajouterReclamation() {
        jdbc myApp = new jdbc();
        try {
            myApp.supprimerReclamationParId(ID_RECLAMATION);
            myApp.ajouterReclamationAvecId(ID_RECLAMATION, DATE_RECLAMATION, DATE_PAIMENT, MONTANT);
            assertEquals(MONTANT, myApp.rechercheMontant(ID_RECLAMATION));

        } catch (SQLException sqle) {
            fail(sqle);
        }
    }

    @org.junit.jupiter.api.Test
    void rechercheMontant() {
        jdbc myApp = new jdbc();
        try {
            assertEquals(MONTANT, myApp.rechercheMontant(ID_RECLAMATION));

        } catch (SQLException sqle) {
            fail(sqle);
        }
    }

    @org.junit.jupiter.api.Test
    void rechercheDateReclamation() {
        jdbc myApp = new jdbc();
        try {
            assertEquals(DATE_RECLAMATION, myApp.rechercheDateReclamation(ID_RECLAMATION));

        } catch (SQLException sqle) {
            fail(sqle);
        }
    }

    @org.junit.jupiter.api.Test
    void rechercheDatePaiment() {
        jdbc myApp = new jdbc();
        try {
            assertEquals(DATE_PAIMENT, myApp.rechercheDatePaiment(ID_RECLAMATION));

        } catch (SQLException sqle) {
            fail(sqle);
        }
    }

    @org.junit.jupiter.api.Test
    void supprimerReclamation() {
        jdbc myApp = new jdbc();
        try {
            myApp.supprimerReclamationParId(ID_RECLAMATION);
            assertEquals(null, myApp.rechercheDateReclamation(ID_RECLAMATION));

        } catch (SQLException sqle) {
            fail(sqle);
        }
    }

    @org.junit.jupiter.api.Test
    void miseAJourDatePaiment() {
        String newDate = "12 juillet 2020";
        jdbc myApp = new jdbc();
        try {
            myApp.miseAJourDatePaiment(ID_RECLAMATION, newDate);
            assertEquals(newDate, myApp.rechercheDatePaiment(ID_RECLAMATION));

        } catch (SQLException sqle) {
            fail(sqle);
        }
    }

    // Note
    @org.junit.jupiter.api.Test
    void ajouterNote() {
        jdbc myApp = new jdbc();
        try {
            myApp.supprimerNoteParId(ID_NOTE);
            myApp.ajouterNoteAvecId(ID_NOTE, DATE_NOTE, TEXTE, ID_RECLAMATION);
            assertEquals(DATE_NOTE, myApp.rechercheDateNote(ID_NOTE));

        } catch (SQLException sqle) {
            fail(sqle);
        }
    }

    @org.junit.jupiter.api.Test
    void rechercheDateNote() {
        jdbc myApp = new jdbc();
        try {
            assertEquals(DATE_NOTE, myApp.rechercheDateNote(ID_NOTE));

        } catch (SQLException sqle) {
            fail(sqle);
        }
    }

    @org.junit.jupiter.api.Test
    void supprimerNoteParId() {
        jdbc myApp = new jdbc();
        try {
            myApp.supprimerNoteParId(ID_NOTE);
            assertEquals(null, myApp.rechercheDateNote(ID_NOTE));

        } catch (SQLException sqle) {
            fail(sqle);
        }
    }

    @org.junit.jupiter.api.Test
    void miseAJourTexte() {
        String newText = "Nouveau texte";
        jdbc myApp = new jdbc();
        try {
            myApp.miseAJourTexte(ID_NOTE, newText);
            assertEquals(newText, myApp.rechercheTexte(ID_NOTE));

        } catch (SQLException sqle) {
            fail(sqle);
        }
    }

}
