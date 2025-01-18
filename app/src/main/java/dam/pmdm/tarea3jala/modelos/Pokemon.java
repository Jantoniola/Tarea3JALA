package dam.pmdm.tarea3jala.modelos;

public class Pokemon {
    private String name;
    private String url;
    private boolean capturado=false;
    private int id;

    public Pokemon(String name, String url, boolean capturado, int id) {
        this.name = name;
        this.url = url;
        this.capturado = capturado;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isCapturado() {
        return capturado;
    }

    public void setCapturado(boolean capturado) {
        this.capturado = capturado;
    }

    public int getId() {
        String[] cadenas=url.split("/");
        this.id=Integer.parseInt(cadenas[cadenas.length-1]);
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
