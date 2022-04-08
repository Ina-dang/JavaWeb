package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Attach {
	private String uuuid;
	private String origin;
	private String path;
	private boolean image;
	private int ord;
	private Long bno;
}
