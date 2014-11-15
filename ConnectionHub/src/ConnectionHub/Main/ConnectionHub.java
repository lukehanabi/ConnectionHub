package ConnectionHub.Main;

import ConnectionHub.Model.*;
import ConnectionHub.Config.*;

public class ConnectionHub {

	public static void main(String[] args) {
		ConfigFile fileConfig = new ConfigFile();
		fileConfig.setConfigFileName("config.properties");
	    //get folder configuration DIFF
		String configFilesPath = fileConfig.getProperty("SourceConfigPath");
		AppFile appFile = new AppFile();
		//Hola Fede
		appFile.setPath(configFilesPath);
		appFile.processConfigFiles();
		
	}
}

