package br.com.PetRepete.controller;

import br.com.PetRepete.cadastros.Usuarios;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("email");
        String senha = request.getParameter("senha");

        Usuarios usuario = null;
        try {
            usuario = Usuarios.buscarUsuarioPorEmailESenha(login, senha);
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (usuario != null) {
            request.getSession().setAttribute("usuario", usuario);
            response.sendRedirect("index.jsp");
        } else {
            request.setAttribute("erro", "Email ou senha inválidos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
