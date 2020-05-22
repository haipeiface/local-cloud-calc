package demo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Date;
import java.util.List;

@Document(indexName = "gwmsw-*", type = "logs")
public class LogDO {
    @Id
    private String Id;
    private String acctId;
    private String behavior;
    private String body;
    private String clientId;
    private String portal;
    private String connId;
    private Date creatAt;
    private List<String> headers;
    private String host;
    private String ip;
    private String level;
    private Long level_value;
    private String logger_name;
    private String message;
    private String method;
    private String page;
    private String params;
    private String os;
    private Long port;
    private String thread_name;
    private String uri;

//    @JsonProperty(value = "@timestamp")
    @JsonAlias("@timestamp")
    private Date timestamp;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPortal() {
        return portal;
    }

    public void setPortal(String portal) {
        this.portal = portal;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getConnId() {
        return connId;
    }

    public void setConnId(String connId) {
        this.connId = connId;
    }

    public Date getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Date creatAt) {
        this.creatAt = creatAt;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Long getLevel_value() {
        return level_value;
    }

    public void setLevel_value(Long level_value) {
        this.level_value = level_value;
    }

    public String getLogger_name() {
        return logger_name;
    }

    public void setLogger_name(String logger_name) {
        this.logger_name = logger_name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Long getPort() {
        return port;
    }

    public void setPort(Long port) {
        this.port = port;
    }

    public String getThread_name() {
        return thread_name;
    }

    public void setThread_name(String thread_name) {
        this.thread_name = thread_name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "LogDO{" +
                "Id='" + Id + '\'' +
                ", acctId='" + acctId + '\'' +
                ", behavior='" + behavior + '\'' +
                ", body='" + body + '\'' +
                ", clientId='" + clientId + '\'' +
                ", portal='" + portal + '\'' +
                ", connId='" + connId + '\'' +
                ", creatAt=" + creatAt +
                ", headers=" + headers +
                ", host='" + host + '\'' +
                ", ip='" + ip + '\'' +
                ", level='" + level + '\'' +
                ", level_value=" + level_value +
                ", logger_name='" + logger_name + '\'' +
                ", message='" + message + '\'' +
                ", method='" + method + '\'' +
                ", page='" + page + '\'' +
                ", params='" + params + '\'' +
                ", os='" + os + '\'' +
                ", port=" + port +
                ", thread_name='" + thread_name + '\'' +
                ", uri='" + uri + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
