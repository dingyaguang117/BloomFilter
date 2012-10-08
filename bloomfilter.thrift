
service BloomFilterThrift
{
    bool query(1:string s);
    void add(1:string s);
    bool queryAndAdd(1:string s);
    void store();
}
