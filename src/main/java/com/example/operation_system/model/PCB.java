package com.example.operation_system.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author 皮皮皮
 * @date 2022/10/11 18:20
 */
public class PCB implements Comparable<PCB>{

    private final StringProperty progressName;
    private final IntegerProperty PID;
    private final IntegerProperty runningTime;
    private final IntegerProperty priority;
    private StringProperty statement;
    private StringProperty PCB_pointer;

    public PCB(){
        //this.progressName=new SimpleStringProperty("p");
        this(null);
    }

    public PCB(String progressName){
        this.progressName=new SimpleStringProperty(progressName);
        this.PID=new SimpleIntegerProperty((int)(Math.random()*(100+1)));
        this.runningTime=new SimpleIntegerProperty((int)(Math.random()*(8+1)+2));//运行时间随机2~10
        this.priority=new SimpleIntegerProperty((int)(Math.random()*(7+1)));
        this.statement=new SimpleStringProperty("Ready");
        this.PCB_pointer=new SimpleStringProperty("abc");
    }


    public PCB(String progressName, int PID, int runningTime, int priority){

        this.progressName = new SimpleStringProperty(progressName);
        this.PID=new SimpleIntegerProperty(PID);
        this.runningTime=new SimpleIntegerProperty(runningTime);
        this.priority=new SimpleIntegerProperty(priority);

    }

    public String getProgressName() {
        return progressName.get();
    }

    public StringProperty progressNameProperty() {
        return progressName;
    }

    public void setProgressName(String progressName) {
        this.progressName.set(progressName);
    }

    public int getPID() {
        return PID.get();
    }

    public IntegerProperty PIDProperty() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID.set(PID);
    }

    public int getRunningTime() {
        return runningTime.get();
    }

    public IntegerProperty runningTimeProperty() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime.set(runningTime);
    }

    public int getPriority() {
        return priority.get();
    }

    public IntegerProperty priorityProperty() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority.set(priority);
    }

    public String getStatement() {
        return statement.get();
    }

    public StringProperty statementProperty() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement.set(statement);
    }

    public String getPCB_pointer() {
        return PCB_pointer.get();
    }

    public StringProperty PCB_pointerProperty() {
        return PCB_pointer;
    }

    public void setPCB_pointer(String PCB_pointer) {
        this.PCB_pointer.set(PCB_pointer);
    }


    @Override
    public int compareTo(PCB p) {
        return (this.priority.get()-p.priority.get());
    }
}
