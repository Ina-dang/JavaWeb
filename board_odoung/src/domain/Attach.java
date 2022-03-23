package domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
	
	public String getParams() throws UnsupportedEncodingException {
		return "?uuid=" + uuid + "&path=" +URLEncoder.encode(path, "utf-8") + "&origin=" + URLEncoder.encode(origin, "utf-8");
	}
}
