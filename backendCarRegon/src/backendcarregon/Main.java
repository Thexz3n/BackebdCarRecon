// package backendcarregon;

// import org.opencv.core.*;
// import org.opencv.imgcodecs.Imgcodecs;
// import org.opencv.imgproc.Imgproc;
// import net.sourceforge.tess4j.ITesseract;
// import net.sourceforge.tess4j.Tesseract;
// import net.sourceforge.tess4j.TesseractException;
// import javax.swing.*;
// import java.awt.image.BufferedImage;
// import java.awt.*;
// import java.util.ArrayList;
// import java.util.List;

// public class Main {
//     static {
//         // Load OpenCV native library
//         try {
//             System.load("C:/Users/Tech Line/openCV/opencv/build/java/x64/opencv_java490.dll");
//             System.out.println("OpenCV library loaded successfully.");
//         } catch (UnsatisfiedLinkError e) {
//             System.err.println("Error loading OpenCV library: " + e.getMessage());
//             System.exit(1);
//         }
//     }

//     public static void main(String[] args) {
//         // Initialize Tesseract OCR
//         ITesseract tesseract = new Tesseract();
//         tesseract.setDatapath("C:\\Users\\Tech Line\\Tess4J-3.4.8-src\\Tess4J\\tessdata"); // Change to your tessdata path
//         tesseract.setLanguage("ara"); // Arabic language

//         ArrayList<String> plateNumbers = new ArrayList<>();
//         String imagePath = "C:\\Users\\Tech Line\\OneDrive\\Documents\\NetBeansProjects\\backendCarRegon\\src\\backendcarregon\\IMG_20250408_103618.jpg"; // Change to your image path
//         Mat image = Imgcodecs.imread(imagePath);

//         if (image.empty()) {
//             System.err.println("Error: Image not loaded from path: " + imagePath);
//             System.exit(1);
//         }

//         // Preprocessing
//         Mat gray = new Mat();
//         Mat blurred = new Mat();
//         Mat edges = new Mat();
//         Imgproc.cvtColor(image, gray, Imgproc.COLOR_BGR2GRAY);
//         Imgproc.GaussianBlur(gray, blurred, new Size(5, 5), 0);
//         Imgproc.Canny(blurred, edges, 50, 150);

//         // Contour Detection
//         Mat hierarchy = new Mat();
//         List<MatOfPoint> contours = new ArrayList<>();
//         Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

//         if (contours.isEmpty()) {
//             System.out.println("No contours found!");
//             System.exit(0);
//         }

//         // Plate Detection via contour filtering by aspect ratio and area
//         for (MatOfPoint contour : contours) {
//             Rect rect = Imgproc.boundingRect(contour);
//             double aspectRatio = (double) rect.width / rect.height;

//             if (aspectRatio > 2.0 && aspectRatio < 5.0 && rect.area() > 1000) {
//                 Mat plateRegion = new Mat(image, rect);
//                 try {
//                     BufferedImage plateImage = matToBufferedImage(plateRegion);
//                     String text = tesseract.doOCR(plateImage);
//                     // Remove all characters other than Arabic, letters and digits
//                     text = text.replaceAll("[^\\p{InArabic}A-Za-z0-9]", "").trim();

//                     if (!text.isEmpty()) {
//                         if (!plateNumbers.contains(text)) {
//                             plateNumbers.add(text);
//                             System.out.println("Detected Plate: " + text);
//                         }
//                     }
//                 } catch (TesseractException e) {
//                     System.err.println("OCR Error: " + e.getMessage());
//                 }
//             }
//         }

//         // Output Results summary
//         if (plateNumbers.isEmpty()) {
//             System.out.println("No plates detected!");
//         } else {
//             System.out.println("\nDetected Plates:");
//             plateNumbers.forEach(System.out::println);
//         }

//         // Show original loaded image with detected plates info in title
//         showImage(image, "Original Image with Detected Plates");

//         // Optionally, display each detected plate clipped image in separate windows (commented out)
//         /*
//         int i = 1;
//         for (String plate : plateNumbers) {
//             Mat plateMat = getPlateMatFromContours(image, contours, plate, tesseract);
//             if (plateMat != null) {
//                 showImage(plateMat, "Plate " + i + ": " + plate);
//                 i++;
//             }
//         }
//         */
//     }

//     // Converts OpenCV Mat to BufferedImage for display or OCR processing
//     private static BufferedImage matToBufferedImage(Mat mat) {
//         int type = BufferedImage.TYPE_BYTE_GRAY;
//         if (mat.channels() == 3) {
//             type = BufferedImage.TYPE_3BYTE_BGR;
//         }
//         byte[] b = new byte[(int) (mat.total() * mat.channels())];
//         mat.get(0, 0, b);
//         BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
//         image.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), b);
//         return image;
//     }

//     // Shows image via JFrame window, with proper closing on exit
//     private static void showImage(Mat mat, String title) {
//         BufferedImage image = matToBufferedImage(mat);
//         ImageIcon icon = new ImageIcon(image);
//         JFrame frame = new JFrame(title);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         JLabel label = new JLabel();
//         label.setIcon(icon);
//         frame.getContentPane().add(label, BorderLayout.CENTER);
//         frame.pack();

//         // Screen center for the frame
//         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//         int x = (screenSize.width - frame.getWidth()) / 2;
//         int y = (screenSize.height - frame.getHeight()) / 2;
//         frame.setLocation(x, y);

//         frame.setVisible(true);
//     }
// }
