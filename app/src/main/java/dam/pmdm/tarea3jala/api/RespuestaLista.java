package dam.pmdm.tarea3jala.api;

import java.util.ArrayList;

import dam.pmdm.tarea3jala.modelos.Pokemon;

public class RespuestaLista {
private ArrayList<Pokemon> results;

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
