// package backendcarregon;

// import org.opencv.core.*;
// import org.opencv.imgcodecs.*;
// import org.opencv.imgproc.*;
// import org.opencv.videoio.*;
// import net.sourceforge.tess4j.*;
// import java.io.File;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;
// import org.opencv.objdetect.CascadeClassifier;

// public class Serching {
//     static {
//         // ✅ Load OpenCV DLL (FIXED)
//         System.load("C:\\Users\\Tech Line\\openCV\\opencv\\build\\java\\x64\\opencv_java490.dll");
//     }

//     public static void main(String[] args) throws IOException {
//         // 1️⃣ Connect to IP Camera (Update URL!)
//         VideoCapture camera = new VideoCapture("");
//         if (!camera.isOpened()) {
//             System.out.println("❌ Camera Error: Check RTSP URL or connection.");
//             return;
//         }

//         Mat frame = new Mat();
//         List<String> plateNumbers = new ArrayList<>();

//         // 2️⃣ License Plate Detection
//         CascadeClassifier plateCascade = new CascadeClassifier("haarcascade_russian_plate_number.xml");

//         while (camera.read(frame)) {
//             Mat grayFrame = new Mat();
//             Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);

//             MatOfRect plates = new MatOfRect();
//             plateCascade.detectMultiScale(grayFrame, plates);

//             for (Rect plate : plates.toArray()) {
//                 Mat plateRegion = frame.submat(plate);
//                 Imgcodecs.imwrite("plate.jpg", plateRegion);

//                 // 3️⃣ OCR Extraction
//                 String plateText = extractText("plate.jpg");
//                 System.out.println("🚗 Plate: " + plateText);
//                 plateNumbers.add(plateText);
//             }

//             if (System.in.available() > 0) break; // Press 'Enter' to exit
//         }

//         camera.release();
//         System.out.println("📋 Results: " + plateNumbers);
//     }

//     // 4️⃣ Tesseract OCR Function
//     public static String extractText(String imagePath) {
//         Tesseract tesseract = new Tesseract();
//         tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR");
//         try {
//             return tesseract.doOCR(new File(imagePath)).replace("\n", "").trim();
//         } catch (Exception e) {
//             System.err.println("❌ OCR Error: " + e.getMessage());
//             return "UNREADABLE";
//         }
//     }
// }