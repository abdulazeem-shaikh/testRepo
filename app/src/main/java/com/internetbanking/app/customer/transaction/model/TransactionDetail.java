package com.internetbanking.app.customer.transaction.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public class TransactionDetail {
	private String customerId;
	Date lastVisited;
	List<Action> transaction;


	public Date getLastVisited() {
		return lastVisited;
	}

	public void setLastVisited(Date lastVisited) {
		this.lastVisited = lastVisited;
	}

	public List<Action> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Action> transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "TransactionDetail [lastVisited=" + lastVisited + ", transaction=" + transaction + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lastVisited == null) ? 0 : lastVisited.hashCode());
		result = prime * result + ((transaction == null) ? 0 : transaction.hashCode());
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
		TransactionDetail other = (TransactionDetail) obj;
		if (lastVisited == null) {
			if (other.lastVisited != null)
				return false;
		} else if (!lastVisited.equals(other.lastVisited))
			return false;
		if (transaction == null) {
			if (other.transaction != null)
				return false;
		} else if (!transaction.equals(other.transaction))
			return false;
		return true;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
