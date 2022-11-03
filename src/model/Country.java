package model;

import java.io.Serializable;

public class Country implements Comparable<Country>, Serializable {
    private static final long serialVersionUID = 1;
    private String id;
    private String name;
    private String countryCode;
    private double population;

    public Country(String id, String name, double population, String countryCode) {
        super();
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.population = population;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public String information() {
        String all = "Nombre: " + getName() + " ID: " + getId() + " Country Id: " + getCountryCode() + " Population: " + getPopulation();
        return all;
    }

    public int compareTo(Country e) {
        if (e.getPopulation() > population) {
            return -1;
        } else if (e.getPopulation() == population) {
            return 0;
        } else {
            return 1;
        }
    }


}
