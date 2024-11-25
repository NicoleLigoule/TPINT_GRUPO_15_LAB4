package entidades;

import java.time.LocalDateTime;

public class Transferencias {
    private int idTransferencia;
    private int cuentaOrigen;
    private String CbuCuentaDestino;
    private double monto;
    private LocalDateTime fechaTransferencia;
    private String detalle;

    // Constructor vacío
    public Transferencias() {
    }

    // Constructor con parámetros
    public Transferencias(int idTransferencia, int cuentaOrigen, String CbuCuentaDestino, double monto, LocalDateTime fechaTransferencia, String detalle) {
        this.idTransferencia = idTransferencia;
        this.cuentaOrigen = cuentaOrigen;
        this.setCbuCuentaDestino(CbuCuentaDestino);
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

	public String getCbuCuentaDestino() {
		return CbuCuentaDestino;
	}

	public void setCbuCuentaDestino(String cbuCuentaDestino) {
		CbuCuentaDestino = cbuCuentaDestino;
	}

    // Método toString para depuración
  


}
