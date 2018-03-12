
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Task1 {
	
	public static void mergeXMLFiles() {
		/*Creating DocumentBuilders to parse data from xml files*/
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		Document first = null;
		Document second = null;
		
		try {
			/*Parses data from xml files to new DOM files*/
			db = dbf.newDocumentBuilder();
			first = db.parse(new File("First.xml")); //first DOM file
			second = db.parse(new File("Second.xml")); //second DOM file
			
			/*Feeding data from DOM to a NodeList*/
			NodeList firstnodelist = first.getElementsByTagName("person");
            Node fsalarynode = first.importNode(second.getElementsByTagName("salary").item(0), true);
            Node fpensionnode = first.importNode(second.getElementsByTagName("pension").item(0), true);
            Node ssalarynode = first.importNode(second.getElementsByTagName("salary").item(1), true);
            Node spensionnode = first.importNode(second.getElementsByTagName("pension").item(1), true);
		
            firstnodelist.item(0).appendChild(fsalarynode);
            firstnodelist.item(0).appendChild(fpensionnode);
            firstnodelist.item(1).appendChild(ssalarynode);
            firstnodelist.item(1).appendChild(spensionnode);
            System.out.println("Node list has been created and filled with data!");
		
            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer tf = tff.newTransformer();
            tf.setOutputProperty(OutputKeys.INDENT, "yes");  

            DOMSource source = new DOMSource(first);
            StreamResult sr = new StreamResult(new StringWriter());
            tf.transform(source, sr); 

            Writer output = null ;      
            output = new BufferedWriter(new FileWriter("persondata.xml"));
            System.out.println("persondata.xml file has been created!");
            
            String datapasser = sr.getWriter().toString();

            output.write(datapasser);

            output.close();
            System.out.println("Two files has been merged Successfully!!");
		
		}catch (ParserConfigurationException e1) {
	        e1.printStackTrace();
	    } catch (SAXException e2) {
	        e2.printStackTrace();
	    } catch (IOException e3) {
	        e3.printStackTrace();
	    } catch (TransformerException e4) {
	        e4.printStackTrace();
	    }
			
	}
	public static void loadXMLIntoDB() {
		try {
			/*Creating Connection with the database PERSONDATA*/
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/persondata","root","root"); 
			if(con!=null)
				System.out.println("Successfully connected with the persondata DB!");
			
			/*Creating a table with attributes*/ 
			con.createStatement().execute("CREATE TABLE persondata"
					+ "(name varchar(25),"
		    		+ "address varchar(50),"
		    		+ "phno varchar(15),"
		    		+ "salary integer,"
		    		+ "pension integer);");
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			File file = new File("persondata.xml");
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("person");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String PName = eElement.getAttribute("name");
					String PAddress = eElement.getElementsByTagName("address").item(0).getTextContent();
					String PPhNo = eElement.getElementsByTagName("phonenumber").item(0).getTextContent();
					String PSalary = eElement.getElementsByTagName("salary").item(0).getTextContent();
					String PPension = eElement.getElementsByTagName("pension").item(0).getTextContent();
					System.out.println("Person Name : " + PName);
					System.out.println("Address : " + PAddress);
					System.out.println("Phone Number : " + PPhNo);
					System.out.println("Salary : " + PSalary);
					System.out.println("Pension : " + PPension);
					PSalary = PSalary.substring(1);
					PPension = PPension.substring(1);
					int salary = Integer.parseInt(PSalary);
					int pension = Integer.parseInt(PPension);
					
					con.createStatement().execute("insert into persondata values("
							+ "'" + PName + "',"
							+ "'" + PAddress + "',"
							+ "'" + PPhNo + "',"
							+ salary + ","
							+ pension + ");");
				}
			}
			
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			System.err.println("Error in connection : " + e);
		} catch(ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	public static void main(String[] args)  {
		System.out.println("Hello World!!");
		Task1.mergeXMLFiles();
		Task1.loadXMLIntoDB();
	}

}
