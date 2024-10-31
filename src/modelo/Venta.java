package modelo;

/**
 *
 * @author Roberto
 */
public class Venta {

    private int idVenta;
    private String fecha;
    private String metodoPago;
    private int idUsuario;
    private int idCliente;
    private String tipoDocumento;
    private String serieDocumento;
    private String numeroDocumento;
    private String rucCliente;
    private double total;
    private int idProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    //Constructor vacio
    public Venta() {

    }

    //AGREGAR
    public Venta(String metodoPago, String tipoDocumento, String numeroDocumento, String rucCliente) {
        this.metodoPago = metodoPago;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.rucCliente = rucCliente;

    }

    //Constructor para agregar (acualizar)
    public Venta(int idVenta, String fecha, String metodoPago, int idUsuario, int idCliente, String tipoDocumento,
            String serieDocumento, String numeroDocumento, String rucCliente, double total, int idProducto,
            int cantidad, double precioUnitario, double subtotal) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
        this.tipoDocumento = tipoDocumento;
        this.serieDocumento = serieDocumento;
        this.numeroDocumento = numeroDocumento;
        this.rucCliente = rucCliente;
        this.total = total;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    //Constructor GGETANDSET
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getSerieDocumento() {
        return serieDocumento;
    }

    public void setSerieDocumento(String serieDocumento) {
        this.serieDocumento = serieDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getRucCliente() {
        return rucCliente;
    }

    public void setRucCliente(String rucCliente) {
        this.rucCliente = rucCliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
