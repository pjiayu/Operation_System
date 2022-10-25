package com.example.operation_system.Controller;

import com.example.operation_system.Main;
import com.example.operation_system.Util.DialogUtil;
import com.example.operation_system.model.PCB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.util.Collections;


public class CPUSchedulingController {
    public static int t=1;//记录运行三次则优先级-1
    public TableView<PCB> ReadyTable;
    public TableView<PCB> HangTable;
    public TableView<PCB> ReserveTable;
    public TableView<PCB> RunningTable;
    public TableView<PCB> TerminateTable;
    public TableColumn<PCB,String> Ready_ProgressName;
    public TableColumn<PCB, Integer> Ready_PID;
    public TableColumn<PCB,Integer> Ready_RunTime;
    public TableColumn<PCB,Integer> Ready_Priority;
    public TableColumn<PCB,String> Reserve_ProgressName;
    public TableColumn<PCB, Integer> Reserve_PID;
    public TableColumn<PCB, Integer> Reserve_RunTime;
    public TableColumn<PCB, Integer> Reserve_Priority;
    public TableColumn<PCB,String> Hang_ProgressName;
    public TableColumn<PCB, Integer> Hang_PID;
    public TableColumn<PCB, Integer> Hang_RunTime;
    public TableColumn<PCB, Integer> Hang_Priority;
    public TableColumn<PCB,String> Running_ProgressName;
    public TableColumn<PCB,Integer> Running_PID;
    public TableColumn<PCB,Integer> Running_RunTime;
    public TableColumn<PCB,Integer> Running_Priority;
    public TableColumn<PCB,String> Terminate_ProgressName;
    public TableColumn<PCB,Integer>Terminate_PID;
    public TableColumn<PCB,Integer> Terminate_RunTime;
    public TableColumn<PCB,Integer> Terminate_Priority;


    Main main;
    PCB pcb;
    //PCB root=pcb;//记录根指针.暂时不需要

    //@Test
//    public void test1(){
//        AddProgress();
//        AddProgress();
//        System.out.println(root.getPID());
//        System.out.println(root.getPCB_pointer().getPID());
//        System.out.println(root.getPCB_pointer().getPCB_pointer().getPID());
//    }

    /**
     * 初始化controller类 这个方法会在fxml文件加载后被自动调用
     */
    @FXML
    public void initialize(){
        System.out.println("Progress initialize");
        //将列的对象和数据的属性进行绑定
        //方法1
        //Ready_ProgressName.setCellValueFactory(cellData->cellData.getValue().progressNameProperty());//Lambda表达式
        //Ready_PID.setCellValueFactory(cellData -> cellData.getValue().PIDProperty().asObject());

        //方法2
        //需要在module-info.java中添加  opens com.example.operation_system.model to javafx.base;
        ReadyTable.setEditable(true);
        //单元格可编辑
        //Ready_RunTime.setCellFactory(TextFieldTableCell.forTableColumn());
        Ready_ProgressName.setCellValueFactory(new PropertyValueFactory<>("progressName"));
        Ready_PID.setCellValueFactory(new PropertyValueFactory<>("PID"));
        Ready_RunTime.setCellValueFactory(new PropertyValueFactory<>("runningTime"));
        Ready_Priority.setCellValueFactory(new PropertyValueFactory<>("priority"));

        Reserve_ProgressName.setCellValueFactory(new PropertyValueFactory<>("progressName"));
        Reserve_PID.setCellValueFactory(new PropertyValueFactory<>("PID"));
        Reserve_RunTime.setCellValueFactory(new PropertyValueFactory<>("runningTime"));
        Reserve_Priority.setCellValueFactory(new PropertyValueFactory<>("priority"));

        Hang_ProgressName.setCellValueFactory(new PropertyValueFactory<>("progressName"));
        Hang_PID.setCellValueFactory(new PropertyValueFactory<>("PID"));
        Hang_RunTime.setCellValueFactory(new PropertyValueFactory<>("runningTime"));
        Hang_Priority.setCellValueFactory(new PropertyValueFactory<>("priority"));

        Terminate_ProgressName.setCellValueFactory(new PropertyValueFactory<>("progressName"));
        Terminate_PID.setCellValueFactory(new PropertyValueFactory<>("PID"));
        Terminate_RunTime.setCellValueFactory(new PropertyValueFactory<>("runningTime"));
        Terminate_Priority.setCellValueFactory(new PropertyValueFactory<>("priority"));

        Running_ProgressName.setCellValueFactory(new PropertyValueFactory<>("progressName"));
        Running_PID.setCellValueFactory(new PropertyValueFactory<>("PID"));
        Running_RunTime.setCellValueFactory(new PropertyValueFactory<>("runningTime"));
        Running_Priority.setCellValueFactory(new PropertyValueFactory<>("priority"));

        //设置列的属性能让表格可编辑（双击表格进入编辑状态）
        RunningTable.setEditable(true);
        Running_ProgressName.setCellFactory(TextFieldTableCell.forTableColumn());
        //Running_PID.setCellFactory(TextFieldTableCell.forTableColumn());

//        Ready_Priority.setSortable(true);
//        Ready_Priority.setSortType(TableColumn.SortType.ASCENDING);
//        ReadyTable.getSortOrder().add(Ready_Priority);



        /*
        JavaFX TableView没有显示行，表中无数据时，可以设置一个占位符来显示
        占位符必须是JavaFX Node类的一个实例，大多数(如果不是全部)JavaFX控件都是这样的。
        因此，可以使用JavaFX ImageView或JavaFX Label作为占位符，
         */
        ReadyTable.setPlaceholder(new Label("空白"));
        HangTable.setPlaceholder(new Label("空白"));
        ReserveTable.setPlaceholder(new Label("空白"));
        TerminateTable.setPlaceholder(new Label("空白"));
        RunningTable.setPlaceholder(new Label("空白"));
        //ReserveTable.setPlaceholder(new ImageView("sky.jpg"));



    }
    @FXML
    public void AddProgress() throws IOException {
       //设置PCB指针指向下一个PCB,暂时不需要
        PCB p1=new PCB();
        //pcb.setPCB_pointer(p1);
        //pcb=p1;
        boolean okClicked=main.ProgressAddDialog(p1);
        if(okClicked){
                main.getReadyPCBData().add(p1);
                //每次操作后各个队列都是按优先级排好序
                Collections.sort(main.getReadyPCBData());

        }

    }

    public void HangUp(ActionEvent actionEvent) {
        int index=ReadyTable.getSelectionModel().getSelectedIndex();
        pcb=ReadyTable.getSelectionModel().getSelectedItem();
        try{
            System.out.println("CPUSchedulingController.HangUp"+"------Hang up 进程："
                    +pcb.getProgressName());
            //从ReadyTable中移除，加进HangTable,并将pcb的状态改为Hang
            ReadyTable.getItems().remove(index);
            pcb.setStatement("Hang");
            main.getHangPCBData().add(pcb);
            Collections.sort(main.getHangPCBData());

        }catch(Exception e){
            DialogUtil.showDialog("就绪");
        }
    }

    public void RelieveHang(ActionEvent actionEvent) {
        int index=HangTable.getSelectionModel().getSelectedIndex();
        pcb=HangTable.getSelectionModel().getSelectedItem();

        try{
            System.out.println("CPUSchedulingController.HangUp"+"------Relieve Hang 进程："
                    +pcb.getProgressName());

            //从HangTable中移除，加进ReadyTable,并将pcb的状态改为Ready
            HangTable.getItems().remove(index);
            pcb.setStatement("Ready");
            main.getReadyPCBData().add(pcb);
            //每次操作后各个队列都是按优先级排好序
            Collections.sort(main.getReadyPCBData());
        }catch (Exception e){
            DialogUtil.showDialog("挂起");
        }
    }
    public void AddToRun(ActionEvent actionEvent) {
        //将就绪队列中的进程添加到运行队列
        //1.选中就绪队列中的进程
        int index=ReadyTable.getSelectionModel().getSelectedIndex();
        pcb=ReadyTable.getSelectionModel().getSelectedItem();
        try{
            if(main.getRunningPCBData().size()<5){
                System.out.println("CPUSchedulingController.AddToRun"+"------Running 进程："
                        +pcb.getProgressName());
                //从ReadyTable中移除，加进RunningTable,并将pcb的状态改为Running
                ReadyTable.getItems().remove(index);
                pcb.setStatement("Running");
                main.getRunningPCBData().add(pcb);
                //每次操作后各个队列都是按优先级排好序,Running和terminate就不用
                //Collections.sort(main.getRunningPCBData());
            }else{
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.getDialogPane().getButtonTypes().add(new ButtonType("确认", ButtonBar.ButtonData.OK_DONE));
                dialog.setTitle("警告");
                dialog.setHeaderText("运行队列已满！");
                dialog.show();
            }
        }catch (Exception e){
            DialogUtil.showDialog("就绪");
        }
    }
    public void Run(ActionEvent actionEvent) {
        //每过2秒,运行时间-1，每运行3次，优先级-1

        //for(元素类型 元素变量 : 遍历对象){ 代码语句 }
        //Lambda for循环使用：    遍历对象.forEach(元素变量 -> 代码语句);
        for(int i=0;i<main.getRunningPCBData().size();i++){
            //如果运行时间大于0，则运行时间-1，否则进入终止队列
            pcb=main.getRunningPCBData().get(i);
            if(pcb.getRunningTime()>0){
                pcb.setRunningTime(pcb.getRunningTime()-1);
            }
            if(pcb.getRunningTime()==0){
                //下面一行代码运行后，i已经指向了原本的下一个PCB，若for循环中再执行i++，则跳过了一个
                main.getRunningPCBData().remove(pcb);
                //所以此处需i--
                i--;
                pcb.setStatement("termination");
                main.getTerminatePCBData().add(pcb);

                //终止队列按先后顺序
                //Collections.sort(main.getTerminatePCBData());
            }
        }

        if(t<3){
            t++;
        }else{
            //Lambda
            main.getReadyPCBData().forEach(pcb->{
                    if(pcb.getPriority()>0){
                        pcb.setPriority(pcb.getPriority()-1);
                    }
            });
            t=1;
        }
        //每次运行一次后，检查Running队列是否空缺( <5)，若空，则从Ready队列调进程进入Running队列。
        //然后检查Ready队列进程数是否<8，若小于，则从后备队列调入Ready队列
        int Running_size=main.getRunningPCBData().size();
        if(Running_size<5){
            for(int index=0;index<5-Running_size && main.getReadyPCBData().size()!=0;index++){
                pcb=main.getReadyPCBData().get(0);
                main.getReadyPCBData().remove(0);
                main.getRunningPCBData().add(pcb);
            }
        }

        int Ready_size=main.getReadyPCBData().size();
        if(Ready_size<8){
            for(int index=0;index<8-Ready_size && main.getReservePCBData().size()!=0;index++){
                pcb=main.getReservePCBData().get(0);
                main.getReservePCBData().remove(0);
                main.getReadyPCBData().add(pcb);
            }
        }
        //最后再将调度后的队列重新排好序
        //Collections.sort(main.getRunningPCBData());
        Collections.sort(main.getReadyPCBData());

        try {
            Thread.sleep( 1500);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

    }

    public void End(ActionEvent actionEvent) {
    }
    public void setApp(Main main){
        this.main=main;

        //Comparator接口排序，这个出了点问题
//        SortedList<PCB> p=main.getReservePCBData().sorted(new Comparator<PCB>() {//干嘛的我也不知道 官方给的方法  排序用
//                                            @Override
//                                            public int compare(PCB o1, PCB o2) {
//                                                int a = o1.getPriority();//我用的是年龄排序 age
//                                                int b = o2.getPriority();
//                                                return a - b;
//                                            }
//                                        });
//        ReadyTable.setItems(p);

        //Comparator接口，这个可以在PCB类中未实现Comparable接口时使用，但我自己感觉没有Comparable接口好用

//        Collections.sort(main.getReadyPCBData(), new Comparator<PCB>() {
//            @Override
//            public int compare(PCB o1, PCB o2) {
//                return o1.getPriority()- o2.getPriority();
//            }
//        });

        //实现Comparable接口
        Collections.sort(main.getRunningPCBData());
        RunningTable.setItems(main.getRunningPCBData());

        Collections.sort(main.getReadyPCBData());
        ReadyTable.setItems(main.getReadyPCBData());

        Collections.sort(main.getReservePCBData());
        ReserveTable.setItems(main.getReservePCBData());

        HangTable.setItems(main.getHangPCBData());
        TerminateTable.setItems(main.getTerminatePCBData());


    }


}