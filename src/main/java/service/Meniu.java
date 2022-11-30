package service;

import db.DbVeiksmai;
import model.Filmas;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Meniu {

    private static final Scanner skait = new Scanner(System.in);

    public static void isvestiMeniu() {
        System.out.println("Pasirinkite vieną iš meniu variantų:");
        System.out.println("Įveskite 1, jei norite pridėti naują filmą");
        System.out.println("Įveskite 2, jei norite ištrinti filmą");
        System.out.println("Įveskite 3, jei norite paredaguoti filmą");
        System.out.println("Įveskite 4, jei norite, jog jums išvestų visus filmus");
        System.out.println("Įveskite 5, jei norite surasti filmus, kurių reitingas yra didesnis už X");
    }

    public static int nuskaitytiPasirinkima() {
        int pasirinkimas = skait.nextInt();
        skait.nextLine();
        return pasirinkimas;
    }

    public static void filmoIdejimas(Connection jungtis) throws SQLException {
        System.out.println("Pasirinkote pridėti naują filmą");
        System.out.println("Įveskite filmo pavadinimą:");
        String pavadinimas = skait.nextLine();
        System.out.println("Įveskite filmo trukmę:");
        int trukme = skait.nextInt();
        System.out.println("Įveskite įvertinimą filmo:");
        double ivertinimas = skait.nextDouble();
        System.out.println("Įveskite filmo aprašymą:");
        skait.nextLine();
        String aprasymas = skait.nextLine();
        Filmas filmas = new Filmas(pavadinimas, trukme, ivertinimas, aprasymas);

        DbVeiksmai.idetiFilma(jungtis, filmas);
        System.out.println("Filmas sėkmingai įdėtas");
    }

    public static void filmoIstrynimas(Connection jungtis) throws SQLException {
        System.out.println("Pasirinkote filmo ištrynimą.");
        System.out.println("Įveskite 1, jei norite ištrinti pagal id. Įveskite 2, jei norite ištrinti pagal pavadinimą.");
        int pasirinkimas = nuskaitytiPasirinkima();
        switch (pasirinkimas) {
            case 1:
                System.out.println("Įveskite id:");
                int id = nuskaitytiPasirinkima();
                DbVeiksmai.istrintiFilmaPagalId(jungtis, id);
                System.out.println("Filmas sėkmingai ištrintas");
                break;
            case 2:
                System.out.println("Įveskite pavadinimą:");
                String pavadinimas = skait.nextLine();
                DbVeiksmai.istrintiFilmaPagalPavadinima(jungtis, pavadinimas);
                System.out.println("Filmas sėkmingai ištrintas");
                break;
            default:
                System.out.println("Įvedėte netinkamą skaičių");
        }
    }

    public static void filmuIsvedimas(Connection jungtis) throws SQLException {
        System.out.println("Pasirinkote filmų išvedimą. Išvedame filmus:");
        ArrayList<Filmas> visiFilmai = DbVeiksmai.grazintiVisusFilmus(jungtis);
        Utility.isvestiArrayListKasEilute(visiFilmai);
    }

    public static void filmuIsvedimasPagalReitinga(Connection jungtis) throws SQLException {
        System.out.println("Pasirinkote filmų išvedimą, kurie turi aukštesnį reitingą, nei...");
        System.out.println("Įveskite reitingą (skaičių), už kurį norėtumėte, jog filmai būtų aukštesni");
        double ivertinimas = skait.nextDouble();
        ArrayList<Filmas> filmai = DbVeiksmai.grazintiFilmusDidesniusUzReitinga(jungtis, ivertinimas);
        Utility.isvestiArrayListKasEilute(filmai);
    }

    public static void meniuVykdymas(Connection jungtis, int pasirinkimas) throws SQLException {
        switch(pasirinkimas) {
            case 1:
                filmoIdejimas(jungtis);
                break;
            case 2:
                filmoIstrynimas(jungtis);
                break;
            case 3:
                System.out.println("Redagavimo nėra");
                break;
            case 4:
                filmuIsvedimas(jungtis);
                break;
            case 5:
                filmuIsvedimasPagalReitinga(jungtis);
                break;
            default:
                System.out.println("Įvedėte netinkamą pasirinkimą. Programa sustos.");

        }
    }

}
