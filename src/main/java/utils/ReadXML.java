package utils;
import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
public class ReadXML 
{
	/*
	 * Method Name : readXML
	 * Description : Reads a XML file
	 */
	public static String readXML(String fPath)
	{
		String input=null;
		try
		{
			FileInputStream fis=new FileInputStream(fPath);
			Document document=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fis);
			XPath xPath=XPathFactory.newInstance().newXPath();
			Node node=(Node)xPath.compile("//input[@id='1']").evaluate(document,XPathConstants.NODE);
			input=node.getTextContent();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return input;
	}
}