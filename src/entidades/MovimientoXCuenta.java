package entidades;

public class MovimientoXCuenta {

	    private int idMovimientoMovMXC;       
	    private int numCuentaCuMXC;           
	    private Movimiento movimiento;    // Relación con la clase Movimiento (llave foránea)   
	    private Cuenta cuenta;            // Relación con la clase Cuenta (llave foránea) 

	    // Constructores
	    public MovimientoXCuenta() {
	    }


	    public MovimientoXCuenta(int idMovimientoMovMXC, int numCuentaCuMXC, Movimiento movimiento, Cuenta cuenta) {
	        this.idMovimientoMovMXC = idMovimientoMovMXC;
	        this.numCuentaCuMXC = numCuentaCuMXC;
	        this.movimiento = movimiento;
	        this.cuenta = cuenta;
	    }

	    // Getters y setters
	    public int getIdMovimientoMovMXC() {
	        return idMovimientoMovMXC;
	    }

	    public void setIdMovimientoMovMXC(int idMovimientoMovMXC) {
	        this.idMovimientoMovMXC = idMovimientoMovMXC;
	    }

	    public int getNumCuentaCuMXC() {
	        return numCuentaCuMXC;
	    }

	    public void setNumCuentaCuMXC(int numCuentaCuMXC) {
	        this.numCuentaCuMXC = numCuentaCuMXC;
	    }

	    public Movimiento getMovimiento() {
	        return movimiento;
	    }

	    public void setMovimiento(Movimiento movimiento) {
	        this.movimiento = movimiento;
	    }

	    public Cuenta getCuenta() {
	        return cuenta;
	    }

	    public void setCuenta(Cuenta cuenta) {
	        this.cuenta = cuenta;
	    }


	    // Override toString() 
		@Override
		public String toString() {
			return "MovimientoXCuenta [idMovimientoMovMXC=" + idMovimientoMovMXC + ", numCuentaCuMXC=" + numCuentaCuMXC
					+ ", movimiento=" + movimiento + ", cuenta=" + cuenta + "]";
		}	    
	    
	}

