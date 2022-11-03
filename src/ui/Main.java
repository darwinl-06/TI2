package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import exceptions.CountryDoesNotExist;
import model.World;

public class Main {
    private static Scanner scan = new Scanner(System.in);
    private static Scanner scanText = new Scanner(System.in);
    private static World world ;
    private int count = 0;
    public Main() throws ClassNotFoundException, IOException {
        world = new World();
    }
    public static void main(String[] args) throws CountryDoesNotExist, IOException, ClassNotFoundException {
        Main main = new Main();
        main.menu();


    }

    private void menu() throws CountryDoesNotExist, IOException, ClassNotFoundException {
        if(count ==0) {
            world.loadData();
            count=1;
        }
        System.out.println("Por favor selecciona una opcion " + "\n 1- Insertar comando"
                + "\n 2- Importar datos desde archivo" + "\n 3- Salir");
        int opcion = scan.nextInt();
        switch (opcion) {
            case 1:
                insertCommand();
                break;
            case 2:
                importText();
                break;
            case 3:
                System.out.println("	Hasta la proxima	");
                break;
        }

    }

    private void insertCommand() throws CountryDoesNotExist, IOException, ClassNotFoundException {

        System.out.println("Por favor ingresa la consulta que desea hacer");
        String consult = scanText.nextLine();
        String[] partsConsult = consult.split(" ");

        if (partsConsult[0].equalsIgnoreCase("insert")) {
            try {
                System.out.println(world.insertFuntion(partsConsult, consult));
            } catch (CountryDoesNotExist cd) {
                System.out.println("ERROR EL PAIS NO EXISTE");
            }
        } else {
            if (partsConsult[0].equalsIgnoreCase("select")) {
                System.out.println(world.insertConsult(partsConsult, consult));
            } else {
                if (partsConsult[0].equalsIgnoreCase("delete")) {
                    System.out.println(world.deleteFuntion(partsConsult, consult));
                } else {
                    System.out.println("La operacion que intenta hacer no es valida");
                }
            }
        }
        menu();
    }

    private void importText() throws IOException, CountryDoesNotExist, ClassNotFoundException {
        System.out.println("por favor minimiza la venta del editor, para seleccionar el archivo");
        JFileChooser fileChoser = new JFileChooser();
        FileNameExtensionFilter filtrado = new FileNameExtensionFilter("TXT", "txt");
        fileChoser.setFileFilter(filtrado);
        fileChoser.setDialogTitle("Selecciona el archivo");
        int returnValue = fileChoser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChoser.getSelectedFile();
            importText(selectedFile.getAbsolutePath());

        } else {

        }

    }

    private void importText(String absolutePath) throws IOException, CountryDoesNotExist, ClassNotFoundException {
        BufferedReader bReader2 = new BufferedReader(new FileReader(absolutePath));
        String line = bReader2.readLine();
        while (line != null) {
            String[] partsConsult = line.split(" ");
            if (partsConsult[0].equalsIgnoreCase("insert")) {
                try {
                    System.out.println(world.insertFuntion(partsConsult, line));
                } catch (CountryDoesNotExist cd) {
                    System.out.println("ERROR EL PAIS NO EXISTE");
                }
            } else {
                if (partsConsult[0].equalsIgnoreCase("select")) {
                    System.out.println(world.insertConsult(partsConsult, line));
                } else {
                    if (partsConsult[0].equalsIgnoreCase("delete")) {
                        System.out.println(world.deleteFuntion(partsConsult, line));
                    } else {
                        System.out.println("La operacion que intenta hacer no es valida");
                    }
                }
            }
            line = bReader2.readLine();
        }
        bReader2.close();
        menu();

    }

}
