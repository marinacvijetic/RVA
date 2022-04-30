package rva.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the radnik database table.
 * 
 */
@Entity
@NamedQuery(name="Radnik.findAll", query="SELECT r FROM Radnik r")
public class Radnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RADNIK_ID_GENERATOR", sequenceName="RADNIK_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RADNIK_ID_GENERATOR")
	private Integer id;

	@Column(name="broj_lk")
	private Integer brojLk;

	private String ime;

	private String prezime;

	//bi-directional many-to-one association to Obrazovanje
	@ManyToOne
	@JoinColumn(name="obrazovanje")
	private Obrazovanje obrazovanjeBean;

	//bi-directional many-to-one association to Sektor
	@ManyToOne
	@JoinColumn(name="sektor")
	private Sektor sektorBean;

	public Radnik() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBrojLk() {
		return this.brojLk;
	}

	public void setBrojLk(Integer brojLk) {
		this.brojLk = brojLk;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Obrazovanje getObrazovanjeBean() {
		return this.obrazovanjeBean;
	}

	public void setObrazovanjeBean(Obrazovanje obrazovanjeBean) {
		this.obrazovanjeBean = obrazovanjeBean;
	}

	public Sektor getSektorBean() {
		return this.sektorBean;
	}

	public void setSektorBean(Sektor sektorBean) {
		this.sektorBean = sektorBean;
	}

}