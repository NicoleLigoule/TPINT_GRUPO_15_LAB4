package entidades;

import java.time.LocalDateTime;

public class Transferencias {
    private int idTransferencia;
    private int cuentaOrigen;
    private int cuentaDestino;
    private double monto;
    private LocalDateTime fechaTransferencia;
    private String detalle;

    // Constructor vacío
    public Transferencias() {
    }

    // Constructor con parámetros
    public Transferencias(int idTransferencia, int cuentaOrigen, int cuentaDestino, double monto, LocalDateTime fechaTransferencia, String detalle) {
        this.idTransferencia = idTransferencia;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.monto = monto;
        this.fechaTransferencia = fechaTransferencia;
        this.detalle = detalle;
    }

    // Getters y setters
    public int getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(int idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public int getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(int cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public int getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(int cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDateTime getFechaTransferencia() {
        return fechaTransferencia;
    }

    public void setFechaTransferencia(LocalDateTime fechaTransferencia) {
        this.fechaTransferencia = fechaTransferencia;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    // Método toString para depuración
    @Override
    public String toString() {
        return "Transferencias{" +
                "idTransferencia=" + idTransferencia +
                ", cuentaOrigen=" + cuentaOrigen +
                ", cuentaDestino=" + cuentaDestino +
                ", monto=" + monto +
                ", fechaTransferencia=" + fechaTransferencia +
                ", detalle='" + detalle + '\'' +
                '}';
    }
}
