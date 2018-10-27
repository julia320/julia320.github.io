package LinkedLists;
import jm.util.Read;

public class ListNode 
{
	String title;
	String path;
	float[] songData;
	ListNode prev;
	ListNode next; 
	
	ListNode (String title, String path)
	{
		// build a new list node
		this.title = title;
		this.path = path;
		songData = Read.audio(path);
		prev = null;
		next = null;
	}
}
