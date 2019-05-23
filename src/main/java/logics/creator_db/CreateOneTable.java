/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logics.creator_db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.markov.sqlvisualization.hibernate.HibernateWorkWithDB;
import ru.markov.sqlvisualization.table.Attribute;
import ru.markov.sqlvisualization.table.Entity;
import ru.markov.sqlvisualization.table.Value;

/**
 *
 * @author rodion
 */
public class CreateOneTable extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dat = request.getParameter("data");
        JSONObject jsonObj = new JSONObject();
        if (dat.equals("") || dat == null) {
            jsonObj.put("data", "Please, insert data!");

            response.getWriter().write(jsonObj.toString());
        } else {
            ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
            HibernateWorkWithDB withDB = context.getBean("hibWWDB", HibernateWorkWithDB.class);

            JSONObject jObj = new JSONObject(request.getParameter("data"));
            String nameEntity = jObj.getString("tableName");
            JSONArray jSONArray = jObj.getJSONArray("columns");
            System.out.println(nameEntity);
            Entity entity = new Entity(nameEntity);
            withDB.setEntity(entity);

            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jsono = jSONArray.getJSONObject(i);
                String nameAttribute = jsono.getString("column");
                String valueName = jsono.getString("value");
                int num = jsono.getInt("num");
                System.out.println(nameAttribute + " " + valueName + " " + num);
                Attribute attribute = new Attribute(entity, nameAttribute);
                Value value = new Value(attribute, valueName, num);
                withDB.setAttribute(attribute);
                withDB.setValue(value);
            }
            jsonObj.put("data", "Table created successfully!");

            response.getWriter().write(jsonObj.toString());

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("GET");
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("PUT");
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
