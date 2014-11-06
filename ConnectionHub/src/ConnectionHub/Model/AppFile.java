package ConnectionHub.Model;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class AppFile {

	String Path;

	public String getPath() {
		return Path;
	}

	public void setPath(String path) {
		Path = path;
	}
	
	public void processConfigFiles () {
		File configFilesDir = new File(this.Path);
		XmlFilter xmlFilter = new XmlFilter();
		
		if (configFilesDir.isDirectory()) {
			for (File xmlFile :configFilesDir.listFiles(xmlFilter)){
				System.out.println("Reading config file: " + xmlFile.getName());
				
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = null;
				Document xmlDoc = null;
				try {
					dBuilder = dbFactory.newDocumentBuilder();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				}		
				try {
					xmlDoc = dBuilder.parse(xmlFile);
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {					
					e.printStackTrace();
				}
								
				xmlDoc.getDocumentElement().normalize();
				NodeList nList = xmlDoc.getElementsByTagName("configFile");
				
				for (int temp = 0; temp < nList.getLength(); temp++) {
					 
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			 
						Element eElement = (Element) nNode;
			 
						System.out.println("Mask : " + eElement.getElementsByTagName("mask").item(0).getTextContent());
						System.out.println("Type : " + eElement.getElementsByTagName("type").item(0).getTextContent());
						System.out.println("Source : " + eElement.getElementsByTagName("source").item(0).getTextContent());
						System.out.println("Destination : " + eElement.getElementsByTagName("destination").item(0).getTextContent());
						
						//Verify if the type is Folder
						if (eElement.getElementsByTagName("type").item(0).getTextContent() == "FOLDER") {
							NetFile file = new NetFile();
							file.setMask(eElement.getElementsByTagName("mask").item(0).getTextContent());
							file.setSourcePath(eElement.getElementsByTagName("source").item(0).getTextContent());
							file.setDestinationPath(eElement.getElementsByTagName("destination").item(0).getTextContent());
							file.Move();
						}
					}
				}
				
			}
		} else {
			System.out.println("Configuration Files folder incorrect... " + configFilesDir.getAbsolutePath());			
		}
	}
	
}
