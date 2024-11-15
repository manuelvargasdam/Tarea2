package com.tarea2.mvs;

/**
 * La clase Personaje representa un personaje en la aplicación
 */
public class Personaje {
    // Nombre del personaje.
    private String name;
    // Recurso de la imagen del personaje (int ID de drawable).
    private int imageResource;
    // Descripción del personaje.
    private String description;
    // Habilidades del personaje.
    private String abilities;

    /**
     * Constructor de la clase Personaje.
     *
     * @param name          El nombre del personaje.
     * @param imageResource El recurso de imagen asociado al personaje.
     * @param description   La descripción del personaje.
     * @param abilities     Las habilidades especiales del personaje.
     */
    public Personaje(String name, int imageResource, String description, String abilities) {
        this.name = name;
        this.imageResource = imageResource;
        this.description = description;
        this.abilities = abilities;
    }

    /**
     * Obtiene el nombre del personaje.
     *
     * @return El nombre del personaje.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene el recurso de imagen del personaje.
     *
     * @return El ID del recurso de imagen.
     */
    public int getImageResource() {
        return imageResource;
    }

    /**
     * Obtiene la descripción del personaje.
     *
     * @return La descripción del personaje.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Obtiene las habilidades del personaje.
     *
     * @return Las habilidades del personaje.
     */
    public String getAbilities() {
        return abilities;
    }
}