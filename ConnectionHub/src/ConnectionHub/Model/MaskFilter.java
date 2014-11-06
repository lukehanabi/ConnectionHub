package ConnectionHub.Model;

import java.io.File;
import java.io.FilenameFilter;

public class MaskFilter implements FilenameFilter {
	
	String Mask;

	public String getMask() {
		return Mask;
	}

	public void setMask(String mask) {
		Mask = mask;
	}

	@Override
	public boolean accept(File dir, String name) {
		if (name.startsWith(this.Mask)) { 
			return true;
		}
		return false;
	}
}
