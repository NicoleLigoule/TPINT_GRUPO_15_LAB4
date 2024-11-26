package entidades;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Cuenta {
    
    private int numeroDeCuentaCu;  
    private String cuilCliCu;      
    private LocalDate fechaCreacionCu; 
    private int idTipoCuenta;      
    private String cbuCu;          
    private BigDecimal saldoCu;    
    private boolean estadoCu;
    private String tipoCuentaDescripcion; // Nueva propiedad para la descripción del tipo de cuenta

    // Constructores
    public Cuenta() {
        this.saldoCu = new BigDecimal("10000.00"); // saldo por defecto
        this.estadoCu = true;
    }

    public Cuenta(int numeroDeCuentaCu, String cuilCliCu, LocalDate fechaCreacionCu,
                  int idTipoCuenta, String tipoCuentaDescripcion, String cbuCu, BigDecimal saldoCu, boolean estadoCu) {
        this.numeroDeCuentaCu = numeroDeCuentaCu;
        this.cuilCliCu = cuilCliCu;
        this.fechaCreacionCu = fechaCreacionCu;
        this.idTipoCuenta = idTipoCuenta;
        this.tipoCuentaDescripcion = tipoCuentaDescripcion;
        this.cbuCu = cbuCu;
        this.saldoCu = saldoCu;
        this.estadoCu = estadoCu;
    }

    // Getters y Setters
    public int getNumeroDeCuentaCu() {
        return numeroDeCuentaCu;
    }

    public void setNumeroDeCuentaCu(int numeroDeCuentaCu) {
        this.numeroDeCuentaCu = numeroDeCuentaCu;
    }

    public String getCuilCliCu() {
        return cuilCliCu;
    }

    public void setCuilCliCu(String cuilCliCu) {
        this.cuilCliCu = cuilCliCu;
    }

    public LocalDate getFechaCreacionCu() {
        return fechaCreacionCu;
    }

    public void setFechaCreacionCu(LocalDate fechaCreacionCu) {
        this.fechaCreacionCu = fechaCreacionCu;
    }

    public int getIdTipoCuenta() {
        return idTipoCuenta;
    }

    public void setIdTipoCuenta(int idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    public String getCbuCu() {
        return cbuCu;
    }

    public void setCbuCu(String cbuCu) {
        this.cbuCu = cbuCu;
    }

    public BigDecimal getSaldoCu() {
        return saldoCu;
    }

    public void setSaldoCu(BigDecimal saldoCu) {
        this.saldoCu = saldoCu;
    }

    public boolean isEstadoCu() {
        return estadoCu;
    }

    public void setEstadoCu(boolean estadoCu) {
        this.estadoCu = estadoCu;
    }

    public String getTipoCuentaDescripcion() {
        return tipoCuentaDescripcion;
    }

    public void setTipoCuentaDescripcion(String tipoCuentaDescripcion) {
        this.tipoCuentaDescripcion = tipoCuentaDescripcion;
    }

    @Override
    public String toString() {
        return "Cuenta [numeroDeCuentaCu=" + numeroDeCuentaCu + ", cuilCliCu=" + cuilCliCu + ", fechaCreacionCu="
                + fechaCreacionCu + ", idTipoCuenta=" + idTipoCuenta + ", tipoCuentaDescripcion=" + tipoCuentaDescripcion
                + ", cbuCu=" + cbuCu + ", saldoCu=" + saldoCu + ", estadoCu=" + estadoCu + "]";
    }
}
