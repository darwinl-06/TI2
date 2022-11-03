package model;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import exceptions.CountryDoesNotExist;

public class WorldTestSelect {

	public String consult;
	World world = new World();

	public void setupScenary1() throws CountryDoesNotExist, IOException {

		consult = "INSERT INTO countries(id, name, population, countryCode) VALUES ('6ecass3e8ec-3dd0-11ed-b878-0242ac120002', 'venezuela', 500.2, '+7')";
		String[] partsConsult = consult.split(" ");
		world.insertFuntion(partsConsult, consult);
		consult = "INSERT INTO countries(id, name, population, countryCode) VALUES ('6ec3e8ec-3dd0-11ed-b878-0242ac120002', 'Colombia', 50.2, '+57')";
		partsConsult = consult.split(" ");
		world.insertFuntion(partsConsult, consult);

		consult = "INSERT INTO countries(id, name, population, countryCode) VALUES ('6ec3e8ec-3dd0-11ed-b878-0242ac120002', 'Colombia', 50.2, '+57')";
		partsConsult = consult.split(" ");
		world.insertFuntion(partsConsult, consult);

	}

	@Test
	public void orderByPopulation() throws CountryDoesNotExist, IOException {
		setupScenary1();
		consult = "DELETE FROM cities WHERE name = 'Caracas'";
		String[] partsConsult = consult.split(" ");
		world.deleteFuntion(partsConsult, consult);
		String out = "";
		out = " Nombre: Cali ID: e4aa04f6-3dd0-11ed-b878-0242ac120002 Country Id: 6ec3e8ec-3dd0-11ed-b878-0242ac120002 Population: 2.2\n";
		out += " Nombre: Bucaramanga ID: 34s4f6-3dd0-11ed-b878-0242ac120002 Country Id: 6ec3e8ec-3dd0-11ed-b878-0242ac120002 Population: 4.5\n";
		out += " Nombre: Bogota ID: asfasf6-3dd0-11ed-b878-0242ac120002 Country Id: 6ec3e8ec-3dd0-11ed-b878-0242ac120002 Population: 10.6\n";

		consult = "SELECT * FROM cities ORDER BY population";
		partsConsult = consult.split(" ");
		assertEquals(out, world.insertConsult(partsConsult, consult));
	}

	@Test
	public void orderByName() throws CountryDoesNotExist, IOException {
		setupScenary1();
		consult = "DELETE FROM cities WHERE name = 'Caracas'";
		String[] partsConsult = consult.split(" ");
		world.deleteFuntion(partsConsult, consult);
		String out = "";

		out += " Nombre: Bogota ID: asfasf6-3dd0-11ed-b878-0242ac120002 Country Id: 6ec3e8ec-3dd0-11ed-b878-0242ac120002 Population: 10.6\n";
		out += " Nombre: Bucaramanga ID: 34s4f6-3dd0-11ed-b878-0242ac120002 Country Id: 6ec3e8ec-3dd0-11ed-b878-0242ac120002 Population: 4.5\n";
		out += " Nombre: Cali ID: e4aa04f6-3dd0-11ed-b878-0242ac120002 Country Id: 6ec3e8ec-3dd0-11ed-b878-0242ac120002 Population: 2.2\n";

		consult = "SELECT * FROM cities ORDER BY name";
		partsConsult = consult.split(" ");

		assertEquals(out, world.insertConsult(partsConsult, consult));
	}

	@Test
	public void listCities() throws CountryDoesNotExist, IOException {
		setupScenary1();
		consult = "INSERT INTO cities(id, name, countryID, population) VALUES ('e4aa04f6-3dd0-11ed-b878-0242ac120002', 'Cali', '6ec3e8ec-3dd0-11ed-b878-0242ac120002', 2.2)";
		String[] partsConsult = consult.split(" ");
		world.insertFuntion(partsConsult, consult);
		consult = "INSERT INTO cities(id, name, countryID, population) VALUES ('asfasf6-3dd0-11ed-b878-0242ac120002', 'Bogota', '6ec3e8ec-3dd0-11ed-b878-0242ac120002', 10.6)";
		partsConsult = consult.split(" ");
		world.insertFuntion(partsConsult, consult);
		consult = "INSERT INTO cities(id, name, countryID, population) VALUES ('34s4f6-3dd0-11ed-b878-0242ac120002', 'Bucaramanga', '6ec3e8ec-3dd0-11ed-b878-0242ac120002', 4.5)";
		partsConsult = consult.split(" ");
		world.insertFuntion(partsConsult, consult);
		String out = "";
		out = " Nombre: Cali ID: e4aa04f6-3dd0-11ed-b878-0242ac120002 Country Id: 6ec3e8ec-3dd0-11ed-b878-0242ac120002 Population: 2.2\n";

		out += " Nombre: Bogota ID: asfasf6-3dd0-11ed-b878-0242ac120002 Country Id: 6ec3e8ec-3dd0-11ed-b878-0242ac120002 Population: 10.6\n";
		out += " Nombre: Bucaramanga ID: 34s4f6-3dd0-11ed-b878-0242ac120002 Country Id: 6ec3e8ec-3dd0-11ed-b878-0242ac120002 Population: 4.5\n";
		consult = "SELECT * FROM cities WHERE name = 'Colombia'";
		partsConsult = consult.split(" ");

		assertEquals(out, world.insertConsult(partsConsult, consult));
	}
}
