package com.github.lwhite1.tablesaw.api.plot;

import com.github.lwhite1.tablesaw.api.CategoryColumn;
import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.kapi.CategoryCol;
import com.github.lwhite1.tablesaw.kapi.NumberCol;
import com.github.lwhite1.tablesaw.plotting.fx.FxBar;
import com.github.lwhite1.tablesaw.plotting.fx.FxPlot;
import com.github.lwhite1.tablesaw.reducing.NumericSummaryTable;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;

import javax.swing.*;

public class Bar extends FxPlot {

    private static final String WINDOW_TITLE = "Tablesaw";

    public static void show(String title, CategoryColumn categoryColumn, DoubleColumn numericColumn) throws Exception {

        SwingUtilities.invokeLater(() -> {
            try {
                initAndShowGUI(title, categoryColumn, numericColumn, 640, 480);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void show(String title, CategoryCol categoryColumn, NumberCol numericColumn) throws Exception {

        SwingUtilities.invokeLater(() -> {
            try {
                initAndShowGUI(title, categoryColumn.getTarget(), numericColumn.getTarget(), 640, 480);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void show(String title, DoubleColumn categoryColumn, DoubleColumn numericColumn) throws Exception {

        SwingUtilities.invokeLater(() -> {
            try {
                initAndShowGUI(title, categoryColumn, numericColumn, 640, 480);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void show(String title, NumericSummaryTable table) throws Exception {

        SwingUtilities.invokeLater(() -> {
            try {
                if (table.column(0) instanceof CategoryColumn) {
                    initAndShowGUI(title, table.categoryColumn(0),
                            table.numericColumn(1), 640, 480);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void initAndShowGUI(String title,
                                       CategoryColumn categoryColumn,
                                       DoubleColumn numericColumn,
                                       int width,
                                       int height) throws Exception {

        final JFXPanel fxPanel = getJfxPanel(WINDOW_TITLE, width, height);
        BarChart<String, Number> chart = FxBar.chart(title, categoryColumn, numericColumn);
        Platform.runLater(() -> initFX(fxPanel, chart));
    }

    private static void initAndShowGUI(String title,
                                       DoubleColumn categoryColumn,
                                       DoubleColumn numericColumn,
                                       int width,
                                       int height) throws Exception {

        final JFXPanel fxPanel = getJfxPanel(WINDOW_TITLE, width, height);
        BarChart<String, Number> chart = FxBar.chart(title, categoryColumn, numericColumn);
        Platform.runLater(() -> initFX(fxPanel, chart));
    }

    private static void initFX(JFXPanel fxPanel, BarChart<String, Number> chart) {
        // This method is invoked on the JavaFX thread
        Scene scene = new Scene(chart, chart.getWidth(), chart.getHeight());
        fxPanel.setScene(scene);
    }
}
