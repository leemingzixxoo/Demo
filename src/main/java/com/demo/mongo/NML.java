package com.demo.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "NML")
public class NML {
	private long timestamp;
	
	private String protocalType;
	
	private String srcIp;
	
	private String destIp;
	
	private long sentSize;
	
	private long receiveSize;
	
	private String inOrOut;
	
	private String content;

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getProtocalType() {
		return protocalType;
	}

	public void setProtocalType(String protocalType) {
		this.protocalType = protocalType;
	}

	public String getSrcIp() {
		return srcIp;
	}

	public void setSrcIp(String srcIp) {
		this.srcIp = srcIp;
	}

	public String getDestIp() {
		return destIp;
	}

	public void setDestIp(String destIp) {
		this.destIp = destIp;
	}

	public long getSentSize() {
		return sentSize;
	}

	public void setSentSize(long sentSize) {
		this.sentSize = sentSize;
	}

	public long getReceiveSize() {
		return receiveSize;
	}

	public void setReceiveSize(long receiveSize) {
		this.receiveSize = receiveSize;
	}

	public String getInOrOut() {
		return inOrOut;
	}

	public void setInOrOut(String inOrOut) {
		this.inOrOut = inOrOut;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
