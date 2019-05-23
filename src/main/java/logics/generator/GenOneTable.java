/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logics.generator;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.markov.sqlvisualization.hibernate.HibernateWorkWithDB;
import ru.markov.sqlvisualization.table.Attribute;
import ru.markov.sqlvisualization.table.Entity;

import ru.markov.sqlvisualization.table.ForGenerateJSON;
import ru.markov.sqlvisualization.table.Value;

/**
 *
 * @author rodion
 */
public class GenOneTable extends HttpServlet {

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
        System.out.println("put");
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        HibernateWorkWithDB withDB = context.getBean("hibWWDB", HibernateWorkWithDB.class);

        List<Value> listVal = withDB.getListValue();
        List<Entity> listEnt = withDB.getListEntity();
        List<Attribute> listAttr = withDB.getListAttribute();

        List<Entity> listE = new ForGenerateJSON().convertEntity(listEnt);
        List<Attribute> listA = new ForGenerateJSON().convertAttr(listAttr);
        List<Value> listV = new ForGenerateJSON().convertValue(listVal);

        JSONObject jObj = new JSONObject();

        jObj.put("data", listV);
        jObj.put("listEnt", listE);
        jObj.put("listAttr", listA);

        response.getWriter().write(jObj.toString());

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
