package com.demo.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "NetMonitorLog")
public class NetMonitorLog {
	@Id
	private String id;

	private String guid;

	private long timestamp;

	private String dateStr;

	private String hourStr;

	private String minStr;

	private String protocalType;

	private String protocalL4;

	private int duration;

	private long sentSize;

	private String inOrOut;

	private long receiveSize;

	private String user;

	private String operation;// Get or Post

	private String clientIP;

	private String srcIP;

	private int srcPort;

	private String dstIP;

	private int dstPort;

	private String applicationName;

	private long sendPacketNum;

	private long recvPacketNum;

	private String url;

	private String host;
		
	private int ishttp;

	public NetMonitorLog() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getHourStr() {
		return hourStr;
	}

	public void setHourStr(String hourStr) {
		this.hourStr = hourStr;
	}

	public String getMinStr() {
		return minStr;
	}

	public void setMinStr(String minStr) {
		this.minStr = minStr;
	}

	public String getProtocalType() {
		return protocalType;
	}

	public void setProtocalType(String protocalType) {
		this.protocalType = protocalType;
	}

	public String getProtocalL4() {
		return protocalL4;
	}

	public void setProtocalL4(String protocalL4) {
		this.protocalL4 = protocalL4;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public long getSentSize() {
		return sentSize;
	}

	public void setSentSize(long sentSize) {
		this.sentSize = sentSize;
	}

	public String getInOrOut() {
		return inOrOut;
	}

	public void setInOrOut(String inOrOut) {
		this.inOrOut = inOrOut;
	}

	public long getReceiveSize() {
		return receiveSize;
	}

	public void setReceiveSize(long receiveSize) {
		this.receiveSize = receiveSize;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	public String getSrcIP() {
		return srcIP;
	}

	public void setSrcIP(String srcIP) {
		this.srcIP = srcIP;
	}

	public int getSrcPort() {
		return srcPort;
	}

	public void setSrcPort(int srcPort) {
		this.srcPort = srcPort;
	}

	public String getDstIP() {
		return dstIP;
	}

	public void setDstIP(String dstIP) {
		this.dstIP = dstIP;
	}

	public int getDstPort() {
		return dstPort;
	}

	public void setDstPort(int dstPort) {
		this.dstPort = dstPort;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public long getSendPacketNum() {
		return sendPacketNum;
	}

	public void setSendPacketNum(long sendPacketNum) {
		this.sendPacketNum = sendPacketNum;
	}

	public long getRecvPacketNum() {
		return recvPacketNum;
	}

	public void setRecvPacketNum(long recvPacketNum) {
		this.recvPacketNum = recvPacketNum;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getIshttp() {
		return ishttp;
	}

	public void setIshttp(int ishttp) {
		this.ishttp = ishttp;
	}

}
