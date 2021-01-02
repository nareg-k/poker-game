package poker;

public class Cards{
private String suit;
private String face;
private int value; 

public Cards(String s, String f,int v)
{
suit=s;  
face=f;
value=v;
}
public String getSuit()
{
return suit;
}
public String getface()
{
return face;
}
public int getValue()
{
return value;
}
public String toString(){
return face+"of"+suit;
}
}
