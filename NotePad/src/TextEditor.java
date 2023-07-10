import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javafx.event.ActionEvent;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menubar;
    JMenu file ,edit;
    JMenuItem newFile,openFile,saveFile;
    JMenuItem cut,copy,paste,selectAll,close;
    JTextArea textArea;
    TextEditor(){
        frame=new JFrame();
        menubar=new JMenuBar();
        //Inintialize Text Area
        textArea=new JTextArea();
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0,0));
        panel.add(textArea,BorderLayout.CENTER);
        //SCROLL PANEL
        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);

        frame.add(panel);

        //initialize menu items;
        file=new JMenu("File");
        edit=new JMenu("Edit");

        //Initialize menu Item
        newFile=new JMenuItem("New Window");
        openFile=new JMenuItem("Open Flie");
        saveFile=new JMenuItem("Save File");
        //Add actionListner
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        copy=new JMenuItem("Copy");
        cut=new JMenuItem("Cut");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");
        //Add actionListner
        copy.addActionListener(this);
        paste.addActionListener(this);
        cut.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectAll);
        edit.add(close);
        //Add menu to menuBar
        menubar.add(file);
        menubar.add(edit);

    
        frame.setJMenuBar(menubar);

        frame.setBounds(0, 0, 400, 400);
        frame.setVisible(true);
        frame.setLayout(null);

    }
    
    public static void main(String[] args) throws Exception {
        TextEditor textEditor=new TextEditor();
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent actionEvente) {
        if(actionEvente.getSource()==cut){
            textArea.cut();
        }
        if(actionEvente.getSource()==copy){
            textArea.copy();
        }
        if(actionEvente.getSource()==paste){
            textArea.paste();
        }
        if(actionEvente.getSource()==selectAll){
            textArea.selectAll();
        }
        if(actionEvente.getSource()==close){
            System.exit(0);
        }
        if(actionEvente.getSource()==openFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption= fileChooser.showOpenDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file=fileChooser.getSelectedFile();
                String filePath=file.getPath();
                try {
                    FileReader fileReader=new FileReader(filePath);
                    BufferedReader bufferedReader=new BufferedReader(fileReader);
                    String intermediate="",output="";
                    while((intermediate=bufferedReader.readLine())!=null){
                        output+=intermediate+"/n";
                    }
                    textArea.setText(output);

                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                catch(IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }

        if(actionEvente.getSource()==saveFile){
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption=fileChooser.showSaveDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try {
                FileWriter fileWriter=new FileWriter(file);
                BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                textArea.write(bufferedWriter);
                bufferedWriter.close();
                } catch (IOException ioException) {
                ioException.printStackTrace();
                }
            }
            
        }
        if(actionEvente.getSource()==newFile){
            TextEditor newTextEditor=new TextEditor();
        }



    }
}
