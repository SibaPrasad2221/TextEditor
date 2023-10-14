// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame; //declaring properties of texteditor

    JMenuBar menuBar;

    JMenu file, edit;

    //file menu items
    JMenuItem newFile, openFile, saveFile;

    //edit menu items
    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;

    TextEditor(){
        //initialize a frame
        frame = new JFrame();

        //intialize menubar
        menuBar = new JMenuBar();

        //intialize text area
        textArea = new JTextArea();

        //intialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");


        //initialize file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //add action listener to file menu items, because without specifying where button is presssed then how action listener will perform action
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //add menu options to file
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //initialize edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //add active listener to edit menus.
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //add edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //add options to menu ba
        menuBar.add(file);
        menuBar.add(edit);


        //set menubar to frame
        frame.setJMenuBar(menuBar);

        //create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        //add text area to panel
        panel.add(textArea, BorderLayout.CENTER);
        //create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to panel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);

//        //add text area to frame
//        frame.add(textArea);

        //set dimensions of frame
        frame.setBounds(0,0,400,400);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setTitle("Text Editor By Siba Prasad");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource() == cut ){
            //perform cut operation
            textArea.cut();
        }

        if(actionEvent.getSource()==copy){
            textArea.copy();
        }

        if(actionEvent.getSource()==paste){
            textArea.paste();
        }

        if(actionEvent.getSource()==selectAll){
            textArea.selectAll();
        }

        if(actionEvent.getSource()==close){
            System.exit(0);
        }

        if(actionEvent.getSource()==openFile){
            //open a file choose
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);

            //if we have click on open button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //getting the select file
                File file = fileChooser.getSelectedFile();

                //get the path of select file
                String filepath = file.getPath();

                try{
                    //initialize file reader
                    FileReader fileReader= new FileReader(filepath);

                    //initialize buffered reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    String intermediate = "", output="";

                    //read contents of file line by line
                    while((intermediate=bufferedReader.readLine()) != null){
                        output+= intermediate+"\n";
                    }

                    //set the output string to text area
                    textArea.setText(output);
                }

                catch(IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource()==saveFile){
            //initialize file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            //get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //check if we clicked on save button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //create a new file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //intialize file write
                    FileWriter fileWriter = new FileWriter(file);

                    //initialize buffer writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    //write contents of text area to fle
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource()==newFile){
            TextEditor textEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}