package clases;

public class Productos {
    String codigo;
    String nombre;
    String empresa;
    Double precio;
    String fvecimiento;
    String tipo;
    int cantidad;
    public Productos(String codigo, String nombre, String empresa, Double precio, String fvecimiento, String tipo, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.empresa = empresa;
        this.precio = precio;
        this.fvecimiento = fvecimiento;
        this.tipo = tipo;
    }
    public String getCodigo() {
        return codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public String getEmpresa() {
        return empresa;
    }
    public Double getPrecio() {
        return precio;
    }
    public String getFvecimiento() {
        return fvecimiento;
    }
    public String getTipo() {
        return tipo;
    }
    public int getCantidad() {
        return cantidad;
    }
}
