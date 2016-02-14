function() {
	var key={timestamp:NumberLong(NumberLong(this.timestamp/1000/60)*1000*60), protocalType:this.protocalType};
	var value={input:this.receiveSize, output:this.sentSize, total:NumberLong(this.receiveSize + this.sentSize), count:1};
	emit(key, value);
}