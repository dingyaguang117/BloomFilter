package co.huohua.BloomFilter;
import java.lang.Math;
import co.huohua.BloomFilter.BitSet;

public class BloomFilter<T extends Object>{
	
	private BitSet bitSet = null;
	private long bitnum;
	private int f_num;
	private int seed[] = new int[]{31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101,103,109,113,127,131,137,139,149,151,157,163,167,173,179,181,191,193,197,199,211,223,227,229,233,239,241,251,257,263,269,271,277,281,283,293,307,311,313,317,331,337,347,349,353,359,367,373,379,383,389,397,401,409,419,421,431,433,439,443,449,457,461,463,467,479,487,491,499,503,509,521,523,541,547,557,563,569,571,577,587,593,599,601,607,613,617,619,631,641,643,647,653,659,661,673,677,683,691,701,709,719,727,733,739,743,751,757,761,769,773,787,797,809,811,821,823,827,829,839,853,857,859,863,877,881,883,887,907,911,919,929,937,941,947,953,967,971,977,983,991,997};
	public BloomFilter(long bitnum,long expect_num) throws Exception
	{
		//xxxx
		if (bitnum > 1L<<33) throw new Exception("bitnum at most 1L<<33");
		
		//xxxx
		this.bitnum = bitnum;
		int efn =  calc_f_num(this.bitnum,expect_num);
		this.f_num = efn > seed.length ? seed.length : efn;
		this.bitSet = new BitSet(this.bitnum);
		
		System.out.println(efn);
		System.out.println(calc_error(this.bitnum,expect_num));
	}
	
	private int calc_f_num(long bitnum,long expect_num)
	{
		return (int)(Math.log(2)*bitnum/expect_num);
	}
	
	private double calc_error(long bitnum,long expect_num)
	{
		return Math.pow(2, -1*Math.log(2)*bitnum/expect_num);
	}
	
	private long hash(String line,int n) 
	{  
		long h = 0;  
		int len = line.length();  
		for (int i = 0; i < len; i++)
		{  
			h = n * h + line.charAt(i);  
		}  
		return check(h);  
	}  
	private long check(long h) {  
		return Math.abs(h % this.bitnum);  
	}  
	
	public void put(T item)
	{
		for(int i=0;i<f_num;++i)
		{
			String s = item.toString();
			bitSet.set(hash(s,seed[i]));
		}
	}
	
	public boolean test(T item)
	{
		for(int i=0;i<f_num;++i)
		{
			String s = item.toString();
			if (!bitSet.get(hash(s,seed[i]))) return false;
		}
		return true;
	}
	
	@Override
	public String toString()
	{
		
		return this.bitSet.toString();
	}
	
	public static void main(String []args) throws Exception
	{
		
		BloomFilter<String> a = new BloomFilter<String>(1L<<33,500000000);
		
		a.put("a");
		System.out.println(a.test("a"));
		System.out.println(a.test("b"));
		System.out.println(a.test("c"));
		
		a.put("afsdfasdfasdfasdfasdfsdgdfhdfyetjkhkylk46568yh.lmndrstgfkhl,jh");
		System.out.println(a.test("afsdfasdfasdfasdfasdfsdgdfhdfyetjkhkylk46568yh.lmndrstgfkhl,jh"));
		System.out.println(a.test("bfsdfasdfasdfasdf"));
		System.out.println(a.test("cfasdfsdghjgkuhlk"));
	}
	
}


