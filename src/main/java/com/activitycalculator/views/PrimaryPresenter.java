package com.activitycalculator.views;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import static com.activitycalculator.util.ActivityUtil.activityAtTime;
import static com.activitycalculator.util.ActivityUtil.halfLife;
import static com.activitycalculator.util.FieldUtil.textFieldToDouble;
import static com.activitycalculator.util.FieldUtil.textFieldToLocalDateTime;
import static com.activitycalculator.util.FieldUtil.localDateTimeToString;

//public class PrimaryPresenter {
//
//    @FXML
//    private View primary;
//
//    @FXML
//    private Label label;
//
//    public void initialize() {
//        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
//            if (newValue) {
//                AppBar appBar = MobileApplication.getInstance().getAppBar();
//                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e ->
//                        MobileApplication.getInstance().getDrawer().open()));
//                appBar.setTitleText("Primary");
//                appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e ->
//                        System.out.println("Search")));
//            }
//        });
//    }
//
//    @FXML
//    void buttonClick() {
//        label.setText("Hello JavaFX Universe!");
//    }
//
//}


public class PrimaryPresenter {

    @FXML
    private ChoiceBox<String> choiceBqId;
    @FXML
    private ChoiceBox<String> choiceIsotopId;
    @FXML
    private Label isotopId;
    @FXML
    private TextField actStartId;
    @FXML
    private TextField actFinalId;
    @FXML
    private TextField timeStartId;
    @FXML
    private TextField timeFinalId;
    @FXML
    private TextField halfLifeId;
    @FXML
    private View primary;
    @FXML
    private Label timeId;

//    @FXML
//    private DatePicker datePickerStart;
//    @FXML
//    private DateTimePicker datePickerFinal;
//    @FXML
//    private Label label;
//    @FXML
//    private Label labelStartData;
//    @FXML
//    private Label BqLabelId;
//    @FXML
//    private Button calculate;
//    @FXML
//    private URL location;
//    @FXML
//    private Label labelFinalDate;
//    @FXML
//    private ResourceBundle resources;

    public void initialize() {
//        datePickerFinal.setValue(LocalDate.now());
        Map<String, Double> isotops = new HashMap<>();
        isotops.put("F-18", 109.771);
        isotops.put("Cs-137", 15855834.59);
        isotops.put("Ba-133", 5526684.0);
        isotops.put("Ga-68", 67.71);
        isotops.put("Tc-99m", 360.34872);
//        isotops.put("I-131", 15855834.593);
//        isotops.put("C-14", 15855834.593);
//        isotops.put("Re-188", 15855834.593);
//        isotops.put("Co-60", 15855834.593);
//        isotops.put("Co-57", 15855834.593);
        List<String> boxList = new LinkedList<String>(isotops.keySet());
        choiceIsotopId.setItems(FXCollections.observableList(boxList));
//        ChoiceBox<String> choBoxAddLR = new ChoiceBox<>(FXCollections.observableList(boxList));

//Инициализируем Единицы измерений
        List<String> listBq = new ArrayList<>();
        Collections.addAll(listBq, "kBq", "MBq", "GBq", "Bq", "Ci", "mCi");
        choiceBqId.setItems(FXCollections.observableList(listBq));


//        Add header and  listener
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().getDrawer().open()));
                appBar.setTitleText("Введите известные величины");
            }
        });

//        Isotope selection listener
        choiceIsotopId.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.equals(oldValue)) {
                String isotop = newValue;
                isotopId.setText(isotop);
                halfLifeId.setText(String.valueOf(isotops.get(isotop)));
            }
        });

//     Вывод текущего времени
//        Thread timerThread = new Thread(() -> {
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            while (true) {
//                try {
//                    Thread.sleep(1000); //1 second
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                final String time = simpleDateFormat.format(new Date());
//                Platform.runLater(() -> {
//                    timeId.setText(time);
//                });
//            }
//        });
//        timerThread.start();//start the thread and its ok

        actStartId.setText("640033.4932");
        timeStartId.setText("2021-06-07 02:00");
        timeFinalId.setText("2021-06-07 04:00");
        halfLifeId.setText("109.771");
        choiceIsotopId.setValue("F-18");
        choiceBqId.setValue("MBq");
//        calculate.setOnAction(actionEvent -> {
//            calculationActivityAtTime();
//        });

    }

    @FXML
    void buttonClick() {
        calculationActivityAtTime();
    }

    public void calculationActivityAtTime() {
        double startAct = textFieldToDouble(actStartId);
        double finalAct = textFieldToDouble(actFinalId);
        double halfLife = textFieldToDouble(halfLifeId);
        long duration = 0;

//        try {
//            if (!startActStr.isEmpty()) {
//                startAct = Double.parseDouble(startActStr);
//            }
//        } catch (NumberFormatException e) {
//            ActStart.setText("");
//        }


//        try {
//            if (!finalActStr.isEmpty()) {
//                finalAct = Double.parseDouble(finalActStr);
//            }
//        } catch (NumberFormatException e) {
//            ActFinal.setText("");
//        }

//        try {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//            LocalDateTime dateTimeStart = LocalDateTime.parse(timeStart, formatter);
//            LocalDateTime dateTimeFinal = LocalDateTime.parse(timeFinal, formatter);
//            duration = Duration.between(dateTimeStart, dateTimeFinal).getSeconds() / 60;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        LocalDateTime dateTimeStart = textFieldToLocalDateTime(timeStartId);
        LocalDateTime dateTimeFinal = textFieldToLocalDateTime(timeFinalId);

        timeStartId.setText(localDateTimeToString(dateTimeStart));
        timeFinalId.setText(localDateTimeToString(dateTimeFinal));

        duration = Duration.between(dateTimeStart, dateTimeFinal).getSeconds() / 60;

//        try {
//            if (halfLife!=0) {
//                halfLife = Double.parseDouble(halfLifeStr);
//            } else if (startAct > 0 && finalAct > 0 && halfLife==0)
//                halfLife = halfLife(duration, startAct, finalAct);
//        } catch (Exception ignored) {
//        }

        //считываем или насчитываем период полураспада
        if (startAct > 0 && finalAct > 0 && halfLife == 0)
            halfLife = halfLife(duration, startAct, finalAct);

        if (startAct > 0 && finalAct > 0) {
            halfLifeId.setText(String.valueOf(halfLife));
        }

        if ((startAct > 0)) {
            actFinalId.setText(String.valueOf((activityAtTime(startAct, -duration, halfLife))));
        } else if (finalAct > 0) {
            actStartId.setText(String.valueOf(activityAtTime(finalAct, duration, halfLife)));
        } else {
            actStartId.setText("");
            actFinalId.setText("");
        }

        ////////////////
//        labelFinalDate.setText(datePickerFinal.getDateTimeValue().toString());

    }
}