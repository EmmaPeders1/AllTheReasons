package src.main.java.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class gui {

	private static Map<Integer,String> map = new HashMap<>();
  private static int maxKey;

  public static void main(String args[]){

    //the frame
    JFrame frame = new JFrame("My 1000th reason");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000,600);

    //textarea
    JTextArea textarea = new JTextArea();
    textarea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    Font font = new Font("Verdana", Font.BOLD, 14);
    textarea.setFont(font);
    textarea.setForeground(Color.BLACK);

    JScrollPane scroll = new JScrollPane (textarea,
    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    //saving
    JPanel topPanel = new JPanel();
    JButton save = new JButton("Lagre");
    JButton getSaved = new JButton("Hent lagrede grunner");
    topPanel.add(save);
    topPanel.add(getSaved);
    save.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        SaveHandler sh = new SaveHandler();
        sh.writeToFile(textarea.getText());
      }
    });
    getSaved.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        map = rema.getReasonsFromFile();
        maxKey = rema.getMaxKey(map);
        textarea.setText(rema.printReasons(maxKey, map));
      }
    });

    //input fields, labels and add-button
    JLabel numberLabel = new JLabel("Nummer: ");
    JTextField number = new JTextField(4);
    JLabel reasonLabel = new JLabel("Grunn: ");
    JTextField reason = new JTextField(40);
    JButton addButton = new JButton("Legg til");
    addButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        rema.addReason(Integer.parseInt(number.getText()), reason.getText(), map);
        maxKey = rema.getMaxKey(map);
        textarea.setText(rema.printReasons(maxKey, map));
      }
    });
    topPanel.add(numberLabel);
    topPanel.add(number);
    topPanel.add(reasonLabel);
    topPanel.add(reason);
    topPanel.add(addButton);

    if(map.isEmpty()){
      textarea.setText("1000 gode grunner, my ass. Her er det tomt!");
    }
    else{
      maxKey = rema.getMaxKey(map);
      textarea.setText(rema.printReasons(maxKey, map));
    }



    JPanel searchPanel = new JPanel();
    JTextField searchField = new JTextField(20);
    JButton searchButton = new JButton("Søk");
    searchPanel.add(searchField);
    searchPanel.add(searchButton);
    searchButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        Map<Integer, String> searchresult = rema.search(searchField.getText(), map);
        if(searchresult.isEmpty()){
          textarea.setText("Ingen resultater for søket ditt: '" + searchField.getText() + "'");
        }
        else{
          int maxSearchKey = rema.getMaxKey(searchresult);
          textarea.setText(rema.printReasons(maxSearchKey, searchresult));
        }
      }
    });

    //adding components to the frame
    frame.getContentPane().add(BorderLayout.PAGE_START, topPanel);
    frame.getContentPane().add(BorderLayout.PAGE_END, searchPanel);
    frame.getContentPane().add(BorderLayout.CENTER, scroll);
    frame.setVisible(true);
 }
}
