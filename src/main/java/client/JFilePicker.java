package client;

import quickfix.SessionID;
import utils.FileParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JFilePicker extends JPanel {
    JPanel replayPanel = new JPanel();
    JPanel fileChooserPanel  = new JPanel();
    private String textFieldLabel;
    private String buttonLabel;

    private JLabel label;
    private JTextField textField;
    private JButton button;

    private List<String> messages = new ArrayList<>();
    private JFileChooser fileChooser;

    private int mode;
    private SessionID id;
    private ClientApplication application;
    public static final int MODE_OPEN = 1;
    public static final int MODE_SAVE = 2;

    public JFilePicker(String textFieldLabel, String buttonLabel, SessionID id, ClientApplication application) {
        this.textFieldLabel = textFieldLabel;
        this.buttonLabel = buttonLabel;
        this.id = id;
        this.application = application;


        // creates the GUI
        label = new JLabel(textFieldLabel);

        textField = new JTextField(30);
        button = new JButton(buttonLabel);

        JTextArea messagePreview = new JTextArea();


        JButton parseButton = new JButton("Parse");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        parseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = textField.getText();
                String message = "";
                messages = FileParser.parseFile(path);
                for(String msg : messages){
                    if(message ==""){
                        message = msg;
                    }else{
                        message = message + "\n" + msg;
                    }

                }
                messagePreview.setText(message);
            }
        });

        fileChooserPanel.add(label);
        fileChooserPanel.add(textField);
        fileChooserPanel.add(button);
        fileChooserPanel.add(parseButton);
        replayPanel.add(messagePreview,BorderLayout.CENTER);
        JButton submit = new JButton("submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fixMessages = messagePreview.getText();
                String msgs[] = fixMessages.split("\\n");
                for(int i=0;i<msgs.length;i++){
                    application.sendOrder(id, msgs[i]);
                }
            }
        });
       replayPanel.add(submit);
       setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
       add(fileChooserPanel);
       add(replayPanel);
    }

    private void buttonActionPerformed(ActionEvent evt) {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showOpenDialog(null);
        textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

    }



    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getSelectedFilePath() {
        return textField.getText();
    }

    public JFileChooser getFileChooser() {
        return this.fileChooser;
    }

    public List<String> getMessages(){
        return this.messages;
    }
}
