package com.carrental.car.utility;

public class Constants {

	public enum ActiveStatus {
		ACTIVE("Active"), DEACTIVATED("Deactivated");

		private String status;

		private ActiveStatus(String status) {
			this.status = status;
		}

		public String value() {
			return this.status;
		}
	}

	public enum FuelType {
		GASOLINE("Gasoline"), DIESEL("Diesel"), ELECTRIC("Electric"), HYBRID("Hybrid"), PETROL("Petrol");

		private String type;

		private FuelType(String type) {
			this.type = type;
		}

		public String value() {
			return this.type;
		}
		
	}
	
	public enum IsAC {
		YES("Yes"),
		NO("No");

		private String type;

		private IsAC(String type) {
			this.type = type;
		}

		public String value() {
			return this.type;
		}
		
	}

}
