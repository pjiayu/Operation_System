package com.example.operation_system;

import com.example.operation_system.Controller.AddProgressController;
import com.example.operation_system.Controller.CPUSchedulingController;
import com.example.operation_system.model.PCB;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/*
通过新增进程到后备队列，直至达到一定数量，然后运行（根据调度算法），后备队列的进程进入running状态
*/

//************************************************************
/*
尚待改进：1、单元格编辑保存数据
        2、Comparator和Comparable
        3、每个tableView的背景图片

 */
public class Main extends Application {
    private Stage primaryStage;


    //这个PCBData是数据源
    private ObservableList<PCB> ReadyPCBData= FXCollections.observableArrayList(
            //添加部分进程
            new PCB("QQ"),
            new PCB("weChat"),
            new PCB("Edge"),
            new PCB("music"),
            new PCB("p"),
            new PCB("j"),
            new PCB("y")

    );

    private ObservableList<PCB>HangPCBData=FXCollections.observableArrayList();
    private ObservableList<PCB>ReservePCBData=FXCollections.observableArrayList(
            new PCB("pdf",-1,-1,-1),
            new PCB("Idea",-1,-1,-1),
            new PCB("VS",-1,-1,-1),
            new PCB("douYin",-1,-1,-1),
            new PCB("WangZheRongYao",-1,-1,-1),
            new PCB("WPS",-1,-1,-1),
            new PCB("market",-1,-1,-1),
            new PCB("payApp",-1,-1,-1)
    );
    private ObservableList<PCB>TerminatePCBData=FXCollections.observableArrayList();
    private ObservableList<PCB>RunningPCBData=FXCollections.observableArrayList(
            new PCB("ppp"),
            new PCB("pjy")
    );

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage=stage;

        CPU_Scheduling();
    }

    public void CPU_Scheduling() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/CPU_Scheduling.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 650);

        primaryStage.setTitle("CPU_Scheduling!");
        primaryStage.setScene(scene);

        //获取control信息，传递上下文对象.
        // 让控制器访问主应用程序。
        CPUSchedulingController cpuSchedulingController=fxmlLoader.getController();
        cpuSchedulingController.setApp(this);

        primaryStage.show();
    }

    public Main(){
        //添加部分进程方法之一
//        ReadyPCBData.add(new PCB("QQ"));
//        ReadyPCBData.add(new PCB("weChat"));
//        ReadyPCBData.add(new PCB("Edge"));
    }

    public ObservableList<PCB> getReadyPCBData(){
        return ReadyPCBData;
    }
    public ObservableList<PCB> getHangPCBData(){
        return HangPCBData;
    }
    public ObservableList<PCB> getReservePCBData(){
        return ReservePCBData;
    }
    public ObservableList<PCB> getTerminatePCBData(){
        return TerminatePCBData;
    }
    public ObservableList<PCB> getRunningPCBData(){
        return RunningPCBData;
    }

    public boolean ProgressAddDialog(PCB pcb) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/AddProgress.fxml"));

        Stage stage=new Stage();
        stage.setTitle("AddProgress!");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);

        Scene scene = new Scene(fxmlLoader.load(), 500, 400);
        stage.setScene(scene);

        //将对象的默认字段加进新增进程的窗口中，此段代码需放stage.show()前
        AddProgressController controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
        controller.setPCB(pcb);

        // Show the dialog and wait until the user closes it
        stage.showAndWait();

        return controller.isOkClicked();
    }
    public static void main(String[] args) {
        launch();
    }
}