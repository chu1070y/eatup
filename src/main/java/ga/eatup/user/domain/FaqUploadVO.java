package ga.eatup.user.domain;

import lombok.Data;

@Data
public class FaqUploadVO {

	private Integer fno;
	private String fname;
	private String uuid;
	private String upload_path;
	private Integer filetype = 0;
}
