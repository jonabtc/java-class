/*
   Autor: Jonathan Ocles - 00112226
   Fecha: 30/04/2016
   Buscaminas: Archivo tipo java, subclase de frame, agrega los componentes visuales de la clase Boton, adicionalmente provee una barra de menu
               con el menu juego que posee nuevo juego y opciones modificables por el usuario.
   
*/
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

public class Buscaminas extends JFrame {

	private Boton botones = new Boton();
	private JRadioButtonMenuItem[] options = new JRadioButtonMenuItem[3];
	private JMenuItem nuevoJuego;

	private ReadWriteText fichero = new ReadWriteText();

	Buscaminas() {
		ItemHandler handler = new ItemHandler();

		Boton.puntosAltos = fichero.getPuntajes();
		JMenu menuJuego = new JMenu("Juego");
		nuevoJuego = new JMenuItem("Nuevo Juego");
		nuevoJuego.addActionListener(handler);

		JMenu opciones = new JMenu("Opciones");
		JMenuBar barra = new JMenuBar();
		setJMenuBar(barra);
		barra.add(menuJuego);

		options[0] = new JRadioButtonMenuItem("Fijar movimientos");
		options[1] = new JRadioButtonMenuItem("Puntajes altos");
		options[2] = new JRadioButtonMenuItem("Borrar puntajes altos");

		ButtonGroup grupoOpciones = new ButtonGroup();
		for (int i = 0; i < 3; i++) {
			opciones.add(options[i]);
			options[i].addActionListener(handler);
			grupoOpciones.add(options[i]);
		}
		menuJuego.add(nuevoJuego);
		menuJuego.addSeparator();
		menuJuego.add(opciones);
		agregar();
	}
	public void agregar(){
		add(botones.botonGrid);
		add(botones.layout, BorderLayout.NORTH);
		validate();
	}

	private class ItemHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == nuevoJuego) {
				botones.nuevoJuego();
				fichero.setPuntajes(Boton.puntosAltos);
			}
			//click cambiar movimientos
			if (e.getSource() == options[0]) { 
				while (true) {
					String movimientos = JOptionPane.showInputDialog(botones,
							"Ingrese el número de movimientos que desea", JOptionPane.QUESTION_MESSAGE);
					if (esNumero(movimientos)) {
						botones.setMovimientos(Integer.parseInt(movimientos));
						botones.nuevoJuego();
						break;
					} else
						JOptionPane.showMessageDialog(botones, "El dato que ha ingresado no es válido");
				}
			}
			//click ver puntajes
			if (e.getSource() == options[1]) { 
				JOptionPane.showMessageDialog(botones, String.format(" 1: %d\n 2: %d\n 3: %d", Boton.puntosAltos[0],
						Boton.puntosAltos[1], Boton.puntosAltos[2]));
			}
			//click eliminar puntajes
			if (e.getSource() == options[2]) {
				int si = JOptionPane.showConfirmDialog(botones, "Los puntajes altos serán borrados");
				if (JOptionPane.OK_OPTION == si) {
					for (int i = 0; i < 3; i++)
						Boton.puntosAltos[i] = 0;
					fichero.setPuntajes(Boton.puntosAltos);
				}
			}
		}

		public boolean esNumero(String a) {
			try {
				Integer.parseInt(a);
				return true;
			} catch (NumberFormatException n) {
				return false;
			}
		}
	}

	public static void main(String[] args) {
		Buscaminas buscaminas = new Buscaminas();
		buscaminas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buscaminas.setSize(1100, 820);
		buscaminas.setResizable(false);
		buscaminas.setVisible(true);
		buscaminas.getContentPane().validate();

	}
}
