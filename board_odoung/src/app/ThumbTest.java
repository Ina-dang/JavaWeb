package app;

import java.io.File;
import java.nio.file.Files;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

//썸네일 테스트
public class ThumbTest {
	public static void main(String[] args) throws Exception {
		//빌더형식으로 작업
//		File file = new File("C:\\Users\\h\\Desktop\\thumb", "5feb5d6a-85e0-48e3-945f-f12553d0b6d6.jpg");
		Thumbnails
			.of(new File("C:\\Users\\h\\Desktop\\thumb", "img.jpg"))
			.sourceRegion(Positions.TOP_CENTER,200,200)
			.size(200, 200)
//			.forceSize(200, 200) 강제로 줄임
			.toFile(new File("C:\\Users\\h\\Desktop\\thumb", "result.jpg"));
//		String contentType = Files.probeContentType(file.toPath());
//		System.out.println(contentType);
//		System.out.println(contentType.startsWith("image"));
		
	}
}
