package cn.yueying.beautymessage.model;

import java.util.Date;

/**
 * Created by luojian on 2017/4/18.
 */
public class ManagerLog implements java.io.Serializable {
    private static final long serialVersionUID = -692989133998722727L;
    private int id;
    private int managerId;
    private long operationId;
    private String operationName;
    private Date operationTime;
    private int operationGranted;

    public ManagerLog() {
    }

    public ManagerLog(int managerId, long operationId, String operationName, Date operationTime, int operationGranted) {
        this.managerId = managerId;
        this.operationId = operationId;
        this.operationName = operationName;
        this.operationTime = operationTime;
        this.operationGranted = operationGranted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public long getOperationId() {
        return operationId;
    }

    public void setOperationId(long operationId) {
        this.operationId = operationId;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public int getOperationGranted() {
        return operationGranted;
    }

    public void setOperationGranted(int operationGranted) {
        this.operationGranted = operationGranted;
    }

    public boolean granted() {
        return operationGranted > 0;
    }
}
