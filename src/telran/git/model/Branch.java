package telran.git.model;

import java.io.Serial;
import java.io.Serializable;

public class Branch implements Serializable{

	@Serial
	private static final long serialVersionUID = 1L;
	public String branchName;
	public String commitName;
}