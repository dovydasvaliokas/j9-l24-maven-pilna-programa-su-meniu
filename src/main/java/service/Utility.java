package service;

import model.Filmas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Utility {
    public static <T> void isvestiArrayListKasEilute(ArrayList<T> sarasas) {
        for (T t : sarasas) {
            System.out.println(t);
        }
    }


    public static ArrayList<Filmas> konvertuotiResultSetIArrayList(ResultSet rezultatas) throws SQLException {
        ArrayList<Filmas> filmai = new ArrayList<>();
        while (rezultatas.next()) {
            int id = rezultatas.getInt("id");
            String pavadinimas = rezultatas.getString("pavadinimas");
            int trukme = rezultatas.getInt("trukme");
            double ivertinimas = rezultatas.getDouble("ivertinimas");
            String aprasymas = rezultatas.getString("aprasymas");

            Filmas filmas = new Filmas(id, pavadinimas, trukme, ivertinimas, aprasymas);
            filmai.add(filmas);
        }
        return filmai;
    }
}
