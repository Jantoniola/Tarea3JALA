package dam.pmdm.tarea3jala.modelos;

import java.util.ArrayList;

public class PokemonCapturado {
    private String name;
    private boolean capturado = false;
    private int id;
    private int height;
    private int weight;
    private ArrayList<Tipos> types;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCapturado() {
        return capturado;
    }

    public void setCapturado(boolean capturado) {
        this.capturado = capturado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public ArrayList<Tipos> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Tipos> types) {
        this.types = types;
    }

    public static class Tipos {
        private String slot;
        private ClaseTipo type;

        public String getSlot() {
            return slot;
        }

        public void setSlot(String slot) {
            this.slot = slot;
        }

        public ClaseTipo getType() {
            return type;
        }

        public void setType(ClaseTipo type) {
            this.type = type;
        }
    }
    public static class ClaseTipo{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}


