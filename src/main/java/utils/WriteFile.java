package utils;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class WriteFile 
{
	/*
	 * Method Name : writeFile
	 * Description : Writes data to a file
	 */
	public static void writeFile(String string)
	{
		try
		{
			LocalDateTime localDateTime=LocalDateTime.now();
			DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd-MM-yy-HH");
			FileOutputStream fos=new FileOutputStream("./src/test/resources/TestFlowOutputs"+localDateTime.format(dtf)+".txt",true);
			fos.write(string.getBytes());
			fos.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}