import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Decryption {
	public static void main(String[] args) {
		// TODO READ FROM PLOTTED GRAPH AND WRITE THE POINTS TO A FILE
		  try{
				String strLine = "";
				String[] coordinates = new String[100];	
				int i=0;
				FileReader fr = new FileReader("XYLine.dat");
				BufferedReader br = new BufferedReader(fr);
				while((strLine = br.readLine()) != null){
		            coordinates[i++] = strLine;
		        }
				String decrypt = "";
				for(int j=0;j<i;j++)
				{
					String[] num = coordinates[j].split("  ");
					double d1 = Double.parseDouble(num[0]);
					double d2 = Double.parseDouble(num[1]);
					int x = ((Long)(Math.round(d1))).intValue();
					int y = ((Long)(Math.round(d2))).intValue();
					if(x<0)
					{
						x = x * -1;
					}
					if(y < 0)
					{
						y = y * -1;
					}
					int asc = (x * (x+1))/2 + y;
					decrypt += Character.toString((char)asc);
				}
				 
				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

					File file = new File("secret.txt");
					file.createNewFile();
					FileWriter fw = new FileWriter(file);
					BufferedWriter bw = new BufferedWriter(fw);
					System.out.println("adding contents to file...");
					bw.write(decrypt+"\n");
					System.out.println("Done");

					if (bw != null)
						bw.close();

					if (fw != null)
						fw.close();
		       }
				catch(Exception e)
				{
					e.printStackTrace();
				}		
   }
}
