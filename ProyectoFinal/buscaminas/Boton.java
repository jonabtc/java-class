/*
   Autor: Jonathan Ocles - 00112226
   Fecha: 30/04/2016
   Boton: Archivo tipo java, subclase de JPanel, contiene los botones, la forma en que se controla los clicks de cada boton de la grilla 
          y textos de salida que van informando al jugador los movimientos que le quedan y el puntaje que posee.
*/
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.security.SecureRandom;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Boton extends JPanel {

	public static int[] puntosAltos = new int[3];
	private static int movimientos;
	private static int contMovimientos;
	private static int puntuacion = 0;
	private static JButton[][] botones = null;
	public static final SecureRandom randomNumbers = new SecureRandom();
	public JPanel botonGrid, layout;
	private ImageIcon[][] iconos;
	private boolean[][] estado;
	final Image mina = new ImageIcon(getClass().getResource("mina.jpg")).getImage();
	final Image vitamina = new ImageIcon(getClass().getResource("vitamina.jpg")).getImage();
	final Image alimentoNormal = new ImageIcon(getClass().getResource("alimentoNormal.jpg")).getImage();
	final Image vacio = new ImageIcon(getClass().getResource("vacio.jpg")).getImage();
	final Image incognita = new ImageIcon(getClass().getResource("incognita.jpg")).getImage();
	private JLabel labelMovimientos = new JLabel("Mov: ");
	private JLabel labelPuntos = new JLabel("Pts: ");
	private JLabel textMovimientos = new JLabel();
	private JLabel textPuntos = new JLabel();

	Boton() {

		botonGrid = new JPanel();
		botonGrid.setLayout(new GridLayout(30, 30));// , 2, 2));
		layout = new JPanel();
		layout.setLayout(new FlowLayout());
		layout.add(labelMovimientos);
		layout.add(textMovimientos);
		layout.add(labelPuntos);
		layout.add(textPuntos);
		iconos = new ImageIcon[30][30];
		estado = new boolean[30][30];
		setMovimientos(15);
		nuevoJuego();
	}

	public void setMovimientos(int x) {
		contMovimientos = x;
		movimientos = x;
		dibujarValores();
	}

	public void setPuntuacion(int x) {
		puntuacion = x;
		dibujarValores();
	}

	public int getMovimientos() {
		return contMovimientos;
	}

	public void nuevoJuego() {
		botonGrid.removeAll();
		inicializar();
		iconificar();
		shuffle();
		activarBotones(true);
		dibujarValores();
		setPuntuacion(0);
		setMovimientos(movimientos);
		ordenarPuntajes(puntosAltos);
	}

	public void inicializar() {
		ButtonHandler handler = new ButtonHandler();
		botones = new JButton[30][30];
		for (int i = 0; i < 30; i++)
			for (int j = 0; j < 30; j++) {
				botones[i][j] = new JButton();
				iconos[i][j] = new ImageIcon(getClass().getResource("vacio.jpg"));
				botones[i][j].addActionListener(handler);
				botonGrid.add(botones[i][j]);
			}
	}

	public void iconificar() {
		int contadorMinas = 100, contadorAlimento = 300, contadorVitaminas = 300;

		for (int i = 0; i < 30; i++)
			for (int j = 0; j < 30; j++) {
				if (contadorMinas > 0) {
					contadorMinas--;
					iconos[i][j].setImage(mina);
				} else {
					if (contadorAlimento > 0) {
						contadorAlimento--;
						iconos[i][j].setImage(alimentoNormal);
					} else if (contadorVitaminas > 0) {
						contadorVitaminas--;
						iconos[i][j].setImage(vitamina);
					}
				}
			}
	}

	public void activarBotones(boolean a) {
		for (int i = 0; i < 30; i++)
			for (int j = 0; j < 30; j++)
				estado[i][j] = !a;
	}

	public void shuffle() {
		for (int i = 0; i < 30; i++)
			for (int j = 0; j < 30; j++) {
				int rand1 = randomNumbers.nextInt(30), rand2 = randomNumbers.nextInt(30);
				ImageIcon temp = iconos[i][j];
				iconos[i][j] = iconos[rand1][rand2];
				iconos[rand1][rand2] = temp;
			}
	}

	public void dibujarValores() {
		String a = String.format("%d / %d", getMovimientos(), movimientos);
		textMovimientos.setText(a);
		textPuntos.setText(String.valueOf(puntuacion));
	}

	public void puntajesAltos(int puntos) {
		if (puntosAltos[2] < puntos)
			puntosAltos[2] = puntos;
		ordenarPuntajes(puntosAltos);
	}

	public void ordenarPuntajes(int[] lista) {
		for (int i = 0; i < (lista.length - 1); i++)
			for (int j = i + 1; j < lista.length; j++)
				if (lista[i] < lista[j]) {
					int aux = lista[i];
					lista[i] = lista[j];
					lista[j] = aux;
				}
	}

	private class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			int x = 0, y = 0;
			for (int i = 0; i < 30; i++)
				for (int j = 0; j < 30; j++) {
					if (event.getSource().equals(botones[i][j])) {
						x = i;
						y = j;
						break;
					}
				}

			if (!estado[x][y]) {
				if (contMovimientos > 0) {

					botones[x][y].setIcon(iconos[x][y]);
					estado[x][y] = true;
					if (iconos[x][y].getImage() == mina) {
						puntuacion = puntuacion - 10;
					}
					if (iconos[x][y].getImage() == vitamina) {
						puntuacion = puntuacion + 6;
					}
					if (iconos[x][y].getImage() == alimentoNormal) {
						puntuacion = puntuacion + 2;
					}
					contMovimientos--;
				}
				dibujarValores();

				if (contMovimientos == 0) {
					activarBotones(false);
					puntajesAltos(puntuacion);
				}
			}
			if (contMovimientos == 0)
				JOptionPane.showMessageDialog(null, "Se te terminaron los movimientos, tu puntuación es " + puntuacion);
		}
	}
}

