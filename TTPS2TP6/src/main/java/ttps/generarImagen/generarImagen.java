package ttps.generarImagen;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

/**
 * Servlet implementation class generarImagen
 */
public class generarImagen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public generarImagen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        
        BufferedImage image = new BufferedImage(2044, 1173, BufferedImage.TYPE_INT_BGR);
        Graphics2D graphics = image.createGraphics();
        BufferedImage img = ImageIO.read(this.getServletContext().getResourceAsStream("/imagenes/cupon3.jpg"));
        graphics.drawImage(img, 0, 0, 2044, 1173, null);
        graphics.setFont(new Font("TimesRoman", Font.BOLD, 77));
        graphics.setColor(Color.WHITE); 
        graphics.drawString((String) request.getAttribute("texto"), 500, 876);
        int resultado = ThreadLocalRandom.current().nextInt(1000000,99999999);
        
        graphics.drawString("#" + String.valueOf(resultado), 1400, 1039);
        javax.imageio.ImageIO.write(image, "png", outputStream);
        outputStream.close();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
