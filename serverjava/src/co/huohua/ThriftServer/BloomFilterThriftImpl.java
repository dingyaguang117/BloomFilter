package co.huohua.ThriftServer;

import org.apache.thrift.TException;

import co.huohua.BloomFilter.BloomFilter;


public class BloomFilterThriftImpl implements BloomFilterThrift.Iface {
	BloomFilter<String> bf = null;
	
	
	public BloomFilterThriftImpl(long bit_num,long expect_num) throws Exception
	{
		bf = new BloomFilter<String>(bit_num,expect_num);
	}
	
	@Override
	public boolean query(String s) throws TException {
		// TODO Auto-generated method stub
		return bf.test(s);
	}

	@Override
	public void add(String s) throws TException {
		bf.put(s);
	}

	@Override
	public boolean queryAndAdd(String s) throws TException {
		boolean r = bf.test(s);
		bf.put(s);
		return r;
	}

	@Override
	public void store() throws TException {
		// TODO Auto-generated method stub
		
	}

	
	
}
