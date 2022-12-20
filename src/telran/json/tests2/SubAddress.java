package telran.json.tests2;

public class SubAddress {
    String name;
    String value;

    public SubAddress() {}
    
    public SubAddress(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof SubAddress))
			return false;
		SubAddress other = (SubAddress) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
    public String toString() {
        return "SubAddress{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
