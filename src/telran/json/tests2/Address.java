package telran.json.tests2;

public class Address {
	String city;
    String street;
    int house;
    SubAddress subAddress;

    public Address() {}
    
	public Address(String city, String street, int house, SubAddress subAddress) {
		super();
		this.city = city;
		this.street = street;
		this.house = house;
		this.subAddress = subAddress;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + house;
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((subAddress == null) ? 0 : subAddress.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Address))
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (house != other.house)
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (subAddress == null) {
			if (other.subAddress != null)
				return false;
		} else if (!subAddress.equals(other.subAddress))
			return false;
		return true;
	}
    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house=" + house +
                ", subAddress=" + subAddress +
                '}';
    }
}
