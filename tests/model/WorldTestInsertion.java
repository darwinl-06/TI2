package model;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import exceptions.CountryDoesNotExist;

public class WorldTestInsertion {
	private World world = new World();
	public String consult;

	public void setupScenary1() {
		consult = "INSERT INTO countries(id, name, population, countryCode) VALUES ('6ec3e8ec-3dd0-11ed-b878-0242ac1200021', 'Venezuela', 50.2, '+57')";

	}

	@Test
	public void addCity() throws CountryDoesNotExist, IOException {

		setupScenary1();
		String[] partsConsult = consult.split(" ");
		world.insertFuntion(partsConsult, consult);
		consult = "INSERT INTO cities(id, name, countryID, population) VALUES ('e4aa04f6-3dd0-11ed-b878-0242ac120002', 'Caracas', '6ec3e8ec-3dd0-11ed-b878-0242ac1200021', 2.2)";
		partsConsult = consult.split(" ");
		assertEquals("Se creo la ciudad", world.insertFuntion(partsConsult, consult));
		consult = "SELECT * FROM cities WHERE name = 'Caracas'";
		partsConsult = consult.split(" ");
		assertEquals(" Nombre: Caracas ID: e4aa04f6-3dd0-11ed-b878-0242ac120002 Country Id: 6ec3e8ec-3dd0-11ed-b878-0242ac1200021 Population: 2.2\n", world.insertConsult(partsConsult, consult));

	}

	@Test
	public void addCountry() throws CountryDoesNotExist, IOException {

		setupScenary1();
		String[] partsConsult = consult.split(" ");
		assertEquals("Se creo el pais", world.insertFuntion(partsConsult, consult));
		consult = "SELECT * FROM countries WHERE name = 'Venezuela'";
		partsConsult = consult.split(" ");
		assertEquals("Nombre: Venezuela ID: 6ec3e8ec-3dd0-11ed-b878-0242ac1200021 Country Id: +57 Population: 50.2\n", world.insertConsult(partsConsult, consult));
	}

	@Test
	public void addCountryAgain() throws CountryDoesNotExist, IOException {

		setupScenary1();
		String consult = "INSERT INTO countries(id, name, population, countryCode) VALUES ('6ec3e8ec-3dd0-11ed-b878-0242ac1200021', 'Venezuela', 50.2, '+57')";
		String[] partsConsult = consult.split(" ");
		assertEquals("El pais ya existe", world.insertFuntion(partsConsult, consult));
	}

	@Test
	public void addCityWithoutCountry() {
		consult = "INSERT INTO cities(id, name, countryID, population) VALUES ('e4aa04f6-3dd0-11ed-b878-0242ac120002', 'Cali', '6aaec3e8ec-3dd0-11ed-b878-0242ac120002', 2.2)";
		String[] partsConsult = consult.split(" ");
		try {
			assertEquals("ERROR EL PAIS NO EXISTE", world.insertFuntion(partsConsult, consult));
		} catch (CountryDoesNotExist e) {
		} catch (IOException e) {
		}
	}
}
