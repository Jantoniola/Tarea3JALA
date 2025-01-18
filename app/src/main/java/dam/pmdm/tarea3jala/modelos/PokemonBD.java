package dam.pmdm.tarea3jala.modelos;

/**
 * Modelo de datos que representa un documento de la base de datos
 */
public class PokemonBD {
    private String name;
    private int id;
    private String image;
    private String types;
    private int height;
    private int weight;

    public PokemonBD() {
    }

    public PokemonBD(String name, int id, String image, String types, int height, int weight) {
        this.name = name;
        this.id = id;
        this.image = image;
        this.types = types;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
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
}
