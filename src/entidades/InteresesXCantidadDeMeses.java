package entidades;

import java.math.BigDecimal;

public class InteresesXCantidadDeMeses {

	    private String plazoDPagosEnMesesIxm;  
	    private BigDecimal interesIxm;         
	    private int meses;                     

	    // Constructores
	    public InteresesXCantidadDeMeses() {
	    }

	    
	    public InteresesXCantidadDeMeses(String plazoDPagosEnMesesIxm, BigDecimal interesIxm, int meses) {
	        this.plazoDPagosEnMesesIxm = plazoDPagosEnMesesIxm;
	        this.interesIxm = interesIxm;
	        this.meses = meses;
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
	        return meses;
	    }

	    public void setMeses(int meses) {
	        this.meses = meses;
	    }


	    // Override toString()
		@Override
		public String toString() {
			return "InteresesXCantidadDeMeses [plazoDPagosEnMesesIxm=" + plazoDPagosEnMesesIxm + ", interesIxm="
					+ interesIxm + ", meses=" + meses + "]";
		}

	    
	}


