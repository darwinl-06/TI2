package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import exceptions.CountryDoesNotExist;

public class World {
    private static ArrayList<Country> country = new ArrayList<Country>();
    private static ArrayList<City> city = new ArrayList<City>();
    public static final String CITIES = "src/data/city.bbd";
    public static final String COUNTRIES = "src/data/countries.bbd";

    public String insertConsult(String[] partsConsult, String consult) {
        String out = "";
        String where = "where";
        String from = "from";
        String sign = "*";
        if (partsConsult[3].equals("cities")) {
            if (sign.equals(partsConsult[1]) && from.equalsIgnoreCase(partsConsult[2])) {
                int value = partsConsult.length;
                if (consult.contains("ORDER BY")) {
                    value = partsConsult.length - 3;
                }
                if (value > 4) {
                    if (where.equalsIgnoreCase(partsConsult[4])) {
                        if (partsConsult[6].equals("=")) {
                            out = consultEqualsCities(partsConsult, consult);

                        } else if (partsConsult[6].equals(">")) {

                            out = consultMajorCities(partsConsult, consult);
                        } else if (partsConsult[6].equals("<")) {
                            out = consultMinorCities(partsConsult, consult);
                        }
                    }
                } else {
                    String order = "order";
                    String by = "by";
                    String name = "name";
                    String id = "id";
                    String countryID = "countryid";
                    String population = "population";
                    if (partsConsult.length == 7) {
                        if (order.equalsIgnoreCase(partsConsult[4]) && by.equalsIgnoreCase(partsConsult[5])) {
                            if (name.equalsIgnoreCase(partsConsult[6])) {
                                Collections.sort(city, new Comparator<City>() {
                                    public int compare(City p1, City p2) {
                                        return new String(p1.getName()).compareTo(new String(p2.getName()));
                                    }
                                });

                            } else if (id.equalsIgnoreCase(partsConsult[6])) {
                                Collections.sort(city, new Comparator<City>() {
                                    public int compare(City p1, City p2) {
                                        return new String(p1.getId()).compareTo(new String(p2.getId()));
                                    }
                                });
                            } else if (countryID.equalsIgnoreCase(partsConsult[6])) {
                                Collections.sort(city, new Comparator<City>() {
                                    public int compare(City p1, City p2) {
                                        return new String(p1.getCountryId()).compareTo(new String(p2.getCountryId()));
                                    }
                                });
                            } else if (population.equalsIgnoreCase(partsConsult[6])) {
                                Collections.sort(city);
                            }
                        }
                    }
                    for (int i = 0; i < city.size(); i++) {
                        out += city.get(i).information() + "\n";

                    }
                }
            } else {
                out = "Debe acomodar la estructura de la consulta";
            }

        } else if (partsConsult[3].equals("countries")) {

            if (sign.equals(partsConsult[1]) && from.equalsIgnoreCase(partsConsult[2])) {
                if (partsConsult.length > 4) {
                    if (where.equalsIgnoreCase(partsConsult[4])) {
                        if (partsConsult[6].equals("=")) {
                            out = consultEqualsCountry(partsConsult, consult);

                        } else if (partsConsult[6].equals(">")) {

                            out = consultMajorCountry(partsConsult, consult);
                        } else if (partsConsult[6].equals("<")) {
                            out = consultMinorCountry(partsConsult, consult);
                        }
                    }
                } else {
                    for (int i = 0; i < country.size(); i++) {
                        out += country.get(i).information() + "\n";
                    }
                }
            } else {
                out = "Debe acomodar la estructura de la consulta";
            }

        } else {
            out = "la consulta no es valida  ";
        }
        return out;
    }

    public String consultMinorCountry(String[] partsConsult, String consult) {
        String out = "";
        String name = "name";
        String id = "id";
        String countryCode = "countrycode";
        String population = "population";
        String order = "order";
        String by = "by";
        consult = consult.replace("'", "");
        partsConsult = consult.split(" ");
        if (partsConsult.length > 10) {
            if (order.equalsIgnoreCase(partsConsult[8]) && by.equalsIgnoreCase(partsConsult[9])) {
                if (name.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(country, new Comparator<Country>() {
                        public int compare(Country p1, Country p2) {
                            return new String(p1.getName()).compareTo(new String(p2.getName()));
                        }
                    });

                } else if (id.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(country, new Comparator<Country>() {
                        public int compare(Country p1, Country p2) {
                            return new String(p1.getId()).compareTo(new String(p2.getId()));
                        }
                    });
                } else if (countryCode.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(country, new Comparator<Country>() {
                        public int compare(Country p1, Country p2) {
                            return new String(p1.getCountryCode()).compareTo(new String(p2.getCountryCode()));
                        }
                    });
                } else if (population.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(country);
                }
            }
        }
        double value = Double.parseDouble(partsConsult[7]);
        if (population.equalsIgnoreCase(partsConsult[5])) {
            for (int i = 0; i < country.size(); i++) {
                if (value > country.get(i).getPopulation()) {
                    out += country.get(i).information() + "\n";
                }
            }
        } else {
            out = "No puede aplicar esta operacion a esa columna";
        }
        return out;
    }

    public String consultMajorCountry(String[] partsConsult, String consult) {
        String out = "";
        String name = "name";
        String id = "id";
        String countryCode = "countrycode";
        String population = "population";
        String order = "order";
        String by = "by";
        consult = consult.replace("'", "");
        partsConsult = consult.split(" ");
        if (partsConsult.length > 10) {
            if (order.equalsIgnoreCase(partsConsult[8]) && by.equalsIgnoreCase(partsConsult[9])) {
                if (name.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(country, new Comparator<Country>() {
                        public int compare(Country p1, Country p2) {
                            return new String(p1.getName()).compareTo(new String(p2.getName()));
                        }
                    });

                } else if (id.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(country, new Comparator<Country>() {
                        public int compare(Country p1, Country p2) {
                            return new String(p1.getId()).compareTo(new String(p2.getId()));
                        }
                    });
                } else if (countryCode.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(country, new Comparator<Country>() {
                        public int compare(Country p1, Country p2) {
                            return new String(p1.getCountryCode()).compareTo(new String(p2.getCountryCode()));
                        }
                    });
                } else if (population.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(country);
                }
            }
        }
        double value = Double.parseDouble(partsConsult[7]);
        if (population.equalsIgnoreCase(partsConsult[5])) {
            for (int i = 0; i < country.size(); i++) {
                if (value < country.get(i).getPopulation()) {
                    out += country.get(i).information() + "\n";
                }
            }
        } else {
            out = "No puede aplicar esta operacion a esa columna";
        }
        return out;
    }

    public String consultEqualsCountry(String[] partsConsult, String consult) {
        String out = "";
        String name = "name";
        String id = "id";
        String countryCode = "countrycode";
        String population = "population";
        consult = consult.replace("'", "");
        partsConsult = consult.split(" ");
        String order = "order";
        String by = "by";
        if (partsConsult.length > 10) {
            if (order.equalsIgnoreCase(partsConsult[8]) && by.equalsIgnoreCase(partsConsult[9])) {
                if (name.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(country, new Comparator<Country>() {
                        public int compare(Country p1, Country p2) {
                            return new String(p1.getName()).compareTo(new String(p2.getName()));
                        }
                    });

                } else if (id.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(country, new Comparator<Country>() {
                        public int compare(Country p1, Country p2) {
                            return new String(p1.getId()).compareTo(new String(p2.getId()));
                        }
                    });
                } else if (countryCode.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(country, new Comparator<Country>() {
                        public int compare(Country p1, Country p2) {
                            return new String(p1.getCountryCode()).compareTo(new String(p2.getCountryCode()));
                        }
                    });
                } else if (population.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(country);
                }
            }
        }
        for (int i = 0; i < country.size(); i++) {
            if (name.equalsIgnoreCase(partsConsult[5])) {
                if (partsConsult[7].equalsIgnoreCase(country.get(i).getName())) {
                    out += country.get(i).information() + "\n";
                }
            } else if (id.equalsIgnoreCase(partsConsult[5])) {
                if (partsConsult[7].equals(country.get(i).getId())) {
                    out += country.get(i).information() + "\n";
                }
            } else if (countryCode.equalsIgnoreCase(partsConsult[5])) {
                if (partsConsult[7].equals(country.get(i).getCountryCode())) {
                    out += country.get(i).information() + "\n";
                }
            } else if (population.equalsIgnoreCase(partsConsult[5])) {
                double value = Double.parseDouble(partsConsult[7]);
                if (value == country.get(i).getPopulation()) {
                    out += country.get(i).information() + "\n";
                }
            }
        }
        return out;
    }

    public String consultMinorCities(String[] partsConsult, String consult) {
        String out = "";
        String order = "order";
        String by = "by";
        String name = "name";
        String id = "id";
        String countryID = "countryid";
        String population = "population";
        consult = consult.replace("'", "");
        partsConsult = consult.split(" ");
        if (partsConsult.length > 10) {
            if (order.equalsIgnoreCase(partsConsult[8]) && by.equalsIgnoreCase(partsConsult[9])) {
                if (name.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(city, new Comparator<City>() {
                        public int compare(City p1, City p2) {
                            return new String(p1.getName()).compareTo(new String(p2.getName()));
                        }
                    });

                } else if (id.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(city, new Comparator<City>() {
                        public int compare(City p1, City p2) {
                            return new String(p1.getId()).compareTo(new String(p2.getId()));
                        }
                    });
                } else if (countryID.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(city, new Comparator<City>() {
                        public int compare(City p1, City p2) {
                            return new String(p1.getCountryId()).compareTo(new String(p2.getCountryId()));
                        }
                    });
                } else if (population.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(city);
                }
            }
        }
        double value = Double.parseDouble(partsConsult[7]);
        if (population.equalsIgnoreCase(partsConsult[5])) {
            for (int i = 0; i < city.size(); i++) {
                if (value > city.get(i).getPopulation()) {
                    out += city.get(i).information() + "\n";
                }
            }
        } else {
            out += "No puede aplicar esta operacion a esa columna";
        }
        return out;
    }

    public String consultMajorCities(String[] partsConsult, String consult) {
        String out = "";
        String order = "order";
        String by = "by";
        String name = "name";
        String id = "id";
        String countryID = "countryid";
        String population = "population";
        consult = consult.replace("'", "");
        partsConsult = consult.split(" ");
        if (partsConsult.length > 10) {
            if (order.equalsIgnoreCase(partsConsult[8]) && by.equalsIgnoreCase(partsConsult[9])) {
                if (name.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(city, new Comparator<City>() {
                        public int compare(City p1, City p2) {
                            return new String(p1.getName()).compareTo(new String(p2.getName()));
                        }
                    });

                } else if (id.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(city, new Comparator<City>() {
                        public int compare(City p1, City p2) {
                            return new String(p1.getId()).compareTo(new String(p2.getId()));
                        }
                    });
                } else if (countryID.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(city, new Comparator<City>() {
                        public int compare(City p1, City p2) {
                            return new String(p1.getCountryId()).compareTo(new String(p2.getCountryId()));
                        }
                    });
                } else if (population.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(city);
                }
            }
        }
        double value = Double.parseDouble(partsConsult[7]);
        if (population.equalsIgnoreCase(partsConsult[5])) {
            for (int i = 0; i < city.size(); i++) {
                if (value < city.get(i).getPopulation()) {
                    out += city.get(i).information() + "\n";
                }
            }
        } else {
            out = "No puede aplicar esta operacion a esa columna";
        }
        return out;
    }

    public String consultEqualsCities(String[] partsConsult, String consult) {
        String out = "";
        String name = "name";
        String id = "id";
        String countryID = "countryid";
        String population = "population";
        consult = consult.replace("'", "");
        partsConsult = consult.split(" ");
        String order = "order";
        String by = "by";
        if (partsConsult.length > 10) {
            if (order.equalsIgnoreCase(partsConsult[8]) && by.equalsIgnoreCase(partsConsult[9])) {
                if (name.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(city, new Comparator<City>() {
                        public int compare(City p1, City p2) {
                            return new String(p1.getName()).compareTo(new String(p2.getName()));
                        }
                    });

                } else if (id.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(city, new Comparator<City>() {
                        public int compare(City p1, City p2) {
                            return new String(p1.getId()).compareTo(new String(p2.getId()));
                        }
                    });
                } else if (countryID.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(city, new Comparator<City>() {
                        public int compare(City p1, City p2) {
                            return new String(p1.getCountryId()).compareTo(new String(p2.getCountryId()));
                        }
                    });
                } else if (population.equalsIgnoreCase(partsConsult[10])) {
                    Collections.sort(city);
                }
            }
        }
        for (int i = 0; i < city.size(); i++) {
            if (name.equalsIgnoreCase(partsConsult[5])) {
                for (int j = 0; j < country.size(); j++) {
                    if (city.get(i).getCountryId().equalsIgnoreCase(country.get(j).getId())) {
                        if (partsConsult[7].equalsIgnoreCase(country.get(j).getName())) {
                            out += city.get(i).information() + "\n";
                        }
                    }
                }
                if (city.get(i).getName().equalsIgnoreCase(partsConsult[7])) {
                    out += city.get(i).information() + "\n";
                }
            } else if (id.equalsIgnoreCase(partsConsult[5])) {
                if (partsConsult[7].equals(city.get(i).getId())) {
                    out += city.get(i).information() + "\n";
                }
            } else if (countryID.equalsIgnoreCase(partsConsult[5])) {
                if (partsConsult[7].equals(city.get(i).getCountryId())) {
                    out += city.get(i).information() + "\n";
                }
            } else if (population.equalsIgnoreCase(partsConsult[5])) {
                double value = Double.parseDouble(partsConsult[7]);
                if (value == city.get(i).getPopulation()) {
                    out += city.get(i).information() + "\n";
                }
            }
        }
        return out;
    }

    public String insertFuntion(String[] partsConsult, String consult) throws CountryDoesNotExist, IOException {
        String out = "";
        boolean correctComand = false;
        partsConsult = consult.split(" values ");
        if (partsConsult.length > 1) {
            if (partsConsult[0].equalsIgnoreCase("INSERT INTO countries(id, name, population, countryCode)")
                    || partsConsult[0].equalsIgnoreCase("INSERT INTO cities(id, name, countryID, population)")) {
                correctComand = true;
            }
        } else {
            partsConsult = consult.split(" VALUES ");
            if (partsConsult.length > 1) {
                if (partsConsult[0].equalsIgnoreCase("INSERT INTO countries(id, name, population, countryCode)")
                        || partsConsult[0].equalsIgnoreCase("INSERT INTO cities(id, name, countryID, population)")) {
                    correctComand = true;
                }
            } else {
                out = "La operacion que intenta hacer no es valida";
            }
        }
        if (correctComand == true) {
            if (partsConsult[0].contains("countries")) {
                String dentroParent = partsConsult[1].substring(partsConsult[1].indexOf("(") + 1,
                        partsConsult[1].indexOf(")"));
                dentroParent = dentroParent.replace("'", "").replace(" ", "");
                String[] partsCountry = dentroParent.split(",");
                boolean found = false;
                for (int i = 0; i < country.size() && found == false; i++) {
                    if (country.get(i).getId().equalsIgnoreCase(partsCountry[0])) {
                        found = true;

                        out = "El pais ya existe";
                    }

                }
                if (found == false) {
                    double value = Double.parseDouble(partsCountry[2]);
                    Country countryInsert = new Country(partsCountry[0], partsCountry[1], value, partsCountry[3]);
                    country.add(countryInsert);
                    saveDataCountry();
                    out = "Se creo el pais";
                }
            }
            if (partsConsult[0].contains("cities")) {
                String dentroParent = partsConsult[1].substring(partsConsult[1].indexOf("(") + 1,
                        partsConsult[1].indexOf(")"));
                dentroParent = dentroParent.replace("'", "").replace(" ", "");
                String[] partsCity = dentroParent.split(",");

                double value = Double.parseDouble(partsCity[3]);
                boolean notFound = true;
                for (int i = 0; i < country.size() && notFound == true; i++) {
                    if (country.get(i).getId().equalsIgnoreCase(partsCity[2])) {
                        notFound = false;
                    }
                }
                if (notFound == true) {
                    out = "PAIS NO EXISTE";
                    throw new CountryDoesNotExist();

                } else {
                    City cityInsert = new City(partsCity[0], partsCity[1], partsCity[2], value);
                    city.add(cityInsert);
                    saveDataCities();
                    out = "Se creo la ciudad";
                }
            }
        }
        return out;
    }

    public String deleteFuntion(String[] partsConsult, String consult) throws IOException {
        String out = "";
        String cities = "cities";
        String countries = "countries";
        consult = consult.replace("'", "");
        partsConsult = consult.split(" ");
        if (cities.equalsIgnoreCase(partsConsult[2])) {
            out = deleteInCity(partsConsult, consult);
        } else if (countries.equalsIgnoreCase(partsConsult[2])) {
            out = deletInCountry(partsConsult, consult);
        } else {
            out = "La tabla no esta definida";
        }
        return out;
    }

    public String deletInCountry(String[] partsConsult, String consult) throws IOException {
        String out = "";
        String name = "country";
        String id = "id";
        String countryCode = "countrycode";
        String population = "population";
        String where = "where";
        if (where.equalsIgnoreCase(partsConsult[3])) {
            if (partsConsult[5].equals("=")) {
                if (name.equalsIgnoreCase(partsConsult[4])) {
                    for (int i = 0; i < country.size(); i++) {
                        if (partsConsult[6].equalsIgnoreCase(country.get(i).getName())) {
                            country.remove(i);
                            out += "Eliminaste un pais" + "\n";
                            i--;
                            saveDataCountry();
                        }
                    }
                } else if (id.equalsIgnoreCase(partsConsult[4])) {
                    for (int i = 0; i < country.size(); i++) {
                        if (partsConsult[6].equalsIgnoreCase(country.get(i).getId())) {
                            country.remove(i);
                            out += "Eliminaste un pais" + "\n";
                            i--;
                            saveDataCities();
                        }
                    }
                } else if (countryCode.equalsIgnoreCase(partsConsult[6])) {
                    for (int i = 0; i < country.size(); i++) {
                        if (partsConsult[6].equalsIgnoreCase(country.get(i).getCountryCode())) {
                            country.remove(i);
                            out += "Eliminaste un pais" + "\n";
                            i--;
                            saveDataCountry();
                        }
                    }
                } else if (population.equalsIgnoreCase(partsConsult[4])) {
                    double value = Double.parseDouble(partsConsult[6]);
                    for (int i = 0; i < country.size(); i++) {
                        if (value == country.get(i).getPopulation()) {
                            country.remove(i);
                            out += "Eliminaste un pais" + "\n";
                            i--;
                            saveDataCountry();
                        }
                    }
                }
            } else if (partsConsult[5].equals("<") && population.equalsIgnoreCase(partsConsult[4])) {
                double value = Double.parseDouble(partsConsult[6]);
                for (int i = 0; i < country.size(); i++) {
                    if (value > country.get(i).getPopulation()) {
                        country.remove(i);
                        out += "Eliminaste un pais" + "\n";
                        i--;
                    }
                }

            } else if (partsConsult[5].equals(">") && population.equalsIgnoreCase(partsConsult[4])) {
                double value = Double.parseDouble(partsConsult[6]);
                for (int i = 0; i < country.size(); i++) {
                    if (value < country.get(i).getPopulation()) {
                        country.remove(i);
                        out += "Eliminaste un pais" + "\n";
                        i--;
                    }
                }
            }
        }
        return out;
    }

    public String deleteInCity(String[] partsConsult, String consult) {
        String out = "";
        String name = "name";
        String id = "id";
        String countryID = "country";
        String population = "population";

        String where = "where";
        if (where.equalsIgnoreCase(partsConsult[3])) {
            if (partsConsult[5].equals("=")) {
                if (name.equalsIgnoreCase(partsConsult[4])) {
                    for (int i = 0; i < city.size(); i++) {
                        if (partsConsult[6].equalsIgnoreCase(city.get(i).getName())) {
                            city.remove(i);
                            out = "Eliminaste una ciudad";
                            i--;
                        }
                    }
                } else if (id.equalsIgnoreCase(partsConsult[4])) {
                    for (int i = 0; i < city.size(); i++) {
                        if (partsConsult[6].equalsIgnoreCase(city.get(i).getId())) {
                            city.remove(i);
                            out = "Eliminaste una ciudad";
                            i--;
                        }
                    }
                } else if (countryID.equalsIgnoreCase(partsConsult[4])) {
                    for (int j = 0; j < country.size(); j++) {
                        if (country.get(j).getName().equalsIgnoreCase(partsConsult[6])) {
                            for (int i = 0; i < city.size(); i++) {
                                if (country.get(j).getId().equalsIgnoreCase(city.get(i).getCountryId())) {
                                    city.remove(i);
                                    out = "Eliminaste una ciudad";
                                    i--;
                                }
                            }
                        }
                    }
                } else if (population.equalsIgnoreCase(partsConsult[4])) {
                    double value = Double.parseDouble(partsConsult[6]);
                    for (int i = 0; i < city.size(); i++) {
                        if (value == city.get(i).getPopulation()) {
                            city.remove(i);
                            out = "Eliminaste una ciudad";
                            i--;
                        }
                    }
                }
            } else if (partsConsult[5].equals("<") && population.equalsIgnoreCase(partsConsult[4])) {
                double value = Double.parseDouble(partsConsult[6]);
                for (int i = 0; i < city.size(); i++) {
                    if (value > city.get(i).getPopulation()) {
                        city.remove(i);
                        out = "Eliminaste un pais";
                        i--;
                    }
                }

            } else if (partsConsult[5].equals(">") && population.equalsIgnoreCase(partsConsult[4])) {
                double value = Double.parseDouble(partsConsult[6]);
                for (int i = 0; i < city.size(); i++) {
                    if (value < city.get(i).getPopulation()) {
                        city.remove(i);
                        out = "Eliminaste un pais";
                        i--;
                    }
                }
            }
        }
        return out;
    }

    public void saveDataCountry() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COUNTRIES));
        oos.writeObject(country);
        oos.close();
    }
    public void saveDataCities() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CITIES));
        oos.writeObject(city);
        oos.close();
    }

    @SuppressWarnings("unchecked")
    public void loadData() throws IOException, ClassNotFoundException {

        File f = new File(CITIES);

        if (f.exists()) {
            ObjectInputStream oisCustomers = new ObjectInputStream(new FileInputStream(f));
            city = (ArrayList<City>) oisCustomers.readObject();
            oisCustomers.close();
        }

        f = new File(COUNTRIES);

        if (f.exists()) {
            ObjectInputStream employesOut = new ObjectInputStream(new FileInputStream(f));
            country = (ArrayList<Country>) employesOut.readObject();
            employesOut.close();

        }
    }
}
