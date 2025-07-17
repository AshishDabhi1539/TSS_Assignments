package com.tss.util;

import java.util.Properties;
import java.util.regex.Pattern;

import com.tss.config.ConfigService;
import com.tss.exception.AppException;

public class ValidationUtil {
	private static final Properties config = ConfigService.getInstance().loadProperties("validation.properties");

	// Validation constants from configuration
	private static final int MAX_NAME_LENGTH = Integer.parseInt(config.getProperty("max.name.length", "50"));
	private static final double MAX_PRICE = Double.parseDouble(config.getProperty("max.food.price", "10000.0"));
	private static final int MAX_QUANTITY = Integer.parseInt(config.getProperty("max.order.quantity", "100"));
	private static final double MAX_DISCOUNT_THRESHOLD = Double
			.parseDouble(config.getProperty("max.discount.threshold", "100000.0"));
	private static final double MAX_DISCOUNT_PERCENT = Double
			.parseDouble(config.getProperty("max.discount.percent", "50.0"));
	private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9 ]+$");

	/**
	 * Validates a food item name.
	 * 
	 * @param name          The name to validate.
	 * @param existingNames Existing names in the same cuisine.
	 * @throws AppException If validation fails.
	 */
	public static void validateFoodName(String name, Iterable<String> existingNames) throws AppException {
		if (name == null || name.trim().isEmpty()) {
			throw new AppException("Food name cannot be empty.");
		}
		name = name.trim();
		if (name.length() > MAX_NAME_LENGTH) {
			throw new AppException("Food name cannot exceed " + MAX_NAME_LENGTH + " characters.");
		}
		if (!NAME_PATTERN.matcher(name).matches()) {
			throw new AppException("Food name can only contain letters, numbers, and spaces.");
		}
		for (String existing : existingNames) {
			if (existing.equalsIgnoreCase(name)) {
				throw new AppException("Food item '" + name + "' already exists in this cuisine.");
			}
		}
	}

	/**
	 * Validates a food price.
	 * 
	 * @param price The price to validate.
	 * @throws AppException If validation fails.
	 */
	public static void validateFoodPrice(double price) throws AppException {
		if (price <= 0) {
			throw new AppException("Price must be positive.");
		}
		if (price > MAX_PRICE) {
			throw new AppException("Price cannot exceed ₹" + MAX_PRICE + ".");
		}
	}

	/**
	 * Validates an order quantity.
	 * 
	 * @param quantity The quantity to validate.
	 * @throws AppException If validation fails.
	 */
	public static void validateQuantity(int quantity) throws AppException {
		if (quantity <= 0) {
			throw new AppException("Quantity must be positive.");
		}
		if (quantity > MAX_QUANTITY) {
			throw new AppException("Quantity cannot exceed " + MAX_QUANTITY + ".");
		}
	}

	/**
	 * Validates a discount threshold and amount.
	 * 
	 * @param threshold The discount threshold.
	 * @param amount    The discount amount.
	 * @throws AppException If validation fails.
	 */
	public static void validateDiscount(double threshold, double amount) throws AppException {
		if (threshold < 0) {
			throw new AppException("Discount threshold cannot be negative.");
		}
		if (threshold > MAX_DISCOUNT_THRESHOLD) {
			throw new AppException("Discount threshold cannot exceed ₹" + MAX_DISCOUNT_THRESHOLD + ".");
		}
		if (amount < 0) {
			throw new AppException("Discount amount cannot be negative.");
		}
		if (threshold > 0 && amount > threshold * MAX_DISCOUNT_PERCENT / 100) {
			throw new AppException("Discount amount cannot exceed " + MAX_DISCOUNT_PERCENT + "% of the threshold.");
		}
	}

	/**
	 * Validates a delivery partner name.
	 * 
	 * @param name          The name to validate.
	 * @param existingNames Existing partner names.
	 * @throws AppException If validation fails.
	 */
	public static void validatePartnerName(String name, Iterable<String> existingNames) throws AppException {
		if (name == null || name.trim().isEmpty()) {
			throw new AppException("Partner name cannot be empty.");
		}
		name = name.trim();
		if (name.length() > MAX_NAME_LENGTH) {
			throw new AppException("Partner name cannot exceed " + MAX_NAME_LENGTH + " characters.");
		}
		if (!NAME_PATTERN.matcher(name).matches()) {
			throw new AppException("Partner name can only contain letters, numbers, and spaces.");
		}
		for (String existing : existingNames) {
			if (existing.equalsIgnoreCase(name)) {
				throw new AppException("Delivery partner '" + name + "' already exists.");
			}
		}
	}

	/**
	 * Validates that the username is not null, empty, or too long.
	 * 
	 * @param username The username to validate.
	 * @throws AppException If the username is invalid.
	 */
	public static void validateUsername(String username) throws AppException {
		if (username == null || username.trim().isEmpty()) {
			throw new AppException("Username cannot be null or empty.");
		}
		if (username.length() > 50) {
			throw new AppException("Username is too long. Maximum length is 50 characters.");
		}
	}

	/**
	 * Validates that the password is not null, empty, or too weak.
	 * 
	 * @param password The password to validate.
	 * @throws AppException If the password is invalid.
	 */
	public static void validatePassword(String password) throws AppException {
		if (password == null || password.trim().isEmpty()) {
			throw new AppException("Password cannot be null or empty.");
		}
		if (password.length() < 6) {
			throw new AppException("Password must be at least 6 characters long.");
		}
	}

	/**
	 * Sanitizes input by trimming and removing potentially harmful characters.
	 * 
	 * @param input The input string to sanitize.
	 * @return The sanitized string or null if invalid.
	 */
	public static String sanitizeInput(String input) {
		if (input == null) {
			return null;
		}
		// Remove leading/trailing whitespace and basic harmful characters
		return input.trim().replaceAll("[<>\"&]", "");
	}
}