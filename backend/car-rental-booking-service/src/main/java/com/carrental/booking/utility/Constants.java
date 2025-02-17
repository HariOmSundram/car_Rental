package com.carrental.booking.utility;

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

	public enum BookingStatus {
		PENDING("Pending"), REJECTED("Rejected") , CANCELLED("Cancelled") , APPROVED("Approved"), PAID_AND_CONFIRMED("Paid & Confirmed");

		private String status;

		private BookingStatus(String status) {
			this.status = status;
		}

		public String value() {
			return this.status;
		}
	}

}
