package com.common.utilities;

import com.qmetry.qaf.automation.data.BaseDataBean;
import com.qmetry.qaf.automation.util.RandomStringGenerator.RandomizerTypes;
import com.qmetry.qaf.automation.util.Randomizer;

import java.util.Random;

public class RandomDataGenerator extends BaseDataBean {
	Random rand = new Random();
	@Randomizer(type = RandomizerTypes.LETTERS_ONLY, length = 4, prefix = "Auto_")
	private String firstName;
	@Randomizer(type = RandomizerTypes.LETTERS_ONLY, length = 4)
	private String lastName;
	@Randomizer(type = RandomizerTypes.LETTERS_ONLY, length = 4)
	private String middleName;
	@Randomizer(type = RandomizerTypes.LETTERS_ONLY, length = 3)
	private String suffix;
	@Randomizer(type = RandomizerTypes.DIGITS_ONLY, maxval = 30, minval = 20)
	private int age;
	@Randomizer(type = RandomizerTypes.DIGITS_ONLY, length = 9)
	private long phone_mobile;
	@Randomizer(type = RandomizerTypes.LETTERS_ONLY, length = 6)
	private String other;
	@Randomizer(dataset = {"Mr.","Mrs."})
	private String[] salutation;
	@Randomizer(dataset = { "Male" })
	private String gender;
	@Randomizer(type = RandomizerTypes.DIGITS_ONLY, length = 6)
	private long number;
	@Randomizer(type = RandomizerTypes.DIGITS_ONLY, length = 4)
	private long tollFreeNumber;
	

	// created by waseem: to generate title
	public String RandomSalutation() {
		String[] title = { "Dr.", "Mr.", "Mrs.", "Ms.", "Prof." };
		//int rand_title = rand.nextInt(title.length); //dont remove
		int rand_title = 1;
		return title[rand_title];
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String[] getSalutation() {
		return salutation;
	}

	public void setSalutation(String[] salutation) {
		this.salutation = salutation;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getPhone_mobile() {
		return phone_mobile;
	}

	public void setPhone_mobile(Long phone_mobile) {
		this.phone_mobile = phone_mobile;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}
	
	public long getTollFreeNumber() {
		return tollFreeNumber;
	}

	public void setTollFreeNumber(long tollFreeNumber) {
		this.tollFreeNumber = tollFreeNumber;
	}
	
	/**
	 * @author waseem
	 * @param currency -- Opportunity Currency type
	 * @return Currency symbol.
	 */
	public String getCurrencyType(String currency) {
		String CurrencyType = null;
		switch (currency) {
		case "U.S. Dollar":
			CurrencyType = "$";
			break;
		case "Philippine Peso":
			CurrencyType = "₱";
			break;
		}
		return CurrencyType;
	}
	
}
