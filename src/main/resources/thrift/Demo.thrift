namespace java com.demo.thrift

struct HelloMan {
	1: string name,
	2: i32 age,
}

service DemoService {
	string sayHello(1: HelloMan man);
}