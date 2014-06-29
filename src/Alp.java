import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Alp {

	/**
	 * @param args
	 */
	public static boolean canBePalin(String s)
	{
		//
		HashMap<Character, Integer> hm = 
				new HashMap<Character, Integer>();
		for (int i = 0; i < s.length(); i++)
		{
			hm.put(s.charAt(i), hm.containsKey(s.charAt(i)) 
					? hm.get(s.charAt(i)) + 1 : 1); 
		}
		ArrayList<Character> al = new ArrayList<Character>(hm.keySet());
		if (s.length() % 2 == 0)
		{
			
			for (int j = 0; j < al.size(); j++)
			{
				if (hm.get(al.get(j)) % 2 != 0)
					return false;
			}
		}
		else
		{
			
			int cnt = 0;
			for (int j = 0; j < al.size(); j++)
			{
				if (hm.get(al.get(j)) % 2 != 0)
					cnt++;
			}
			if (cnt > 1)
				return false;
		}
		return true;
	}
	public static boolean chBalance(String left, String right)
	{
		HashMap<Character, Integer> hmL = 
				new HashMap<Character, Integer>();
		for (int i = 0; i < left.length(); i++)
		{
			hmL.put(left.charAt(i), hmL.containsKey(left.charAt(i)) 
					? hmL.get(left.charAt(i)) + 1 : 1); 
		}
		
		HashMap<Character, Integer> hmR = 
				new HashMap<Character, Integer>();
		for (int i = 0; i < right.length(); i++)
		{
			hmR.put(right.charAt(i), hmR.containsKey(right.charAt(i)) 
					? hmR.get(right.charAt(i)) + 1 : 1); 
		}
		ArrayList<Character> alL = new ArrayList<Character>(hmL.keySet());
		ArrayList<Character> alR = new ArrayList<Character>(hmR.keySet());
		
		for(int i = 0; i < alL.size(); i++)
		{
			if(!alR.contains(alL.get(i)))
				return false;
			if(hmR.get(alL.get(i)) < hmL.get(alL.get(i)))
				return false;
		}
		return true;
	}

	public static char[] revArr(char[] ch1, char[] ch2)
	{

		String s2 = String.valueOf(ch2);		
		for (int i = 0; i < ch1.length; i++)
		{
//			System.out.println(i);
			ch2 = swap(ch2, s2.lastIndexOf(ch1[i]), ch2.length - 1 - i);
		}
		return ch2;
	}
	public static char[] swap(char[] ch, int i, int j)
	{
		char tmp = ch[i];
		ch[i] = ch[j];
		ch[j] = tmp;
		return ch;
	}
	public static char[] ordStr(String orig, String chg)
	{
		//NMZFXY
		//DDOOAA
		//NMNM
		char[] chOr = orig.toCharArray();
		char[] chChg = chg.toCharArray();
		outer:
		while (true)
		{
			inner:
			for (int i = 0; i < chOr.length - 1; i++)
			{
				if (chOr[i] == chChg[i])
					continue inner;
				int min = Integer.MAX_VALUE;
				int ind = i;
				int j;
				boolean fnd = false;
//				char tmp = 1;
				for (j = i; j < chChg.length - 1; j++)
				{
					if (chOr[i] == chChg[j])
						continue inner;
					int diff = chChg[j] - chOr[i];
//					System.out.println(diff + ", 1: " + chChg[j] + ", 2: " + chOr[i]);
					if (diff >= 0)
					{
						if (diff < min)
						{ 
							min = diff;
							ind = j;
							fnd = true;
						}
					}
				}
				if (fnd == false)
				{
					System.out.println("Unsuccessful attempt!");
					System.exit(0);
//					System.out.println("This method cannot be performed");
//					break outer;
					
				}
//				tmp = chChg[ind];
				chChg = swap(chChg, i, ind); 
//				System.out.println("1: " + chChg[ind] + ", 2: " + chOr[i]);
//				if (chOr[i] == tmp)
 				if (chChg[i + 1] == chOr[i + 1])
				{
 					System.out.println("succ! " + chChg[i + 1]);
					continue inner; 
				}
				else
				{
//					StringBuilder sb = new StringBuilder(chChg.size() - i);
//					for (Character c : chars)
//					    sb.append(c);
//					String result = sb.toString();
//					char[] tmpC = sort_((char[])Arrays.asList(chChg).subList(i + 1, chChg.length));
					break outer;
				}
			}
		}
		return chChg;
	}
	public static char[] sort_(char[] arr)
	{
		Arrays.sort(arr);
		return arr;
	}
	//NMZFXY
	//DDOOAA
	//NMNM
	public static void mainFnc(String s)
	{
		if (!canBePalin(s))
		{
			System.out.println("Cannot be a palindrome!");
			return;
		}
		
		int piv = s.length() % 2 == 0 ? s.length() / 2 - 1 : s.length() / 2;
		for (int i = piv; i >= 0; i--)
		{
			if (!chBalance(s.substring(0, i + 1), s.substring(i + 1, s.length())))
				continue;
			else
			{
				String tmp = String.valueOf(revArr(s.substring(0, i + 1).toCharArray(), s.substring(i + 1, s.length()).toCharArray()));
			
				StringBuilder sb = new StringBuilder(s.substring(0, i + 1)).reverse();
				System.out.print(s.substring(0, i + 1));
//				ordStr(s.substring(i + 1, ), chg)
				System.out.print(sb);
//				mainFnc();
			}
		}

		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(chBalance("aaaaa", "aaaacca"));
		
		System.out.println(revArr("NMO".toCharArray(), "ANMKOJS".toCharArray()));
		
		System.out.println(String.valueOf(ordStr("MMOFXY", "MMEZAA")));
	}

}
