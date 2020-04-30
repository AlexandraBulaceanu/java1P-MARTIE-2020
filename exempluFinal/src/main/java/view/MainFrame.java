package view;

import model.Produs;
import service.MainService;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MainFrame extends JFrame {

    private JPanel panel;
    private JButton adaugareButton;
    private JButton exitButton;
    private JTextField textField1;
    private JTextField textField2;
    private JList list1;
    private DefaultListModel<Produs> model;

    public MainFrame(){
        add(panel);
        model = new DefaultListModel<>();
        list1.setModel(model);
        exitButton.addActionListener(ev->logout());
        adaugareButton.addActionListener(ev->adaugaProdus());
        afisareProduse();
        list1.addMouseListener(//adaugare eveniment la selectarea pe lista
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent ev){//suprascriu metoda ce imi permite sa verific op pe mouse
                        if(ev.getClickCount()==2){//numara de cate ori am dat click -> dublu click
                            Produs produs = (Produs) list1.getSelectedValue();//obtin produsul pe care l-am selectat si il salvez in instanta de produs
                            MainService.getInstance().stergeProdus(produs.getId());//elimin produsul selectat prin dublu click->strgere din baza de date
                            afisareProduse();//reactualizez lista -> vizual
                        }
                    }
                }
        );
        setLocationRelativeTo(null);
        setSize(500,500);
        setVisible(true);
    }
    public void logout(){
        new LoginFrame();//se concteaza alt utilizator
        dispose();
    }
    public void adaugaProdus(){
        String nume = textField1.getText();
        double pret = Double.parseDouble(textField2.getText());
        Produs p = new Produs(nume,pret);
        MainService.getInstance().adaugaProdus(p);
        afisareProduse();
        JOptionPane.showMessageDialog(null,"A fost adaugat un produs");
    }
    public void afisareProduse(){
        List<Produs> produse = MainService.getInstance().getAllProducts();
        //pe baza listei de produse pe care o obt din baza de date fiecare elem din lista produse,
        //prin iterare cu forEach va fi adaugat pe model(lista de pe fereastra)
        model.clear();
        produse.forEach(model::addElement);
    }
}
