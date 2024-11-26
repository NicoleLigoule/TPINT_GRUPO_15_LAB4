package dao;

import java.util.List;


import entidades.Transferencias;

public interface TransferenciaDao {
    void realizarTransferencia(int cuentaOrigen, String CbuCuentaDestino, double monto, String detalle) throws Exception;
}


