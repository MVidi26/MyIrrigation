package com.example.irrigatedland;

import Farming.Model.Farming;
import Strategy.Model.Strategy;

import java.io.*;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.transaction.UserTransaction;

@WebServlet(name = "helloServlet", value = "/teste")
public class HelloServlet extends HttpServlet {
    @PersistenceContext(name = "irrigatedLand")
    private EntityManager em;
    @Inject
    private UserTransaction ut;
    private String message;

    public void init() {
        message = "Produto Criado";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

//        String hql = "SELECT far FROM Farming far WHERE far.id = 1";
//
//        Farming far = (Farming) em.createQuery(hql).getSingleResult();
//
//        Strategy strat = new Strategy();
//            strat.setFarm(far);
//            strat.setStrategyName("Padrao");
//
//        try{
//            ut.begin();
//            em.persist(strat);
//            ut.commit();
//        } catch (Exception e) {
//            throw new ServletException(e);
//        }


        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}