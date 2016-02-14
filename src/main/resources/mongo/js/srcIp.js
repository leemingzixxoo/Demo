function() {
	var key={timestamp:NumberLong(NumberLong(this.timestamp/(1000*60))*(1000*60)), srcIp:this.srcIp};
	var value={input:this.receiveSize, output:this.sentSize, count:1};
	emit(key, value);
}