package ConnectionHub.Config;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

public class ConfigFile {
	String configFileName;

	public String getConfigFileName() {
		return configFileName;
	}

	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}
	
	public String getProperty(String oneProperty) { 
		Properties Prop = new Properties();
		
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(this.configFileName);
        try {
			Prop.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Prop.getProperty(oneProperty);
	}
}
