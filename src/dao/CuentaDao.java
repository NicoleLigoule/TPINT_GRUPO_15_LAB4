package dao;

import java.time.LocalDate;
import java.util.List;
import entidades.Cuenta;
import entidades.TipoDeCuenta;

public interface CuentaDao {
	public List<Cuenta> obtenerTodos();
	public Cuenta obtenerUno(int id);
	public boolean insertar(Cuenta cuenta);
	public boolean insertarSinCliente(Cuenta cuenta);
	public boolean editar(Cuenta cuenta);
	public boolean borrar(int id);
	public List<Cuenta> obtenerCuentasPorCuil(String cuil);
	public List<Cuenta> obtenerCuentasReporte(int tipo, LocalDate fechaDesde, LocalDate fechaHasta);
     public List<TipoDeCuenta> readallTipoDeCuentas(); 

}
