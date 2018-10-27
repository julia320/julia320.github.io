package LinkedLists;
import jm.util.*;
import jm.JMC;

public class LinkedPlayList implements JMC
{
	ListNode first;
	ListNode last;
	int size;
	
	public static void main (String[] args)
	{
		LinkedPlayList playlist = new LinkedPlayList();
		
		// add 3 songs to the playlist
		playlist.add("All I Want For Christmas Is You", "C:\\Users\\Julia\\Music\\Audacity\\AllIWantForChristmasIsYou.wav");
		playlist.add("Baby It's Cold Outside", "C:\\Users\\Julia\\Music\\Audacity\\BabyItsColdOutside.wav");
		playlist.add("Winter Wonderland mix", "C:\\Users\\Julia\\Music\\Audacity\\WinterWonderland.wav");
		playlist.print(); // prints these 3 songs
		
		// check delete method
		playlist.delete("Baby It's Cold Outside"); 
		playlist.print(); // prints All I Want and Winter Wonderland
		
		// check add at position method
		playlist.addAtPos("All I Want For Christmas Is You", "Santa Baby", "C:\\Users\\Julia\\Music\\Audacity\\SantaBaby.wav"); // adds Santa Baby after All I Want
		playlist.print(); // prints All I Want, Santa Baby, then Winter Wonderland
		
		// check contains method
		System.out.println(playlist.contains("All I Want For Christmas Is You"));
		System.out.println(playlist.contains("Jingle Bells"));
		
		playlist.play(); // play what was last printed
	}
	
	void add (String title, String path)
	{
		if (size!=0)
		{
			ListNode node = new ListNode(title, path);
			last.next = node;
			last.next.title = title;
			node.prev = last;
			last = last.next;
			size++;
		}
		else
		{
			first = new ListNode(title, path);
			first.title = title;
			last = first;
			size++;
		}
	}
	
	void delete (String songTitle)
	{
		ListNode iter = first;
		for (int x=0; x<this.size; x++) // advances iter until it reaches the song you want to delete
		{
			if (iter.title.equals(songTitle))
				break;
			iter = iter.next;
		}
		if (iter == first) // whatever is in front of first now becomes first
		{
			iter.next.prev = null;
			first = iter.next;
			iter.next = null;
			size--;
		}
		else if (iter == last) // whatever is before last now becomes last
		{
			iter.prev.next = null;
			last = iter.prev;
			iter.prev = null;
			size--;
		}
		else if (size == 1) // only 1 element, deleting it will make the list empty
		{
			first = null;
			last = null;
			size--;
		}
		else
		{
			iter.prev.next = iter.next;
			iter.next.prev = iter.prev;
			iter.prev = null;
			iter.next = null;
			size--;
		}
	}
	
	void addAtPos (String titlePosition, String newSongTitle, String newPath) // titlePosition is the song you want newSong to go after
	{
		if (size!=0) // if list is not empty
		{
			ListNode iter = first;
			for (int x=0; x<this.size; x++)
			{
				if (iter.title.equals(titlePosition)) // if you get to titlePosition
				{
					if (iter == last) // if titlePosition is the last element
					{
						ListNode node = new ListNode (newSongTitle, newPath);
						last.next = node;
						node.next = null;
						node.prev = last;
						last = node;
						break;
					}
					else // if titlePosition is NOT the last element
					{
						ListNode node = new ListNode (newSongTitle, newPath);
						node.next = iter.next;
						iter.next = node;
						iter.next.prev = node;
						node.prev = iter;
						break;
					}
				}
				else // if you haven't reached titlePosition yet
					iter = iter.next;
			}
			size++;
		}
		else // if size = 0, list is empty
		{
			first = new ListNode(newSongTitle, newPath);
			first.title = newSongTitle;
			last = first;
			size++;
		}
	}
	
	boolean contains (String songTitle)
	{
		boolean contains = false;
		ListNode iter = first;
		for (int x=0; x<this.size; x++) // goes through the list
		{
			if (iter.title.equals(songTitle)) // if an element in the list equals the title you check for, make it true
				contains = true;
			iter = iter.next;
		}
		return contains;
	}
	
	void play ()
	{
		// create a float[] the size of all songs
		int newSize = 0;
		ListNode sizeCount = first;
		for (int x=0; x<this.size; x++)
		{
			newSize += sizeCount.songData.length;
			sizeCount = sizeCount.next;
		}
		float[] allSongData = new float[newSize];
		
		// copy the data from each song over to the cumulative float
		int count = 0;
		ListNode iter = first;
		for (int i=0; i<this.size; i++)
		{
			for (int j=0; j<iter.songData.length; j++)
			{
				allSongData[count] = iter.songData[j];
				count++;
			}
			iter = iter.next;
		}
		
		// make it into one thing that can be played
		// Write.audio (float[] songData, String filename, int numChan, int SamplesPerSec, int BitsPerSample)
		Write.audio (allSongData, "mix.wav", 2, 44100, 16);
		Play.au("mix.wav");
	}
	
	void print() // print out the titles of each element to see what's in the list without having to play it
	{
		ListNode iter = first;
		for (int x=0; x<this.size; x++)
		{
			System.out.println(iter.title);
			iter = iter.next;
		}
		System.out.println();
	}
}