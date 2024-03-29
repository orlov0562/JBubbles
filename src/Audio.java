import java.io.File;  
import java.io.IOException;

import javax.sound.sampled.AudioFormat;  
import javax.sound.sampled.AudioInputStream;  
import javax.sound.sampled.AudioSystem;  
import javax.sound.sampled.DataLine;  
import javax.sound.sampled.SourceDataLine;  
  
public class Audio implements Runnable, Config  
{

public void run()  
{  
String filename="beep.wav";
try {
	filename = new File(".").getCanonicalPath()+System.getProperty("file.separator")+"media"+System.getProperty("file.separator")+SOUND_FILE_NAME;
} catch (IOException e2) {
	// TODO Auto-generated catch block
	e2.printStackTrace();
}  
  
int EXTERNAL_BUFFER_SIZE = 524288;  
  
File soundFile = new File(filename);  


if (!soundFile.exists())  
{  
 System.err.println("Wave file not found: " + filename);  
 return;  
}  
  
AudioInputStream audioInputStream = null;  
try  
{  
 audioInputStream = AudioSystem.getAudioInputStream(soundFile);  
}  
catch(Exception e)  
{  
 e.printStackTrace();  
 return;  
}  
  
AudioFormat format = audioInputStream.getFormat();  
  
SourceDataLine auline = null;  
  
//Describe a desired line  
DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);  
  
try  
{  
 auline = (SourceDataLine) AudioSystem.getLine(info);  
  
 //Opens the line with the specified format,  
 //causing the line to acquire any required  
 //system resources and become operational.  
 auline.open(format);  
}  
catch(Exception e)  
{  
 e.printStackTrace();  
 return;  
}  
  
 //Allows a line to engage in data I/O  
auline.start();  
  
int nBytesRead = 0;  
byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];  
  
try  
{  
 while (nBytesRead != -1)  
 {  
  nBytesRead = audioInputStream.read(abData, 0, abData.length);  
  if (nBytesRead >= 0)  
  {  
   //Writes audio data to the mixer via this source data line  
   //NOTE : A mixer is an audio device with one or more lines  
   auline.write(abData, 0, nBytesRead);  
  }  
 }  
}  
catch(Exception e)  
{  
 e.printStackTrace();  
 return;  
}  
finally  
{  
 //Drains queued data from the line  
 //by continuing data I/O until the  
 //data line's internal buffer has been emptied  
 auline.drain();  
  
 //Closes the line, indicating that any system  
 //resources in use by the line can be released  
 auline.close();  
}  
}  
}  