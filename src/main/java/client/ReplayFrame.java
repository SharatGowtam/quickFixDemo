package client;

import quickfix.Application;
import quickfix.SessionID;
import utils.FileParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ReplayFrame extends JFrame {

    public ReplayFrame(SessionID id, ClientApplication application){
        super();
        setTitle("Replay");
        setSize(600, 400);

        JFilePicker picker = new JFilePicker("Pick FIle", "Browse", id, application);
        getContentPane().add(picker,BorderLayout.EAST);



        setVisible(true);
    }
}
