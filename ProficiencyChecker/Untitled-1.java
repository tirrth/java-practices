//Rutvi Shah
//Lab 8 Player class


public class player 
{

	private int yearInducted;
	private int id;
	private int yearsPlayed;
	private float battingAvg;
	private float averageYearsPlayed;
	private String playerName;
	private String teamName;
	
	
	
	public void setyearInducted(int YI)
	{
		yearInducted = YI;
	}
	
	public int getyearInducted()
	{
		return yearInducted;
	}
	
	public void setid(int ID)
	{
		id = ID;
	}
	
	public int getid()
	{
		return id;
	}
	
	public void setyearsPlayed(int YP)
	{
		yearsPlayed = YP;
	}
	
	public int getyearsPlayed()
	{
		return yearsPlayed;
	}
	
	public void setbattingAvg(float BA)
	{
		battingAvg = BA;
	}
	
	public float getbattingAvg()
	{
		return battingAvg;
	}
	
	public void setaverageYearsPlayed(float aYP)
	{
		averageYearsPlayed = aYP;
	}
	
	public float getaverageYearsPlayed()
	{
		return averageYearsPlayed;
	}
	
	public void setplayerName(String PN)
	{
		playerName = PN;
	}
	
	public String getplayerName()
	{
		return playerName;
	}
	
	public void setteamName(String TN)
	{
		teamName = TN;
	}
	
	public String getteamName()
	{
		return teamName;
	}
	
	
	
	public String toString()
	{
		String str;
		str=String.format("%8.2f%8.2f%8.2f%8.2f%8.2f%8.2f%8.2f\n", yearInducted, id, yearsPlayed, battingAvg, averageYearsPlayed, playerName, teamName);
		return str;
	}
	
	
}//end class player