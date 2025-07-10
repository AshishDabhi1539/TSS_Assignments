package com.tss.model;

public class FixedDeposit {
	private int accountNumber;
    private String name;
    private double principal;
    private int duration;
    private FestivalType festival;
    
	public FixedDeposit(int accountNumber, String name, double principal, int duration, FestivalType festival) {
		super();
		this.accountNumber = accountNumber;
		this.name = name;
		this.principal = principal;
		this.duration = duration;
		this.festival = festival;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public FestivalType getFestival() {
		return festival;
	}

	public void setFestival(FestivalType festival) {
		this.festival = festival;
	}
	
	public double getInterestRates() {
        switch (festival) {
            case NEWYEAR: return 8.0;
            case DIWALI: return 8.5;
            case HOLI: return 7.5;
            default: return 6.5;
        }
    }

    public double calculateSimpleInterest() {
        return (principal * duration * getInterestRates()) / 100;
    }

	@Override
	public String toString() {
		return "FixedDeposit [accountNumber=" + accountNumber + ", name=" + name + ", principal=" + principal
				+ ", duration=" + duration + ", festival=" + festival + "]";
	}
    
    
}
