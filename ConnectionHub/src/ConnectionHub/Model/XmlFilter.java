package ConnectionHub.Model;

import java.io.File;
import java.io.FilenameFilter;

public class XmlFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		if (name.endsWith(".config.xml")) { 
			return true;
		}
		return false;
	}

}
