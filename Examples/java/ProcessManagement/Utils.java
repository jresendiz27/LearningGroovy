import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.ReplicateScaleFilter;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author ai2-27
 * @description It's helps to interact between the user and grammar class it's
 * an static class in order to use it anytime.
 */
public class Utils extends java.awt.Component {

    private static int count = 0;
    private static String regex = "[a-zA-Z&&[^0123456789]]";
    private static String word = "";
    private static Pattern pattern = null;
    private static Matcher matcher = null;

    // private static ArrayList<Word> wordArray;
    public static void setMemoryFree(Object... obj) {
        for (int i = 0; i < obj.length; i++) {
            obj[i] = (Object) null;
        }
        System.gc();
    }

    public static BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight, boolean preserveAlpha) {
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }

    public static Image resizeImage(String nombre, float widthPercent, float heightPercent) {
        try {
            Image imagen;
            float wpercent, hpercent;
            wpercent = widthPercent;
            hpercent = heightPercent;
            imagen = new ImageIcon(nombre).getImage();
            BufferedImage source = ImageIO.read(new File(nombre));
            int width = source.getWidth();
            int height = source.getHeight();
            ImageFilter replicate;
            replicate = new ReplicateScaleFilter((int) ((width * wpercent)), (int) ((height * hpercent)));
            ImageProducer prod = new FilteredImageSource(source.getSource(), replicate);
            Image resizedImage = new Utils().createImage(prod);
            return resizedImage;
        } catch (IOException ex) {
            System.err.println(ex.getCause());
            return null;
        }
    }

    public static void copyfile(String srFile, String dtFile) {
        try {
            File f1 = new File(srFile);
            File f2 = new File(dtFile);
            InputStream in = new FileInputStream(f1);
            OutputStream out = new FileOutputStream(f2);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getTypeOfFile(String pathOfFile) {
        File file = new File(pathOfFile);
        InputStream is;
        String mimeType;
        try {
            is = new BufferedInputStream(new FileInputStream(file));
            mimeType = URLConnection.guessContentTypeFromStream(is);
            return mimeType;
        } catch (Exception ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public static boolean checkAlphabet(String str) { //Returns true if it matches, false if not
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(str);
        if (matcher.find()) {
            setMemoryFree((Object) null);
            return true;
        } else {
            setMemoryFree((Object) null);
            return false;
        }
    }

    public static boolean checkAlphabet(String str, String rgx) {
        String auxstr = str;
        String auxregex = rgx;
        pattern = Pattern.compile(auxregex);
        matcher = pattern.matcher(auxstr);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

    public static String wordInversion(String str) {
        word = "";
        for (int i = (str.length() - 1); i >= 0; i--) {
            word += str.charAt(i);
        }
        return word;
    }

    public static String stringPower(String str, int pow) {
        if (pow == 0) {
            return "";
        } else if (pow >= 1) {
            return str + stringPower(str, pow - 1);
        } else {
            return wordInversion(stringPower(str, Math.abs(pow)));
        }
    }

    public static boolean isNumber(String str) {
        try {
            count = Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    public static int getNumber(String str) {
        try {
            count = Integer.parseInt(str);
            return count;
        } catch (Exception e) {
            e.getMessage();
            return Integer.MIN_VALUE;
        }
    }

    public static void systemCommand(String cmd) {
        try {
            // Get runtime
            java.lang.Runtime rt = java.lang.Runtime.getRuntime();
            java.lang.Process p = null;
            // Start a new process
            p = rt.exec(cmd);
            // You can or maybe should wait for the process to complete
            p.waitFor();
            // Get process' output: its InputStream
            java.io.InputStream is = p.getInputStream();
            java.io.BufferedReader reader = new java.io.BufferedReader(new InputStreamReader(is));
            // And print each line
            String s = null;
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
            }
            is.close();
        } catch (Exception e) {
        }

    }

    public static int coincidences(String wordGiven, String regExp) {
        count = 0;
        pattern = Pattern.compile(regExp);
        matcher = pattern.matcher(wordGiven);
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    public static String getPrefixes(String wordGiven, int index) {
        word = "";
        if (index == 0) {
            return new Character((char) 238) + " - " + getPrefixes(wordGiven, index + 1);
        } else if (index > 0 && index <= wordGiven.length()) {
            for (int i = 0; i < index; i++) {
                word += wordGiven.charAt(i);
            }
            return word + " - " + getPrefixes(wordGiven, index + 1);
        } else {
            return "";
        }
    }

    public static String getSuffixes(String wordGiven, int index) {
        word = "";
        if (index >= 0 && index < wordGiven.length()) {
            //System.out.println(" |" +index+ " | \n");
            for (int i = index; i < wordGiven.length(); i++) {
                word += wordGiven.charAt(i);
            }
            return word + " - " + getSuffixes(wordGiven, index + 1);
        } else if (index == wordGiven.length()) {
            return "" + new Character((char) 228);
        } else {
            return "";
        }
    }

    private static boolean loadFile(String str) {
        //systemCommand("ls");
        File f = new File(str);
        if (f.canRead()) {
            //System.out.println("encontró");
            return true;
        } else {
            //System.out.println("no encontró");
            return false;
        }
    }

    public static String readString() {
        if (System.console() == null) {
            try {
                Scanner scanner = new Scanner(System.in);
                return scanner.nextLine();
            } catch (Exception e) {
                System.err.println(e.getCause());
                return null;
            }

        } else {
            return System.console().readLine();
        }
    }

    /**
     *
     * @return
     */
    public static int readInt() {
        try {
            return Integer.parseInt(readString());
        } catch (Exception e) {
            System.err.print(e.getCause());
            return Integer.MIN_VALUE;
        }
    }

    /**
     *
     * @return
     */
    public static Float readFloat() {
        try {
            return Float.parseFloat(readString());
        } catch (Exception e) {
            System.err.print(e.getCause());
            return Float.MIN_VALUE;
        }
    }

    /**
     *
     * @return
     */
    public static long readLong() {
        try {
            return Long.parseLong(readString());
        } catch (Exception e) {
            System.err.print(e.getCause());
            return Long.MIN_VALUE;
        }
    }

    /**
     *
     * @return
     */
    public static double readDouble() {
        try {
            return Double.parseDouble(readString());
        } catch (Exception e) {
            System.err.print(e.getCause());
            return Double.MIN_VALUE;
        }
    }

    /**
     *
     * @return
     */
    public static char readChar() {
        try {
            String aux = readString();
            if (aux.length() == 1) {
                return aux.charAt(0);
            } else {
                System.out.println("A character required, found String");
                return '\0';
            }
        } catch (Exception e) {
            System.err.print(e.getCause());
            return '\0';
        }
    }

    /**
     *
     * @return
     */
    public static byte readByte() {
        try {
            return Byte.parseByte(readString());
        } catch (Exception e) {
            System.err.print(e.getCause());
            return Byte.MIN_VALUE;
        }
    }

    /**
     *
     * @return
     */
    public static short readShort() {
        try {
            return Short.parseShort(readString());
        } catch (Exception e) {
            System.err.print(e.getCause());
            return Short.MIN_VALUE;
        }
    }

    /**
     *
     * @return
     */
    public static boolean readBoolean() {
        return checkAlphabet(readString(), "true");
    }

    public static Image loadImage(String src) {
        String pathTOFile = src;
        BufferedImage source;
        ImageIcon imageIcon = new javax.swing.ImageIcon(pathTOFile);
        try {
            source = ImageIO.read(new File(pathTOFile));
            ImageFilter replicate = new ReplicateScaleFilter(87, 98);
            ImageProducer prod = new FilteredImageSource(source.getSource(), replicate);
            Image resizedImage = new Utils().createImage(prod);
            return resizedImage;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static ImageProducer getImageProducerFromFile(String src) {
        String pathTOFile = src;
        BufferedImage source;
        ImageIcon imageIcon = new javax.swing.ImageIcon(pathTOFile);
        try {
            source = ImageIO.read(new File(pathTOFile));
            ImageFilter replicate = new ReplicateScaleFilter(87, 98);
            ImageProducer prod = new FilteredImageSource(source.getSource(), replicate);
            return prod;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
