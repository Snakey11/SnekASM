
public class Entry
{
	private String Type, Offset, Name; // .thumb etc, location, function/data loc name.
	public Entry(String type, String offset, String name)
	{
		if ( type != null)
		{
			Type = new String(type);
		}
		else
		{
			Type = null;
		}
		Offset = new String(offset);
		if ( Integer.decode("0x" + Offset) % 0x2 != 0 )
		{
			// If the top bit is set, unset it.
			Offset = "0" + Integer.toHexString(Integer.decode("0x" + Offset)-0x1);
		}
		Name = new String(name);
	}
	public String getFormatted()
	{
		if ( Type == null )
		{
			return Offset + " " + Name;
		}
		else
		{
			return Offset + " " + Type + "\n" + Offset + " " + Name;
		}
	}
}
