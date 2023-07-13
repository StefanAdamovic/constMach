package models.machine;

public class Machine {

	private int id;
	private MachineType type;
	private String model;
	private int stockUnits;
	private String manufacturerName;
	private int yearProduced;
	private String imgPath;

	public Machine(MachineType type, String model, int stockUnits, String manufacturerName, int yearProduced,
			String imgPath) {
		super();
		this.type = type;
		this.model = model;
		this.stockUnits = stockUnits;
		this.manufacturerName = manufacturerName;
		this.yearProduced = yearProduced;
		this.imgPath = imgPath;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Machine(int id, MachineType type, String model, int stockUnits, String manufacturerName, int yearProduced) {
		super();
		this.id = id;
		this.type = type;
		this.model = model;
		this.stockUnits = stockUnits;
		this.manufacturerName = manufacturerName;
		this.yearProduced = yearProduced;
	}

	public Machine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MachineType getType() {
		return type;
	}

	public void setType(MachineType type) {
		this.type = type;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getStockUnits() {
		return stockUnits;
	}

	public void setStockUnits(int stockUnits) {
		this.stockUnits = stockUnits;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public int getYearProduced() {
		return yearProduced;
	}

	public void setYearProduced(int yearProduced) {
		this.yearProduced = yearProduced;
	}

	@Override
	public String toString() {
		return "Machine [id=" + id + ", type=" + type + ", model=" + model + ", stockUnits=" + stockUnits
				+ ", manufacturerName=" + manufacturerName + ", yearProduced=" + yearProduced + ", imgPath=" + imgPath
				+ "]";
	}

}
