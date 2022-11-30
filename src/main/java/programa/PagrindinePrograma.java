package programa;

import db.DbVeiksmai;
import service.Meniu;

import java.sql.Connection;
import java.sql.SQLException;

public class PagrindinePrograma {
    public static void main(String[] args) {
        Meniu.isvestiMeniu();
        int pasirinkimas = Meniu.nuskaitytiPasirinkima();
        Connection jungtis = null;

        try {
            jungtis = DbVeiksmai.prisijungtiPrieDuombazes();
            Meniu.meniuVykdymas(jungtis, pasirinkimas);
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nepavyko prisijungti prie duomenų bazės.");
        }
    }



}
