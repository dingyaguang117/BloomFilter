import sys
from pymongo.connection import Connection


sys.path.append('./gen-py')
 
from bloomfilter import BloomFilterThrift
 
from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol




def GetOne():
    con = Connection("219.232.242.222:20010")
    col = con.friend.weiboContent
    for one in col.find({},{'weiboId':1}):
        yield one['weiboId']

try:
    transport = TSocket.TSocket('127.0.0.1', 9090)
    transport = TTransport.TBufferedTransport(transport)
    protocol = TBinaryProtocol.TBinaryProtocol(transport)
    client = BloomFilterThrift.Client(protocol)
    transport.open()
    
    total = 0
    err   = 0
    for id in GetOne():
        b = client.queryAndAdd(id)
        if b:
            err += 1
        total += 1
        if total % 1000 == 0:
            print err,'/',total
 
    transport.close()
 
except Thrift.TException, ex:
    print "%s" % (ex.message)
