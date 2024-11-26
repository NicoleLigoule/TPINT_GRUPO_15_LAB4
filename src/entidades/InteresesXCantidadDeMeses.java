package entidades;

import java.math.BigDecimal;

public class InteresesXCantidadDeMeses {

	    private String plazoDPagosEnMesesIxm;  
	    private BigDecimal interesIxm;         
	    private int Meses_int;                     

	    // Constructores
	    public InteresesXCantidadDeMeses() {
	    }

	    
	    public InteresesXCantidadDeMeses(String plazoDPagosEnMesesIxm, BigDecimal interesIxm, int meses) {
	        this.plazoDPagosEnMesesIxm = plazoDPagosEnMesesIxm;
	        this.interesIxm = interesIxm;
	        this.Meses_int = meses;
	    }

	    // Getters y Setters
	    public String getPlazoDPagosEnMesesIxm() {
	        return plazoDPagosEnMesesIxm;
	    }

	    public void setPlazoDPagosEnMesesIxm(String plazoDPagosEnMesesIxm) {
	        this.plazoDPagosEnMesesIxm = plazoDPagosEnMesesIxm;
	    }

	    public BigDecimal getInteresIxm() {
	        return interesIxm;
	    }

	    public void setInteresIxm(BigDecimal interesIxm) {
	        this.interesIxm = interesIxm;
	    }

	    public int getMeses() {
	        return Meses_int;
	    }

	    public void setMeses(int meses) {
	        this.Meses_int = meses;
	    }


	    // Override toString()
		@Override
		public String toString() {
			return "InteresesXCantidadDeMeses [plazoDPagosEnMesesIxm=" + plazoDPagosEnMesesIxm + ", interesIxm="
					+ interesIxm + ", Meses_int=" + Meses_int + "]";
		}

	    
	}


