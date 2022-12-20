package telran.json.tests;

import java.time.LocalDate;

import telran.json.annotations.JsonField;
import telran.json.annotations.JsonFormat;

public class Company {
	@JsonField("companyName")
    String name;
    String address;
    @JsonField("companyCountry")
    String country;
    @JsonField("open_date")
    @JsonFormat("dd MM yyyy")
    LocalDate date;
    
	public Company() {
		super();
	}

    public Company(String name, String address, String country, LocalDate date) {
		super();
		this.name = name;
		this.address = address;
		this.country = country;
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Company))
			return false;
		Company other = (Company) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}