package gui;

import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import clases.Clientes;
import clases.ProductoStock;
import clases.Productos;
import clases.Ventas;
import db.BaseDatos;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import java.awt.Color;

public class Interfaz extends JFrame implements ActionListener{
	
	private JTable tableStock;
	private JTextField agregarProductoNombre;
	private JTextField agregarProductoEmpresa;
	private JTextField agregarProductoCodigo;
	private JTextField precio;
	private JTextField vencimiento;
	private JTextField cantidad;
	private Choice tipo;
	private DefaultTableModel modeloTablaStock = new DefaultTableModel();
	private DefaultTableModel modeloTablaClientes = new DefaultTableModel();
	private DefaultTableModel modeloTablaVentas = new DefaultTableModel();
	private JTable tableClientes;
	private JTable tableVentas;
	private JButton stockButton;
	private JButton usuario;
	private JButton ventas;
	private JButton btnVentanaVender;
	private BaseDatos db;
	private ArrayList<ProductoStock> catalogoStock;
	private ArrayList<Clientes> catalogoClientes;
	private ArrayList<Ventas> catalogoVentas;
//constructor 
	public Interfaz() {
		
	}
	public Interfaz(ArrayList <ProductoStock> CatalogoStock, ArrayList <Clientes> CatalogoClientes, ArrayList <Ventas> CatalogoVentas, ArrayList<String> tipos) throws HeadlessException {
		db = new BaseDatos();
		this.catalogoStock = CatalogoStock;
		this.catalogoClientes = CatalogoClientes;
		this.catalogoVentas = CatalogoVentas;
		
		getContentPane().setBackground(new Color(65, 65, 65));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SUPERMERCADO");
		lblNewLabel.setForeground(new Color(192, 192, 192));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(407, 10, 150, 26);
		getContentPane().add(lblNewLabel);
//		------MODELOS DE TABLAS-------
		modeloTablaStock.addColumn("Codigo");
		modeloTablaStock.addColumn("Nombre de Producto");
		modeloTablaStock.addColumn("Empresa");
		modeloTablaStock.addColumn("Precio total");
		modeloTablaStock.addColumn("Cantidad");
		modeloTablaStock.addColumn("Fecha de Vencimiento");
		modeloTablaStock.addColumn("Tipo de producto");
		modeloTablaClientes.addColumn("Dni");
		modeloTablaClientes.addColumn("Nombre");
		modeloTablaClientes.addColumn("Apellido");
		modeloTablaClientes.addColumn("Direccion");
		modeloTablaClientes.addColumn("Fecha de Nacimiento");
		modeloTablaVentas.addColumn("ID de venta");
		modeloTablaVentas.addColumn("Dni Cliente");
		modeloTablaVentas.addColumn("Codigo de Producto");
		modeloTablaVentas.addColumn("Nombre de Producto");
		modeloTablaVentas.addColumn("Fecha");
		modeloTablaVentas.addColumn("Monto");
		modeloTablaVentas.addColumn("Cantidad");
		String stock[]= {"Codigo","Nombre de Producto","Empresa","Precio","Cantidad","Fecha de Vencimiento","Tipo de Producto"};
		String clientes[] = {"Dni", "Nombre","Apellido","Direccion","Fecha de Nacimiento"};
		String venta[] = {"ID de Venta","Dni Cliente","Codigo de Producto","Nombre de Producto", "Cantidad", "Fecha","Monto"};
		modeloTablaStock.addRow(stock);
		modeloTablaClientes.addRow(clientes);
		modeloTablaVentas.addRow(venta);
//		------MODELOS DE TABLAS-------
		modeloTablaStock = this.llenarTablaStock(this.catalogoStock , modeloTablaStock);
		this.llenarTablaUsuario(this.catalogoClientes, modeloTablaClientes);
		this.llenarTablaVentas(this.catalogoVentas, modeloTablaVentas);
//		-------------TABLAS-------
		tableStock = new JTable(modeloTablaStock);
		tableStock.setGridColor(new Color(0, 0, 0));
		tableStock.setBackground(new Color(183, 219, 255));
		tableStock.setSurrendersFocusOnKeystroke(true);
		tableStock.setCellSelectionEnabled(true);
		tableStock.setColumnSelectionAllowed(true);
		tableStock.setBounds(47, 65, 736, 281);
		tableStock.setVisible(true);
		getContentPane().add(tableStock);
		
		tableClientes = new JTable();
		tableClientes.setBackground(new Color(183, 219, 255));
		tableClientes.setSurrendersFocusOnKeystroke(true);
		tableClientes.setCellSelectionEnabled(true);
		tableClientes.setColumnSelectionAllowed(true);
		tableClientes.setVisible(false);
		tableClientes.setBounds(47, 65, 736, 281);
		getContentPane().add(tableClientes);
		
		tableVentas = new JTable();
		tableVentas.setBackground(new Color(183, 219, 255));
		tableVentas.setVisible(false);
		tableVentas.setBounds(47, 65, 736, 281);
		getContentPane().add(tableVentas);
//		-------------TABLAS-------
//		-------------BOTONES-------
		stockButton = new JButton("STOCK");
		stockButton.setForeground(new Color(192, 192, 192));
		stockButton.setBackground(new Color(0, 64, 128));
		stockButton.addActionListener(this);
		stockButton.setBounds(819, 117, 125, 33);
		getContentPane().add(stockButton);
		
		usuario = new JButton("USUARIO");
		usuario.setForeground(new Color(192, 192, 192));
		usuario.setBackground(new Color(0, 64, 128));
		usuario.addActionListener(this);
		usuario.setBounds(819, 194, 125, 33);
		getContentPane().add(usuario);
		
		ventas = new JButton("VENTAS");
		ventas.addActionListener(this);
		ventas.setForeground(new Color(192, 192, 192));
		ventas.setBackground(new Color(0, 64, 128));
		ventas.setBounds(819, 267, 125, 33);
		getContentPane().add(ventas);
//		-------------BOTONES-------
//		-------------AGREGAR PRODUCTO-------
		agregarProductoNombre = new JTextField();
		agregarProductoNombre.setBorder(null);
		agregarProductoNombre.setCaretColor(new Color(96, 96, 96));
		agregarProductoNombre.setSelectionColor(new Color(96, 96, 96));
		agregarProductoNombre.setBackground(new Color(96, 96, 96));
		agregarProductoNombre.setBounds(47, 383, 143, 41);
		getContentPane().add(agregarProductoNombre);
		agregarProductoNombre.setColumns(10);
		
		agregarProductoEmpresa = new JTextField();
		agregarProductoEmpresa.setBorder(null);
		agregarProductoEmpresa.setBackground(new Color(96, 96, 96));
		agregarProductoEmpresa.setBounds(228, 383, 143, 41);
		getContentPane().add(agregarProductoEmpresa);
		agregarProductoEmpresa.setColumns(10);
		
		agregarProductoCodigo = new JTextField();
		agregarProductoCodigo.setBorder(null);
		agregarProductoCodigo.setBackground(new Color(96, 96, 96));
		agregarProductoCodigo.setBounds(47, 462, 143, 41);
		getContentPane().add(agregarProductoCodigo);
		agregarProductoCodigo.setColumns(10);
		
		JButton AGREGAR = new JButton("AGREGAR PRODUCTO");
		AGREGAR.setForeground(new Color(192, 192, 192));
		AGREGAR.setBackground(new Color(0, 0, 128));
		AGREGAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValidarYAgregarProducto();
			}
		});
		AGREGAR.setBounds(736, 375, 188, 57);
		getContentPane().add(AGREGAR);
		
		precio = new JTextField();
		precio.setBorder(null);
		precio.setBackground(new Color(96, 96, 96));
		precio.setBounds(228, 462, 143, 41);
		getContentPane().add(precio);
		precio.setColumns(10);
		
		vencimiento = new JTextField();
		vencimiento.setBorder(null);
		vencimiento.setBackground(new Color(96, 96, 96));
		vencimiento.setBounds(414, 383, 143, 41);
		getContentPane().add(vencimiento);
		vencimiento.setColumns(10);
		
		cantidad = new JTextField();
		cantidad.setBorder(null);
		cantidad.setBackground(new Color(96, 96, 96));
		cantidad.setBounds(414, 462, 143, 41);
		getContentPane().add(cantidad);
		cantidad.setColumns(10);
		
		tipo = new Choice();
		tipo.setForeground(new Color(255, 255, 255));
		tipo.setBackground(new Color(96, 96, 96));
		tipo.setBounds(601, 393, 104, 57);
		for (String tipofor : tipos) {
			tipo.addItem(tipofor.split(",")[1]);
		}
		getContentPane().add(tipo);
		
		JLabel agregarProductoEmpresaLabel = new JLabel("Empresa del Producto");
		agregarProductoEmpresaLabel.setForeground(new Color(192, 192, 192));
		agregarProductoEmpresaLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		agregarProductoEmpresaLabel.setBounds(228, 360, 143, 26);
		getContentPane().add(agregarProductoEmpresaLabel);
		
		JLabel venciminetoDelProductoLabel = new JLabel("Vencimiento Producto");
		venciminetoDelProductoLabel.setForeground(new Color(192, 192, 192));
		venciminetoDelProductoLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		venciminetoDelProductoLabel.setBounds(414, 356, 143, 30);
		getContentPane().add(venciminetoDelProductoLabel);
		
		JLabel codigoDelProductoLabel = new JLabel("Codigo del Producto");
		codigoDelProductoLabel.setForeground(new Color(192, 192, 192));
		codigoDelProductoLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codigoDelProductoLabel.setBounds(47, 434, 143, 26);
		getContentPane().add(codigoDelProductoLabel);
		
		JLabel agregarProductoNombreLabel = new JLabel("Nombre Del Poducto");
		agregarProductoNombreLabel.setForeground(new Color(192, 192, 192));
		agregarProductoNombreLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		agregarProductoNombreLabel.setBounds(47, 360, 143, 26);
		getContentPane().add(agregarProductoNombreLabel);
		
		JLabel precioLabel = new JLabel("Precio Producto");
		precioLabel.setForeground(new Color(192, 192, 192));
		precioLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		precioLabel.setBounds(228, 439, 143, 21);
		getContentPane().add(precioLabel);
		
		JLabel cantidadDeProductoLabel = new JLabel("Cantidad Producto");
		cantidadDeProductoLabel.setForeground(new Color(192, 192, 192));
		cantidadDeProductoLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cantidadDeProductoLabel.setBounds(414, 434, 143, 26);
		getContentPane().add(cantidadDeProductoLabel);
		
		JLabel tipoDeProductoLabel = new JLabel("Tipo de Producto");
		tipoDeProductoLabel.setForeground(new Color(192, 192, 192));
		tipoDeProductoLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tipoDeProductoLabel.setBounds(601, 361, 130, 26);
		getContentPane().add(tipoDeProductoLabel);
		
		btnVentanaVender = new JButton("Vender Producto");
		btnVentanaVender.setVisible(false);
		btnVentanaVender.addActionListener(this);
		btnVentanaVender.setForeground(new Color(192, 192, 192));
		btnVentanaVender.setBackground(new Color(0, 0, 128));
		btnVentanaVender.setBounds(756, 445, 148, 42);
		getContentPane().add(btnVentanaVender);
//		-------------AGREGAR PRODUCTO-------
	
		setSize(1000,550);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.stockButton) {
			this.tableStock.setVisible(true);
			this.tableClientes.setVisible(false);
			this.tableVentas.setVisible(false);
			this.btnVentanaVender.setVisible(true);
			this.tableStock.setModel(modeloTablaStock);
		}
		if(e.getSource() == this.usuario) {
			this.tableStock.setVisible(false);
			this.tableClientes.setVisible(true);
			this.tableVentas.setVisible(false);
			this.tableClientes.setModel(modeloTablaClientes);
		}
		if(e.getSource() == this.ventas) {
			this.tableStock.setVisible(false);
			this.tableClientes.setVisible(false);
			this.tableVentas.setVisible(true);
			this.tableVentas.setModel(modeloTablaVentas);
		}
		if(e.getSource() == this.btnVentanaVender) {
			String IDcliente = JOptionPane.showInputDialog("Ingrese el dni del cliente");
			String IDproducto = JOptionPane.showInputDialog("Ingrese el codigo del producto");
			int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad que desea comprar"));
			ProductoStock p = db.buscarProductoStock(IDproducto);
			db.agregarVentas(Integer.parseInt(IDcliente), cantidad, p);
			this.catalogoVentas = db.obtenerVentas();
			this.modeloTablaVentas.addRow(this.catalogoVentas.get(this.catalogoVentas.size() -1).toString().split(","));
			this.tableVentas.setModel(modeloTablaVentas);
		}
	}
	
	public DefaultTableModel llenarTablaStock(ArrayList <ProductoStock> CatalogoStock , DefaultTableModel modelo) {
		for (ProductoStock productoStock : CatalogoStock) {
			Object StockFila [] = new Object [7];
			StockFila[0] = productoStock.getCodigo();
			StockFila[1] = productoStock.getNombre();
			StockFila[2] = productoStock.getEmpresa();
			StockFila[3] = productoStock.getPrecio();
			StockFila[4] = productoStock.getCantidad();
			StockFila[5] = productoStock.getFvecimiento();
			StockFila[6] = productoStock.getTipo();
			modelo.addRow(StockFila);
		}
		return modelo;
	}
	
	public void llenarTablaUsuario(ArrayList <Clientes> CatalogoClientes , DefaultTableModel modelo) {
		for (Clientes clientes : CatalogoClientes) {
			Object StockFila [] = new Object [7];
			StockFila[0] = clientes.getDni();
			StockFila[1] = clientes.getNombre();
			StockFila[2] = clientes.getApellido();
			StockFila[3] = clientes.getDireccion();
			StockFila[4] = clientes.getFnacimiento();
			modelo.addRow(StockFila);
		}
	}
	
	public void llenarTablaVentas(ArrayList <Ventas> CatalogoVentas , DefaultTableModel modelo) {
		for (Ventas ventas : CatalogoVentas) {
			Object StockFila [] = new Object [7];
			StockFila[0] = ventas.getIdVenta();
			StockFila[1] = ventas.getDniCliente();
			StockFila[2] = ventas.getCodigoProducto();
			StockFila[3] = ventas.getNombreProducto();
			StockFila[4] = ventas.getCantidad();
			StockFila[5] = ventas.getFechaCompra();
			StockFila[6] = ventas.getMonto();
			modelo.addRow(StockFila);
		}
	}
	
	public void ValidarYAgregarProducto() {
		boolean validad=false;
		JTextField Inputs [] = {this.agregarProductoNombre, this.agregarProductoEmpresa, this.agregarProductoCodigo, this.precio, this.vencimiento, this.cantidad};
		for (JTextField Input : Inputs) {
			if (Input.getText().equals("")) {
				validad=false;
			} else {
				validad=true;
			}
		}
		if(validad==false) {
			JOptionPane.showConfirmDialog(null, "Porfavor complete todos los campos antes de agregar un producto","",JOptionPane.DEFAULT_OPTION);
		}else {
			String codigo = this.agregarProductoCodigo.getText();
			String nombre = this.agregarProductoNombre.getText();
			String empresa = this.agregarProductoEmpresa.getText();
			Double precio = Double.parseDouble(this.precio.getText());
			String fecha = this.vencimiento.getText();
			String tipo = this.tipo.getSelectedItem();
			int cantidad = Integer.parseInt(this.cantidad.getText());
			boolean resultado = db.agregarStock(new Productos(codigo, nombre, empresa, precio, fecha,tipo), cantidad);
			if(resultado) {
				JOptionPane.showConfirmDialog(null, "El producto fue agregado correctamente", "", JOptionPane.DEFAULT_OPTION);
				this.catalogoStock = db.obtenerStock();
				this.modeloTablaStock.addRow(this.catalogoStock.get(this.catalogoStock.size()-1).toString().split(","));
				this.tableStock.setModel(this.modeloTablaStock);
				for (JTextField jTextField : Inputs) {
					jTextField.setText("");
				}
			}
		}
	}
}

