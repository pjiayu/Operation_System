package com.example.operation_system.Util;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * @author 皮皮皮
 * @date 2022/10/23 10:54
 */
public class DialogUtil {
    public static void showDialog(String str) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().add(new ButtonType("确认", ButtonBar.ButtonData.OK_DONE));
        dialog.setTitle("警告");
        dialog.setHeaderText(str+" 队列没有中PCB被选中");
        dialog.setContentText("请在 " +str +" 队列中选中进程");
        dialog.show();
    }
}
