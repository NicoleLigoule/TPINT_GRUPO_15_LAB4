package negocio;


import daoImpl.TransferenciaDaoImpl;

public class NegocioTransferencia {

    private TransferenciaDaoImpl transferenciaDao;

    public NegocioTransferencia() {
        this.transferenciaDao = new TransferenciaDaoImpl(); // Usar implementación del DAO
    }

    public void realizarTransferencia(int cuentaOrigen, String CbuCuentaDestino, double monto, String detalle) throws Exception {
        if (cuentaOrigen <= 0 || monto <= 0) {
            throw new Exception("Los parámetros de la transferencia son inválidos.");
        }
        transferenciaDao.realizarTransferencia(cuentaOrigen, CbuCuentaDestino, monto, detalle);
    }
}
