package co.huohua.ThriftClient;


import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import co.huohua.ThriftServer.BloomFilterThrift;

public class Main {

	private void run() throws TException
	{
		TSocket transport = new TSocket("127.0.0.1",9090);
		TProtocol protocol = new TBinaryProtocol(transport);
		BloomFilterThrift.Client client = new BloomFilterThrift.Client(protocol);
		
		transport.open();
		
		System.out.println(client.query("dingyaguang117"));
		client.add("dingyaguang117");
		System.out.println(client.query("dingyaguang117"));
		System.out.println(client.queryAndAdd("abc"));
		System.out.println(client.query("abc"));
		
		
		transport.close();
		
	}
	
	public static void main(String[] args) throws TException {
		Main main = new Main();
		main.run();
	}

}
