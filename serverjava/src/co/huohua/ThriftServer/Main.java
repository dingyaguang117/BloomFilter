package co.huohua.ThriftServer;

import org.apache.thrift.TProcessor; 
import org.apache.thrift.protocol.TBinaryProtocol; 
import org.apache.thrift.protocol.TBinaryProtocol.Factory; 
import org.apache.thrift.server.TServer; 
import org.apache.thrift.server.TThreadPoolServer; 
import org.apache.thrift.transport.TServerSocket; 
import org.apache.thrift.transport.TTransportException; 


import co.huohua.ThriftServer.BloomFilterThriftImpl;

public class Main {
	
	private void run(long bitnum,long expectnum) throws Exception
	{
		//构造transport类
		TServerSocket transport = new TServerSocket(9090);
		//构造 处理器
		TProcessor processor = new BloomFilterThrift.Processor(new BloomFilterThriftImpl(bitnum,expectnum));
		//构造传输协议工厂类
		Factory proFactory = new TBinaryProtocol.Factory(); 
		//构造 Server的构造函数需要的参数
		TThreadPoolServer.Args argss = new TThreadPoolServer.Args(transport);
		argss.inputProtocolFactory(proFactory);
		argss.outputProtocolFactory(proFactory);
		argss = argss.processor(processor);
		argss.maxWorkerThreads = 10;
		
		TServer server = new TThreadPoolServer(argss);
		System.out.println("server running...");
		server.serve();
	}
	
	
	public static void main(String[] args) throws Exception {
		
		if (args.length != 3)
		{
			System.out.println("java -jar xxx.jar bitnum expectnum");
			return;
		}
		long bitnum = Integer.parseInt(args[1]);
		long expectnum = Integer.parseInt(args[2]);
		
		Main main = new Main();
		main.run(bitnum,expectnum);
	}

}
