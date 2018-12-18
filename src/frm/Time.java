/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frm;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author mhieu
 */
public class Time {
    int ngay, thang, nam;
    public String getDate(JComboBox cbb){
        String rs = cbb.getSelectedItem().toString();
        if(rs.length()==1) rs = "0" + rs;
        return rs;
    }
    public String getYear(JComboBox cbb){
        return cbb.getSelectedItem().toString();
    }
    public String getMonth(JComboBox cbb) {
        String rs = cbb.getSelectedItem().toString();
        if(rs.length()==1) rs = "0" + rs;
        return rs;
    }
    
    public void setDate(JComboBox cbb){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for(int i = 1; i <= 31; i++) model.addElement(i);
        cbb.setModel(model);
    }
    
    public void setMonth(JComboBox cbb){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for(int i = 1; i <= 12; i++) model.addElement(i);
        cbb.setModel(model);
    }
    
    public void setYear(JComboBox cbb){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for(int i=2020; i> 1920; i--) model.addElement(i);
        cbb.setModel(model);
    }
}
