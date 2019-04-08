### redis 数据持久化的方法
1.



### redis 常见问题
1. 在redis中,多个命令的原子性是指,是指这些命令正在读取或者操作数据时,其他命令不能读取这些数据

### redis 数据结构
#### redis可以储存键与5中不同数据结构类型之间的映射,分别是string(字符串),list(列表),set(集合),hash(散列),ZSET(有序集合)

##### string及常用命令(包含字符串,整数,浮点数)

|命令 | 用法及解释|
|:---|:---|
|incr key  |对该key对应的值自增1|
|decr key  |对该key对应的值自减1|
|incrby key  amount |对该key对应的值+整数型的amount|
|decrby key  amount |对该key对应的值-整数型的amount|
/incrbyfloat key amount|对该key对应的值+浮点型的amount|
|getrange key start end |获取key对应的字符串的子串, 包含start和end




1. set key value [EX seconds || PX millseconds] [ NX if not exists || XX if exists]
2.  get key
3.  del key
4.


##### list(列表)及常用命令 (一个key对应一个list<linked list>链表)
|命令 | 用法及解释|
|:---|:---|
|lpush key value [value...] |向列表左边push元素
|rpush key value [value...] |向列表右边push元素
|lpop key  |弹出列表左边元素
|rpop key  |弹出列表右边元素
|lange key start end|返回列表从start到end的所有元素 (-1代表末尾)
|lindex key offset|返回列表指定位置的元素
|ltrim key start end|修剪 
###### 有些命令可以将列表中的元素移动,或者阻塞(block)运行直到有其他客户端给列表添加元素
|命令 | 用法及解释|
|:---|:---|
|BLPOP key[key...] timeout|从第一个非空列表中弹出最左侧的元素,或者在timeout秒之内阻塞直到有客户端向列表中添加元素 弹出为 key+value的组合.timeout为0表示无时间限制,timeout结束后,若所有的列表仍无元素,则会返回nil
|BRPOP key[key...] timeout |从第一个非空列表弹出最右侧的元素,其他同BLPOP
|BRPOPLPUSH sourcekey destkey timeout 从soureckey中弹出最右侧的元素,将其lpush到destkey对应的列表中,若sourcekey对应列表为空,则阻塞timeout秒,



##### set(集合) 以无序的方式储存不同的元素
###### set和list都可以储存多个字符串或者数字,但是list可以储存多个相同的字符串(内部为链表),而set内部则通过hash表来保证存储的元素唯一(类似java中的hashset,hash表只有key,没有与key对应的value),集合有插入,移除,将元素从一个集合移动到另外一个集合中的命令,以及对两个集合中的元素求交集,并集,差集的命令
|命令 | 用法及解释|
|:---|:---|
| sadd key memebr[member...]| 向key对应的集合member
| srem key member[member ...] |从集合中移除对应的member,返回移除的数量
| sismember key member |检查一个member是否在集合中
| smembers key |获取该key对应的所有member,谨慎使用该命令
|scard key|获取集合中的元素个数
|spop key|随机的移除并返回集合中的元素
|smove source-key dest-key item|如果source-key对应的集合中包含item,那么便会从集合中会移除item,并将它添加到
|sdiff key[key...]| 返回第一个集合存在,其它集合不存在的,差集
|sdiffstore destkey key[key...] 将差集存到destkey中
|sinter key[key...] |多个集合中的并集,
|sinterstore destkey key[key...] |将交集存到destkey中
|sunion key[key...] |求并集,返回个数
|sunionstore destkey key[key...]|将并集存到destkey,返回个数

#####  散列 储存多个key-value对之间的映射,适合储存对象,命令和字符串类似
|命令 | 用法及解释|
|:---|:---|
|hmget key filed[filed...] |获取散列多个键的值
|hmset key filed value[filed value...] |批量设置散列多个键值对
|hdel key filed[filed...] |批量删除散列的多个键值对
| hset key filed value|  在给定key对应的散列中添加键值对
| hget key filed|  获取指定散列键的值
| hgetall key |获取散列中包含的所有的键值对
|hexists key filed|检查给定值是否存在散列中
|hkeys key|获取散列中的所有key
|hvals key |获取散列中的所有value
|hgetall key|获取散列中的所有键值对
|hincrby key filed amout|散列中的键对应值+整数型的amout

##### sort set 有序集合 ,
###### 有序集合和散列一样,都用于储存键值对,有序集合储存的是分值(score)与成员(member).有序集合中的键被称为成员(member),每个成员都是各不相同的,而有序集合的值被称为分值(score),分值必须为浮点数

|命令 | 用法及解释|
|:---|:---|
| zadd key [NX||XX] [CH] [INCR] score member [score member ...]
| zrange key start stop| 根据元素在有序集合中的位置,从有序集合中获取多个元素
| zrem key  member [member...] |如果给定成员存在于有序集合中,移除该成员
| zrangebyscore key min max| 获取有序集合在指定的分数范围的元素
| zscore key member |获取有序集合指定成员的score
|zincrby key addscore member|对有序集合成员进行加分
|zcount key min max|获取有序集合中min<score<max的成员数量
