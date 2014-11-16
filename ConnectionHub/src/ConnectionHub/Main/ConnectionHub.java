package ConnectionHub.Main;

import ConnectionHub.Model.*;
import ConnectionHub.Config.*;

public class ConnectionHub {

	public static void main(String[] args) {
		ConfigFile fileConfig = new ConfigFile();
		fileConfig.setConfigFileName("config.properties");
		String configFilesPath = fileConfig.getProperty("SourceConfigPath");
		AppFile appFile = new AppFile();
		appFile.setPath(configFilesPath);
		appFile.processConfigFile();
		
	}
}

