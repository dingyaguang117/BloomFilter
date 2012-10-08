package co.huohua.BloomFilter;

public class BitSet {
	
	private long length;
	private byte[] data;
	private int k = 8;
	
	public BitSet(long size)
	{
		length = size;
		int t = (int)(size/8);
		if (size%8 >0) t+=1;
		data = new byte[t];
	}
	
	public void set(long pos)
	{
		int p = (int)(pos/k);
		int pp = (int)(pos%k);
		data[p] |= 1<<pp;
	}
	public boolean get(long pos)
	{
		int p = (int)(pos/k);
		int pp = (int)(pos%k);
		return (data[p] & (1<<pp)) > 0;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(0|(1<<31));	
		
		// TODO Auto-generated method stub
		BitSet bs = new BitSet(1000000000L);
		
		bs.set(1000);
		System.out.println(bs.get(1000));
		System.out.println(bs.get(1001));
		System.out.println(bs.get(999));
		System.out.println(bs.get(10000));
		
		bs.set(200000000);
		System.out.println(bs.get(200000000));
		System.out.println(bs.get(199999999));

	}

}
