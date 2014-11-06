package ConnectionHub.Model;

import java.io.File;

public class NetFile implements fileMask{

	String sourcePath;
	String destinationPath;
	String mask;
	
	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public String getDestinationPath() {
		return destinationPath;
	}

	public void setDestinationPath(String destinationPath) {
		this.destinationPath = destinationPath;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	@Override
	public void Move() {
		File[] filesFound;
						
		File fileSearch = new File(this.sourcePath);
		if (fileSearch.exists()) {
			MaskFilter filter = new MaskFilter();
			filter.Mask = this.mask;
			filesFound = fileSearch.listFiles(filter);
						
			for (File f : filesFound) {
				System.out.println(f.getName());
				f.renameTo(new File(this.destinationPath + "/" + f.getName()));
				System.out.println("File moved to " + this.destinationPath);
			}
		}		
	}

}
