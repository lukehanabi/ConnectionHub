package ConnectionHub.Model;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class AppFile {

	String Path;

	public String getPath() {
		return Path;
	}

	public void setPath(String path) {
		Path = path;
	}
	
	public void processConfigFile () {
		File configFile = new File(this.Path + "files.config.xml");
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		Document xmlDoc = null;
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}		
		try {
			xmlDoc = dBuilder.parse(configFile);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {					
			e.printStackTrace();
		}
		
		xmlDoc.getDocumentElement().normalize();
		Element docEle = xmlDoc.getDocumentElement();

		//get a nodelist of elements
		NodeList nl = docEle.getElementsByTagName("configFile");

			for(int i = 0 ; i < nl.getLength();i++) {
		
				//get the employee element
				Element eElement = (Element)nl.item(i);
				
				System.out.println("Mask : " + eElement.getElementsByTagName("mask").item(0).getTextContent());
				System.out.println("Type : " + eElement.getElementsByTagName("type").item(0).getTextContent());
				System.out.println("Source : " + eElement.getElementsByTagName("source").item(0).getTextContent());
				System.out.println("Destination : " + eElement.getElementsByTagName("destination").item(0).getTextContent());
		
				FileFactory.getMoveFile(eElement).move();
		}
	}
	
}
