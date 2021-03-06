import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CSVtoXML_java {       
    
    public static void main(String[] args) {
        
            String userstr = null;
            ArrayList<String> user;
            user = new ArrayList<String>();
            
            String categorystr = null;
            ArrayList<String> category;
            category = new ArrayList<String>();
            
            String citystr = null;
            ArrayList<String> city;
            city = new ArrayList<String>();
            
            String poststr = null;            
            ArrayList<String> post;
            post = new ArrayList<String>();
            
            String timestr = null;
            ArrayList<String> time;
            time = new ArrayList<String>();
            
            String linkstr = null;
            ArrayList<String> link;
            link = new ArrayList<String>();                        

            try {                
		String csvFile = "C:\\Path\\To\\CSVFile.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
                            
		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {

			// Use comma as separator
			String[] cldata = line.split(cvsSplitBy);
			
			userstr = cldata[0];
                        if (userstr != null){
                            user.add(userstr);
                        } else {
                            user.add("");
                        }                        
                        
                        citystr = cldata[1];
                        if (citystr != null){
                            city.add(citystr);
                        } else {
                            city.add("");
                        }
                         
                        categorystr =  cldata[2];
                        if (categorystr != null){
                            category.add(categorystr);
                        } else { 
                            category.add("");
                        }
                        
                        poststr =  cldata[3];                        
                        if (poststr != null){
                            post.add("\"" + poststr + "\"");
                        } else {
                            post.add("");
                        }
                            
                        linkstr =  cldata[4];
                        if (linkstr != null){
                            link.add(linkstr);
                        } else {
                            link.add("");
                        }
                            
                        timestr =  cldata[5];
                        if (timestr != null){
                            time.add(timestr);
                        } else {
                            time.add("");
                        }
		}
                
		// Write to XML document
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();            
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
                
                // Root element
		Element rootElement = doc.createElement("CLData");
		doc.appendChild(rootElement);
            
                // Data rows
                for (int j=0; j<(user.size()); j++ ) {
		    
		    if (j == 0 ){
			// Skip column headers
			continue;
		    }
		    
                    Element mcNode = doc.createElement("missedConnection");
                    rootElement.appendChild(mcNode);    
                    
                    Element userNode = doc.createElement("user");
                    userNode.appendChild(doc.createTextNode(user.get(j).toString()));
                    mcNode.appendChild(userNode);
                    
                    Element cityNode = doc.createElement("city");
                    cityNode.appendChild(doc.createTextNode(city.get(j).toString()));
                    mcNode.appendChild(cityNode);
                    
                    Element categoryNode = doc.createElement("category");
                    categoryNode.appendChild(doc.createTextNode(category.get(j).toString()));
                    mcNode.appendChild(categoryNode);
                    
                    Element postNode = doc.createElement("post");
                    postNode.appendChild(doc.createTextNode(post.get(j).toString()));
                    mcNode.appendChild(postNode);
                    
                    Element linkNode = doc.createElement("link");
                    linkNode.appendChild(doc.createTextNode(link.get(j).toString()));
                    mcNode.appendChild(linkNode);
                    
                    Element timeNode = doc.createElement("time");
                    timeNode.appendChild(doc.createTextNode(time.get(j).toString()));
                    mcNode.appendChild(timeNode);                       
                }                               

                // Output content to xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();                
		Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("C:\\Path\\To\\XMLFile.xml"));		
		transformer.transform(source, result);
                
                System.out.println("Successfully created xml file!");
	            
	    } catch (FileNotFoundException ffe) {
                System.out.println(ffe.getMessage());
	    } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            } catch (ParserConfigurationException pce) {
		System.out.println(pce.getMessage());            
            } catch (TransformerException tfe) {
		System.out.println(tfe.getMessage());                
	    }
    }
}
