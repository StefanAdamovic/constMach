package dto;

import models.machine.MachineType;

public class DtoRentMachine {

	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int userId;
	private String rentStatus;
	private String startDate;
	private String endDate;
	private int unitsRented;

	private int machineId;


	public DtoRentMachine(int id, int userId, String rentStatus, String startDate, String endDate, int unitsRented,
			int machineId, MachineType type, String model, String manufacturerName, int yearProduced, String imgPath) {
		super();
		this.id = id;
		this.userId = userId;
		this.rentStatus = rentStatus;
		this.startDate = startDate;
		this.endDate = endDate;
		this.unitsRented = unitsRented;
		this.machineId = machineId;
		this.type = type;
		this.model = model;
		this.manufacturerName = manufacturerName;
		this.yearProduced = yearProduced;
		this.imgPath = imgPath;
	}

	@Override
	public String toString() {
		return "DtoRentMachine [id=" + id + ", userId=" + userId + ", rentStatus=" + rentStatus + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", unitsRented=" + unitsRented + ", machineId=" + machineId + ", type="
				+ type + ", model=" + model + ", manufacturerName=" + manufacturerName + ", yearProduced="
				+ yearProduced + ", imgPath=" + imgPath + "]";
	}

	public int getMachineId() {
		return machineId;
	}

	public void setMachineId(int machineId) {
		this.machineId = machineId;
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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	private MachineType type;
	private String model;

	private String manufacturerName;
	private int yearProduced;
	private String imgPath;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getRentStatus() {
		return rentStatus;
	}

	public void setRentStatus(String rentStatus) {
		this.rentStatus = rentStatus;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getUnitsRented() {
		return unitsRented;
	}

	public void setUnitsRented(int unitsRented) {
		this.unitsRented = unitsRented;
	}

	public DtoRentMachine() {

	}

}
