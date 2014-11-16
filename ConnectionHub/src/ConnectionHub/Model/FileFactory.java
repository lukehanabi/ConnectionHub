package ConnectionHub.Model;

import org.w3c.dom.Element;

public class FileFactory {
	
	public static MoveFile getMoveFile (Element element) {
		
		String fileType = element.getElementsByTagName("type").item(0).getTextContent();
		
		if (fileType.equalsIgnoreCase("LOCAL")) {
			LocalFile local = new LocalFile();
			local.setMask(element.getElementsByTagName("mask").item(0).getTextContent());
			local.setSourcePath(element.getElementsByTagName("source").item(0).getTextContent());
			local.setDestinationPath(element.getElementsByTagName("destination").item(0).getTextContent());
			return local;
		}
		
		if (fileType.equalsIgnoreCase("FTP")) {
			FTPFile ftpfile = new FTPFile();
			ftpfile.setMask(element.getElementsByTagName("mask").item(0).getTextContent());
			ftpfile.setSourcePath(element.getElementsByTagName("source").item(0).getTextContent());
			ftpfile.setDestinationPath(element.getElementsByTagName("destination").item(0).getTextContent());
			ftpfile.setHost(element.getElementsByTagName("host").item(0).getTextContent());
			ftpfile.setUser(element.getElementsByTagName("user").item(0).getTextContent());
			ftpfile.setPassword(element.getElementsByTagName("password").item(0).getTextContent());
			return ftpfile;
		}
		
		return null;
	}
}
