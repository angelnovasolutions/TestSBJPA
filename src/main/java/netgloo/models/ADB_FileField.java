package netgloo.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ADB_FileFields database table.
 * 
 */
@Entity
@Table(name="ADB_FileFields")
@NamedQuery(name="ADB_FileField.findAll", query="SELECT a FROM ADB_FileField a")
public class ADB_FileField implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ADB_FileFieldPK id;

	@Column(name="EndField")
	private int endField;

	@Column(name="Field")
	private String field;

	@Column(name="Format")
	private String format;

	@Column(name="LongField")
	private int longField;

	@Column(name="Recovered")
	private String recovered;

	@Column(name="Saved")
	private String saved;

	@Column(name="StartField")
	private int startField;

	@Column(name="Validation")
	private String validation;

	//bi-directional many-to-one association to ADB_FileDefinition
	@ManyToOne
	@JoinColumn(name="IDFileType")
	private ADB_FileDefinition adbFileDefinition;

	//bi-directional many-to-one association to ADB_FileLine
	@ManyToOne
	@JoinColumn(name="IDLineType")
	private ADB_FileLine adbFileLine;

	public ADB_FileField() {
	}

	public ADB_FileFieldPK getId() {
		return this.id;
	}

	public void setId(ADB_FileFieldPK id) {
		this.id = id;
	}

	public int getEndField() {
		return this.endField;
	}

	public void setEndField(int endField) {
		this.endField = endField;
	}

	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public int getLongField() {
		return this.longField;
	}

	public void setLongField(int longField) {
		this.longField = longField;
	}

	public String getRecovered() {
		return this.recovered;
	}

	public void setRecovered(String recovered) {
		this.recovered = recovered;
	}

	public String getSaved() {
		return this.saved;
	}

	public void setSaved(String saved) {
		this.saved = saved;
	}

	public int getStartField() {
		return this.startField;
	}

	public void setStartField(int startField) {
		this.startField = startField;
	}

	public String getValidation() {
		return this.validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public ADB_FileDefinition getAdbFileDefinition() {
		return this.adbFileDefinition;
	}

	public void setAdbFileDefinition(ADB_FileDefinition adbFileDefinition) {
		this.adbFileDefinition = adbFileDefinition;
	}

	public ADB_FileLine getAdbFileLine() {
		return this.adbFileLine;
	}

	public void setAdbFileLine(ADB_FileLine adbFileLine) {
		this.adbFileLine = adbFileLine;
	}

}