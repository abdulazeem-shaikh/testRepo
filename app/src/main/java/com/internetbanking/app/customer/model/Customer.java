package com.internetbanking.app.customer.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Customer {
	private String customerId;
	private String name;
	private double availableBalance;
	private Date lastVisitedAt;
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	 
	public Date getLastVisitedAt() {
		return lastVisitedAt;
	}

	public void setLastVisitedAt(Date lastVisitedAt) {
		this.lastVisitedAt = lastVisitedAt;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	 

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", availableBalance=" + availableBalance
				+ ", lastVisitedAt=" + lastVisitedAt + ", address=" + address + "]";
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

 

	public double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		long temp;
		temp = Double.doubleToLongBits(availableBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((lastVisitedAt == null) ? 0 : lastVisitedAt.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (Double.doubleToLongBits(availableBalance) != Double.doubleToLongBits(other.availableBalance))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (lastVisitedAt == null) {
			if (other.lastVisitedAt != null)
				return false;
		} else if (!lastVisitedAt.equals(other.lastVisitedAt))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	 

}