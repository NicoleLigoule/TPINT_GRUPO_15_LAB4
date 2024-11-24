package dao;

import entidades.Transferencias;

public interface TransferenciaDao {
    void realizarTransferencia(int cuentaOrigen, int cuentaDestino, double monto, String detalle) throws Exception;
}
