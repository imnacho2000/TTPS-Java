package misServlets;

import jakarta.servlet.ServletContext;
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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * Servlet implementation class ImprimeEntrada
 */
public class ImprimeEntrada extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImprimeEntrada() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String textoQR = generarTexto(request);

        ServletOutputStream outputStream = response.getOutputStream();
        
        BufferedImage combinedImage = new BufferedImage(800, 1000, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = combinedImage.createGraphics();

        BufferedImage logo = ImageIO.read(this.getServletContext().getResourceAsStream("/Imagenes/LogoQuilmes.jpeg"));
        graphics.drawImage(logo, 0, 0, 800, 200, null);

        BufferedImage qrImage = generarCodigoQR(textoQR, 800, 800);
        
        graphics.drawImage(qrImage, 0, 200, null);

        graphics.dispose();

        response.setContentType("image/png");
        ImageIO.write(combinedImage, "png", outputStream);
        outputStream.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Vengo del filtro y decremento la variable
		String cuponSeleccionado = request.getParameter("cupos");
		
		ServletContext sce = request.getServletContext();
        sce.setAttribute(cuponSeleccionado, Integer.parseInt(sce.getAttribute(cuponSeleccionado).toString()) - 1);
//        System.out.println(sce.getAttribute(cuponSeleccionado));
        // Ahora procedo a generar el codigo QR + imagen 
        doGet(request, response);              

	}
	
	private String generarTexto(HttpServletRequest request) {
        int resultado = ThreadLocalRandom.current().nextInt(1,3);
        
        String msj = "Entrada para " + request.getParameter("nombre") + request.getParameter("apellido") + request.getParameter("dni");
        if (resultado == 2) {
        	msj += "Te ganaste una remera. El dia del evento pasá por el Stand TTPS y retirala con el QR";
        }
        else {
        	msj += "No te ganaste una remera, pero esperamos disfrutes mucho el show.";
        }
        
        return msj;
	}
	
	private BufferedImage generarCodigoQR(String texto, int ancho, int alto) {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		try {
			// Configuración del QR
			Map<EncodeHintType, Object> hints = new HashMap<>();
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

			BitMatrix bitMatrix = qrCodeWriter.encode(texto, BarcodeFormat.QR_CODE, ancho, alto, hints);

			// Crear el código QR con colores personalizados
			MatrixToImageConfig config = new MatrixToImageConfig(Color.BLACK.getRGB(), Color.WHITE.getRGB());
			return MatrixToImageWriter.toBufferedImage(bitMatrix, config);
		} catch (WriterException e) {
			e.printStackTrace();
			return null;
		}
	}

}
