function() {
	var key={timestamp:this.time, srcIp:this.srcIP};
	var value={input:this.receiveSize, output:this.sentSize, count:1};
	emit(key, value);
}