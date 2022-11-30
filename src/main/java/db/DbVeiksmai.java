package db;

import model.Filmas;
import service.Utility;

import java.sql.*;
import java.util.ArrayList;

public class DbVeiksmai {
    public final static String DB_PAVADINIMAS = "jdbc:mysql://localhost:3306/java9_filmai?useUnicode=true&characterEncoding=UTF-8";
    public final static String DB_USER = "root";
    public final static String DB_PASSWORD = "";

    public static final String FILMO_IDEJIMAS = "INSERT INTO filmai(pavadinimas, trukme, ivertinimas, aprasymas) VALUES (?, ?, ?, ?)";
    public static final String FILMO_ISTRYNIMAS_ID = "DELETE FROM filmai WHERE id = ?";
    public static final String FILMO_ISTRYNIMAS_PAVADINIMA = "DELETE FROM filmai WHERE pavadinimas = ?";
    public static final String VISU_FILMU_GAVIMAS = "SELECT * FROM filmai";
    public static final String FILMU_AUKSTESNIU_UZ_REITINGA = "SELECT * FROM filmai WHERE ivertinimas > ?";

    public static Connection prisijungtiPrieDuombazes() throws SQLException {
        Connection jungtis = DriverManager.getConnection(DB_PAVADINIMAS, DB_USER, DB_PASSWORD);
        return jungtis;
    }

    public static boolean idetiFilma(Connection jungtis, Filmas filmas) throws SQLException {
        PreparedStatement paruostukas = jungtis.prepareStatement(FILMO_IDEJIMAS);

        paruostukas.setString(1, filmas.getPavadinimas());
        paruostukas.setInt(2, filmas.getTrukme());
        paruostukas.setDouble(3, filmas.getIvertinimas());
        paruostukas.setString(4, filmas.getAprasymas());

        boolean ats = paruostukas.execute();
        paruostukas.close();
        return ats;
    }

    public static int istrintiFilmaPagalId(Connection jungtis, int id) throws SQLException {
        PreparedStatement paruostukas = jungtis.prepareStatement(FILMO_ISTRYNIMAS_ID);

        paruostukas.setInt(1, id);
        int ats = paruostukas.executeUpdate();
        paruostukas.close();
        return ats;
    }

    public static int istrintiFilmaPagalPavadinima(Connection jungtis, String pavadinimas) throws SQLException {
        PreparedStatement paruostukas = jungtis.prepareStatement(FILMO_ISTRYNIMAS_PAVADINIMA);

        paruostukas.setString(1, pavadinimas);
        int ats = paruostukas.executeUpdate();
        paruostukas.close();
        return ats;
    }

    public static ArrayList<Filmas> grazintiVisusFilmus(Connection jungtis) throws SQLException {
        PreparedStatement paruostukas = jungtis.prepareStatement(VISU_FILMU_GAVIMAS);
        ResultSet rezultatas = paruostukas.executeQuery();

        ArrayList<Filmas> filmai = Utility.konvertuotiResultSetIArrayList(rezultatas);
        return filmai;
    }

    public static ArrayList<Filmas> grazintiFilmusDidesniusUzReitinga(Connection jungtis, double ivertinimas) throws SQLException {
        PreparedStatement paruostukas = jungtis.prepareStatement(FILMU_AUKSTESNIU_UZ_REITINGA);
        paruostukas.setDouble(1, ivertinimas);
        ResultSet rezultatas = paruostukas.executeQuery();

        ArrayList<Filmas> filmai = Utility.konvertuotiResultSetIArrayList(rezultatas);
        return filmai;
    }
}
