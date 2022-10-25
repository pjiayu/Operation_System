package com.example.operation_system.Controller;

import com.example.operation_system.model.PCB;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author 皮皮皮
 * @date 2022/10/22 17:00
 */
public class AddProgressController {
    public TextField ProgressNameField;
    public TextField PIDField;
    public TextField RunTimeField;
    public TextField PCBPointerField;
    public TextField StateField;
    public TextField PriorityField;

    public boolean okClicked;
    private PCB pcb;
    private Stage dialogStage;

    public void handleOk(ActionEvent actionEvent) {
        if(isVInputValid()){
            pcb.setProgressName(ProgressNameField.getText());
            pcb.setPID(Integer.parseInt(PIDField.getText()));
            pcb.setRunningTime(Integer.parseInt(RunTimeField.getText()));
            pcb.setPriority(Integer.parseInt(PriorityField.getText()));
            pcb.setStatement(StateField.getText());

            okClicked=true;
            dialogStage.close();
        }
    }

    private boolean isVInputValid() {
        return true;
    }

    public void handleCancel(ActionEvent actionEvent) {
        dialogStage.close();
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setPCB(PCB pcb){
        this.pcb=pcb;
        ProgressNameField.setText(pcb.getProgressName());
        PIDField.setText(Integer.toString(pcb.getPID()));
        RunTimeField.setText(Integer.toString(pcb.getRunningTime()));
        StateField.setText(pcb.getStatement());
        PriorityField.setText(Integer.toString(pcb.getPriority()));
        PCBPointerField.setText(pcb.getPCB_pointer());
    }
    public void setDialogStage(Stage dialogStage){
        this.dialogStage=dialogStage;
    }
}
