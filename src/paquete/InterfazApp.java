package paquete;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfazApp{

	private JFrame frame;
	private GestionReservas gestion;
	private JTextArea areaOfertas = new JTextArea();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazApp window = new InterfazApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazApp() {
		this.gestion= new GestionReservas();
		gestion.leerSerializacion();
		initialize();
		cargarOfertas();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(66, 65, 73));
		frame.setBounds(100, 100, 786, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane interfazConPestañas = new JTabbedPane();
		interfazConPestañas.setBounds(10, 11, 750, 477);
		frame.getContentPane().add(interfazConPestañas);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		interfazConPestañas.addTab("Formulario", null, panel, "");

		JPanel panelOfertas = new JPanel();
		panelOfertas.setBackground(new Color(0, 0, 89));
		panelOfertas.setLayout(null);
		interfazConPestañas.addTab("Ofertas", null, panelOfertas, "");
		
		JLabel tituloPag = new JLabel("Mejores ofertas");
		tituloPag.setBounds(150, 20, 400, 40);
		tituloPag.setForeground(new Color(255, 255, 255));
		tituloPag.setFont(new Font("Georgia", Font.BOLD, 24));
		tituloPag.setHorizontalAlignment(SwingConstants.CENTER);
		panelOfertas.add(tituloPag);
		
		JTextArea areaMejoresOfertas = new JTextArea();
		areaMejoresOfertas.setBackground(new Color(48, 48, 48));
		areaMejoresOfertas.setFont(new Font("Georgia", Font.BOLD, 15));
		areaMejoresOfertas.setForeground(Color.WHITE);
		areaMejoresOfertas.setEditable(false);
		
		JScrollPane deslizarMejoresOfertas = new JScrollPane(areaMejoresOfertas);
		deslizarMejoresOfertas.setBounds(50, 80, 650, 300);
		panelOfertas.add(deslizarMejoresOfertas);

		JPanel panelFormulario = new JPanel();
		panelFormulario.setBackground(new Color(0, 0, 89));
		panelFormulario.setBounds(91, 156, 409, 241);
		panel.add(panelFormulario);
		panelFormulario.setLayout(null);

		JLabel tituloPagina = new JLabel("Reserva la sala");
		tituloPagina.setBackground(new Color(255, 255, 255));
		tituloPagina.setHorizontalAlignment(SwingConstants.CENTER);
		tituloPagina.setBounds(139, 32, 267, 76);
		tituloPagina.setFont(new Font("Georgia", Font.BOLD, 30));
		tituloPagina.setForeground(Color.WHITE);
		panel.add(tituloPagina);

		JLabel ofertas = new JLabel("Ofertas realizadas");
		ofertas.setBackground(new Color(255, 255, 255));
		ofertas.setHorizontalAlignment(SwingConstants.CENTER);
		ofertas.setBounds(540, 11, 200, 28);
		ofertas.setFont(new Font("Georgia", Font.BOLD, 20));
		ofertas.setForeground(new Color(255, 255, 255));
		panel.add(ofertas);

		JLabel ingresarNombre = new JLabel("Ingrese su nombre: ");
		ingresarNombre.setHorizontalAlignment(SwingConstants.CENTER);
		ingresarNombre.setFont(new Font("Georgia", Font.BOLD, 15));
		ingresarNombre.setForeground(new Color(255, 255, 255));
		ingresarNombre.setBounds(10, 23, 159, 27);
		panelFormulario.add(ingresarNombre);

		JLabel ingresarHoraEntrada = new JLabel("Ingrese hora entrada: ");
		ingresarHoraEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		ingresarHoraEntrada.setFont(new Font("Georgia", Font.BOLD, 15));
		ingresarHoraEntrada.setForeground(new Color(255, 255, 255));
		ingresarHoraEntrada.setBounds(10, 61, 177, 27);
		panelFormulario.add(ingresarHoraEntrada);

		JLabel ingresarHoraSalida = new JLabel("Ingrese hora salida:");
		ingresarHoraSalida.setHorizontalAlignment(SwingConstants.CENTER);
		ingresarHoraSalida.setFont(new Font("Georgia", Font.BOLD, 15));
		ingresarHoraSalida.setForeground(new Color(255, 255, 255));
		ingresarHoraSalida.setBounds(10, 99, 159, 29);
		panelFormulario.add(ingresarHoraSalida);

		String[] horas = new String[24];
		for (int i = 0; i < 24; i++) {
			horas[i] = String.valueOf(i + 1) + ":00";
		}
		JComboBox<String> comboInicio = new JComboBox<>(horas);
		JComboBox<String> comboFin = new JComboBox<>(horas);

		comboInicio.setBounds(270, 63, 123, 25);
		comboFin.setBounds(270, 102, 123, 25);
		panelFormulario.add(comboInicio);
		panelFormulario.add(comboFin);

		JLabel ingresarDinero = new JLabel("Ingrese cuanto dinero ofrece: ");
		ingresarDinero.setHorizontalAlignment(SwingConstants.CENTER);
		ingresarDinero.setFont(new Font("Georgia", Font.BOLD, 15));
		ingresarDinero.setForeground(new Color(255, 255, 255));
		ingresarDinero.setBounds(10, 137, 228, 27);
		panelFormulario.add(ingresarDinero);

		JTextField campoTextoDelNombre = new JTextField();
		campoTextoDelNombre.setBounds(270, 24, 123, 26);
		panelFormulario.add(campoTextoDelNombre);
		campoTextoDelNombre.setColumns(10);

		JTextField campoTextoDelNumero = new JTextField();
		campoTextoDelNumero.setBounds(270, 138, 123, 26);
		panelFormulario.add(campoTextoDelNumero);
		campoTextoDelNumero.setColumns(20);

		areaOfertas.setBackground(new Color(0, 0, 89));
		areaOfertas.setFont(new Font("Georgia", Font.BOLD, 15));
		areaOfertas.setForeground(new Color(255, 255, 255));
		areaOfertas.setEditable(false);

		JScrollPane barraDesplazamiento = new JScrollPane(areaOfertas);
		barraDesplazamiento.setBounds(540, 40, 200, 347);
		panel.add(barraDesplazamiento);

		JButton enviarOferta = new JButton("Enviar oferta");
		enviarOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = campoTextoDelNombre.getText();
				String horaInicio = (String) comboInicio.getSelectedItem();
				int horaIniEnNumEntero = Integer.parseInt(horaInicio.split(":")[0]);
				String horaFinal = (String) comboFin.getSelectedItem();
				int horaFinEnNumEntero = Integer.parseInt(horaFinal.split(":")[0]);
				String campoDineroVacio = campoTextoDelNumero.getText();
				double dineroOfrecido = 0;
				
				if(verificarCampoDineroVacio(campoDineroVacio)) {
					JOptionPane.showMessageDialog(null, "No puedes dejar el campo de dinero vacío", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					dineroOfrecido = Double.parseDouble(campoDineroVacio);
				}
				
				verificarCampoVacio(nombre);
				verificarHoraInicioMenorAHoraFinal(horaIniEnNumEntero, horaFinEnNumEntero);
				verificarDineroMayorACero(dineroOfrecido);
				agregarOferta(nombre, horaIniEnNumEntero, horaFinEnNumEntero, dineroOfrecido);

				String oferta = "Oferta de: " + nombre + "\nHorario: " + horaIniEnNumEntero + "-" + horaFinEnNumEntero + "hs"+ " \nOfrece: $" + dineroOfrecido + "\n\n";
				
				areaOfertas.append(oferta);
				gestion.escrituraSerializacion();
				campoTextoDelNombre.setText("");
				comboInicio.setSelectedIndex(0); 
				comboFin.setSelectedIndex(0); 
				campoTextoDelNumero.setText("");
			}

		});
		enviarOferta.setBackground(new Color(255, 255, 255));
		enviarOferta.setFont(new Font("Georgia", Font.BOLD, 13));
		enviarOferta.setBounds(119, 185, 159, 27);
		enviarOferta.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelFormulario.add(enviarOferta);

		JButton mejoresOfertas = new JButton("Ver mejores ofertas");
		mejoresOfertas.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Solucion mejoresSolucion = gestion.seleccionarMejorSolucion();
		        if (mejoresSolucion != null) {
		            String mejoresSol = mejoresSolucion.toString();
		            areaMejoresOfertas.setText(mejoresSol);
		            interfazConPestañas.setSelectedIndex(1);
		        } else {
		            JOptionPane.showMessageDialog(null, "No hay soluciones disponibles", "Mejor Solución", JOptionPane.INFORMATION_MESSAGE);
		        }
		    }
		});
		mejoresOfertas.setBounds(540, 398, 200, 43);
		mejoresOfertas.setBackground(new Color(255, 255, 255));
		mejoresOfertas.setFont(new Font("Georgia", Font.BOLD, 13));
		mejoresOfertas.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(mejoresOfertas);

		JLabel fondoPag = new JLabel("");
		fondoPag.setIcon(new ImageIcon(getClass().getResource("/imagenes/sala.jpg")));
		fondoPag.setBounds(0, 0, 750, 452);
		panel.add(fondoPag);

	}

	public void agregarOferta(String nombre, int horaIniEnNumEntero, int horaFinEnNumEntero, double dineroOfrecido) {
		gestion.crearObjeto(nombre, horaIniEnNumEntero, horaFinEnNumEntero, dineroOfrecido);
	}

	public void verificarCampoVacio(String nombre) {
		if(nombre == null || nombre.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No puedes dejar el campo de nombre vacío", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public boolean verificarCampoDineroVacio(String campoDineroVacio){
		if(campoDineroVacio == null || campoDineroVacio.isEmpty()) {
			return true;
		}
		return false;
	}

	public void verificarHoraInicioMenorAHoraFinal(int horaIniEnNumEntero,int horaFinEnNumEntero) {
		if(horaIniEnNumEntero >= horaFinEnNumEntero) {
			JOptionPane.showMessageDialog(null, "La hora de inicio no puede ser mayor o igual a la hora final", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void verificarDineroMayorACero(double dineroOfrecido){
		if(dineroOfrecido <= 0) {
			JOptionPane.showMessageDialog(null, "Debe ofrecer un valor mayor a $0", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cargarOfertas() {
		List<String> ofertas= gestion.obtenerOfertas();
		for (String of : ofertas) {
			areaOfertas.append(of +"\n"); 
		}
	}
}
