package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.ControladorAsignatura;
import controladores.ControladorAsignaturaPorDocente;
import controladores.ControladorDocente;

import examen270423JPA.Asignatura;
import examen270423JPA.Asignaturaspordocente;
import examen270423JPA.Docente;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;

public class Main extends JFrame {

	private final JButton btnCargarMaterias = new JButton("Cargar materias");
	private JList jlistNoSelect;
	private JList jlistSelect;
	private DefaultListModel<Asignatura> listModelAsignaturaSelect = null;
	private DefaultListModel<Asignatura> listModelAsignaturaNoSelect = null;
	private List<Asignaturaspordocente> asignaturasSeleccionadas = null;
	private List<Asignaturaspordocente> asignaturasNoSeleccionadas = null;
	private int indiceProximaAsignaturaParaAgregar = 0;
	JComboBox<Docente> jcmbDocente;
	private JTextField jtfDocente;
	private List<Asignatura> asignaturas = ControladorAsignatura.findAll();

	public Main() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 202, 0, 214, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 58, 0, 0, 0, 0, 128, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Docentes y asignaturas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		jtfDocente = new JTextField();
		GridBagConstraints gbc_jtfDocente = new GridBagConstraints();
		gbc_jtfDocente.gridwidth = 3;
		gbc_jtfDocente.insets = new Insets(0, 0, 5, 5);
		gbc_jtfDocente.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfDocente.gridx = 1;
		gbc_jtfDocente.gridy = 1;
		getContentPane().add(jtfDocente, gbc_jtfDocente);
		jtfDocente.setColumns(10);

		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarComboDocente();
			}
		});
		GridBagConstraints gbc_btnFiltrar = new GridBagConstraints();
		gbc_btnFiltrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnFiltrar.gridx = 4;
		gbc_btnFiltrar.gridy = 1;
		getContentPane().add(btnFiltrar, gbc_btnFiltrar);

		jcmbDocente = new JComboBox<Docente>();
		GridBagConstraints gbc_jcmbDocente = new GridBagConstraints();
		gbc_jcmbDocente.gridwidth = 3;
		gbc_jcmbDocente.insets = new Insets(0, 0, 5, 5);
		gbc_jcmbDocente.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcmbDocente.gridx = 1;
		gbc_jcmbDocente.gridy = 2;
		getContentPane().add(jcmbDocente, gbc_jcmbDocente);
		GridBagConstraints gbc_btnCargarMaterias = new GridBagConstraints();
		gbc_btnCargarMaterias.gridwidth = 2;
		gbc_btnCargarMaterias.insets = new Insets(0, 0, 5, 0);
		gbc_btnCargarMaterias.gridx = 4;
		gbc_btnCargarMaterias.gridy = 2;
		btnCargarMaterias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarAsignaturas();
			}
		});
		getContentPane().add(btnCargarMaterias, gbc_btnCargarMaterias);

		JLabel lblNewLabel_2 = new JLabel("Asignaturas no seleccionadas");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 3;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 4;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Asignaturas seleccionadas");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 3;
		gbc_lblNewLabel_3.gridy = 4;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 5;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 5;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 79, 0, 69, 0 };
		gbl_panel.rowHeights = new int[] { 128, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane);

		jlistNoSelect = new JList(getDefaultListModelNoSelect());
		scrollPane.setViewportView(jlistNoSelect);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);

		JButton btnTodoIzq = new JButton("<<");
		btnTodoIzq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pasarTodosANoSeleccionados();
			}
		});
		btnTodoIzq.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(Box.createVerticalStrut(4));
		panel_1.add(btnTodoIzq);

		JButton btnIzq = new JButton("<");
		btnIzq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pasarANoSeleccionados();
			}
		});
		btnIzq.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(Box.createVerticalStrut(4));
		panel_1.add(btnIzq);

		JButton btnDer = new JButton(">");
		btnDer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pasarASeleccionados();
			}
		});
		btnDer.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(Box.createVerticalStrut(4));
		panel_1.add(btnDer);

		JButton btnTodoDer = new JButton(">>");
		btnTodoDer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pasarTodosASeleccionados();
			}
		});
		btnTodoDer.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(Box.createVerticalStrut(4));
		panel_1.add(btnTodoDer);

		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 2;
		gbc_scrollPane_1.gridy = 0;
		panel.add(scrollPane_1, gbc_scrollPane_1);

		jlistSelect = new JList(getDefaultListModelSelect());
		scrollPane_1.setViewportView(jlistSelect);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.insets = new Insets(0, 0, 0, 5);
		gbc_btnGuardar.gridwidth = 4;
		gbc_btnGuardar.gridx = 1;
		gbc_btnGuardar.gridy = 6;
		getContentPane().add(btnGuardar, gbc_btnGuardar);

	}

	/**
	 * 
	 * @return
	 */
	private DefaultListModel getDefaultListModelSelect() {
		this.listModelAsignaturaSelect = new DefaultListModel<Asignatura>();
		return this.listModelAsignaturaSelect;
	}

	/**
	 * 
	 * @return
	 */
	private DefaultListModel getDefaultListModelNoSelect() {
		this.listModelAsignaturaNoSelect = new DefaultListModel<Asignatura>();
		return this.listModelAsignaturaNoSelect;
	}

	/**
	* 
	*/
	private void agregarAsignaturas() {

		listModelAsignaturaNoSelect.removeAllElements();
		listModelAsignaturaSelect.removeAllElements();

		Docente d = (Docente) jcmbDocente.getSelectedItem();

		List<Asignaturaspordocente> asignaturasPD = ControladorAsignaturaPorDocente.findIsSelect(d.getId());
		List<Asignaturaspordocente> asignaturasPDN = ControladorAsignaturaPorDocente.findIsNotSelect(d.getId());

		List<Asignatura> asignaturaSelect = new ArrayList<Asignatura>();
		List<Asignatura> asignaturaNoSelect = new ArrayList<Asignatura>();

		for (Asignaturaspordocente ad : asignaturasPD) {
			asignaturaSelect.add(ad.getAsignatura());
		}

		for (Asignaturaspordocente ad : asignaturasPDN) {
			asignaturaNoSelect.add(ad.getAsignatura());
		}

		if (asignaturaSelect != null) {
			for (Asignatura a : asignaturaSelect) {
				this.listModelAsignaturaSelect.add(indiceProximaAsignaturaParaAgregar, a);
				indiceProximaAsignaturaParaAgregar++;
			}
		}

		if (asignaturaNoSelect != null) {
			for (Asignatura a : asignaturaNoSelect) {
				this.listModelAsignaturaNoSelect.add(indiceProximaAsignaturaParaAgregar, a);
				indiceProximaAsignaturaParaAgregar++;
			}
		}
	}

//   /**
//    * 
//    */
//   private void agregarAsignaturasSelect() {
//	   asignaturasSeleccionadas = ControladorAsignaturaPorDocente.findIsSelect(jcmbDocente.getSelectedIndex());
//       if (asignaturasSeleccionadas != null) {
//           for (Asignaturaspordocente e : asignaturasSeleccionadas) {
//               this.listModelAsignaturaNoSelect.add(indiceProximoEstudianteParaAgregar, e);
//               indiceProximoEstudianteParaAgregar++;
//           }
//       }
//   }

//   /**
//    * 
//    */
//   private void agregarAsignaturasNoSelect() {
//	   asignaturasNoSeleccionadas = ControladorAsignaturaPorDocente.findIsNotSelect(jcmbDocente.getSelectedIndex());
//       if (asignaturasNoSeleccionadas != null) {
//           for (Asignaturaspordocente e : asignaturasNoSeleccionadas) {
//               this.listModelAsignaturaNoSelect.add(indiceProximoEstudianteParaAgregar, e);
//               indiceProximoEstudianteParaAgregar++;
//           }
//       }
//   }

//   private void agregarAsignaturas() {
//	   agregarAsignaturasSelect();
//	   agregarAsignaturasNoSelect();
//   }

	/**
	* 
	*/
	private void pasarASeleccionados() {
		for (int i = this.jlistNoSelect.getSelectedIndices().length - 1; i >= 0; i--) {
			this.listModelAsignaturaSelect
					.addElement(this.listModelAsignaturaNoSelect.get(this.jlistNoSelect.getSelectedIndices()[i]));
			this.listModelAsignaturaNoSelect.removeElementAt(this.jlistNoSelect.getSelectedIndices()[i]);
		}
	}

	/**
	* 
	*/
	private void pasarANoSeleccionados() {
		for (int i = this.jlistSelect.getSelectedIndices().length - 1; i >= 0; i--) {
			this.listModelAsignaturaNoSelect
					.addElement(this.listModelAsignaturaSelect.get(this.jlistSelect.getSelectedIndices()[i]));
			this.listModelAsignaturaSelect.removeElementAt(this.jlistSelect.getSelectedIndices()[i]);
		}
	}

	/**
	* 
	*/
	private void pasarTodosASeleccionados() {
		listModelAsignaturaNoSelect.removeAllElements();
		listModelAsignaturaSelect.removeAllElements();
		listModelAsignaturaSelect.addAll(asignaturas);
	}

	/**
	* 
	*/
	private void pasarTodosANoSeleccionados() {
		listModelAsignaturaSelect.removeAllElements();
		listModelAsignaturaNoSelect.removeAllElements();
		listModelAsignaturaNoSelect.addAll(asignaturas);
	}

	/**
	 * 
	 */
	private void cargarComboDocente() {
		jcmbDocente.removeAllItems();
		List<Docente> lista = ControladorDocente.findByDescription(jtfDocente.getText());

		for (Docente c : lista) {
			this.jcmbDocente.addItem(c);
		}
	}

	/**
	* 
	*/
	private void guardar() {
		List<Asignatura> listaParaGuardar = new ArrayList<Asignatura>();
		listaParaGuardar.removeAll(listaParaGuardar);

		for (int i = 0; i < listModelAsignaturaSelect.size(); i++) {
			listaParaGuardar.add(listModelAsignaturaSelect.getElementAt(i));
		}
		for (Asignatura asignaturas : listaParaGuardar) {
			Asignaturaspordocente v = ControladorAsignaturaPorDocente.docenteAsignatura(
					(Docente) jcmbDocente.getSelectedItem(), (Asignatura) this.jlistSelect.getSelectedValue());
			if (v != null) {
				v.setAsignatura(asignaturas);
				ControladorAsignaturaPorDocente.modificar(v);
			} else {
				v = new Asignaturaspordocente();
				v.setAsignatura(asignaturas);
				v.setDocente((Docente) jcmbDocente.getSelectedItem());
				ControladorAsignaturaPorDocente.insertar(v);
			}
		}
	}

	/**
	* 
	*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
