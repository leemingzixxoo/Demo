function(key, values){
	var rtn = {input:0, output:0, total:0, count:0};
	for(var i=0; i<values.length; i++) {
		rtn.input += values[i].input;
		rtn.output += values[i].output;
		rtn.total += values[i].total;
		rtn.count += values[i].count;
	}
	rtn.input = NumberLong(rtn.input);
	rtn.output = NumberLong(rtn.output);
	rtn.total = NumberLong(rtn.total);
	rtn.count = NumberLong(rtn.count);
	return rtn;
}