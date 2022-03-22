package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Attach {
	private String uuid;
	private String origin;
	private String path;
	private boolean image;
	private int ord;
	private Long bno;
	
	public String getParams() {
		return "?uuid=" + uuid + "&path=" + path + "&origin=" + origin;
	}
}
