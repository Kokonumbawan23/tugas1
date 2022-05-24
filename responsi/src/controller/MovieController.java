/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.MovieModel;
import controller.MovieController;
import view.MovieView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 *
 * @author Lenovo
 */
public class MovieController {
    
    MovieModel model;
    MovieView view;
    String data;

    public MovieController(MovieModel model,MovieView view) {
        this.model = model;
        this.view = view;
        
        if (model.getBanyakData()!=0) {
            String dataMovie[][] = model.readMovie();
            view.tabel.setModel((new JTable(dataMovie, view.namaKolom)).getModel());
        }
        else {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }
        
        view.btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String judul = view.getJudul();
                double alur = Double.parseDouble(view.getAlur());
                double penokohan = Double.parseDouble(view.getPenokohan());
                double akting = Double.parseDouble(view.getAkting());
                model.createMovie(judul,alur,penokohan,akting);
         
                String dataMovie[][] = model.readMovie();
                view.tabel.setModel((new JTable(dataMovie, view.namaKolom)).getModel());
            }
        });
        
        view.btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String judul = view.getJudul();
                double alur = Double.parseDouble(view.getAlur());
                double penokohan = Double.parseDouble(view.getPenokohan());
                double akting = Double.parseDouble(view.getAkting());
                model.updateMovie(judul,alur,penokohan,akting);
                
                String dataMovie[][] = model.readMovie();
                view.tabel.setModel((new JTable(dataMovie, view.namaKolom)).getModel());
            }
        });
        
  
        
        view.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               int input = JOptionPane.showConfirmDialog(null,
                "Apa anda ingin menghapus film ?", "Pilih Opsi...", JOptionPane.YES_NO_OPTION);

            if (input == 0) {
                model.deleteMovie(data);
                String dataMovie[][] = model.readMovie();
                view.tabel.setModel((new JTable(dataMovie, view.namaKolom)).getModel());
            } else {
                JOptionPane.showMessageDialog(null, "Tidak Jadi Dihapus");
            }
                }
        });
        
        view.btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                view.tfJudul.setText("");
                view.tfAlur.setText("");
                view.tfPenokohan.setText("");
                view.tfAkting.setText("");
            }
        });
        
    
          view.tabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mousePressed(e);
                
                int row = view.tabel.getSelectedRow();
                data = view.tabel.getValueAt(row, 0).toString();
                String dataUpdate[] = new String[4];
                dataUpdate[0] = view.tabel.getValueAt(row, 0).toString();
                dataUpdate[1] = view.tabel.getValueAt(row, 1).toString();
                
                
                System.out.println(data);
                 
                
                
            }
           });
    }
    
    
    
}
