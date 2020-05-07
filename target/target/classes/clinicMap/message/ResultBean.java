package clinicMap.message;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "Statistic")
public class ResultBean {
	private int clinicID;
	private float ave;
	private int total;

	@Id
	@Column(name = "clinicID")
	public int getClinicID() {
		return clinicID;
	}

	public void setClinicID(int clinicID) {
		this.clinicID = clinicID;
	}

	@Column(name = "ave")
	public float getAve() {
		return ave;
	}

	public void setAve(float ave) {
		this.ave = ave;
	}

	@Column(name = "total")
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
