package Components;

import Utilities.GUIConstants;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class JScrollPane extends javax.swing.JScrollPane {
    public JScrollPane(JComponent component) {
        super(component);
        setBackground(null);
        getViewport().setBackground(null);
        setBorder(null);

        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setBackground(null);
        scrollBar.setBorder(null);
        scrollBar.setUI(new BasicScrollBarUI() {

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = GUIConstants.textAreaHintColor;
            }

            @Override
            protected javax.swing.JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected javax.swing.JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private javax.swing.JButton createZeroButton() {
                javax.swing.JButton button = new javax.swing.JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                return button;

            }
        });

        setVerticalScrollBar(scrollBar);
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        getVerticalScrollBar().setPreferredSize(new Dimension(5,0));
        getHorizontalScrollBar().setPreferredSize(new Dimension(0,5));
    }
}
