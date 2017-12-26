function(key, values){
	var rtn = {input:0, output:0, count:0};
	for(var i=0; i<values.length; i++) {
		rtn.input += values[i].input;
		rtn.output += values[i].output;
		rtn.count += values[i].count;
	}
	rtn.input = NumberLong(rtn.input);
	rtn.output = NumberLong(rtn.output);
	rtn.count = NumberLong(rtn.count);
	return rtn;
}