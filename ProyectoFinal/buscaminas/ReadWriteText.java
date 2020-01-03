/*
   Autor: Jonathan Ocles - 00112226
   Fecha: 30/04/2016
   ReadWriteText: archivo tipo java que implementa metodos para el manejo de ficheros, crea, escribe, lee y mantiene un arreglo en el cual se cargan
                  los valores que se encuentran en el fichero.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadWriteText {
	private static int[] puntajes = { 0, 0, 0 };
	private static File file = new File("Puntajes.txt");

	ReadWriteText() {

		existFile();
		readFile();
	}

	public void setPuntajes(int[] punt) {
		puntajes = punt;			
		writeFile();
	}

	public int[] getPuntajes() {
		return puntajes;
	}

	public void openFile() {

	}

	public void readFile() {
		try {
			Scanner input = new Scanner(new FileReader(file.getPath()));
			for (int i = 0; i < puntajes.length; i++)
				puntajes[i] = input.nextInt();
			input.close();
		} catch (NoSuchElementException e) {
			for (int i = 0; i < puntajes.length; i++)
				puntajes[i] = 0;
		} catch (FileNotFoundException e) {
			existFile();
		}
	}

	public void writeFile() {
		try {
			PrintWriter print = new PrintWriter(file);
			for (int i = 0; i < puntajes.length; i++)
				print.println(puntajes[i]);
			print.close();
		} catch (IOException io) {
			existFile();
			writeFile();
		}
	}

	public void existFile() {
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				existFile();
			}

	}

	public void closeFile() {

	}

}
