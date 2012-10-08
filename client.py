import sys
sys.path.append('./gen-py')
 
from bloomfilter import BloomFilterThrift
 
from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
 
try:
    transport = TSocket.TSocket('127.0.0.1', 9090)
    transport = TTransport.TBufferedTransport(transport)
    protocol = TBinaryProtocol.TBinaryProtocol(transport)
    client = BloomFilterThrift.Client(protocol)
    transport.open()
    
    print client.query('dingyaguang117')
 
    transport.close()
 
except Thrift.TException, ex:
    print "%s" % (ex.message)
