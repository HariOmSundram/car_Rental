package com.carrental.booking.utility;

import java.util.UUID;

public class Helper {

	public static String generateBookingId() {

		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		StringBuilder sb = new StringBuilder(16);

		for (int i = 0; i < 9; i++) {

			int index = (int) (AlphaNumericString.length() * Math.random());

			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString().toUpperCase();
	}

	public static String generateTransactionRefId() {
		UUID uuid = UUID.randomUUID();
		String uuidHex = uuid.toString().replace("-", ""); // Remove hyphens
		String uuid16Digits = uuidHex.substring(0, 16); // Take the first 16 characters

		return uuid16Digits;
	}

}
