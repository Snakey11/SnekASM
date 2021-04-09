
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;
import java.util.ArrayList;

public class Sym
{
	public static void main(String[] args) // args has a list of .sym file names to combine.
	{
		ArrayList<Entry> entries = new ArrayList<Entry>();
		try
		{
			for ( int i = 0 ; i < args.length ; i++ )
			{
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(new File(args[i]));
				while ( sc.hasNextLine() )
				{
					String First = sc.nextLine();
					if ( First.length() != 0 && First.charAt(0) != ';' )
					{
						if ( First.charAt(First.indexOf(' ')+1) == '.' )
						{
							// There is a .* (.thumb, etc).
							String Offset = sc.next();
							String Loc = sc.nextLine();
							entries.add(new Entry(First.split(" ")[1],Offset,Loc));
						}
						else
						{
							// There is no .*. Therefore, First has both the offset and loc.
							String[] Params = First.split(" ");
							entries.add(new Entry(null,Params[0],Params[1]));
						}
					}
				}
			}
			// At this point, entries should have all entries to add to a new .sym file.
			// Let's just convert the first entry of args to a new .sym file.
			Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(args[0]))));
			for ( int i = 0 ; i < entries.size() ; i++ )
			{
				w.write(entries.get(i).getFormatted() + "\n");
			}
			w.close();
		}
		catch(FileNotFoundException e)
		{
			System.err.println("Error in finding files.");
			e.printStackTrace();
		}
		catch(IOException e)
		{
			System.err.print("Error in writing to " + args[0]);
			e.printStackTrace();
		}
	}
}
