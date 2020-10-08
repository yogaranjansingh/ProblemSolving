import java.util.HashMap;

class Info{
	int end;
	String number;
	public Info(int end, String number) {
		super();
		this.end = end;
		this.number = number;
	}
	
}

public class WordsToNumberReplacer {
	
	
	public static String replaceWordsWithNumbersInSentence(String input, HashMap<String, Integer> numbersMap, HashMap<String, Integer> tensPrefixMap, HashMap<String, Integer> prefixMap) 
	{
		String str[] = input.toLowerCase().split(" ");
		int n = str.length;
		boolean started = false;
		int endIndex = 0;
		int startIndex=-1;
		HashMap<Integer,Info> infoMap = new HashMap<Integer,Info>();
		String num = "";
		
		for(int i=0;i<n;i++)
		{
			if(present(str[i].trim(), numbersMap, tensPrefixMap, prefixMap))
			{
				if(started==false)
				{
					started=true;
					num = num +" " + str[i].trim();
					startIndex = i;
				}
				else
				{
					num = num + " " +str[i].trim();
				}
						
				if(i==n-1)
				{
					if(started)
					{
						long number  = wordToNumber(num, numbersMap, tensPrefixMap, prefixMap);
						endIndex = i;
						Info info = new Info(endIndex, number+"");
						infoMap.put(startIndex, info);
					}
				}
			}
			else
			{
				if(started)
				{
					long number  = wordToNumber(num, numbersMap, tensPrefixMap, prefixMap);
					endIndex = i;
					Info info = new Info(endIndex, number+"");
					
					infoMap.put(startIndex, info);
					started=false;
					
					startIndex= -1;
					num = "";
					
				}
			}
			
		}
		
		
		
		String ans = "";
		
		for(int i=0;i<n;i++)
		{
			if(! present(str[i], numbersMap, tensPrefixMap, prefixMap))
			ans = ans + " " + str[i];
			else
			{
				Info info = infoMap.get(i);
				ans = ans + " "+ info.number;
				if(info.end==n-1)
					break;
				else
				i = info.end-1;
			}
			
		}
		System.out.println(ans);
		return ans;
	}
	
	public static boolean present(String input, HashMap<String, Integer> numbersMap, HashMap<String, Integer> tensPrefixMap, HashMap<String, Integer> prefixMap)
	{
		if(numbersMap.containsKey(input) || tensPrefixMap.containsKey(input) || prefixMap.containsKey(input))
			return true;
		return false;
	}

	public static void main(String args[]) {
		
		 HashMap<String, Integer> numbersMap = new HashMap<String, Integer>();
		 HashMap<String, Integer> prefixMap = new HashMap<String, Integer>();
		 HashMap<String, Integer> tensPrefixMap = new HashMap<String, Integer>();

			numbersMap.put("zero", 0);
			numbersMap.put("one", 1);
			numbersMap.put("two", 2);
			numbersMap.put("three", 3);
			numbersMap.put("four", 4);
			numbersMap.put("five", 5);
			numbersMap.put("six", 6);
			numbersMap.put("seven", 7);
			numbersMap.put("eight", 8);
			numbersMap.put("nine", 9);
			numbersMap.put("ten", 10);
			numbersMap.put("eleven", 11);
			numbersMap.put("twelve", 12);
			numbersMap.put("thirteen", 13);
			numbersMap.put("fourteen", 14);
			numbersMap.put("fifteen", 15);
			numbersMap.put("sixteen", 16);
			numbersMap.put("seventeen", 17);
			numbersMap.put("eighteen", 18);
			numbersMap.put("nineteen", 19);

			tensPrefixMap.put("twenty", 20);
			tensPrefixMap.put("thirty", 30);
			tensPrefixMap.put("fourty", 40);
			tensPrefixMap.put("fifty", 50);
			tensPrefixMap.put("sixty", 60);
			tensPrefixMap.put("seventy", 70);
			tensPrefixMap.put("eighty", 80);
			tensPrefixMap.put("ninety", 90);

			prefixMap.put("hundred", 100);
			prefixMap.put("thousand", 1000);
			prefixMap.put("million", 1000000);
			
	
		String sentence1 = "there are about three hundred twenty five coins in the basket";
		
		String sentence4 = "there are about three hundred nineteen coins in the basket";
		
		String sentence2 = "There are total of three hundred twenti five coins in the basket one hundred twenty";
		
		String sentence3 = "IPL twenty twenty has been shifted to UAE due to rising COVID- Nineteen cases in India";
		
		replaceWordsWithNumbersInSentence(sentence1, numbersMap, tensPrefixMap, prefixMap);
		
		replaceWordsWithNumbersInSentence(sentence2, numbersMap, tensPrefixMap, prefixMap);
		
		replaceWordsWithNumbersInSentence(sentence4, numbersMap, tensPrefixMap, prefixMap);
		
		replaceWordsWithNumbersInSentence(sentence3, numbersMap, tensPrefixMap, prefixMap);

	}

	
	//twenty twenty
	//
	
	private static long wordToNumber(String input, HashMap<String, Integer> numbersMap, HashMap<String, Integer> tensPrefixMap, HashMap<String, Integer> prefixMap) {
		long sum = 0;
		Integer temp = null;
		Integer prev = 0;
		String[] str = input.toLowerCase().split(" ");
		int last =-1;

		for (String s : str) {

			if (numbersMap.get(s) != null) {
				temp = numbersMap.get(s);

				sum = sum + temp;

				prev = prev + temp;
				last = 0;
			} else if (prefixMap.get(s) != null) {
				if (sum != 0) {
					sum = sum - prev;
				}
				sum = sum + (long) prev * (long) prefixMap.get(s);
				temp = null;
				prev = 0;
				last = 2;
				
			} else if (tensPrefixMap.get(s) != null) {
				temp = tensPrefixMap.get(s);
				sum = sum + temp;

				prev = temp;
				last = 1;
			}

		}

		return sum;
	}

}
