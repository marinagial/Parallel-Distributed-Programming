import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class SetPixels {
   public static void main(String args[]) {
	
		String fileNameR = null;
		String fileNameW = null;
		
		 int numThreads = Runtime.getRuntime().availableProcessors();
		
		if (args.length != 2) {
			System.out.println("Usage: java SetPixel <file to read > <file to write");
			System.exit(1);
		}
		fileNameR = args[0];
		fileNameW = args[1];
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(fileNameR));
		} catch (IOException e) {}

		long start = System.currentTimeMillis(); 
		
		
 	 	int block = img.getHeight() / numThreads;
 	 	int from = 0;
 	 	int to = 0;
 	        
 	 	PixelsThread threads[] = new PixelsThread[numThreads];
      
		for (int i=0;i<numThreads;i++) {
			from = i * block;
	 			to = i * block + block;
	 			if (i == (numThreads - 1)) to = img.getHeight();
	 				threads[i]= new PixelsThread(from,to,img);
		}
		
			// wait for threads to terminate            
			for(int i = 0; i < numThreads; i++) {
				try {
					threads[i].join();
				} catch (InterruptedException e) {}
			}
		
	
	    long elapsedTimeMillis = System.currentTimeMillis()-start;
       
		try {
		  File file = new File(fileNameW);
		  ImageIO.write(img, "jpg", file);
		} catch (IOException e) {}
      
		System.out.println("Done...");
		System.out.println("time in ms = "+ elapsedTimeMillis);
   }
}
class PixelsThread extends Thread{
	
	int myfrom;
	int myto;
	BufferedImage img;
	
	
	int redShift = 100;
	int greenShift = 100;
	int blueShift = 100;
	
	// constructor
	public PixelsThread(int from, int to,BufferedImage img1)
	{

		myfrom = from;
		myto = to;
		img=img1;
	}
	
	
	//Thread Code
	
	public void run() {
		
		for (int y = myfrom; y < myto; y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				//Retrieving contents of a pixel
				int pixel = img.getRGB(x,y);
				//Creating a Color object from pixel value
				Color color = new Color(pixel, true);
				//Retrieving the R G B values, 8 bits per r,g,b
				int red = color.getRed();
				int green = color.getGreen();
				int blue = color.getBlue();
				//Modifying the RGB values
				red = (red + redShift)%256;
				green = (green + greenShift)%256;
				blue = (blue + blueShift)%256;
				//Creating new Color object
				color = new Color(red, green, blue);
				//Setting new Color object to the image
				img.setRGB(x, y, color.getRGB());
			}
		}
	}
	
	
}
