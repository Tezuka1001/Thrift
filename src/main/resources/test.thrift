/**
* namespace example
**/
namespace * org.lyh
namespace java org.lyh.service

/**
* include example
**/
include "hello.thrift"

/**
* typedef example
**/
typedef i64 long

/**
* enum example
**/
enum logic {
    AND = 1
    OR = 2
    NOT = 3
}

/**
* const example
**/
const i32 MAX_TIME = 10
const i64 MAX_COUNT = 100

/**
* struct example
**/
struct Trade {
    1: required string symbol //required
    2: double price = 0 //默认值
    3: optional i32 size //optional
    4: binary bin
    5: bool boo
    6: i8 ibyte
    7: i16 ishort
    8: i32 iint
    9: long ilong //typedef
    10: double dou
    11: string str
    12: list<i32> elementList
    13: set<string> elementSet
    14: map<i64, binary> elementMap
}

/**
* service example
**/
service Base {
    void a();
}
service Derived extends Base{
    i64 b(1: string str);
}

/**
* exception example
**/
exception badTrade {
    1: i32 id
    2: string comment
}

/**
* annotation example
* cpp.type 对Java无效
**/
struct anno {
    1: i32 (cpp.type = "long") counter
} (final = "true")